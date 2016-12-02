package br.estudy.allan.quotations;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Allan on 23/09/2016.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "CurrencyQuotations";
    private static final int DB_VERSION = 1;

    public SQLiteHelper(Context context){

        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users(_id integer primary key autoincrement, name text not null, email text not null, password text not null);");
        db.execSQL("create table quotations(_id integer primary key autoincrement, id text not null, name text not null, symbol text not null, rank integer not null, price_usd double not null, price_btc double not null, volume_24h double not null, marketcap double not null, avaiable_supply double not null, total_supply not null, percent_1h float not null, percent_24h float not null, percent_7d float not null, last_update long not null);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table users;");
        db.execSQL("drop table quotations");
        onCreate(db);
    }

    public void dropQuotations(){
        if(super.getWritableDatabase()!=null) {
            super.getWritableDatabase().execSQL("drop table quotations");
            super.getWritableDatabase().execSQL("create table quotations(_id integer primary key autoincrement, id text not null, name text not null, symbol text not null, rank integer not null, price_usd double not null, price_btc double not null, volume_24h double not null, marketcap double not null, avaiable_supply double not null, total_supply not null, percent_1h float not null, percent_24h float not null, percent_7d float not null, last_update long not null);");
        }
        else{
            Log.i("Error","SQLiteHelper - dropQuotations -> super.getWritableDatabase() returned null");
        }
    }
}
