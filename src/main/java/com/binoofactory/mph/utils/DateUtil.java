package com.binoofactory.mph.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Component;

@Component
public final class DateUtil {
	
	public final int UTIL_VERSION = 1;

	public String getToday() 
	{
		return new SimpleDateFormat("yyyyMMdd").format(new Date());
	}
	public String getSearchToday() 
	{
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}
	public String getDatetime() 
	{
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}
	@SuppressWarnings("deprecation")
	public String getDateTimeZoneForBatch(String startDate) throws ParseException 
	{
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(startDate);
		
		StringBuilder stb = new StringBuilder();
		stb.append(new SimpleDateFormat("yyyy-MM-01").format(date));
		stb.append("T");
		stb.append(new SimpleDateFormat("HH:mm:ss").format(date));
		stb.append("Z");
		stb.append(",");
		date.setMonth(date.getMonth()+1);
		stb.append(new SimpleDateFormat("yyyy-MM-01").format(date));
		stb.append("T");
		stb.append(new SimpleDateFormat("HH:mm:ss").format(date));
		stb.append("Z");
		return stb.toString();
	}
	
	public String getDatetimeDetail() 
	{
		return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
	}
	public String getDatetimeDetailCSVFormat() 
	{
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date());
	}
	@SuppressWarnings("deprecation")
	public String getDatetimeDetailAfterMinute(int afterMinute) 
	{
		Date date = new Date();
		date.setMinutes(date.getMinutes() + afterMinute);
		return new SimpleDateFormat("yyyyMMddHHmmss").format(date);
	}
	@SuppressWarnings("deprecation")
	public String getDatetimeDetailAfterSecond(int afterSecond) 
	{
		Date date = new Date();
		date.setSeconds(date.getSeconds() + afterSecond);
		return new SimpleDateFormat("yyyyMMddHHmmss").format(date);
	}
	public String getDatetimeZoneDetail()
	{
		Calendar calendar = Calendar.getInstance(Locale.KOREA);
		StringBuilder stb = new StringBuilder();
		stb.append(calendar.get(Calendar.YEAR));
		stb.append(
				(calendar.get(Calendar.MONTH)+1 < 10 ? "0" : "")
					+ calendar.get(Calendar.MONTH)+1
		);
		stb.append(
				(calendar.get(Calendar.DATE) < 10 ? "0" : "")
					+ calendar.get(Calendar.DATE)
		);
		stb.append(
				(calendar.get(Calendar.HOUR) < 10 ? "0" : "")
					+ calendar.get(Calendar.HOUR)
		);
		stb.append(
				(calendar.get(Calendar.MINUTE) < 10 ? "0" : "")
					+ calendar.get(Calendar.MINUTE)
		);
		stb.append(
				(calendar.get(Calendar.SECOND) < 10 ? "0" : "")
					+ calendar.get(Calendar.SECOND)
		);
		return stb.toString();
	}
}
