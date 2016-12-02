package br.estudy.allan.quotations;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

public class Main extends AppCompatActivity implements QuotationCallback {

    private final String URLRequest = "https://api.coinmarketcap.com/v1/ticker";
    private SQLiteHelper database;
    private UsersDBHelper databaseHelper;
    private TaskGetQuotations task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new SQLiteHelper(this);
        database.dropQuotations();
        databaseHelper = new UsersDBHelper(database);
        task = new TaskGetQuotations(this,this,URLRequest);
        task.setDatabaseHelper(database);

        SharedPreferences sp = getSharedPreferences("QuotationPrefs", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sp.edit();

        if(!sp.getBoolean("logged",false)) {

            final Button btnLogin = (Button) this.findViewById(R.id.btnLogin);
            final EditText etUser = (EditText) this.findViewById(R.id.etUser);
            final EditText etPassword = (EditText) this.findViewById(R.id.etPassword);
            final TextView tvRegister = (TextView) this.findViewById(R.id.tvRegister);

            setOnTouch(etUser);
            setOnTouch(etPassword);

            assert btnLogin != null;
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    User user = databaseHelper.search(etUser.getText().toString());

                    if (user != null) {
                        if (etPassword.getText().toString().equals(user.getPassword())) {
                            editor.putBoolean("logged",true);
                            editor.apply();
                            quotationLogin();
                        } else {
                            editor.putBoolean("logged",false);
                            editor.apply();
                            toast(" Password is invalid");
                            etUser.setText(R.string.login);
                            etPassword.setText(R.string.password);
                        }
                    } else {
                        editor.putBoolean("logged",false);
                        editor.apply();
                        toast("User  is invalid");
                        etUser.setText(R.string.login);
                        etPassword.setText(R.string.password);
                    }

                }
            });
            assert tvRegister != null;
            tvRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startRegister();
                }
            });
        }
        else{
            quotationLogin();
        }
    }
    private void setOnTouch(EditText editText){
        final EditText et = editText;
        et.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                et.setText("");
                return false;
            }
        });
    }
    private void quotationLogin(){
        task.execute();
    }

    private void startRegister(){
        database.close();
        Intent iRegister = new Intent(this, Register.class);
        startActivity(iRegister);
        this.finish();
    }
    private void toast(String message){
        Toast.makeText(Main.this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void callback(List<Quotation> quotationList) {
        database.close();
        Intent iQuotations = new Intent(this, Quotations.class);
        iQuotations.putExtra("quotations", (Serializable)quotationList);
        this.startActivity(iQuotations);
        this.finish();
    }
}
