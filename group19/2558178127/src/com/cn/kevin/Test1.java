package com.cn.kevin;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
public class Test1 {
@Test
	public void res(){
		System.out.println("111");
		String regext = "(回Q关闭通知)";
		String con = "【回Q关闭通知，回复按标准资费】";
		System.out.println(regexPattern(regext,con));
	}

public static boolean regexPattern(String regex, String content) {
	boolean isMatch = false;
	
	try{
		Pattern p = Pattern.compile(regex + ".*");
		Matcher m = p.matcher(content);
		if (m.find()) {
			isMatch = true;
		}
	}catch(Exception e){//NOSONAR
//		logger.info("marchet err: regex:{}",regex);
		return isMatch;
	}
	return isMatch;
}
}
