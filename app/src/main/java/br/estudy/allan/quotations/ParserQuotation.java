package br.estudy.allan.quotations;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allan on 13/09/2016.
 */
public class ParserQuotation {
    ;
    private QuotationsDBHelper databaseHelper;

    public ParserQuotation(QuotationsDBHelper databaseHelper){
        this.databaseHelper = databaseHelper;
    }

    public void parserJSON(String json)throws Exception{
        try{
            JSONArray jsonRaw = new JSONArray(json);
            Quotation quotation;

            for(int i=0; i<jsonRaw.length(); i++){
                JSONObject jsonQuotation = jsonRaw.getJSONObject(i);
                quotation = new Quotation();

                if(jsonQuotation.getString("id").equals("null"))
                    quotation.setId("Null");
                else
                    quotation.setId(jsonQuotation.getString("id"));

                if(jsonQuotation.getString("name").equals("null"))
                    quotation.setName("Null");
                else
                    quotation.setName(jsonQuotation.getString("name"));

                if(jsonQuotation.getString("symbol").equals("null"))
                    quotation.setSymbol("Null");
                else
                    quotation.setSymbol(jsonQuotation.getString("symbol"));

                if(jsonQuotation.getString("rank").equals("null"))
                    quotation.setRank(0);
                else
                    quotation.setRank(Integer.parseInt(jsonQuotation.getString("rank")));

                if(jsonQuotation.getString("price_usd").equals("null"))
                    quotation.setPriceUSD(0.00);
                else
                    quotation.setPriceUSD(Double.parseDouble(jsonQuotation.getString("price_usd")));

                if(jsonQuotation.getString("price_btc").equals("null"))
                    quotation.setPriceBTC(0.00);
                else
                    quotation.setPriceBTC(Double.parseDouble(jsonQuotation.getString("price_btc")));

                if(jsonQuotation.getString("24h_volume_usd").equals("null"))
                    quotation.setVolume24hUSD(0.00);
                else
                    quotation.setVolume24hUSD(Double.parseDouble(jsonQuotation.getString("24h_volume_usd")));

                if(jsonQuotation.getString("market_cap_usd").equals("null"))
                    quotation.setMarketCapUSD(0.00);
                else
                    quotation.setMarketCapUSD(Double.parseDouble(jsonQuotation.getString("market_cap_usd")));

                if(jsonQuotation.getString("available_supply").equals("null"))
                    quotation.setAvailableSupply(0.00);
                else
                    quotation.setAvailableSupply(Double.parseDouble(jsonQuotation.getString("available_supply")));

                if(jsonQuotation.getString("total_supply").equals("null"))
                    quotation.setTotalSupply(0.00);
                else
                    quotation.setTotalSupply(Double.parseDouble(jsonQuotation.getString("total_supply")));

                if(jsonQuotation.getString("percent_change_1h").equals("null"))
                    quotation.setPercent1h((float)0);
                else
                    quotation.setPercent1h(Float.parseFloat(jsonQuotation.getString("percent_change_1h")));

                if(jsonQuotation.getString("percent_change_24h").equals("null"))
                    quotation.setPercent24h((float)0);
                else
                    quotation.setPercent24h(Float.parseFloat(jsonQuotation.getString("percent_change_24h")));

                if(jsonQuotation.getString("percent_change_7d").equals("null"))
                    quotation.setPercent7d((float)0);
                else
                    quotation.setPercent7d(Float.parseFloat(jsonQuotation.getString("percent_change_7d")));

                if(jsonQuotation.getString("last_updated").equals("null"))
                    quotation.setLastUpdate((long)0);
                else
                    quotation.setLastUpdate(Long.parseLong(jsonQuotation.getString("last_updated")));

                //quotations.add(quotation);
                databaseHelper.insertQuotation(quotation);
            }
            //return quotations;
        }
        catch (JSONException e){
            throw new Exception("Some attribute could not be found");
        }
    }
}
