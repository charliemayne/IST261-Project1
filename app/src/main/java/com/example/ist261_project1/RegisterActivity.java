package com.example.ist261_project1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    EditText enterfirstname;
    EditText enterlastname;
    EditText enteremail;
    EditText enterusername;
    EditText enterpassword;

    TextView errorText;

    Button signInButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        enterfirstname = findViewById(R.id.create_firstname);
        enterlastname = findViewById(R.id.create_lastname);
        enteremail = findViewById(R.id.create_email);
        enterusername = findViewById(R.id.create_username);
        enterpassword = findViewById(R.id.create_password);

        errorText = findViewById(R.id.errorText);

        signInButton = findViewById(R.id.signInButton);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    String firstname = String.valueOf(enterfirstname.getText());
                    String lastname = String.valueOf(enterlastname.getText());
                    String email = String.valueOf(enteremail.getText());
                    String username = String.valueOf(enterusername.getText());
                    String password = String.valueOf(enterpassword.getText());

                    if (firstname.isEmpty() || lastname.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty())
                    {
                        throw new Exception();
                    }

                }

                catch (Exception MT)
                {
                    errorText.setText("Enter Valid Input");
                    errorText.setTextColor(Color.RED);
                }




            }
        });


    }
}