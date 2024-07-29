package com.example.homeseek;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class my_adapter extends FirebaseRecyclerAdapter<model,my_adapter.myviewholder> {
    public my_adapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull model model) {
      holder.name.setText(model.getName());
        holder.age.setText(model.getAge());
        holder.gender.setText(model.getGender());
        holder.details.setText(model.getDetails());
        holder.phn.setText(model.getPhone());
        


        Glide.with(holder.purl.getContext()).load(model.getPurl()).into(holder.purl);

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singledata,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder{

        CircleImageView purl;
        TextView name,age,gender,details,phn;
        Button phone;


        

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            purl=itemView.findViewById(R.id.imageurl);
            name=itemView.findViewById(R.id.fname);
            age=itemView.findViewById(R.id.fage);
            gender=itemView.findViewById(R.id.fgender);
            details=itemView.findViewById(R.id.fdetails);
            phone=itemView.findViewById(R.id.callBtn);
            phn = itemView.findViewById(R.id.fnumber);


            phone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String num = phn.getText().toString();
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:"+num));
                    view.getContext().startActivity(intent);
                }
            });


        }
    }


}
