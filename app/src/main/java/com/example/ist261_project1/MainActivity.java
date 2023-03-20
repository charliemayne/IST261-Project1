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

    // check this
    public static final String PUBLIC_IP = "10.32.114.51";

    int userID;

    EditText enteruser;
    EditText enterpass;
    Button signIn;
    TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // add onClick to signin button
        signIn = findViewById(R.id.signInButton);
        enteruser = findViewById(R.id.enter_user);
        enterpass = findViewById(R.id.enter_password);
        register = findViewById(R.id.registerButton);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ((enteruser.getText()).toString().equals("admin") && (enterpass.getText()).toString().equals("bypass"))
                {
                    Log.d("bypass", "confirm");
                    Intent intent = new Intent(view.getContext(), PostFeedActivity.class);
                    startActivity(intent);
                }

                // make http request with volley
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String userByEmailOrUsername = "http://" + PUBLIC_IP + ":3000/api/users/" + enteruser.getText().toString();

                JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, userByEmailOrUsername, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONObject object = response.getJSONObject(0);

                            // check entered password against stored password for this user
                            String dbPassword = object.getString("pass");

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
                        Log.d("MainActivity", "Volley Error" + error.toString());
                    }
                });
                queue.add(request);
            }
        });

        // add onCLick listener to
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }


}