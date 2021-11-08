package pk.edu.uiit.aridNo2474.MAD_assignment2_q2.hashirApp;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    private static final String db_name = "myDB";
    private static final String table_name = "student";
    private static final int db_ver = 1;
    private final SQLiteDatabase w_db;
    private final SQLiteDatabase r_db;

    public DBHelper(Context c) {
        super( c, db_name, null, db_ver );
        w_db = this.getWritableDatabase();
        r_db = this.getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String db_specs = "(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, email VARCHAR, phone VARCHAR, password VARCHAR, country VARCHAR )";
        final String query = "CREATE TABLE IF NOT EXISTS " + table_name + db_specs;
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertStudentRecord (String name, String email, String phone, String password, String country ) {
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("email", email);
        cv.put("phone", phone);
        cv.put("password", password);
        cv.put("country", country);
        return w_db.insert( table_name, null, cv );
    }

    public int checkLoggedUser (String email, String password) {
        Cursor c = r_db.rawQuery("SELECT * FROM "+ table_name +" WHERE email = '" + email + "' AND password = '" + password + "'", null);
        int id = -1;
        if (c.moveToFirst()) id = c.getInt(0);
        return id;
    }

    public String[] getStudent (int id) {
        Cursor c = r_db.rawQuery("SELECT * FROM " + table_name + " WHERE id = " + id, null);
        String student[] = new String[10];
        if (c.moveToFirst()) {
            student[0] = String.valueOf(c.getInt(0));
            student[1] = c.getString(1);
            student[2] = c.getString(2);
            student[3] = c.getString(3);
            student[4] = c.getString(4);
            student[5] = c.getString(5);
        }
        return student;
    }
}

