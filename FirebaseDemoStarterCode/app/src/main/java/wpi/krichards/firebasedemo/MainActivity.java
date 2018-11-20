package wpi.krichards.firebasedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    // TODO: Get instance of firebaseWrapper

    final String colorField = "favoriteColor";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: Initialize firebaseWrapper

        watchUserKey(colorField);
    }


    public void onClickSubmitButton(View v) {
        EditText e = (EditText)findViewById(R.id.inputColor);
        String color = e.getText().toString();

        if(!color.isEmpty()) {
            // TODO: Add code here

            e.setText("");
        }

    }

    public void onClickSignOutButton(View v) {
        // TODO: Add code here

        // After signing out, we want to go back to the login screen
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void watchUserKey(final String key) {
        // TODO: Add code here
    }

    private void setColorText(String text) {
        TextView t = (TextView)findViewById(R.id.color);
        t.setText(text);
    }
}