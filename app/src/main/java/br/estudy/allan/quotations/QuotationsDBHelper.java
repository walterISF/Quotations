package br.estudy.allan.quotations;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Allan on 23/09/2016.
 */
public class QuotationsDBHelper {
    private static SQLiteHelper dbConn = null;
    private SQLiteDatabase db = null;

    public QuotationsDBHelper(SQLiteHelper dbConn){
        this.dbConn = dbConn;
        db = this.dbConn.getWritableDatabase();
    }

    public void insertQuotation (Quotation quotation){
        ContentValues values = new ContentValues();

        values.put("id", quotation.getId());
        values.put("name", quotation.getName());
        values.put("symbol", quotation.getSymbol());
        values.put("rank", quotation.getRank());
        values.put("price_usd", quotation.getPriceUSD());
        values.put("price_btc", quotation.getPriceBTC());
        values.put("volume_24h", quotation.getVolume24hUSD());
        values.put("marketcap", quotation.getMarketCapUSD());
        values.put("avaiable_supply", quotation.getAvailableSupply());
        values.put("total_supply", quotation.getTotalSupply());
        values.put("percent_1h", quotation.getPercent1h());
        values.put("percent_24h", quotation.getPercent24h());
        values.put("percent_7d", quotation.getPercent7d());
        values.put("last_update", quotation.getLastUpdate());

        db.insert("quotations", null, values);
    }
    public Quotation search (Integer i){
        Quotation quotation = new Quotation();
        String[] columns = new String[]{"id","name","symbol","rank","price_usd","price_btc","volume_24h","marketcap","avaiable_supply","total_supply","percent_1h","percent_24h","percent_7d","last_update"};
        Cursor cursor = db.query("quotations",columns,"_id = ?", new String[]{i.toString()},null,null,null);

        if(cursor.getCount() > 0){
            cursor.moveToFirst();

            quotation.setId(cursor.getString(0));
            quotation.setName(cursor.getString(1));
            quotation.setSymbol(cursor.getString(2));
            quotation.setRank(cursor.getInt(3));
            quotation.setPriceUSD(cursor.getDouble(4));
            quotation.setPriceBTC(cursor.getDouble(5));
            quotation.setVolume24hUSD(cursor.getDouble(6));
            quotation.setMarketCapUSD(cursor.getDouble(7));
            quotation.setAvailableSupply(cursor.getDouble(8));
            quotation.setTotalSupply(cursor.getDouble(9));
            quotation.setPercent1h(cursor.getFloat(10));
            quotation.setPercent24h(cursor.getFloat(11));
            quotation.setPercent7d(cursor.getFloat(12));
            quotation.setLastUpdate(cursor.getLong(13));
        }
        else{
            return null;
        }
        return quotation;
    }
}
