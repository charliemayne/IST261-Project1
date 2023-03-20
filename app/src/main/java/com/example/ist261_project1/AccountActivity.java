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

    BottomNavigationView bottomView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        getSupportActionBar().setTitle("Account");

        bottomView = findViewById(R.id.bottomNavigationView1);

        bottomView.setSelectedItemId(R.id.account);
        bottomView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.account:
                        Log.d("MenuBar", "Here");
                        break;

                    default:
                        Log.d("MenuBar", "Home");
                        onBackPressed();
                        break;
                }

                return false;
            }
        });


    }
}