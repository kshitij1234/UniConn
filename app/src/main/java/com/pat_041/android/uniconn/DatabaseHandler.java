package com.pat_041.android.uniconn;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.pat_041.android.uniconn.definitions.User;

/**
 * Created by jaita on 14-Oct-17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "UniConn";

    // Contacts table name
    private static final String TABLE_USERS = "users";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PASS = "password";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_CITY = "city";
    private static final String KEY_STATE = "state";
    private static final String KEY_TYPE = "type";
    private static final String KEY_INSTITUTE = "institute";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PH_NO = "phone";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PASS + " TEXT" + KEY_ADDRESS + " TEXT"+ KEY_CITY + " TEXT"+ KEY_STATE + " TEXT"+ KEY_TYPE + " TEXT"
                + KEY_INSTITUTE + " TEXT" + KEY_EMAIL + " TEXT" + KEY_PH_NO + " TEXT"  +  ")";
        db.execSQL(CREATE_USERS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);

        // Create tables again
        onCreate(db);
    }

    public void addContact(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getName()); // Contact Name
        values.put(KEY_PASS, user.getPassword());
        values.put(KEY_ADDRESS, user.getAddress());
        values.put(KEY_CITY, user.getCity());
        values.put(KEY_STATE, user.getState());
        values.put(KEY_TYPE, user.getType());
        values.put(KEY_INSTITUTE, user.getInstitute());
        values.put(KEY_EMAIL, user.getEmail());
        values.put(KEY_PH_NO, user.getPhone());


        // Inserting Row
        db.insert(TABLE_USERS, null, values);
        db.close(); // Closing database connection
    }
}
