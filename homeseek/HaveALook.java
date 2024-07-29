package com.example.homeseek;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HaveALook extends AppCompatActivity {

    Button look;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.have_a_look);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white,this.getTheme()));

        look = findViewById(R.id.havealook);

        look.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent loginIntent = new Intent(HaveALook .this, login.class );
                startActivity(loginIntent);
                finish();
            }
        });


    }
}