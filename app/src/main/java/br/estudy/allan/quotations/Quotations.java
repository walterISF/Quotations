package br.estudy.allan.quotations;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Quotations extends AppCompatActivity implements RecyclerViewOnClickItemListener {

    private List<Quotation> quotations = null;
    private SQLiteHelper database = null;
    private QuotationsDBHelper databaseHelper = null;
    private QuotationAdapterRV adapter;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotations);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        database = new SQLiteHelper(this);
        databaseHelper = new QuotationsDBHelper(database);
        //Receber a lista e verificar se esta null.
        quotations = (ArrayList<Quotation>) getIntent().getSerializableExtra("quotations");

        if(quotations!=null){

            final RecyclerView rvQuotations = (RecyclerView)this.findViewById(R.id.lvQuotations);
            rvQuotations.setLayoutManager(new LinearLayoutManager(this));
            rvQuotations.setItemAnimator(new DefaultItemAnimator());
            rvQuotations.setHasFixedSize(true);

            rvQuotations.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    LinearLayoutManager layoutManager = (LinearLayoutManager) rvQuotations.getLayoutManager();
                    QuotationAdapterRV adapter = (QuotationAdapterRV) rvQuotations.getAdapter();

                    if(quotations.size() == layoutManager.findLastCompletelyVisibleItemPosition() + 1){
                        for(int i=0; i<100; i++) {
                            Quotation quotationsItem = databaseHelper.search(adapter.getItemCount()+1);
                            if(quotationsItem!=null) {
                                adapter.addItem(quotationsItem, adapter.getItemCount());
                            }
                            else {
                                break;
                            }
                        }
                    }
                }
            });
            adapter = new QuotationAdapterRV(this, this.quotations);
            adapter.setItemClickListener(this);
            rvQuotations.setAdapter(adapter);
        }
    }

    @Override
    public void onItemClickListener(View view, int position) {
        Intent iQuotationItem = new Intent(this, QuotationItem.class);
        Quotation quotationItem = new Quotation();
        quotationItem.clone(adapter.getItem(position));
        iQuotationItem.putExtra("item", quotationItem);
        startActivity(iQuotationItem);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_quotations, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_logout:
                quotationsLogout();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void quotationsLogout(){
        database.close();
        SharedPreferences sp = getSharedPreferences("QuotationPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("logged", false);
        editor.commit();
        this.finish();
    }
}
