package com.misk.amna.habittracker;

import android.provider.BaseColumns;

/**
 * Created by Amna on 6/2/2017 AD.
 */

public final class HabitTrackerContract {

    private HabitTrackerContract() {
    }

    public static class HabitTrackerEntry implements BaseColumns {

        public static final String tableName = "habits";
        public static final String columnHabitName = "habitName";
        public static final String columnFrequency = "frequency";
        public static final String columnIsAlert = "isAlert";
        public static final String columnNote = "notes";

    }
}

