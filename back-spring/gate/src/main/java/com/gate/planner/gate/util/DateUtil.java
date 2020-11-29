package com.gate.planner.gate.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static Date toAsiaTimeZone() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR, 9);
        return cal.getTime();
    }

    public static DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
}
