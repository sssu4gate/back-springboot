package com.gate.planner.gate.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    private static DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public static Date toAsiaTimeZone() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR, 9);
        return cal.getTime();
    }

    public static Date parseDateFormat(String date) throws ParseException {
        return format.parse(date);
    }

    public static String parseString(Date date) {
        return format.format(date);
    }

}
