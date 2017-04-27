package com.github.orajavac.coding2017.jvm.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
	public static int byteToInt(byte[] codes){
    	String s1 = byteToHexString(codes);
    	return Integer.valueOf(s1, 16).intValue();
    }
    
    
    
	public static String byteToHexString(byte[] codes ){
		StringBuffer buffer = new StringBuffer();
		for(int i=0;i<codes.length;i++){
			byte b = codes[i];
			int value = b & 0xFF;
			String strHex = Integer.toHexString(value);
			if(strHex.length()< 2){
				strHex = "0" + strHex;
			}		
			buffer.append(strHex);
		}
		return buffer.toString();
	}
	
	public static Object[] parseOperNumToArray(String str){
		String regEx = "\\d+|(\\*)|(\\+)|(-)|(/)";
		Pattern pat = Pattern.compile(regEx);
        Matcher mat = pat.matcher(str);
        List<String> l = new ArrayList<String>();
        while(mat.find()){
           l.add(mat.group());
        }
        /*Object[] obj = l.toArray();
        for (int i=0;i<obj.length;i++){
			System.out.print(obj[i]+" ");
		}*/
		return l.toArray();
	}
	
	public static String[] parseOperatorToArray(String str){
		String[] array = str.split(" ");
		return array;
	}
}
