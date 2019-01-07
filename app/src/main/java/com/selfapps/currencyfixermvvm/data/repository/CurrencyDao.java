package com.selfapps.currencyfixermvvm.data.repository;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.selfapps.currencyfixermvvm.data.entity.FullCurrency;

import java.util.List;

@Dao
public interface CurrencyDao {

    @Query("SELECT * FROM currencies")
    LiveData<List<FullCurrency>> getCurrencies();


    @Query(("SELECT * FROM currencies WHERE date = :findDate"))
    LiveData<FullCurrency> findCurrency(String findDate);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCurrency(FullCurrency currency);

}
