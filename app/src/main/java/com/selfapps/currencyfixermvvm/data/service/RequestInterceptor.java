package com.selfapps.currencyfixermvvm.data.service;

import android.support.annotation.NonNull;

import com.selfapps.currencyfixermvvm.data.FixerRepository;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RequestInterceptor implements Interceptor {
    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.url()
                .newBuilder()
                .addQueryParameter(FixerRepository.KEY_PREFIX, FixerRepository.ACCESS_KEY)
                .build();

        Request newRequest = request.newBuilder()
                .url(url)
                .build();
        return chain.proceed(newRequest);
    }
}
