package com.beiming.java8.date;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;


import lombok.extern.slf4j.Slf4j;

/**
 * 1. Java8之前的时间API都是非线程安全的，为此Java8引入了新的时间API 
 * 2. LocalDate                                                                  操作当前日期
 * 3. LocalTime                                                                  操作当前时间
 * 4. LocalDateTime                                                           操作当前日期 + 时间
 * 5. Instant                                                                       操作时间戳，并非时间戳
 * 6. Duration                                                                    操作两个时间
 * 7. Period                                                                       操作两个日期
 * 8. DateTimeFormatter                                                   操作时间格式
 * 9. ZonedDate、ZonedTime、ZonedDateTime                操作时区
 *
 */
@Slf4j
public class Java8Date {
	
	public static void main(String[] args) {
		localDate();
		localTime();
		localDateTime();
		instant();
		duration();
		period();
		dateTimeFormatter();
		zone();
		transform();
	}

	public static void localDate() {
		log.info("================LocalDate====================");
		LocalDate now = LocalDate.now();                                                                           System.out.println(now);                                         //获取当前时间，默认为当前时区，可通过参数指定时区
		LocalDate of = LocalDate.of(2019, 12, 21);                                                                System.out.println(of);                                            //构造指定的LocalDate
		LocalDate of2 = LocalDate.of(2019, Month.NOVEMBER, 21);                                    System.out.println(of2);
		int year = now.getYear();                                                                                          System.out.println(year);                                         //获取年
		LocalDate plusDays = now.plusDays(1);                                                                     System.out.println(plusDays);                                 //增操作
		LocalDate minusDays = now.minusDays(1);                                                               System.out.println(minusDays);                              //减操作
		LocalDate withMonth = now.withMonth(11);                                                           System.out.println(withMonth);                              //替换操作
	}
	
	public static void localTime() {
		log.info("================LocalTime====================");
		LocalTime now = LocalTime.now();                                                                            System.out.println(now);
		LocalTime of = LocalTime.of(12, 22, 33);                                                                    System.out.println(of);
		int minute = of.getMinute();                                                                                     System.out.println(minute);
		LocalTime plusHours = now.plusHours(1);                                                                  System.out.println(plusHours);
	}
	
	public static void localDateTime() {
		log.info("================LocalDateTime====================");
		LocalDateTime now = LocalDateTime.now();                                                              System.out.println(now);                                          //操作同上 
		
	}
	
	public static void instant() {
		log.info("================Instant====================");
		Instant now = Instant.now();                                                                                       System.out.println(now);                                      //标准时间戳，与北京存在八小时时差
		long epochMilli = now.toEpochMilli();                                                                         System.out.println(epochMilli);                            //Instant转化为毫秒数
		OffsetDateTime offsetDateTime = now.atOffset(ZoneOffset.ofHours(8));                      System.out.println(offsetDateTime);                   //加上时差
	}
	
	public static void duration() {
		log.info("================Duration(计算时间差)====================");
		LocalTime now = LocalTime.now();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		LocalTime now1 = LocalTime.now();
		Duration between = Duration.between(now, now1);   System.out.println(between);
		long seconds = between.getSeconds();                       System.out.println("seconds ===  " + seconds );
		long millis = between.toMillis();                                  System.out.println(millis);
	}
	
	public static void period() {
		log.info("================Period(计算日期差)====================");
		LocalDate now = LocalDate.now();
		LocalDate plusDays = now.plusDays(12);
		Period between = Period.between(now, plusDays);            System.out.println(between.getDays());
	}
	
	public static void dateTimeFormatter() {
		log.info("================DateTimeFormatter====================");
		DateTimeFormatter format  = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String format2 = LocalDateTime.now().format(format);
		System.out.println(format2);
		LocalDateTime parse2 = LocalDateTime.parse("1994-11-23 16:22:21", format);  System.out.println(parse2);
	}
	
	public static void zone() {
		log.info("================Zone====================");
		LocalDateTime dateTime = LocalDateTime.now(ZoneId.of("America/Chicago"));  System.out.println(dateTime);
	}
	
	//Date和LocalDateTime 间的相互转化借助于  Instant
	public static void transform() {
		log.info("================Date 与 DateTimeFormat 间的转换====================");
		Date asDate = asDate(LocalDate.now());  System.out.println(asDate);
		Date asDate1 = asDate1(LocalDateTime.now());   System.out.println(asDate1);
		LocalDate asLocalDate = asLocalDate(new Date()); System.out.println(asLocalDate);
		LocalDateTime asLocalDateTime1 = asLocalDateTime1(new Date());  System.out.println(asLocalDateTime1);
	}

    public  static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }
 
    public  static Date asDate1(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
 
    public  static LocalDate asLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }
 
    public  static LocalDateTime asLocalDateTime1(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
