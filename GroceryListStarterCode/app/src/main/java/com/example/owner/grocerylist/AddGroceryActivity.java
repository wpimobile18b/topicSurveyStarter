package com.example.owner.grocerylist;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class AddGroceryActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_NOTES = "notes";
    public static final String EXTRA_QUANTITY = "quantity";
    public static final String EXTRA_FILEPATH = "filepath";
    public static final String TAG = "ADD_GROCERY_ACTIVITY";

    public static final int PICK_IMAGE = 1;
    public static final int TAKE_IMAGE = 2;

    private EditText myEditNameView;
    private ImageView mGroceryImageView;
    private EditText myEditNotesView;
    //TODO: This needs to get changed to the view we are using for editing the number
    //  this might entail changing things in other parts of the codebase where we refernce it...
    //  Matt you will need to handle this
    private EditText myEditQuantityView;
    private Button takePicture;
    private Button upload;
    private Button saveButton;
    private String imagePath;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_grocery_activity);

        myEditNameView = findViewById(R.id.edit_grocery_name);
        myEditNotesView = findViewById(R.id.edit_grocery_notes);
        myEditQuantityView = findViewById(R.id.edit_grocery_quantity);
        mGroceryImageView = findViewById(R.id.grocery_image);

        takePicture = findViewById(R.id.capture_image);
        takePicture.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                    File img = File.createTempFile("grocery", ".jpg", dir);
                    Uri imgUri = FileProvider.getUriForFile(AddGroceryActivity.this, "com.example.owner.grocerylist.fileprovider", img);
                    imagePath = img.getAbsolutePath();

                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);

                    List<ResolveInfo> activities =
                            getPackageManager().queryIntentActivities(takePictureIntent, 0);

                    startActivityForResult(takePictureIntent, TAKE_IMAGE);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        upload = findViewById(R.id.upload_image);
        upload.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });

        saveButton = findViewById(R.id.button_save);
        saveButton.setEnabled(imagePath != null);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(myEditNameView.getText()) ||
                        TextUtils.isEmpty(myEditNotesView.getText()) ||
                        TextUtils.isEmpty(myEditQuantityView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String name = myEditNameView.getText().toString();
                    String notes = myEditNotesView.getText().toString();
                    String quantity = myEditQuantityView.getText().toString();

                    replyIntent.putExtra(EXTRA_NOTES, notes);
                    replyIntent.putExtra(EXTRA_NAME, name);
                    replyIntent.putExtra(EXTRA_QUANTITY, quantity);
                    replyIntent.putExtra(EXTRA_FILEPATH, imagePath);

                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TAKE_IMAGE && resultCode == RESULT_OK) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            mGroceryImageView.setImageBitmap(bitmap);
        }

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                mGroceryImageView.setImageBitmap(bitmap);

                if (isWriteStoragePermissionGranted()) {
                    // Assume block needs to be inside a Try/Catch block.
                    File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                    dir.mkdir();
                    String path = dir.toString();
                    long count = dir.listFiles().length + 1;
                    String fileName = "grocery" + count + ".jpg";

                    File file = new File(path, fileName);
                    imagePath = file.getAbsolutePath(); // the File to save, append increasing numeric counter to prevent files from getting overwritten.
                    OutputStream fOut = new FileOutputStream(file);

                    bitmap.compress(Bitmap.CompressFormat.JPEG, 85, fOut); // saving the Bitmap to a file compressed as a JPEG with 85% compression rate
                    fOut.flush(); // Not really required
                    fOut.close(); // do not forget to close the stream

                    MediaStore.Images.Media.insertImage(getContentResolver(), file.getAbsolutePath(), file.getName(), file.getName());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        saveButton.setEnabled(imagePath != null);
    }

    public boolean isWriteStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG, "Permission is granted");
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MainActivity.STORAGE_PERMISSION);
                Log.v(TAG, "Permission is revoked");
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG, "Permission is granted");
            return true;
        }
    }
}
