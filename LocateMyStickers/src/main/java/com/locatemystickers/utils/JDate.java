package com.locatemystickers.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import android.util.Log;

public class JDate {
	private Date _today = new Date();
	private Date _date;
	
	public JDate(String timestamp) {
		_date = new GregorianCalendar(Integer.parseInt(timestamp.split("-")[0]),
				Integer.parseInt(timestamp.split("-")[1]),
				Integer.parseInt(timestamp.split("-")[2].split("T")[0]),
				Integer.parseInt(timestamp.split(":")[0].split("T")[1]),
				Integer.parseInt(timestamp.split(":")[1]),
				Integer.parseInt(timestamp.split(":")[2].split("Z")[0])).getTime();
	}
	
	public Date diffnow() {
		Date d = new Date();
		d.setTime(_today.getTime() - _date.getTime());
		return d;
	}
	
	public String toString(Date d)
	{
		String s = "";
		if (d.getMonth() != 0)
			s += d.getMonth() + " months ";
		if (d.getDay() != 0)
			s += d.getDay() + " days ";
		s += d.getHours() + " hours ";
		s += d.getMinutes() + " minutes ";
		s += d.getSeconds() + " seconds";
		return s;
	}
}
