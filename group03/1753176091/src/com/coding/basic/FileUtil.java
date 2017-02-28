package com.coding.basic;



import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileUtil {
	
	public  static String byteToHexString(byte[] codes ){
		
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
	
	public  static byte[] readByteCodes(String clzFileName) throws IOException  {
		
		File f = new File(clzFileName);		
					
		BufferedInputStream bis =  new BufferedInputStream(new FileInputStream(f));
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		
		
		byte[] buffer = new byte[1024];
		int length = -1;		
		try {
			while((length = bis.read(buffer)) != -1){
				bos.write(buffer, 0, length);				
			}
		} catch (IOException e) {			
			e.printStackTrace();
			throw e;
		} finally{
			if(bis != null){
				bis.close();
			}
			if(bos !=null){
				bos.close();
			}
		}
		
		byte [] codes = bos.toByteArray();
		
		bis.close();
		
		return codes;
	}
	public static void main(String[] args) throws IOException{
		byte[] codes = FileUtil.readByteCodes("C:\\coderising\\workspace_ds\\Warmup\\bin\\FileUtil.class");;
		
		String hexCodes = FileUtil.byteToHexString(codes);
		System.out.println(hexCodes);
	}
}
