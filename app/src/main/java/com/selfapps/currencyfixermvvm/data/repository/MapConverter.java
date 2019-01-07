package com.selfapps.currencyfixermvvm.data.repository;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class MapConverter {

    @TypeConverter
    public String fromMap(Map<String,Double> map){
        return new Gson().toJson(map);
    }

    @TypeConverter
    public Map<String, Double> fromJson(String json){
        Gson gson = new Gson();
        Map<String,Double> map = new HashMap<>();
        return  (Map<String,Double>) gson.fromJson(json, map.getClass());
    }
}
