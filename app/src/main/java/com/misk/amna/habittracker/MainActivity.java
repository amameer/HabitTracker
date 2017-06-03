package com.misk.amna.habittracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

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

        List mlist= mDbHelper.readColumn(HabitTrackerContract.HabitTrackerEntry.tableName,projection,HabitTrackerContract.HabitTrackerEntry.columnHabitName);
        TextView displayView = (TextView) findViewById(R.id.text);
        displayView.setText("All Habit Names  :::"+mlist.toString());

    }

}
