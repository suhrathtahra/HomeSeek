package com.example.homeseek;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class homepagebn extends AppCompatActivity {

    BottomNavigationView btNav;
    private boolean isBackPressed = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepagebn);

        btNav = findViewById(R.id.btmnav);

        btNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                if(id== R.id.nav_profile){

                    frag(new profile(),false);

                }
                else if(id == R.id.nav_Home){

                    frag(new search(),true);

                }
                else{

                    frag(new faq(),false);

                }
                return true;
            }
        });

        btNav.setSelectedItemId(R.id.nav_Home);




    }

    public  void frag (Fragment fragment, boolean flag){

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        if(flag){
            ft.add(R.id.frm,fragment);
        }
        else {
            ft.replace(R.id.frm,fragment);
        }

        ft.commit();
    }


    public void onBackPressed () {
        if(isBackPressed)
        {
            super.onBackPressed();
            startActivity(new Intent(homepagebn.this,login.class));
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