package com.selfapps.currencyfixermvvm.data.entity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class FullCurrency  {
    
	@SerializedName("timestamp")
	@Expose
	public long timestamp;
    
	@SerializedName("date")
	@Expose
	public String date;
    
	@SerializedName("base")
	@Expose
	public String base;
    
	@SerializedName("rates")
	@Expose
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