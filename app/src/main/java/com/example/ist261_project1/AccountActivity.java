package com.example.ist261_project1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AccountActivity extends AppCompatActivity {

    BottomNavigationView bottomView;
    TextView accountUsername;
    TextView leafScoreText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        getSupportActionBar().setTitle("Account");

        bottomView = findViewById(R.id.bottomNavigationView1);
        accountUsername = findViewById(R.id.accountUsername);
        leafScoreText = findViewById(R.id.leafScoreText);

        //accountUsername.setText(MainActivity.USERNAME);

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

        leafScoreText.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(AccountActivity.this);
                builder1.setMessage("Leaf Scores show how many posts a user has created.");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });


                AlertDialog alert11 = builder1.create();
                alert11.show();

                return true;
            }
        });


    }
}