package com.example.covidstn;

import com.google.gson.annotations.SerializedName;

public class VaccineStats
{
    @SerializedName("total_administered")
    public int totalAdministrated;

    public int immunized;
}
