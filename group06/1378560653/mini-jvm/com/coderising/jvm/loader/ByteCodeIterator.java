package com.coderising.jvm.loader;

import java.util.Arrays;

import com.coderising.jvm.util.Util;

public class ByteCodeIterator {
	byte[] codes;
	int pos = 0;
	
	public ByteCodeIterator(byte[] codes) {
		this.codes = codes;
	}
	
	public String nextU4ToHexString() {
		return Util.byteToHexString(new byte[]{codes[pos++],codes[pos++],codes[pos++],codes[pos++]});
	}
	
	public int nextU1ToInt() {
		return Util.byteToInt(new byte[]{codes[pos++]});
	}
	
	public int nextU2ToInt() {
		return Util.byteToInt(new byte[]{codes[pos++],codes[pos++]});
	}
	
	public int nextU4ToInt() {
		return Util.byteToInt(new byte[]{codes[pos++],codes[pos++],codes[pos++],codes[pos++]});
	}

	public byte[] getBytes(int length) {
		if(pos + length >= codes.length){
			throw new ArrayIndexOutOfBoundsException();
		}
		byte[] data = Arrays.copyOfRange(codes, pos, pos+length);
		pos = pos + length;
		return data;
	}

	public String nextUxToHexString(int len) {
		byte[] temp = new byte[len];
		for(int i = 0; i < len; i++){
			temp[i] = codes[pos++];
		}
		return Util.byteToHexString(temp).toLowerCase();
	}
	
	public void back(int i) {
		pos -= i;
		
	}
}
