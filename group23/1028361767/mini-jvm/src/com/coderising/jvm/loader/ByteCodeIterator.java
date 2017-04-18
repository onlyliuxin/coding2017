package com.coderising.jvm.loader;

import java.io.UnsupportedEncodingException;

public class ByteCodeIterator {
	
	private byte[] codes;
	private int pos;
	
	public ByteCodeIterator(byte[] codes){
		this.codes = codes;
	}
	
	public byte[] nextU2(){ return new byte[]{codes[pos++], codes[pos++]};}
	
	public byte[] nextU4(){ return new byte[]{codes[pos++], codes[pos++], codes[pos++], codes[pos++]};}
	
	public int nextU1ToInt() {
		return (codes[pos++] & 0xff);
	}
	
	public int nextU2ToInt() {
		return ((codes[pos++] & 0xff00) << 8) | (codes[pos++] & 0x00ff);
	}
	
	public int nextU4ToInt() {
		return ((codes[pos++] & 0xff000000) << 24) | ((codes[pos++] & 0x00ff0000) << 16)
				| ((codes[pos++] & 0x0000ff00) << 8) | (codes[pos++] & 0x000000ff) ;
	}
	
	public String nextUxToHexString(int len) {
		StringBuffer buffer = new StringBuffer();
   		for(int i=0;i<len;i++){
   			byte b = codes[pos++];
   			int value = b & 0xFF;
   			String strHex = Integer.toHexString(value);
   			if(strHex.length()< 2){
   				strHex = "0" + strHex;
   			}		
   			buffer.append(strHex);
   		}
   		return buffer.toString();
	}
	
	public String nextUxToString(int len){
		byte[] tmp = new byte[len];
		for(int i=0;i<len;i++){
			tmp[i] = codes[pos++];
		}
		try {
			return new String(tmp, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public void back(int len){
		this.pos-=len;
	}
	
	public static void main(String[] args) {
		byte a = 00;
		byte b = 52;
		int value = ((a & 0xff00) << 8) | (b & 0xff);
		System.out.println(value);
	}
	
}
