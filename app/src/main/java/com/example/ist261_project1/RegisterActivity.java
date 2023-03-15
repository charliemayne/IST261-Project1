package com.example.ist261_project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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

                    postDataUsingVolley(firstname, lastname, email, username, password);

                }

                catch (Exception MT)
                {
                    errorText.setText("Enter Valid Input");
                    errorText.setTextColor(Color.RED);
                }


            }


            private void postDataUsingVolley(String firstname, String lastname, String email, String username, String password) {

                // store new user data in hashmap
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("email", email);
                params.put("first_name", firstname);
                params.put("last_name", lastname);
                params.put("pass", password);

                // url to post new user
                String Url = "http://" + MainActivity.PUBLIC_IP + ":3000/api/users/";

                // make Volley post request
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);

                JsonObjectRequest request = new JsonObjectRequest(Url, new JSONObject(params), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // success message?
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

                queue.add(request);
            }


        });


    }
}