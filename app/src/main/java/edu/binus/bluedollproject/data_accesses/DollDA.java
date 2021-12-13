package edu.binus.bluedollproject.data_accesses;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import edu.binus.bluedollproject.models.Doll;

public class DollDA extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = Doll.TABLE_NAME + ".db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Doll.TABLE_NAME + " (" +
                    Doll._ID + " INTEGER PRIMARY KEY," +
                    Doll.COLUMN_NAME_NAME + " TEXT," +
                    Doll.COLUMN_NAME_IMAGE + " TEXT," +
                    Doll.COLUMN_NAME_DESCRIPTION + " TEXT," +
                    Doll.COLUMN_NAME_USER_ID + " INTEGER" +
                    ")";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + Doll.TABLE_NAME;

    public DollDA(Context context) {
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
