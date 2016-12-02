package br.estudy.allan.quotations;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Allan on 23/09/2016.
 */
public class UsersDBHelper {
    private static SQLiteHelper dbConn = null;
    private SQLiteDatabase db = null;

    public UsersDBHelper(SQLiteHelper dbConn){
        this.dbConn = dbConn;
        db = this.dbConn.getWritableDatabase();
    }

    public void insertUser(User user){
        ContentValues values = new ContentValues();

        values.put("name",user.getName());
        values.put("email",user.getEmail());
        values.put("password",user.getPassword());

        db.insert("users", null, values);
    }

    public User search(String email){
        User user = new User();
        String[] columns = new String[]{"name","email","password"};
        Cursor cursor = db.query("users",columns,"email = ?", new String[]{email},null,null,null);

        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            user.setName(cursor.getString(0));
            user.setEmail(cursor.getString(1));
            user.setPassword(cursor.getString(2));
        }
        else{
            return null;
        }
        return user;
    }
}
