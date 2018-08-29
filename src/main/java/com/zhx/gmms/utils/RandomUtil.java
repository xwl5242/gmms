package com.zhx.gmms.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtil {

	/**
	 * 获取随机数list，list的每一项是数值
	 * 在minValue~maxValue之间获取number数个整数
	 * @param number 获取的个数
	 * @param minValue 范围，最小值
	 * @param maxValue 范围，最大值
	 */
	public static List<Integer> getRandomNumberList(int number,int minValue,int maxValue){
		List<Integer> randomList = new ArrayList<Integer>();
		while(randomList.size()<number){
			int ri = ThreadLocalRandom.current().nextInt(minValue,maxValue);
			if(!randomList.contains(ri)){
				randomList.add(ri);
			}
		}
		return randomList;
	}
	
	/**
	 * 获取随机数array，array的每一项是数值
	 * 在minValue~maxValue之间获取number数个整数
	 * @param number 获取的个数
	 * @param minValue 范围，最小值
	 * @param maxValue 范围，最大值
	 * @return
	 */
	public static Integer[] getRandomNumberArray(int number,int minValue,int maxValue){
		List<Integer> list = getRandomNumberList(number, minValue, maxValue);
		Integer[] target = new Integer[list.size()];
		return list.toArray(target);
	}
	
	/**
	 * 获取随机数list，list的每一项是数值
	 * 在0~maxValue之间获取number数个整数
	 * @param number 获取的个数
	 * @param maxValue 范围，最大值（最小值默认为0）
	 * @return
	 */
	public static List<Integer> getRandomNumberList(int number,int maxValue){
		return getRandomNumberList(number,0,maxValue);
	}
	
	/**
	 * 获取随机数array，array的每一项是数值
	 * 在0~maxValue之间获取number数个整数
	 * @param number 获取的个数
	 * @param maxValue 范围，最大值（最小值默认为0）
	 * @return
	 */
	public static Integer[] getRandomNumberArray(int number,int maxValue){
		return getRandomNumberArray(number, 0, maxValue);
	}
	
}
