package com.example.homeseek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class donation extends AppCompatActivity {

    ImageButton rocketBtn,nagadBtn,bkashBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);


        rocketBtn = findViewById(R.id.mrocket);
        nagadBtn = findViewById(R.id.mnagad);
        bkashBtn = findViewById(R.id.mbkash);

        rocketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent (donation.this, rocket.class);
                startActivity (myintent);

            }
        });
        nagadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent (donation.this, nagad.class);
                startActivity (myintent);

            }
        });
        bkashBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myintent = new Intent (donation.this, bkash.class);
                startActivity (myintent);
            }
        });
    }
}