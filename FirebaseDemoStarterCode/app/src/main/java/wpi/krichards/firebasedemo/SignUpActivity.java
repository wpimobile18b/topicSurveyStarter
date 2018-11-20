package wpi.krichards.firebasedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class SignUpActivity extends AppCompatActivity {

    // TODO: Get instance of firebase auth
    // TODO: Get instance of firebaseWrapper

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // TODO: Initialize FirebaseAuth
        // TODO: Initialize firebaseWrapper

    }

    public void onClickSignUpButton(View v) {
        EditText email = (EditText)findViewById(R.id.inputEmailSU);
        EditText password = (EditText)findViewById(R.id.inputPasswordSU);
        EditText color = (EditText)findViewById(R.id.inputFavColor);

        String emailString = email.getText().toString();
        String passwordString = password.getText().toString();
        String colorString = color.getText().toString();

        // Make sure the input strings are not empty
        if (!emailString.isEmpty() && !passwordString.isEmpty() && !colorString.isEmpty()) {
            registerNewUser(emailString, passwordString, colorString);
        }
        else {
            TextView t = (TextView)findViewById(R.id.signUpError);
            t.setText("Must fill in all fields");
        }
    }

    private void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void registerNewUser(final String email, final String password, final String color) {
        // TODO: Add code here
    }
}