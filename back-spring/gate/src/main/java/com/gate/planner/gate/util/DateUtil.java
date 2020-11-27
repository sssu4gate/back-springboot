package com.gate.planner.gate.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static Date toAsiaTimeZone() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR, 9);
        return cal.getTime();
    }
}
