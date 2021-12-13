package edu.binus.bluedollproject.data_accesses;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import edu.binus.bluedollproject.models.User;

public class UserDA extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = User.TABLE_NAME + ".db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + User.TABLE_NAME + " (" +
                    User._ID + " INTEGER PRIMARY KEY," +
                    User.COLUMN_NAME_NAME + " TEXT," +
                    User.COLUMN_NAME_EMAIL + " TEXT," +
                    User.COLUMN_NAME_PASSWORD + " TEXT," +
                    User.COLUMN_NAME_GENDER + " TEXT," +
                    User.COLUMN_NAME_ROLE + " TEXT" +
                    ")";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + User.TABLE_NAME;

    public UserDA(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
