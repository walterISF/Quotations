package br.estudy.allan.quotations;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allan on 09/09/2016.
 */
public class TaskGetQuotations extends AsyncTask<Void, Void, List<Quotation>> {

    private final String URLRequest;
    private final Context context;
    private final QuotationCallback callback;
    private QuotationsDBHelper databaseHelper;
    private ProgressDialog progressDialog;
    private HttpsHandler httpsHandler;

    public TaskGetQuotations(Context context, QuotationCallback callback, String URL){
        this.context = context;
        this.callback = callback;
        this.URLRequest = URL;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(context,"Please wait", "Charging quotations");
    }

    @Override
    protected List<Quotation> doInBackground(Void... params) {
        httpsHandler = new HttpsHandler();
        try {
            String result = httpsHandler.doHttpsRequest(URLRequest);
            if(result!=null){
                ParserQuotation parser = new ParserQuotation(databaseHelper);
                parser.parserJSON(result);
            }
            else{
                throw new Exception("Impossible to establish connection, Please check you connection.");
            }
        } catch (Exception e) {
            Log.i("Error","TaskGetQuotations - doInBackground -> Exception catch of the httpsHandler --- "+e.getMessage());

        }
        return initializeItems();
    }

    @Override
    protected void onPostExecute(List<Quotation> aQuotation) {
        progressDialog.dismiss();
        this.callback.callback(aQuotation);
    }

    private List<Quotation> initializeItems(){
        ArrayList<Quotation> quotations = new ArrayList<>();
        Quotation quotation;
        for(int i=1; i<=20; i++){
            quotation = new Quotation();
            quotation.clone(databaseHelper.search(i));
            quotations.add(quotation);
        }
        return quotations;
    }

    public void setDatabaseHelper(SQLiteHelper database){
        this.databaseHelper = new QuotationsDBHelper(database);
    }
}
