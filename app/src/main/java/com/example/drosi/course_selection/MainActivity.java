package com.example.drosi.course_selection;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create all the widgets
        final EditText userName = (EditText)findViewById(R.id.txtUsername);
        final EditText password = (EditText)findViewById(R.id.txtPassword);
        final Button btnLogin = (Button)findViewById(R.id.btnLogin);

        // Add listener to find button clicks
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // The user clicked the login button, check for valid input
                // Compare the text the user enters against the username
                String inputName = userName.getText().toString();
                String inputPassword = password.getText().toString();
                if (inputName.equals("") || inputPassword.equals("")) {
                    // One or both of the fields are blank... toast it!
                    Toast.makeText(MainActivity.this, R.string.strFailedLogin, Toast.LENGTH_LONG).show();
                } else {
                    // The user entered both a username and password, compare to login credentials
                    if (inputName.equalsIgnoreCase("dave") && inputPassword.equals("password")) {
                        startActivity(new Intent(MainActivity.this, landing.class));
                    } else {
                        // Invalid credentials, alert the user via toast
                        Toast.makeText(MainActivity.this, R.string.strInvalidCreds, Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
    }
}
