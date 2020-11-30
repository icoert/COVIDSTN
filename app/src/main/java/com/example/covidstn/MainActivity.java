package com.example.covidstn;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.covidstn.ui.check_in.CheckInFragment;
import com.example.covidstn.ui.update.UpdateFragment;
import com.example.covidstn.ui.news.NewsFragment;
import com.example.covidstn.ui.tracking.TrackingFragment;
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

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new UpdateFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListner =new BottomNavigationView.OnNavigationItemSelectedListener(){
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment=null;
            switch (menuItem.getItemId()){
                case R.id.navigation_update:
                    selectedFragment=new UpdateFragment();
                    break;
                case R.id.navigation_news:
                    selectedFragment=new NewsFragment();
                    break;
                case R.id.navigation_check_in:
                    selectedFragment=new CheckInFragment();
                    break;
                case R.id.navigation_tracking:
                    selectedFragment=new TrackingFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
            return true;
        }
    };
}