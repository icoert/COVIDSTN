package com.example.covidstn.ui.status;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidstn.CountyData;
import com.example.covidstn.R;

import java.util.List;

public class NationalAdapter extends RecyclerView.Adapter<NationalAdapter.ViewHolder> {

    private List<CountyData> counties;
    private LayoutInflater mInflater;

    // data is passed into the constructor
    NationalAdapter(Context context, List<CountyData> data) {
        this.mInflater = LayoutInflater.from(context);
        this.counties = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recycleview_nationalrow, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CountyData county = counties.get(position);
        holder.countyNameView.setText(county.countyName);
        holder.countyCasesView.setText(String.valueOf(county.cases));
        holder.countyIncidenceView.setText(String.valueOf(county.incidence));
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return counties.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView countyNameView;
        TextView countyCasesView;
        TextView countyIncidenceView;

        ViewHolder(View itemView) {
            super(itemView);
            countyNameView = itemView.findViewById(R.id.countyName);
            countyCasesView = itemView.findViewById(R.id.cases);
            countyIncidenceView = itemView.findViewById(R.id.incidence);
        }
    }
}
