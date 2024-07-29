package com.example.homeseek;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class search extends Fragment {
//    AutoCompleteTextView area,gender,age;

    EditText searchinfo;
    Button searchBtn;
    RecyclerView recyclerView;
    my_adapter myAdapter;

        // Required empty public constructor
//    ArrayList<String> areaarr=new ArrayList<>();
//    ArrayList<String> agearr=new ArrayList<>();
//    ArrayList<String> genarr=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewr = inflater.inflate(R.layout.fragment_search, container, false);

        searchinfo = viewr.findViewById(R.id.searchFild);
        searchBtn = viewr.findViewById(R.id.searchBtn);

   searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String input = searchinfo.getText().toString().trim();
                Toast.makeText(getActivity(), "Searching...", Toast.LENGTH_SHORT).show();
                recyclerView=viewr.findViewById(R.id.recycle);
                recyclerView.setLayoutManager(new LinearLayoutManager (getActivity()));
                FirebaseRecyclerOptions<model> options =
                        new FirebaseRecyclerOptions.Builder<model>()
                                .setQuery(FirebaseDatabase.getInstance().getReference().child("USER").orderByChild("age").startAt(input).endAt(input+"\uf8ff"), model.class)
                                .build();
                myAdapter=new my_adapter(options);
                myAdapter.startListening();
                recyclerView.setAdapter(myAdapter);

                searchinfo.setText("");

            }
        });

//        area=view.findViewById(R.id.area);
//        gender=view.findViewById(R.id.gender);
//        age=view.findViewById(R.id.age);
//
//        //area
//        areaarr.add("Ambarkhana");
//        areaarr.add("Tilagorh");
//        areaarr.add("zindabazar");
//        areaarr.add("Noya sharak");
//        areaarr.add("kumarpara");
//
//        //gender
//        genarr.add("Female");
//        genarr.add("Male");
//
//        //age
//        agearr.add("0-3");
//        agearr.add("4-7");
//        agearr.add("8-13");
//
//        ArrayAdapter<String> arrayAdapter1=new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,areaarr);
//        ArrayAdapter<String> arrayAdapter2=new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,agearr);
//        ArrayAdapter<String> arrayAdapter3=new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,genarr);
//
//
//        area.setAdapter(arrayAdapter1);
//        area.setThreshold(1);
//        gender.setAdapter(arrayAdapter3);
//        gender.setThreshold(1);
//        age.setAdapter(arrayAdapter2);
//        age.setThreshold(1);

        return viewr;

    }
//    @Override
//    public void onStart() {
//        super.onStart();
//        myAdapter.startListening();
//    }
//    @Override
//    public void onStop() {
//        super.onStop();
//        myAdapter.stopListening();
//    }
}