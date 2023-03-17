package com.example.ist261_project1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PostFeedActivity extends AppCompatActivity {

    static int index = 0;
    static int postAmount = 0;

    PostData d1 = new PostData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_feed);

        TableLayout tl = findViewById(R.id.main_table);

        getSupportActionBar().setTitle("Home");

        Runnable task = new Runnable() {
            @SuppressLint("SetTextI18n")
            public void run() {
                addRows(tl);
            }
        };

        createView();

        Handler handler = new Handler();
        handler.postDelayed(task, 500);

        ////////////////////////////////////////////////////////////////////////////
        SwipeRefreshLayout swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                index = 0;
                postAmount = 0;

                PostData d2 = new PostData();
                Arrays.fill(d2.postContents, "");
                d2.i = 0;

                d1 = new PostData();

                Runnable task = new Runnable() {
                    @SuppressLint("SetTextI18n")
                    public void run() {
                        addRows(tl);
                    }
                };

                try {
                    Handler handler = new Handler();
                    handler.postDelayed(task, 500);

                    tl.removeAllViewsInLayout();

                    createView();

                }

                catch (Exception Z)
                {
                    Log.d("Refresh", "error");
                }

                finally
                {
                    swipeContainer.setRefreshing(false);
                }
            }
        });

    }

    @SuppressLint({"ResourceType", "SetTextI18n"})
    public void createView()
    {
        TableLayout tl = (TableLayout) findViewById(R.id.main_table);

        TableRow tr_head = new TableRow(this);
        tr_head.setId(500);
        tr_head.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        tl.addView(tr_head, new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));

        getAssetInfo();

        addRows(tl);

        FloatingActionButton createButton = findViewById(R.id.createPostButton);
        createButton.setOnClickListener(r -> createPost());

    }

    public void getAssetInfo() {

        RequestQueue queue = Volley.newRequestQueue(PostFeedActivity.this);
        String Url = "http://" + MainActivity.PUBLIC_IP + ":3000/api/posts";

        JsonArrayRequest requestCan = new JsonArrayRequest(Request.Method.GET, Url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response)
            {
                postAmount = response.length();

                for (index = 0; index < response.length(); index++)
                {
                    try {
                        JSONObject currentPost = response.getJSONObject(index);

                        String jsonPostMessage = currentPost.getString("content");

                        d1.addToPostsContents(jsonPostMessage);

                        Log.d("Post", jsonPostMessage);

                    }

                    catch (Exception e)
                    {
                        Log.d("PostFeed", "Object Array Error");
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("PostFeed", "Volley Error" + error.toString());
            }
        });
        queue.add(requestCan);
    }

    public void addRows(TableLayout tl) {
        String[] finalPostContent = d1.getPostContent();

        for (int k = 0; k < postAmount; k++) {

            //Colors -----------------------
            int textColor = Color.GRAY;
            Typeface textStyle = Typeface.DEFAULT;

            Log.d("Addrows", finalPostContent[k]);

            TableRow tr = new TableRow(this);
            tr.setId(1000 + k);
            tr.setPadding(50,0,0,0);
            tr.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            TextView label_middle = new TextView(this);
            label_middle.setId(9000 + k);
            label_middle.setText(finalPostContent[k]);
            label_middle.setTextColor(textColor);
            label_middle.setTypeface(textStyle);
            label_middle.setPaintFlags(label_middle.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            label_middle.setPadding(0, 50, 0, 50);
            label_middle.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            label_middle.setTextSize(18);
            tr.addView(label_middle);

            tl.addView(tr, 0, new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));

        }

    }


    public void createPost()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("New Post");

        EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.getBackground().setColorFilter(Color.parseColor("#167700"),
                PorterDuff.Mode.SRC_ATOP);
        builder.setView(input);

        String postText = String.valueOf(input.getText());

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener()
                {@Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        sendPost(postText);
                    }
                });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        final AlertDialog dialog = builder.create();
        dialog.show();

    }

    public void sendPost(String postText)
    {
        // store new user data in hashmap
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("content", postText);

        // url to post new user
        String Url = "http://" + MainActivity.PUBLIC_IP + ":3000/api/users/";

        // make Volley post request
        RequestQueue queue = Volley.newRequestQueue(PostFeedActivity.this);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Url, new JSONObject(params), new Response.Listener<JSONObject>() {
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

}