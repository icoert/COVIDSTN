package com.example.covidstn.dto;

import java.util.Map;

public class CurrentDayStats
{
    public String parsedOnString;

    public int numberInfected;

    public int numberTotalDosesAdministered;

    public int numberCured;

    public int numberDeceased;

    public double percentageOfWomen;

    public double percentageOfMen;

    public VaccineStats vaccines;

    public Map<String, Integer> countyInfectionsNumbers;

    public Map<String, Float> incidence;
}
