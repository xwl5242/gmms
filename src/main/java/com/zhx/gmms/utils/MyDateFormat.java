package com.zhx.gmms.utils;

import java.text.DateFormat;

public class MyDateFormat {

	private String datePattern;
	private DateFormat dateFormat;
	
	public MyDateFormat(String pattern,DateFormat dateFormat){
		this.datePattern = pattern;
		this.dateFormat = dateFormat;
	}
	
	public String getDatePattern() {
		return datePattern;
	}
	public void setDatePattern(String datePattern) {
		this.datePattern = datePattern;
	}
	public DateFormat getDateFormat() {
		return dateFormat;
	}
	public void setDateFormat(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}
	
}
