package com.zhx.gmms.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.zhx.gmms.frame.Const;

public class DateUtils {

	private static ThreadLocal<MyDateFormat> threadLocal = new ThreadLocal<MyDateFormat>();
	
	/**
	 * 获取指定规则的DateFormat
	 * @param pattern 规则
	 * @return
	 */
	private static DateFormat getDateFormat(String pattern){
		MyDateFormat myDF = threadLocal.get();
		if(null==myDF||!myDF.getDatePattern().equals(pattern)){
			myDF = new MyDateFormat(pattern,new SimpleDateFormat(pattern));
			threadLocal.set(myDF);
		}
		return myDF.getDateFormat();
	}
	
	/**
	 * 日期格式化，日期根据规则转换为字符串
	 * @param date 待转换的日期
	 * @param pattern 规则
	 * @return 日期字符串
	 */
	public static String format(Date date,String pattern){
		return getDateFormat(pattern).format(null==date?new Date():date);
	}
	
	/**
	 * 日期格式化，字符串根据规则转换为日期
	 * @param date 日期字符串
	 * @param pattern 规则
	 * @return
	 */
	public static Date parse(String date,String pattern){
		Date retDate = null;
		try {
			retDate = getDateFormat(pattern).parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return retDate;
	}
	
	/**
	 * 获取日期字符串，yyyy-MM-dd HH:mm:ss
	 * @param date 日期
	 * @return
	 */
	public static String date2yyyyMMddHHmmssStr(Date date){
		return format(date==null?new Date():date, Const.DATE_YYYYMMDDHHMMSS_STR);
	}
	
	/**
	 * 获取当前时间的字符串，yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String now2yyyyMMddHHmmssStr(){
		return date2yyyyMMddHHmmssStr(null);
	}
	
	/**
	 * 获取日期字符串，yyyy-MM-dd
	 * @param date 日期
	 * @return
	 */
	public static String date2yyyyMMddStr(Date date){
		return format(date==null?new Date():date, Const.DATE_YYYYMMDD_STR);
	}
	
	/**
	 * 获取当前时间的字符串，yyyy-MM-dd
	 * @return
	 */
	public static String now2yyyyMMddStr(){
		return date2yyyyMMddStr(null);
	}
	
	/**
	 * 获取日期字符串，HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String date2HHmmssStr(Date date){
		return format(date==null?new Date():date, Const.DATE_HHMMSS_STR);
	}
	
	/**
	 * 获取当前时间的字符串，HH:mm:ss
	 * @return
	 */
	public static String now2HHmmssStr(){
		return date2HHmmssStr(null);
	}
	
	/**
	 * yyyy-MM-dd HH:mm:ss格式的字符串转为日期
	 * @param dateStr 日期字符串
	 * @return 日期
	 */
	public static Date str2yyyyMMddHHmmssDate(String dateStr){
		return parse(dateStr, Const.DATE_YYYYMMDDHHMMSS_STR);
	}
	
	/**
	 * yyyy-MM-dd格式的字符串转为日期
	 * @param dateStr 日期字符串
	 * @return 日期
	 */
	public static Date str2yyyyMMddDate(String dateStr){
		return parse(dateStr,Const.DATE_YYYYMMDD_STR);
	}
	
}
