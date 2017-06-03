//help from https://developer.android.com/training/basics/data-storage/databases.html#ReadDbRow
package com.misk.amna.habittracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class HabitTrackerDbHelper extends SQLiteOpenHelper {

    public static final int dbVersion = 1;
    public static final String dbName = "HabitTracker.db";
    public static final SQLiteDatabase.CursorFactory factory = null;

    public static final String SQLSchema = "CREATE TABLE " + HabitTrackerContract.HabitTrackerEntry.tableName + " (" +
            HabitTrackerContract.HabitTrackerEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            HabitTrackerContract.HabitTrackerEntry.columnHabitName + " TEXT," +
            HabitTrackerContract.HabitTrackerEntry.columnFrequency + " INTEGER," +
            HabitTrackerContract.HabitTrackerEntry.columnIsAlert + " BOOLEAN," +
            HabitTrackerContract.HabitTrackerEntry.columnNote + " TEXT)";

    private static final String SQLDropTbl = "DROP TABLE IF EXISTS " + HabitTrackerContract.HabitTrackerEntry.tableName;

    public HabitTrackerDbHelper(Context context) {

        super(context, dbName, factory, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLSchema);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long InsertRow(String columns[], String keys[], String TableName) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        for (int i = 0; i < columns.length; i++)
            values.put(columns[i], keys[i]);

        //return primary key value
        return db.insert(TableName, null, values);
    }

    public List readColumn(String tableName, String[] projection, String ColumnName) {

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(
                tableName,
                projection,
                null,
                null,
                null,
                null,
                null);

        List itemHabits = new ArrayList<>();

        int colIndex = cursor.getColumnIndex(ColumnName);

        while (cursor.moveToNext()) {

            String itemHabit = cursor.getString(colIndex);
            itemHabits.add(itemHabit);
        }

        cursor.close();

        return itemHabits;
    }


    public void deletRow(String TableName, String columnTitle, String[] args) {

        SQLiteDatabase db = getWritableDatabase();
        String selection = columnTitle + " LIKE ?";
        db.delete(TableName, selection, args);

    }

    public int updatTaple(String tableName, String[] columns, String[] keys, String coulmnTitle, String[] args) {

        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();
        String selection = coulmnTitle + " LIKE ?";

        for (int i = 0; i < columns.length; i++)
            values.put(columns[i], keys[i]);

        //return count of effected rows
        return db.update(
                tableName,
                values,
                selection,
                args);
    }

}
