package week6.jvm.util;

public class Util {

	/**
	 * 字节组转换为整数
	 * @param codes
	 * @return
	 */
	public static int byteToInt(byte[] codes){
		
	   String str=byteToHexString(codes);
	   return Integer.valueOf(str,16).intValue();
	}
	
	/**
	 * 字节组转换为16进制字符串
	 * @param codes
	 * @return
	 */
	public static String byteToHexString(byte[] codes){
		
		StringBuffer buffer=new StringBuffer();
		
		for(int i=0;i<codes.length;i++){
			byte b=codes[i];
			int value=b & 0xFF;
			String str=Integer.toHexString(value);
			
			if(str.length()<2){
				buffer.append("0");
			}
			buffer.append(str);
		}
		
		return buffer.toString();
	}
	
}
