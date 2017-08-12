package com.coderising.ood.ocp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String getCurrentDateAsString() {
		
		return SimpleDateFormat.getInstance().format(new Date());
	}

}
