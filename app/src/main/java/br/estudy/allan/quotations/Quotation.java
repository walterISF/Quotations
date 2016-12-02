package br.estudy.allan.quotations;

import android.util.Log;

import java.io.Serializable;

/**
 * Created by Allan on 12/09/2016.
 */
public class Quotation implements Serializable {

    private String id;
    private String name;
    private String symbol;
    private Integer rank;
    private Double priceUSD;
    private Double priceBTC;
    private Double volume24hUSD;
    private Double marketCapUSD;
    private Double availableSupply;
    private Double totalSupply;
    private Float percent1h;
    private Float percent24h;
    private Float percent7d;
    private Long lastUpdate;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setSymbol(String symbol){
        this.symbol = symbol;
    }
    public String getSymbol(){
        return this.symbol;
    }
    public void setRank(Integer rank){
        this.rank = rank;
    }
    public Integer getRank(){
        return this.rank;
    }
    public void setPriceUSD(Double priceUSD){
        this.priceUSD = priceUSD;
    }
    public Double getPriceUSD(){
        return this.priceUSD;
    }
    public void setPriceBTC(Double priceBTC){
        this.priceBTC = priceBTC;
    }
    public Double getPriceBTC(){
        return this.priceBTC;
    }
    public void setVolume24hUSD(Double volume24hUSD){
        this.volume24hUSD = volume24hUSD;
    }
    public Double getVolume24hUSD(){
        return this.volume24hUSD;
    }
    public void setMarketCapUSD(Double marketCapUSD){
        this.marketCapUSD = marketCapUSD;
    }
    public Double getMarketCapUSD(){
        return this.marketCapUSD;
    }
    public void setAvailableSupply(Double availableSupply){
        this.availableSupply = availableSupply;
    }
    public Double getAvailableSupply(){
        return this.availableSupply;
    }
    public void setTotalSupply(Double totalSupply){
        this.totalSupply = totalSupply;
    }
    public Double getTotalSupply(){
        return this.totalSupply;
    }
    public void setPercent1h(Float percent1h){
        this.percent1h = percent1h;
    }
    public Float getPercent1h(){
        return this.percent1h;
    }
    public void setPercent24h(Float percent24h){
        this.percent24h = percent24h;
    }
    public Float getPercent24h(){
        return this.percent24h;
    }
    public void setPercent7d(Float percent7d){
        this.percent7d = percent7d;
    }
    public Float getPercent7d(){
        return this.percent7d;
    }
    public void setLastUpdate(Long lastUpdate){
        this.lastUpdate = lastUpdate;
    }
    public Long getLastUpdate(){
        return this.lastUpdate;
    }


    public void clone(Quotation copy){
        if(copy == null){
            Log.i("Info","Impossible to clone a null object (null object sent as reference)");
        }
        else {

            this.id = copy.id;
            this.name = copy.name;
            this.symbol = copy.symbol;
            this.rank = copy.rank;
            this.priceUSD = copy.priceUSD;
            this.priceBTC = copy.priceBTC;
            this.volume24hUSD = copy.volume24hUSD;
            this.marketCapUSD = copy.marketCapUSD;
            this.availableSupply = copy.availableSupply;
            this.totalSupply = copy.totalSupply;
            this.percent1h = copy.percent1h;
            this.percent24h = copy.percent24h;
            this.percent7d = copy.percent7d;
            this.lastUpdate = copy.lastUpdate;
        }
    }
}
