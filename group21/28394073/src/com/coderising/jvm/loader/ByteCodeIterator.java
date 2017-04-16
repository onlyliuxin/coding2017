package com.coderising.jvm.loader;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import com.coderising.jvm.util.Util;

public class ByteCodeIterator {
	private int pos=0;
	private byte[] byteCode;
	
	public ByteCodeIterator(byte[] byteCode){
		this.byteCode = byteCode;
	}
	
	public int nextU1ToInt(){
		return Util.byteToInt(new byte[]{byteCode[pos++]});
	}
	
	public int nextU2ToInt(){
		return Util.byteToInt(new byte[]{byteCode[pos++],byteCode[pos++]});
	}
	
	public int nextU4ToInt(){
		return Util.byteToInt(new byte[]{byteCode[pos++],byteCode[pos++],byteCode[pos++],byteCode[pos++]});
	}
	
	public String nextU2ToHexString(){
		return Util.byteToHexString(new byte[]{byteCode[pos++],byteCode[pos++]});
	}
	
	public String nextU4ToHexString(){
		return Util.byteToHexString(new byte[]{byteCode[pos++],byteCode[pos++],byteCode[pos++],byteCode[pos++]});
	}
	
	public byte[] getBytes(int length){
		byte[] content = Arrays.copyOfRange(byteCode, pos, pos+length);
		return content;
	}
	
	public String nextUxToHexString(int len) {
		byte[] tmp = new byte[len];

		for (int i = 0; i < len; i++) {
			tmp[i] = byteCode[pos++];
		}
		return Util.byteToHexString(tmp).toLowerCase();

	}
	
	public String getBytesToString(int length){
		byte[] content = Arrays.copyOfRange(byteCode, pos, pos+length);
		pos = pos+length;
		String str_content = null;
		try {
			str_content = new String(content,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str_content;
	}


}
