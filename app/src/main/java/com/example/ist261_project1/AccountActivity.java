package com.example.ist261_project1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AccountActivity extends AppCompatActivity {

    boolean activityActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        activityActive = true;

        BottomNavigationView bottomView = findViewById(R.id.bottomNavigationView1);

        bottomView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.home:
                        activityActive = false;
                        Intent intent = new Intent(bottomView.getContext(), PostFeedActivity.class);
                        startActivity(intent);
                        bottomView.setSelectedItemId(R.id.home);

                    case R.id.account:
                        if (activityActive)
                        {
                            Log.d("MenuBar", "already here");
                        }
                }


                return false;
            }
        });
    }
}