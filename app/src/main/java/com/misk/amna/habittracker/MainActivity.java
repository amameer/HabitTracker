package com.misk.amna.habittracker;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private HabitTrackerDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mDbHelper = new HabitTrackerDbHelper(this);

        String col[]={HabitTrackerContract.HabitTrackerEntry.columnHabitName};
        String key[]={"walking"};
        long res= mDbHelper.InsertRow(col,key,HabitTrackerContract.HabitTrackerEntry.tableName);
        String[] projection = {
                HabitTrackerContract.HabitTrackerEntry._ID,
                HabitTrackerContract.HabitTrackerEntry.columnHabitName,
                HabitTrackerContract.HabitTrackerEntry.columnFrequency,
                HabitTrackerContract.HabitTrackerEntry.columnIsAlert,
                HabitTrackerContract.HabitTrackerEntry.columnNote};

        Cursor cursor=mDbHelper.readColumn(HabitTrackerContract.HabitTrackerEntry.tableName,projection);

        List itemHabits = new ArrayList<>();

        int colIndex = cursor.getColumnIndex(HabitTrackerContract.HabitTrackerEntry.columnHabitName);

        while (cursor.moveToNext()) {

            String itemHabit = cursor.getString(colIndex);
            itemHabits.add(itemHabit);
        }

        cursor.close();

        TextView displayView = (TextView) findViewById(R.id.text);
        displayView.setText("All Habit Names  :::"+itemHabits.toString());

    }

}
