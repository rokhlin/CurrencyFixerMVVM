package com.selfapps.currencyfixermvvm.data.entity;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.selfapps.currencyfixermvvm.data.repository.MapConverter;

import java.util.Map;

@Entity(tableName = "currencies")
public class FullCurrency  {
    
	@SerializedName("timestamp")
	@Expose
	public long timestamp;
    
	@SerializedName("date")
	@Expose
	@NonNull
	@PrimaryKey
	public String date;
    
	@SerializedName("base")
	@Expose
	public String base;
    
	@SerializedName("rates")
	@Expose
	@TypeConverters({MapConverter.class})
	public Map<String,Double> rates;
    
    public FullCurrency() {}

	@Override
	public String toString() {
		return "FullCurrency{" +
				"timestamp=" + timestamp +
				", date='" + date + '\'' +
				", base='" + base + '\'' +
				", rates=" + rates +
				'}';
	}
}