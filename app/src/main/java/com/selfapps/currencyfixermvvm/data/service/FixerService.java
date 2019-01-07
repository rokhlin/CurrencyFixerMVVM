package com.selfapps.currencyfixermvvm.data.service;

import com.selfapps.currencyfixermvvm.data.entity.DefinitionsResponse;
import com.selfapps.currencyfixermvvm.data.entity.FullCurrency;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FixerService {


    @GET("latest")
    Call<FullCurrency> getLatest();

    @GET("symbols")
    Call<DefinitionsResponse> getDefinitions();

//    @GET("group/{id}/users")
//    Call<List<User>> groupList(@Path("id") int groupId);
}
