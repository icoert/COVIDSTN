package com.example.covidstn.dto;

import com.google.gson.annotations.SerializedName;

public class Moderna {
    @SerializedName("total_administered")
    public int totalAdministered;

    public int immunized;
}
