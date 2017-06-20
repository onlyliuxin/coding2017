package edu.coerscnu.ood.ocp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 获取当前时间
 * @author xujie
 *
 */
public class DateUtil {
	public static String getCurrentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}
}
