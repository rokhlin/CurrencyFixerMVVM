package com.selfapps.currencyfixermvvm.data;

import com.google.gson.Gson;
import com.selfapps.currencyfixermvvm.data.service.FixerService;
import com.selfapps.currencyfixermvvm.data.service.RequestInterceptor;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FixerRepository {
//http://data.fixer.io/api/latest?access_key=05802094470ddc2d9fc11699cbfae648
    private static final String BASE_URL = "http://data.fixer.io/api/";
    public static final String KEY_PREFIX = "access_key";
    public static final String ACCESS_KEY = "05802094470ddc2d9fc11699cbfae648";
    private static FixerService INSTANCE;

    public static FixerService getAPI(){
        if(INSTANCE == null)  new FixerRepository();
        return INSTANCE;
    }

    private FixerRepository(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient client = new OkHttpClient
                .Builder()
                .addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(new RequestInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();

        INSTANCE = retrofit.create(FixerService.class);
    }
}
