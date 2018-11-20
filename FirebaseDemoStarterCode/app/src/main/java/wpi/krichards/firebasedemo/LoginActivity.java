package wpi.krichards.firebasedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;




public class LoginActivity extends AppCompatActivity {

    // TODO: Get instance of firebase auth


    /* ****************************************************************************************** */
    // Activity Lifecycle Callbacks

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // TODO: Initialize FirebaseAuth
    }

    @Override
    public void onStart() {
        super.onStart();

        // TODO: Add code here
    }

    /* ****************************************************************************************** */
    // Button Functions

    public void onClickSignUpButton(View v) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void onClickLoginButton(View v) {
        EditText email = (EditText)findViewById(R.id.inputEmail);
        EditText password = (EditText)findViewById(R.id.inputPassword);

        // Make sure the input strings are not empty
        String emailString = email.getText().toString();
        String passwordString = password.getText().toString();

        if (!emailString.isEmpty() && !passwordString.isEmpty()) {
            logInUser(emailString, passwordString);
        }
        else {
            TextView t = (TextView)findViewById(R.id.loginError);
            t.setText("Must fill in email and password fields");
        }
    }

    /* ****************************************************************************************** */
    // Firebase Functions

    public void logInUser(String email, String password) {
        // TODO: Add code here
    }

    /* ****************************************************************************************** */
    // Navigation Functions

    private void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
