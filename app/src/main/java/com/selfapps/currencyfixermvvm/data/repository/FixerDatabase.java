package com.selfapps.currencyfixermvvm.data.repository;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.selfapps.currencyfixermvvm.data.entity.FullCurrency;


@Database(entities = {FullCurrency.class}, version = 1, exportSchema = false)
public abstract class FixerDatabase extends RoomDatabase {
      public abstract CurrencyDao dao();

}
