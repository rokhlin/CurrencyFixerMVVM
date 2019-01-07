package com.selfapps.currencyfixermvvm;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.preference.PowerPreference;
import com.selfapps.currencyfixermvvm.data.FixerRepository;
import com.selfapps.currencyfixermvvm.data.entity.DefinitionsResponse;
import com.selfapps.currencyfixermvvm.data.entity.FullCurrency;
import com.selfapps.currencyfixermvvm.data.repository.CurrencyDao;
import com.selfapps.currencyfixermvvm.data.repository.Database;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConverterViewModel extends AndroidViewModel {

    private static final String CURRENCY_LIST = "currency_list";
    private MutableLiveData<FullCurrency> fullCurrencyLiveData = new MutableLiveData<>();
    private MutableLiveData<Map<String,String>> definitions = new MutableLiveData<>();
    private CurrencyDao db ;

    public ConverterViewModel(@NonNull Application application) {
        super(application);
        db = Database.getDao(application.getApplicationContext());
    }





    public LiveData<FullCurrency> getLatest(){
        FullCurrency latestCurrency = db.findCurrency(Util.getFormattedDate()).getValue();
        if(latestCurrency != null)
            fullCurrencyLiveData.postValue(latestCurrency);
        else
            FixerRepository.getAPI().getLatest().enqueue(new Callback<FullCurrency>() {
             @Override
             public void onResponse(Call<FullCurrency> call, Response<FullCurrency> response) {
                 if(response != null){
                     final FullCurrency latestCurr = response.body();
                     Log.d("TAG", "GET_LATEST body: "+response.body());
                     fullCurrencyLiveData.postValue(response.body());

                     new Thread(new Runnable() {
                         @Override
                         public void run() {
                             db.insertCurrency(latestCurr);
                         }
                     }).start();

                 }
             }

             @Override
             public void onFailure(Call<FullCurrency> call, Throwable t) {
                 Log.d("TAG", "GET_LATEST error "+t.getMessage());
             }
         });
         return fullCurrencyLiveData;
    }

    public LiveData<Map<String,String>> getDefinitions(){
        String currList = PowerPreference.defult().getString(CURRENCY_LIST, null);
        if(currList != null)
            definitions.postValue(Util.toMap(currList));
        else
            FixerRepository.getAPI().getDefinitions().enqueue(new Callback<DefinitionsResponse>() {
                @Override
                public void onResponse(Call<DefinitionsResponse> call, Response<DefinitionsResponse> response) {
                    if(response.body() != null)
                        definitions.postValue(response.body().symbols);
                        PowerPreference.defult().put(CURRENCY_LIST,Util.fromMap(response.body().symbols));
                    Log.d("TAG", "GET_DEFINITIONS body: "+response.body());
                }

                @Override
                public void onFailure(Call<DefinitionsResponse> call, Throwable t) {
                    Log.d("TAG", "GET_DEFINITIONS error "+t.getMessage());
                }
            });

            return definitions;
    }

}
