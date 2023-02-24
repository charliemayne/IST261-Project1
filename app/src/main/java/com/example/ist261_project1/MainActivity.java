package com.example.ist261_project1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText enteruser;
    EditText enterpass;
    Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // add onClick to signin button
        signIn = findViewById(R.id.signInButton);
        enteruser = findViewById(R.id.enter_user);
        enterpass = findViewById(R.id.enter_password);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // make http request with volley
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String userByEmailUrl = "http://10.0.2.2:3000/api/users/username/" + enteruser.getText().toString();

                JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, userByEmailUrl, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONObject object = response.getJSONObject(0);

                            // check entered password against stored password for this user
                            String dbPassword = object.getString("pass");
                            Toast toast = Toast.makeText(getApplicationContext(), "working with json response", Toast.LENGTH_SHORT);

                            if (dbPassword.equals(enterpass.getText().toString())) {
                                // go to home screen
                                Intent intent = new Intent(view.getContext(), PostFeedActivity.class);
                                startActivity(intent);
                            } else {
                                // error message
                                Log.d("MainActivity", "Invalid Credentials or failed startActivity");
                            }

                        } catch (JSONException e) {
                            Log.d("MainActivity", "JSONException");
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("MainActivity", "Volley Error");
                    }
                });

                queue.add(request);
            }
        });


    }


}