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

                RegisterData newUser = new RegisterData();
                newUser.setFirstname(firstname);
                newUser.setLastname(lastname);
                newUser.setEmail(email);
                newUser.setUsername(username);
                newUser.setPassword(password);

                String Url = "http://10.0.2.2:3000/api/users/";

                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);

                StringRequest request = new StringRequest(Request.Method.POST, Url, new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(RegisterActivity.this, "Data added to API", Toast.LENGTH_SHORT).show();

                        try {

                            JSONObject respObj = new JSONObject(response);

                            String firstname = respObj.getString("firstname");
                            String lastname = respObj.getString("lastname");
                            String email = respObj.getString("email");
                            String username = respObj.getString("username");
                            String password = respObj.getString("password");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // method to handle errors.
                        Toast.makeText(RegisterActivity.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();

                        params.put("firstname", newUser.getFirstname());
                        params.put("lastname", newUser.getLastname());
                        params.put("email", newUser.getEmail());
                        params.put("user", newUser.getUsername());
                        params.put("pass", newUser.getPassword());


                        return params;

                    }
                };


                queue.add(request);
            }


        });


    }
}