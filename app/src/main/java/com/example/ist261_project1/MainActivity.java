package com.example.ist261_project1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

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
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // make http request with volley
                RequestQueue queue = new RequestQueue(MainActivity.this);
                String userByEmailUrl = "http://10.0.2.2:3000/api/users/email/";

                JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, userByEmailUrl, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONObject object = response.getJSONObject(0);

                            // check entered password against stored password for this user
                            String dbPassword = object.getString("pass");
                            if (dbPassword.equals(enteruser.getText().toString())) {
                                // go to home screen
                                Intent intent = new Intent(this, PostFeedActivity.class);
                                startActivity(intent);
                            } else {
                                // error message
                            }

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                queue.add(request);
            }
        });


    }


}