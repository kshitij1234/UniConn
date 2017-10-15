package com.pat_041.android.uniconn;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.pat_041.android.uniconn.definitions.Project;
import com.pat_041.android.uniconn.definitions.User;

import java.util.ArrayList;

/**
 * Created by jaita on 14-Oct-17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "UniConn.db";

    // Users table name
    private static final String TABLE_USERS = "users";
    private static final String TABLE_PROJECTS = "projects";


    // Users Table Columns names
    private static final String KEY_ID = "c_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_UNAME = "uname";
    private static final String KEY_PASS = "password";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_CITY = "city";
    private static final String KEY_STATE = "state";
    private static final String KEY_TYPE = "type";
    private static final String KEY_INSTITUTE = "institute";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PH_NO = "phone";

    private static final String PRO_ID="p_id";
    private static final String PRO_NAME= "name";
    private static final String PRO_TAG = "tag";
    private static final String PRO_INFO = "info";
    private static final String PRO_SDATE = "sdate";
    private static final String PRO_DURATION = "duration";
    private static final String PRO_LOCATION  = "location";



    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_UNAME + " TEXT UNIQUE,"+ KEY_NAME + " TEXT,"
                + KEY_PASS + " TEXT," + KEY_ADDRESS + " TEXT,"+ KEY_CITY + " TEXT,"+ KEY_STATE + " TEXT,"+ KEY_TYPE + " TEXT,"
                + KEY_INSTITUTE + " TEXT," + KEY_EMAIL + " TEXT," + KEY_PH_NO + " TEXT"  +  ")";

        String CREATE_PROJECTS_TABLE = "CREATE TABLE " + TABLE_PROJECTS + "("
                + PRO_ID + " INTEGER PRIMARY KEY," + KEY_ID + " INTEGER,"+ PRO_NAME + " TEXT UNIQUE,"
                + PRO_TAG + " TEXT," + PRO_SDATE + " TEXT,"+ PRO_DURATION + " TEXT,"+ PRO_LOCATION + " TEXT," + PRO_INFO + " TEXT,"  +
                "FOREIGN KEY("+KEY_ID+")"+"REFERENCES "+TABLE_USERS+"("+KEY_ID+")"+ ")";
        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_PROJECTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROJECTS);
        // Create tables again
        onCreate(db);
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getName()); // User Name
        values.put(KEY_UNAME, user.getUname()); // User Name
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

    public User getUser(String uname) {
        SQLiteDatabase db = this.getReadableDatabase();
        User myUser = new User();
        System.out.println(uname+"  --  ");
        Cursor cursor = db.query(TABLE_USERS, new String[] { KEY_ID, KEY_UNAME, KEY_NAME, KEY_PASS, KEY_ADDRESS, KEY_CITY, KEY_STATE, KEY_TYPE, KEY_INSTITUTE, KEY_EMAIL, KEY_PH_NO }, KEY_UNAME + "=?", new String[] { uname }, null,null,null,null);
        if (cursor != null) {
            cursor.moveToFirst();
            do {
                myUser = new User(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2),cursor.getString(3), cursor.getString(4),
                        cursor.getString(5), cursor.getString(6),cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10));
            } while (cursor.moveToNext());
            System.out.println(myUser);
        }
        return myUser;
    }

    public User getUser(int c_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USERS, new String[] { KEY_ID, KEY_UNAME,
                        KEY_NAME, KEY_PASS, KEY_ADDRESS, KEY_CITY, KEY_STATE, KEY_TYPE, KEY_INSTITUTE, KEY_EMAIL, KEY_PH_NO }, KEY_ID + "=?",
                new String[] { String.valueOf(c_id) }, null,null,null,null);
        if (cursor != null)
            cursor.moveToFirst();

        User user = new User(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2),cursor.getString(3), cursor.getString(4),
                cursor.getString(5), cursor.getString(6),cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10));
        // return User
        return user;
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> userList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_USERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setUname(cursor.getString(1));
                user.setName(cursor.getString(2));
                user.setPassword(cursor.getString(3));
                user.setAddress(cursor.getString(4));
                user.setCity(cursor.getString(5));
                user.setState(cursor.getString(6));
                user.setType(cursor.getString(7));
                user.setInstitute(cursor.getString(8));
                user.setEmail(cursor.getString(9));
                user.setPhone(cursor.getString(10));
                userList.add(user);
            } while (cursor.moveToNext());
        }

        // return user list
        return userList;
    }

    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, KEY_ID + " = ?",
                new String[] { String.valueOf(user.getId()) });
        db.close();
    }

    public void addProject(Project project) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, project.getCid()); // Project Name
        values.put(PRO_NAME, project.getName()); // Project Name
        values.put(PRO_TAG, project.getTag());
        values.put(PRO_SDATE, project.getSdate());
        values.put(PRO_DURATION, project.getDuration());
        values.put(PRO_INFO, project.getInfo());
        values.put(PRO_LOCATION, project.getLocation());

        // Inserting Row
        db.insert(TABLE_PROJECTS, null, values);
        db.close(); // Closing database connection
    }

    public Project getProject(int p_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PROJECTS, new String[] { PRO_ID, KEY_ID, PRO_NAME,
                        PRO_TAG, PRO_SDATE, PRO_DURATION, PRO_LOCATION, PRO_INFO }, PRO_ID + "=?",
                new String[] { String.valueOf(p_id) }, null,null,null,null);
        if (cursor != null)
            cursor.moveToFirst();

        Project project = new Project(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), cursor.getString(2),cursor.getString(3), cursor.getString(4),
                cursor.getString(5), cursor.getString(6),cursor.getString(7));
        // return Project
        return project;
    }

    public Project getProjectOfUser(int c_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PROJECTS, new String[] { PRO_ID, KEY_ID, PRO_NAME,
                        PRO_TAG, PRO_SDATE, PRO_DURATION, PRO_LOCATION, PRO_INFO }, KEY_ID + "=?",
                new String[] { String.valueOf(c_id) }, null,null,null,null);
        if (cursor != null)
            cursor.moveToFirst();

        Project project = new Project(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), cursor.getString(2),cursor.getString(3), cursor.getString(4),
                cursor.getString(5), cursor.getString(6),cursor.getString(7));
        // return Project
        return project;
    }

    public ArrayList<Project> getAllProjects() {
        ArrayList<Project> projectList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PROJECTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Project project = new Project();
                project.setId(Integer.parseInt(cursor.getString(0)));
                project.setCid(Integer.parseInt(cursor.getString(1)));
                project.setName(cursor.getString(2));
                project.setTag(cursor.getString(3));
                project.setSdate(cursor.getString(4));
                project.setDuration(cursor.getString(5));
                project.setLocation(cursor.getString(6));
                project.setInfo(cursor.getString(7));
                projectList.add(project);
            } while (cursor.moveToNext());
        }

        // return project list
        return projectList;
    }

    public void deleteProject(Project project) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PROJECTS, PRO_ID + " = ?",
                new String[] { String.valueOf(project.getId()) });
        db.close();
    }
}
