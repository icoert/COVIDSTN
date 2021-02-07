package com.example.covidstn.ui.status;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidstn.dto.CountyData;
import com.example.covidstn.dto.CovidData;
import com.example.covidstn.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Map;
import static java.util.Map.entry;

public class NationalFragment extends Fragment {

    private Map<String, String> counties = Map.ofEntries(
            entry("AB", "Alba"),
            entry("AR", "Arad"),
            entry("AG", "Arges"),
            entry("BC", "Bacau"),
            entry("BH", "Bihor"),
            entry("BN", "Bistrita Nasaud"),
            entry("BT", "Botosani"),
            entry("BV", "Brasov"),
            entry("BR", "Braila"),
            entry("BZ", "Buzau"),
            entry("CS", "Caras Severin"),
            entry("CL", "Calarasi"),
            entry("CJ", "Cluj"),
            entry("CT", "Constanta"),
            entry("CV", "Covasna"),
            entry("DB", "Dambovita"),
            entry("DJ", "Dolj"),
            entry("GL", "Galati"),
            entry("GR", "Giurgiu"),
            entry("GJ", "Gorj"),
            entry("HR", "Harghita"),
            entry("HD", "Hunedoara"),
            entry("IL", "Ialomita"),
            entry("IS", "Iasi"),
            entry("IF", "Ilfov"),
            entry("MM", "Maramures"),
            entry("MH", "Mehedinti"),
            entry("MS", "Mures"),
            entry("NT", "Neamt"),
            entry("OT", "Olt"),
            entry("PH", "Prahova"),
            entry("SM", "Satu Mare"),
            entry("SJ", "Salaj"),
            entry("SB", "Sibiu"),
            entry("SV", "Suceava"),
            entry("TR", "Teleorman"),
            entry("TM", "Timis"),
            entry("TL", "Tulcea"),
            entry("VS", "Vaslui"),
            entry("VL", "Valcea"),
            entry("VR", "Vrancea"),
            entry("B", "Bucuresti")
    );

    FragmentManager fragmentManager;
    View view;
    RecyclerView recyclerView;

    public NationalFragment(FragmentManager fragmentManager)
    {
        this.fragmentManager = fragmentManager;
    }

    protected void initializeFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_national, container,false);

        addDatabaseListener();

        return view;
    }

    private void addDatabaseListener() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference covidDataReference = database.getReference("covidData");

        ValueEventListener postListener = new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get CovidData object and use the values to update the UI
                String value = dataSnapshot.getValue(String.class);
                CovidData data = new Gson().fromJson(value, CovidData.class);

                populateForm(data.currentDayStats.countyInfectionsNumbers, data.currentDayStats.incidence);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("", "loadPost:onCancelled", databaseError.toException());
            }
        };

        covidDataReference.addValueEventListener(postListener);
    }

    private void populateForm(Map<String, Integer> cases, Map<String, Float> incidence) {
        // data to populate the RecyclerView with
        ArrayList<CountyData> countyList = new ArrayList<>();

        for (Map.Entry<String, Integer> county : cases.entrySet()) {
            if(counties.containsKey(county.getKey())) {
                CountyData countyData = new CountyData();
                countyData.countyName = counties.get(county.getKey());
                countyData.cases = county.getValue();
                if(incidence.containsKey(county.getKey())) {
                    countyData.incidence = incidence.get(county.getKey());
                }
                countyList.add(countyData);
            }
        }

        // Setup the recycler view
        recyclerView = view.findViewById(R.id.recyclerViewNational);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.view.getContext()));

        NationalAdapter adapter = new NationalAdapter(this.view.getContext(), countyList);
        recyclerView.setAdapter(adapter);
    }
}
