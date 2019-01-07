package com.selfapps.currencyfixermvvm.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class DefinitionsResponse  {
	@SerializedName("symbols")
	@Expose
	public Map<String,String> symbols;
    
    public DefinitionsResponse() {}
}