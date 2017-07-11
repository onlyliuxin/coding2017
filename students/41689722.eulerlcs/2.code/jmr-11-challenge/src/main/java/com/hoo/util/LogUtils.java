package com.hoo.util;

/**
 * <b>function:</b> 日志工具类
 * 
 * @author hoojo
 * @createDate 2011-9-21 下午05:21:27
 * @file LogUtils.java
 * @package com.hoo.util
 * @project MultiThreadDownLoad
 * @blog http://blog.csdn.net/IBM_hoojo
 * @email hoojo_@126.com
 * @version 1.0
 */
public abstract class LogUtils {

	public static void log(Object message) {
		System.err.println(message);
	}

	public static void log(String message) {
		System.err.println(message);
	}

	public static void log(int message) {
		System.err.println(message);
	}

	public static void info(Object message) {
		System.out.println(message);
	}

	public static void info(String message) {
		System.out.println(message);
	}

	public static void info(int message) {
		System.out.println(message);
	}
}