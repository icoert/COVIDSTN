package com.example.covidstn;

import android.app.ActionBar;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.covidstn.ui.status.StatusFragment;
import com.example.covidstn.ui.vaccine.VaccineFragment;
import com.example.covidstn.ui.help.HelpFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigation=findViewById(R.id.bottom_nav_view);
        bottomNavigation.setOnNavigationItemSelectedListener(navListner);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new StatusFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_action_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // action with ID action_sync was selected
        if (item.getItemId() == R.id.action_sync) {
            doRefreshData();
        }

        return true;
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


    private void doRefreshData() {
        try {
            int SDK_INT = android.os.Build.VERSION.SDK_INT;
            if (SDK_INT > 8)
            {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                        .permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            refreshData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void refreshData() throws IOException {

        URL url = new URL("https://d35p9e4fm9h3wo.cloudfront.net/latestData.json");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();

        String result = content.toString();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference covidDataReference = database.getReference("covidData");

        covidDataReference.setValue(result);
    }
}