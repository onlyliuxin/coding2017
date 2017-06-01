package com.coding.test;

import com.coding.jvm.clz.ClassFile;
import com.coding.jvm.loader.ClassFileLoader;
import com.coding.util.IOUtils;

public class Test {
	
	public static void main(String[] args) {
		Stu obj = new Tear();
		obj.say();
		obj.test();
	}
	
	
	private static String byteToHexString(byte[] codes ){
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

	

}
