package com.coding.test;

import com.coding.jvm.clz.ClassFile;
import com.coding.jvm.loader.ClassFileLoader;
import com.coding.util.IOUtils;

public class Test {
	
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

	public static void main(String[] args) {
		String filePath = System.getProperty("user.dir")+"/bin/EmployeeV1.class";
		ClassFileLoader loader = new ClassFileLoader();
		ClassFile clzFile = loader.loadFile(filePath);
		System.out.println(clzFile);
	}

}
