package com.term_tracker.C196.HelperClasses;

import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class StringToCalendarConverterClass {

    public static Calendar stringToCalendar(EditText givenDate){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
        int mMonth = 0;
        int mDay = 0;
        int mYear = 0;

        try {
            Date theDate = sdf.parse(givenDate.getText().toString());
            cal.setTime(theDate);
            int selectedMonth = cal.get(Calendar.MONTH);
            } catch (ParseException e) {
            e.printStackTrace();
        }
        return cal;
    }
}
