package br.estudy.allan.quotations;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity {

    private SQLiteHelper database;
    private UsersDBHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        database = new SQLiteHelper(this);
        databaseHelper = new UsersDBHelper(database);
        final Button btnRegister = (Button)this.findViewById(R.id.btnRegister);
        final Button btnCancel = (Button)this.findViewById(R.id.btnCancel);
        final EditText etName = (EditText)this.findViewById(R.id.etUserName);
        final EditText etEmail = (EditText)this.findViewById(R.id.etUserEmail);
        final EditText etPassword = (EditText)this.findViewById(R.id.etUserPassword);

        setOnTouch(etName);
        setOnTouch(etEmail);
        setOnTouch(etPassword);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setName(etName.getText().toString());
                user.setEmail(etEmail.getText().toString());
                user.setPassword(etPassword.getText().toString());

                databaseHelper.insertUser(user);

                startLogin();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLogin();
            }
        });
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
    private void startLogin(){
        database.close();
        Intent iLogin = new Intent(this, Main.class);
        startActivity(iLogin);
        this.finish();
    }
}
