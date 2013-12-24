package com.locatemystickers.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;
import java.util.concurrent.TimeUnit;

import android.util.Log;


public class JDate {
	private Calendar _date;
    private Calendar _cnow;
	
	public JDate(String timestamp) {
        //2013-06-16T13:33:30Z
        String date = timestamp.split("T")[0];
        String time = timestamp.split("T")[1].split("Z")[0];
        _date = new GregorianCalendar(Integer.parseInt(date.split("-")[0]),
                Integer.parseInt(date.split("-")[1]),
                Integer.parseInt(date.split("-")[2]),
                Integer.parseInt(time.split(":")[0]),
                Integer.parseInt(time.split(":")[1]),
                Integer.parseInt(time.split(":")[2]));
        Log.d(JDate.class.getName(), toString());
	}

    public Date diffNow()
    {
        _cnow = Calendar.getInstance();

        long ldiff = _cnow.getTime().getTime() - _date.getTime().getTime();
        return new Date(ldiff);
    }

    public String toStringDiffNow()
    {
        String str = "";
        Date d = diffNow();
        Calendar c = new GregorianCalendar();
        c.setTime(d);
        if (c.get(Calendar.DAY_OF_MONTH) != 0)
            str += c.get(Calendar.DAY_OF_MONTH) + " days ";
        if (c.get(Calendar.HOUR_OF_DAY) != 0)
            str += c.get(Calendar.HOUR_OF_DAY) + " hours ";
        if (c.get(Calendar.MINUTE) != 0)
            str += c.get(Calendar.MINUTE) + " minutes ";
        str += c.get(Calendar.SECOND) + " seconds";
        return str;
    }

    public String toString() {
        String str = null;
        str += _date.get(Calendar.YEAR) + " years ";
        str += _date.get(Calendar.MONTH) + "months ";
        str += _date.get(Calendar.DAY_OF_MONTH) + "days ";
        str += _date.get(Calendar.HOUR_OF_DAY) + "hours ";
        str += _date.get(Calendar.MINUTE) + "minutes ";
        str += _date.get(Calendar.SECOND) + "seconds";
        return str;
    }
}
