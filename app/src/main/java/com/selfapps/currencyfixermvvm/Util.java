package com.selfapps.currencyfixermvvm;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Util {

    public static String getFormattedDate(){
        android.text.format.DateFormat df = new android.text.format.DateFormat();
        return (String) df.format("yyyy-MM-dd", new Date());
    }


    public static String fromList(List<String> map){
        return new Gson().toJson(map);
    }


    public static List<String> fromJson(String json){
        Gson gson = new Gson();
        List<String> map = new ArrayList<>();
        return  (List<String>) gson.fromJson(json, map.getClass());
    }

    public static String fromMap(Map<String,String> map){
        return new Gson().toJson(map);
    }

    public static Map<String, String> toMap(String json){
        Gson gson = new Gson();
        Map<String,Double> map = new HashMap<>();
        return  (Map<String,String>) gson.fromJson(json, map.getClass());
    }


    public static double convertValue(double amount, double fromExchangeRate, double toExchangeRate){
        return amount/fromExchangeRate*toExchangeRate;
    }
}
