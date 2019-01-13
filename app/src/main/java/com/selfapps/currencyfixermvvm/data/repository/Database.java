package com.selfapps.currencyfixermvvm.data.repository;

import android.arch.persistence.room.Room;
import android.content.Context;

public class Database {
    private static FixerDatabase db;


    public static CurrencyDao getDao(Context context){
        if(db==null) new Database(context);
        return db.dao();
    }


    private Database(Context context) {
        db = Room.databaseBuilder(context.getApplicationContext(),
                FixerDatabase.class, "database-currency").allowMainThreadQueries().build();
    }

}
