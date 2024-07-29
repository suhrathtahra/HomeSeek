package com.example.homeseek;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PatternMatcher;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.homeseek.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class login extends AppCompatActivity {

    private boolean isBackPressed = false;

    EditText emailF,passF;
    TextView ForgetPassword;

    Button signupBtn,logBTN;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white,this.getTheme()));

        ForgetPassword=findViewById(R.id.reset);
        logBTN = findViewById(R.id.loginb);
        emailF = findViewById(R.id.useremail);
        passF = findViewById(R.id.password1);
        signupBtn = findViewById (R.id.signupb);
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog= new ProgressDialog(this);

        ForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* progressDialog.setTitle("Email Sending...");
                progressDialog.show();*/
                String mail = emailF.getText().toString().trim();
                if (mail.isEmpty())
                {
                    emailF.setError("Email Can't empty");
                    emailF.requestFocus();
                }
                else {
                    firebaseAuth.sendPasswordResetEmail(emailF.getText().toString().trim())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    Toast.makeText(login.this, "Send Email", Toast.LENGTH_SHORT).show();

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    //progressDialog.cancel();
                                    Toast.makeText(login.this, "Email Send Failed", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });



        logBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String email=emailF.getText().toString();
                String password=passF.getText().toString();


                String adminregex = "^(?=^[A-Za-z0-9._%+-]+@)(?=.*admin\\.com$).+";
                Pattern pattern = Pattern.compile (adminregex);
                Matcher matcher = pattern.matcher (email);

                String userregex = "^(?=^[A-Za-z0-9._%+-]+@)(?=.*gmail\\.com$).+";
                Pattern userpattern = Pattern.compile (userregex);
                Matcher usermatcher = userpattern.matcher (email);

                if(matcher.matches ()){
                    firebaseAuth.signInWithEmailAndPassword(email,password)
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    progressDialog.cancel();
                                    Intent myintent = new Intent (login.this, admin.class);
                                    startActivity (myintent);
                                    finish();
                                    Toast.makeText(login.this, "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressDialog.cancel();
                                    Toast.makeText(login.this, "LOGIN FAILED", Toast.LENGTH_SHORT).show();
                                }
                            });

                }

                else if(usermatcher.matches ()){
                    firebaseAuth.signInWithEmailAndPassword(email,password)
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    progressDialog.cancel();
                                    Intent myintent = new Intent (login.this, homepagebn.class);
                                    startActivity (myintent);
                                    finish();
                                    Toast.makeText(login.this, "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressDialog.cancel();
                                    Toast.makeText(login.this, "LOGIN FAILED", Toast.LENGTH_SHORT).show();
                                }
                            });

                }



//                forgetpassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent (login.this, signup.class);
//                startActivity (intent);
//                String email=emailF.getText().toString();
//            }
//        });
//        progressDialog.setTitle("Email sending");
//        progressDialog.show();




//        firebaseAuth.sendPasswordResetEmail(email)
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//                        progressDialog.cancel();
//
//                        Toast.makeText(login.this,"Email Sending",Toast.LENGTH_SHORT).show();
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                                public void onFailure(@NonNull Exception e) {
//                    progressDialog.cancel();
//                            Toast.makeText(login.this,"Email Filed",Toast.LENGTH_SHORT).show();
//                }
//            });

        }
    });
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
                    public void onClick(View v) {
            Intent intent = new Intent (login.this, signup.class);
            startActivity (intent);
                finish();
}
});




    }

    public void onBackPressed () {
        if(isBackPressed)
        {
            super.onBackPressed();
//            onStop();
//            onDestroy();
            finish();
            return;
        }
        Toast.makeText(this, "Press again to Exit ", Toast.LENGTH_SHORT).show();
        isBackPressed = true;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isBackPressed= false;
            }
        },2000);


    }
}