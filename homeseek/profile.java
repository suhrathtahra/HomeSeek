package com.example.homeseek;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class profile extends Fragment {
    MaterialTextView name,number,mail,add;
    ImageView img;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        name = view.findViewById(R.id.userp_name);
        number = view.findViewById(R.id.userp_phone);
        mail = view.findViewById(R.id.userp_email);
        add = view.findViewById(R.id.userp_address);
        img = view.findViewById(R.id.imageView3);



        FirebaseFirestore dbroot = FirebaseFirestore.getInstance();
        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        System.out.println("check_id: "+currentuser);
        DocumentReference documentReference = dbroot.collection("Users").document(currentuser);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    name.setText(documentSnapshot.getString("username"));
                    number.setText(documentSnapshot.getString("number"));
                    mail.setText(documentSnapshot.getString("email"));
                    add.setText(documentSnapshot.getString("address"));

                    Glide.with(profile.this).load(documentSnapshot.getString("pic")).into(img);


                }else Toast.makeText(getActivity(), "User name not found", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "Failed to fetch data", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }
}