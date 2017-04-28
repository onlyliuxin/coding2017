package com.coderising.jvm.loader;

public class ByteCodeIterator {
	
	byte[] codes;
	int pos;// Œ¨ª§Œª÷√
	
	public ByteCodeIterator(byte[] codes){
		this.codes = codes;
	}
	
	public int nextU1toInt(){
		return (codes[pos++] & 0xFF);
	}
	
	public int nextU2toInt(){
		byte [] a = new byte[]{ codes[pos++], codes[pos++]};
		return (a[0]<<8) + a[1];
	}
	
	public int nextU4toInt(){
		byte [] a = new byte[]{ codes[pos++], codes[pos++], codes[pos++], codes[pos++]};
		return (a[0]<<24) + (a[1]<<16) + (a[2]<<8) + a[3];
	}
	
	public byte[] getByte(int length){
		byte []a = new byte[length];
		for(int i = 0; i < length; i++){
			a[i] = codes[pos++];
		}
		return a;
	}
	public String nextU4ToHexString(){
		StringBuffer buffer = new StringBuffer();
		for(int i = 0; i < 4; i++){
			int a = codes[pos++] & 0xFF;
			String strHex = Integer.toHexString(a);
			if(strHex.length() < 2){
				strHex = "0" + strHex;
			}
			buffer.append(strHex);
		}
		return buffer.toString();
	}

	public String nextUxToHexString(int len) {
		
		StringBuffer buffer = new StringBuffer();
		for(int i = 0; i < len; i++){
			int a = codes[pos++] & 0xFF;
			String strHex = Integer.toHexString(a);
			if(strHex.length() < 2){
				strHex = "0" + strHex;
			}
			buffer.append(strHex);
		}
		
		return buffer.toString();
		
	}
}
