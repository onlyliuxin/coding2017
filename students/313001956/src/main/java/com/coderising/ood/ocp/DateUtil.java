package com.coderising.ood.ocp;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String getCurrentDateAsString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(new Date());
	}

}
