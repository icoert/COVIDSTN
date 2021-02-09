package com.example.covidstn.dto;

import com.google.gson.annotations.SerializedName;

public class Pfizer {
    @SerializedName("total_administered")
    public int totalAdministered;

    public int immunized;
}
