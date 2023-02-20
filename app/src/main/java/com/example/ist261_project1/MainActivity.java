package com.example.ist261_project1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText enteruser;
    EditText enterpass;
    TextView usertitle;
    TextView passtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enteruser = findViewById(R.id.enter_user);
        usertitle = findViewById(R.id.user_title);

        try {
            enteruser.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onFocusChange(View view, boolean userFocus) {
                    if (userFocus) {
                        usertitle.setText("Username");
                        enteruser.setHint("");
                    } else {
                        usertitle.setText(" ");
                        enteruser.setHint("Username");
                    }
                }
            });
        }

        catch (Exception E)
        {
            usertitle.setText(E.toString());
        }

    }


}