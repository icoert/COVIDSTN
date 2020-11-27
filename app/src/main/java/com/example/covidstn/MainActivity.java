package com.example.covidstn;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.covidstn.ui.check_in.CheckInFragment;
import com.example.covidstn.ui.stats.StatsFragment;
import com.example.covidstn.ui.stiri.StiriFragment;
import com.example.covidstn.ui.urmarire.UrmarireFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigation=findViewById(R.id.bottom_nav_view);
        bottomNavigation.setOnNavigationItemSelectedListener(navListner);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new StatsFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListner =new BottomNavigationView.OnNavigationItemSelectedListener(){
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment=null;
            switch (menuItem.getItemId()){
                case R.id.navigation_stats:
                    selectedFragment=new StatsFragment();
                    break;
                case R.id.navigation_stiri:
                    selectedFragment=new StiriFragment();
                    break;
                case R.id.navigation_check_in:
                    selectedFragment=new CheckInFragment();
                    break;
                case R.id.navigation_umarire:
                    selectedFragment=new UrmarireFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
            return true;
        }
    };
}