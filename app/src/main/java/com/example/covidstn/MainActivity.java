package com.example.covidstn;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.covidstn.ui.status.StatusFragment;
import com.example.covidstn.ui.vaccine.VaccineFragment;
import com.example.covidstn.ui.help.HelpFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigation=findViewById(R.id.bottom_nav_view);
        bottomNavigation.setOnNavigationItemSelectedListener(navListner);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new StatusFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListner =new BottomNavigationView.OnNavigationItemSelectedListener(){
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment=null;
            switch (menuItem.getItemId()){
                case R.id.navigation_update:
                    selectedFragment=new StatusFragment();
                    break;
                case R.id.navigation_news:
                    selectedFragment=new VaccineFragment();
                    break;
                case R.id.navigation_help:
                    selectedFragment=new HelpFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
            return true;
        }
    };
}