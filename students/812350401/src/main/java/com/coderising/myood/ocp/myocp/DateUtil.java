package com.coderising.myood.ocp.myocp;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String getCurrentDateAsString() {

		return new SimpleDateFormat("dd-MM-yyyy").format(new Date());
	}

	public static void main(String[] args) {
		System.out.println(DateUtil.getCurrentDateAsString());
	}

}
