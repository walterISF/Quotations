package br.estudy.allan.quotations;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import java.util.List;

/**
 * Created by Allan on 12/09/2016.
 */

//classe usada para adaptar um ListView
public class QuotationAdapter extends ArrayAdapter<Quotation> {


    public QuotationAdapter(Context context, int resource, List<Quotation> items){
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if(v==null){
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.quotation_row_item,null);
        }
        Quotation quotation = getItem(position);

        if(quotation!=null){
            TextView tvName = (TextView)v.findViewById(R.id.tvName);
            TextView tvSymbol = (TextView)v.findViewById(R.id.tvSymbol);
            TextView tvRank = (TextView) v.findViewById(R.id.tvRank);
            TextView tvPriceUSD = (TextView) v.findViewById(R.id.tvUsdPrice);
            TextView tvPriceBTC = (TextView) v.findViewById(R.id.tvBtcPrice);
            TextView tvAvailableSupply = (TextView)v.findViewById(R.id.tvAvailableSuplly);
            TextView tvVolume24h = (TextView)v.findViewById(R.id.tvVolume24h);
            TextView tvPercent24h = (TextView)v.findViewById(R.id.tvChange24h);

            if(tvName!=null){tvName.setText(quotation.getName());}
            if(tvSymbol!=null){tvSymbol.setText(quotation.getSymbol());}
            if(tvRank!=null){tvRank.setText("Rank: "+quotation.getRank().toString());}
            if(tvPriceUSD!=null){tvPriceUSD.setText("$ "+quotation.getPriceUSD().toString());}
            if(tvPriceBTC!=null){tvPriceBTC.setText(quotation.getPriceBTC().toString().toString());}
            if(tvAvailableSupply!=null){tvAvailableSupply.setText(quotation.getAvailableSupply().toString());}
            if(tvVolume24h!=null){tvVolume24h.setText("$ "+quotation.getVolume24hUSD().toString());}
            if(tvPercent24h!=null){tvPercent24h.setText(quotation.getPercent24h().toString()+"%");}
        }
        return v;
    }
}
