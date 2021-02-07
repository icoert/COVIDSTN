package com.example.covidstn;

import java.util.Map;

public class CurrentDayStats
{
    public String parsedOnString;

    public int numberInfected;

    public int numberCured;

    public int numberDeceased;

    public Map<String, VaccineStats> vaccines;

    public Map<String, Integer> countyInfectionsNumbers;
}
