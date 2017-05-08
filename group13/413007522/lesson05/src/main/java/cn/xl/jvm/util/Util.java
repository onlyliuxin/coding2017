package cn.xl.jvm.util;

import java.math.BigDecimal;

public class Util {
	public static int byteToInt(byte[] codes){
		String s1 = byteToHexString(codes);
		return Integer.valueOf(s1, 16).intValue();
	}

	public static BigDecimal byteToFloat(byte[] codes){
		String s1 = byteToHexString(codes);
		int bits = Integer.valueOf(s1, 16).intValue();
		int s = ((bits >> 31) == 0) ? 1 : -1;
		int e = ((bits >> 23) & 0xff);
		int m =(e == 0) ?(bits & 0x7fffff) << 1 :(bits & 0x7fffff) | 0x800000;
		
		double f = Math.pow(2, e-150);
		
		BigDecimal bs = new BigDecimal(Double.toString(s));
		BigDecimal bm = new BigDecimal(Double.toString(m));
		BigDecimal bf = new BigDecimal(Double.toString(f));
		
		return bs.multiply(bm).multiply(bf);
		
		
		
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
}
