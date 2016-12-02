package br.estudy.allan.quotations;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class QuotationItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotation_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Quotation item = (Quotation) getIntent().getSerializableExtra("item");

        final TextView tvId = (TextView)this.findViewById(R.id.tvItemId);
        final TextView tvName = (TextView)this.findViewById(R.id.tvItemName);
        final TextView tvSymbol = (TextView)this.findViewById(R.id.tvItemSymbol);
        final TextView tvRank = (TextView)this.findViewById(R.id.tvItemRank);
        final TextView tvPriceUSD = (TextView)this.findViewById(R.id.tvItemPriceUSD);
        final TextView tvPriceBTC = (TextView)this.findViewById(R.id.tvItemPriceBTC);
        final TextView tvVolume24h = (TextView)this.findViewById(R.id.tvVolume);
        final TextView tvMarket = (TextView)this.findViewById(R.id.tvItemMarket);
        final TextView tvAvailable = (TextView)this.findViewById(R.id.tvItemAvailable);
        final TextView tvTotal = (TextView)this.findViewById(R.id.tvItemTotal);
        final TextView tvPercent1h = (TextView)this.findViewById(R.id.tvItemPercent1h);
        final TextView tvPercent24h = (TextView)this.findViewById(R.id.tvChange);
        final TextView tvPercent7d = (TextView)this.findViewById(R.id.tvItemPercent7d);
        final TextView tvLast = (TextView)this.findViewById(R.id.tvItemLast);

        tvId.setText(item.getId());
        tvName.setText(item.getName());
        tvSymbol.setText(item.getSymbol());
        tvRank.setText("Rank: "+item.getRank());
        tvPriceUSD.setText(item.getPriceUSD().toString());
        tvPriceBTC.setText(item.getPriceBTC().toString());
        tvVolume24h.setText(item.getVolume24hUSD().toString());
        tvMarket.setText(item.getMarketCapUSD().toString());
        tvAvailable.setText(item.getAvailableSupply().toString());
        tvTotal.setText(item.getTotalSupply().toString());
        tvPercent1h.setText(item.getPercent1h().toString());
        tvPercent24h.setText(item.getPercent24h().toString());
        tvPercent7d.setText(item.getPercent7d().toString());
        tvLast.setText(item.getLastUpdate().toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_quotationitem, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_return:
                this.finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
