package com.github.nurkholik.GeneralJavaLib;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
	
	public enum Pattern {
		DATE_TIME			("dd/MM/yyyy HH:mm:ss"),
		DATE				("dd/MM/yyyy"),
		TIME				("hh:mm:ss"),
		FULL_DATE_TIME		("dd/MMMM/yyyy HH:mm:ss"),
		FULL_DATE			("dd/MMMM/yyyy"),
		DATE_TIME_REV		("yyyy/MM/dd HH:mm:ss"),
		DATE_REV			("yyyy/MM/dd"),
		FULL_DATE_REV		("yyyy/MMMM/dd"),
		TIMESTAMP			("yyyy-MM-dd HH:mm:ss"),
		DATE_REV_NO_LIMITER	("yyyyMMdd"),
		TIME_ID				("yyyyMMddHHmmssSSS"),
		MONTH_DAY			("MMdd")
		
		// Add new patter here
		;
		private String value;
		private Pattern(String value) {
			this.value = value;
		}
		private String value() {
			return this.value;
		}

	}
	
	public static String reformatDate(String strDate, String newSeparator) {
		if (strDate.length() == 10) {
			strDate = strDate.replace("/", newSeparator)
					.replace("-", newSeparator)
					.replace("*", newSeparator)
					.replace(" ", newSeparator);
		} else if (strDate.length() == 8) {
			String[] arr = strDate.split("");
			strDate = 
				arr[0] + arr[1] + newSeparator +
				arr[2] + arr[3] + newSeparator +
				arr[4] + arr[5] + arr[6] + arr[7] ;
		}
		return strDate;
	}
	@SuppressWarnings("deprecation")
	public static String ExactAge(Date pob) {
    	Date now = new Date();
    	
		if (pob.before(now)) {
    		
    		int year 	= now.getYear() 	- pob.getYear();
    		int month	= now.getMonth() 	- pob.getMonth();
    		int day		= now.getDate()		- pob.getDate();
    		    		
    		if (Math.signum(day) == -1) {
    			day = 31 - Math.abs(day);
    			month--;
    		}
    		if (Math.signum(month) == -1) {
    			month = 12 - Math.abs(month);
    			year--;
    		}
    		
    		if (year > 99) {
    			year = 99;
    		}
    		
    		return 	StringUtil.right("00" + year	, 2) +
    				StringUtil.right("00" + month	, 2) + 
    				StringUtil.right("00" + day		, 2);
    	}
		return "000000";
    }
	public static Period getAgeInterval (Date dob) {
		return Period.between(dob.toInstant().atZone(TIMEZONE).toLocalDate(), LocalDate.now());
	}
	public static Period getAgeInterval (LocalDate dob) {
		return Period.between(dob, LocalDate.now());
	}
	public static int getAge (Date dob) {
		return Period.between(dob.toInstant().atZone(TIMEZONE).toLocalDate(), LocalDate.now()).getYears();
	}
	public static int getAge (LocalDate dob) {
		return Period.between(dob, LocalDate.now()).getYears();
	}
	public static Date getDefaultDate() {
		Date date = new Date();
		try {
			date = new SimpleDateFormat(Pattern.TIMESTAMP.value()).parse("1900-01-01 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	public static void getDayOfMonth(Date date) {
		toLocaleDatetime(date).getDayOfMonth();
	}
	public static void getDayOfYear(Date date) {
		toLocaleDatetime(date).getDayOfYear();
	}
	public static String format(Date date, Pattern DateUtilPattern) {
		SimpleDateFormat df = new SimpleDateFormat(DateUtilPattern.value());
		return df.format(date);
	}
	public static String format(Date date, Pattern pattern, String dateSeparator) {
		SimpleDateFormat df = new SimpleDateFormat(pattern.value().replace("/", dateSeparator));
		return df.format(date);
	}
	public static Date toDate(String strDate, Pattern pattern) throws ParseException {
		return new SimpleDateFormat(pattern.value()).parse(strDate);
	}
	
	@SuppressWarnings("deprecation")
	public static Date setCurrentTime(Date date) {
		Date dt = new Date();
		date.setTime	(dt.getTime());
		date.setHours	(dt.getHours());
		date.setMinutes	(dt.getMinutes());
		date.setSeconds	(dt.getSeconds());
		return date;
	}
	private static ZoneId TIMEZONE = ZoneId.of("UTC+7");
	
	public static Date currentTime(Date date) {
		return Date.from(toLocaleDatetime(date).with(LocalTime.now(TIMEZONE)).atZone(TIMEZONE).toInstant());
	}
	public static Date beginDayOf(Date date) {
		return Date.from(toLocaleDatetime(date).with(LocalTime.MIN).atZone(TIMEZONE).toInstant());
	}
	public static Date endDayOf(Date date) {
		return Date.from(toLocaleDatetime(date).with(LocalTime.MAX).atZone(TIMEZONE).toInstant());
	}
	public static Date beginMonthOf(Date date) {
		return Date.from(toLocaleDatetime(date).withDayOfMonth(1).with(LocalTime.MIN).atZone(TIMEZONE).toInstant());
	}
	public static Date endMonthOf(Date date) {
		LocalDateTime ldt = toLocaleDatetime(date).with(LocalTime.MAX);
		return Date.from(ldt.withDayOfMonth(ldt.toLocalDate().lengthOfMonth()).atZone(TIMEZONE).toInstant());
	}
	public static DateTimeFormatter getLocalFormater(Pattern pattern) {
		return DateTimeFormatter.ofPattern(pattern.value());
	}
	public static LocalDateTime toLocaleDatetime (Date date) {
		return LocalDateTime.parse(format(date, Pattern.FULL_DATE_TIME), getLocalFormater(Pattern.FULL_DATE_TIME)).atZone(TIMEZONE).toLocalDateTime();
	}
	public static Date beginThisMonth() {
		return beginMonthOf(new Date());
	}
	public static Date endThisMonth() {
		return endMonthOf(new Date());
	}
	public static Date ajustSeconds(Date date, int seconds_ajust) {
		Date dt = new Date(date.getTime());
		dt.setTime(dt.getTime() + (seconds_ajust*1000));
		return dt;
	}
	
	/**
	 * Add After
	 * @param date		- current date
	 * @param interval	- range 
	 * @param type		- Calendar.xxx
	 * @return
	 */
	public static Date add(Date date, int interval, int type) {
		GregorianCalendar call = new GregorianCalendar();
		call.setTime(date);
		call.add(type, interval);
		return call.getTime();
	}
	
	/**
	 * Add Before
	 * @param date		- current date
	 * @param interval	- range 
	 * @param type		- Calendar.xxx
	 * @return
	 */
	public static Date substract(Date date, int interval, int type) {
		GregorianCalendar call = new GregorianCalendar();
		call.setTime(date);
		call.add(type, interval*-1);
		return call.getTime();
	}
	
	/**
	 * Diff Function
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static long difDay(Date d1, Date d2) {
		return ChronoUnit.DAYS.between(toLocaleDatetime(d1), toLocaleDatetime(d2));
	}
	public static long difWeek(Date d1, Date d2) {
		return ChronoUnit.WEEKS.between(toLocaleDatetime(d1), toLocaleDatetime(d2));
	}
	public static long difMonth(Date d1, Date d2) {
		return ChronoUnit.MONTHS.between(toLocaleDatetime(d1), toLocaleDatetime(d2));
	}
	public static long difYear(Date d1, Date d2) {
		return ChronoUnit.YEARS.between(toLocaleDatetime(d1), toLocaleDatetime(d2));
	}
	public static long difHour(Date d1, Date d2) {
		return ChronoUnit.HOURS.between(toLocaleDatetime(d1), toLocaleDatetime(d2));
	}
	public static long difMinute(Date d1, Date d2) {
		return ChronoUnit.MINUTES.between(toLocaleDatetime(d1), toLocaleDatetime(d2));
	}
	public static long difSecond(Date d1, Date d2) {
		return ChronoUnit.SECONDS.between(toLocaleDatetime(d1), toLocaleDatetime(d2));
	}
	
	public static void main(String[] args) throws ParseException {
//		Date date = new Date();
//		System.out.println("Local Date \t: " 	+ toLocaleDatetime(date));
//		System.out.println("Begin Date \t: " 	+ beginDayOf(date));
//		System.out.println("End Date \t: " 		+ endDayOf(date));
//		System.out.println("Begin Month \t: " 	+ beginMonthOf(date));
//		System.out.println("End Month \t: " 	+ endMonthOf(date));
		
		Date date = getDefaultDate();
		System.out.println(date);
		System.out.println(currentTime(date));
		
	}
}
