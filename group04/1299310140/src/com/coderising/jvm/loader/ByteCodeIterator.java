package com.coderising.jvm.loader;

import java.util.Arrays;
import com.coderising.jvm.util.Util;

public class ByteCodeIterator {
	
	private byte[] codes;
	private int pos;

	public ByteCodeIterator(byte[] codes) {
		this.codes = codes;
	}
	
	public byte[] getBytes(int length){
		byte[] data = Arrays.copyOfRange(codes, pos, pos + length);
		pos += length;
		return data;
	}
	
	public int nextU1ToInt(){
		return Util.byteToInt(new byte[]{codes[pos++]});
	}
	
	public int nextU2ToInt(){
		return Util.byteToInt(new byte[]{codes[pos++],codes[pos++]});
	}
	
	public int nextU4ToInt(){
		return Util.byteToInt(new byte[]{codes[pos++],codes[pos++],codes[pos++],codes[pos++]});
	}
	
	public String nextU1ToString(){
		return Util.byteToHexString(new byte[]{codes[pos++]});
	}
	
	public String nextU2ToString(){
		return Util.byteToHexString(new byte[]{codes[pos++],codes[pos++]});
	}
	
	public String nextU4ToString(){
		return Util.byteToHexString(new byte[]{codes[pos++],codes[pos++],codes[pos++],codes[pos++]});
	}
	
}
