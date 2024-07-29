package com.example.homeseek;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.InputStream;
import java.util.Random;

public class signup extends AppCompatActivity {
    EditText regUsername, adress, regEmail, regPassword, number;
    Button Ready, login;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    ProgressDialog progressDialog;
    ImageView imgupl;
    Uri imageUri;
    Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        adress = findViewById(R.id.full_name);
        regUsername = findViewById(R.id.user_name);
        regEmail = findViewById(R.id.user_email);
        regPassword = findViewById(R.id.password);
        Ready = findViewById(R.id.btnReady);
        login = findViewById(R.id.tologin);
        imgupl = findViewById(R.id.imgupload);
        number = findViewById(R.id.user_number);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        progressDialog = new ProgressDialog(signup.this);

        imgupl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Image File"), 1);

            }
        });

        Ready.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                String Address = adress.getText().toString();
                String username = regUsername.getText().toString();
                String email = regEmail.getText().toString().trim();
                String password = regPassword.getText().toString();
                String Number = number.getText().toString();


                if (TextUtils.isEmpty(Address)) {
                    adress.setError("Name cannot be empty");
                    adress.requestFocus();
                } else if (TextUtils.isEmpty(username)) {
                    regUsername.setError("username cannot be empty");
                    regUsername.requestFocus();
                } else if (TextUtils.isEmpty(email)) {
                    regEmail.setError("Email cannot be empty");
                    regEmail.requestFocus();
                } else if (TextUtils.isEmpty(password)) {
                    regPassword.setError("Password cannot be empty");
                    regPassword.requestFocus();

                } else if (TextUtils.isEmpty(Number)) {
                    number.setError("Number cannot be empty");
                    number.requestFocus();

                } else {

                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {




                                    FirebaseStorage storage = FirebaseStorage.getInstance();
                                    StorageReference uploder = storage.getReference("Image1"+new Random().nextInt(50));

                                    uploder.putFile(imageUri)
                                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                                @Override
                                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                                    uploder.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                        @Override
                                                        public void onSuccess(Uri uri) {
                                                            firebaseFirestore.collection("Users").document(FirebaseAuth.getInstance().getUid())
                                                                    .set(new userModel(Address, username, email, password, Number,uri.toString()));
                                                            progressDialog.cancel();
                                                            System.out.println("Reg_Email : " + email);
                                                            System.out.println("Reg_Pass : " + password);
                                                            Intent myintent = new Intent(signup.this, login.class);
                                                            startActivity(myintent);
                                                            finish();
                                                            Toast.makeText(signup.this, "Registration successfull", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });

                                                }
                                            });

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressDialog.cancel();
                                    Toast.makeText(signup.this, "Registration Failed", Toast.LENGTH_SHORT).show();

                                }
                            });


                }
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(signup.this, login.class);
                startActivity(myintent);
                finish();


            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 1 && resultCode == RESULT_OK) {

            imageUri = data.getData();

            try {
                InputStream inputStream = signup.this.getContentResolver().openInputStream(imageUri);
                bitmap = BitmapFactory.decodeStream(inputStream);
                imgupl.setImageBitmap(bitmap);

            } catch (Exception e) {

            }


        }
    }

}
