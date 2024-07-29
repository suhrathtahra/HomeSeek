package com.example.homeseek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class admin extends AppCompatActivity {

    private boolean isBackPressed = false;
    EditText id,name,gender,purl,details,age,phone;
    Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        id=findViewById(R.id.idnumber);
        name=findViewById(R.id.name);
        gender=findViewById(R.id.gender);
        purl=findViewById(R.id.purlnumber);
        details=findViewById(R.id.details);
        age=findViewById(R.id.age);
        submit=findViewById(R.id.submitbtn);
        phone=findViewById(R.id.phone);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String IDNumber=id.getText().toString().trim();
                String NAME=name.getText().toString().trim();
                String GENDER=gender.getText().toString().trim();
                String PURL=purl.getText().toString().trim();
                String DETAILS=details.getText().toString().trim();
                String AGE=age.getText().toString().trim();
                String PHONE = phone.getText().toString().trim();
                datahold obj=new datahold(IDNumber,NAME,GENDER,PURL,DETAILS,AGE,PHONE);
                FirebaseDatabase FD=FirebaseDatabase.getInstance();
                DatabaseReference node=FD.getReference("USER");
                node.child(IDNumber).setValue(obj);

                id.setText("");
                name.setText("");
                gender.setText("");
                purl.setText("");
                details.setText("");
                age.setText("");
                phone.setText("");


            }
        });
    }

    public void onBackPressed () {
        if(isBackPressed)
        {
            super.onBackPressed();
            startActivity(new Intent(admin.this,login.class));
            return;
        }
        Toast.makeText(this, "Press again to Logout", Toast.LENGTH_SHORT).show();
        isBackPressed = true;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isBackPressed= false;
            }
        },2000);


    }
}