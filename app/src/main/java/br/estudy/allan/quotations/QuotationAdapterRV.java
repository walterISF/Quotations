package br.estudy.allan.quotations;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allan on 21/09/2016.
 */
//classe usada para adaptar um ReciclerView
public class QuotationAdapterRV extends RecyclerView.Adapter<QuotationAdapterRV.ViewHolder> {

    private final List<Quotation> quotationList;
    private final Context context;
    //instancia usada para implementar o metodo listener de click nos items da lista
    private RecyclerViewOnClickItemListener itemClickListener;

    public QuotationAdapterRV(Context context, List<Quotation> quotationList){
        this.context = context;
        this.quotationList = quotationList;
        Log.i("Info",quotationList.toString());
    }

    @Override
    public QuotationAdapterRV.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.quotation_row_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(QuotationAdapterRV.ViewHolder holder, int position) {
        Quotation quotation = quotationList.get(position);

        if(quotation!=null){

            if(holder.tvName!=null){holder.tvName.setText(quotation.getName());}
            if(holder.tvSymbol!=null){holder.tvSymbol.setText(quotation.getSymbol());}
            if(holder.tvRank!=null){holder.tvRank.setText("Rank: "+quotation.getRank().toString());}
            if(holder.tvPriceUSD!=null){holder.tvPriceUSD.setText("$ "+quotation.getPriceUSD().toString());}
            if(holder.tvPriceBTC!=null){holder.tvPriceBTC.setText(quotation.getPriceBTC().toString().toString());}
            if(holder.tvAvailableSupply!=null){holder.tvAvailableSupply.setText(quotation.getAvailableSupply().toString());}
            if(holder.tvVolume24h!=null){holder.tvVolume24h.setText("$ "+quotation.getVolume24hUSD().toString());}
            if(holder.tvPercent24h!=null){holder.tvPercent24h.setText(quotation.getPercent24h().toString()+"%");}
        }
    }

    @Override
    public int getItemCount() {
        return quotationList.size();
    }

    public void setItemClickListener(RecyclerViewOnClickItemListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    public Quotation getItem(int position){
        return quotationList.get(position);
    }

    public void addItem(Quotation quotationItem, int position){
        quotationList.add(quotationItem);
        notifyItemInserted(position);
    }
    public void removeItem(){

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected TextView tvName;
        protected TextView tvSymbol;
        protected TextView tvRank;
        protected TextView tvPriceUSD;
        protected TextView tvPriceBTC;
        protected TextView tvAvailableSupply;
        protected TextView tvVolume24h;
        protected TextView tvPercent24h;
        protected View view;

        public ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;

            this.tvName = (TextView)view.findViewById(R.id.tvName);
            this.tvSymbol = (TextView)view.findViewById(R.id.tvSymbol);
            this.tvRank = (TextView)view.findViewById(R.id.tvRank);
            this.tvPriceUSD = (TextView)view.findViewById(R.id.tvUsdPrice);
            this.tvPriceBTC = (TextView)view.findViewById(R.id.tvBtcPrice);
            this.tvAvailableSupply = (TextView)view.findViewById(R.id.tvAvailableSuplly);
            this.tvVolume24h = (TextView)view.findViewById(R.id.tvVolume24h);
            this.tvPercent24h = (TextView)view.findViewById(R.id.tvChange24h);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(itemClickListener != null){
                itemClickListener.onItemClickListener(v, getAdapterPosition());
            }
            else{
                Log.i("Info","QuotationAdapter - onClick -> itemClickListener is null (interface RecyclerViewOnClickItemListener not instantiated)");
            }
        }
    }
}
