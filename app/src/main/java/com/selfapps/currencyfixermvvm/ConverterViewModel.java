package com.selfapps.currencyfixermvvm;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.selfapps.currencyfixermvvm.data.FixerRepository;
import com.selfapps.currencyfixermvvm.data.entity.FullCurrency;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConverterViewModel extends ViewModel {

    private MutableLiveData<FullCurrency> fullCurrencyLiveData = new MutableLiveData<>();


    public LiveData<FullCurrency> getLatest(){
         FixerRepository.getAPI().getLatest().enqueue(new Callback<FullCurrency>() {
             @Override
             public void onResponse(Call<FullCurrency> call, Response<FullCurrency> response) {
                 if(response != null){
                     Log.d("TAG", "GET_LATEST response: "+response.raw());
                     Log.d("TAG", "GET_LATEST body: "+response.body());
                     fullCurrencyLiveData.postValue(response.body());
                 }
             }

             @Override
             public void onFailure(Call<FullCurrency> call, Throwable t) {
                 Log.d("TAG", "GET_LATEST error "+t.getMessage());
             }
         });
         return fullCurrencyLiveData;
    }

    public LiveData<FullCurrency> getDefinitions(){
        FixerRepository.getAPI().getLatest().enqueue(new Callback<FullCurrency>() {
            @Override
            public void onResponse(Call<FullCurrency> call, Response<FullCurrency> response) {
                if(response != null){
                    Log.d("TAG", "GET_LATEST response: "+response.raw());
                    Log.d("TAG", "GET_LATEST body: "+response.body());
                    fullCurrencyLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<FullCurrency> call, Throwable t) {
                Log.d("TAG", "GET_LATEST error "+t.getMessage());
            }
        });
        return fullCurrencyLiveData;
    }

}
