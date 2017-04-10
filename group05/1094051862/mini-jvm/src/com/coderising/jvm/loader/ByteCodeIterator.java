package com.coderising.jvm.loader;

import java.util.Arrays;

import com.coderising.jvm.util.Util;

public class ByteCodeIterator {
	
	byte[] codes;
	int pos = 0;
	
	ByteCodeIterator(byte[] codes) {
		this.codes = codes;
	}
	
	public byte[] getBytes(int len) {
		if (pos + len >= codes.length) {
			throw new ArrayIndexOutOfBoundsException();
		}
		
		byte[] data = Arrays.copyOfRange(codes, pos, pos + len);
		pos += len;
		
		return data;
	}
	
	public int nextU1toInt() {
		return Util.byteToInt(new byte[] { codes[pos++] });
	}
	
	public int nextU2toInt() {
		return Util.byteToInt(new byte[] { codes[pos++], codes[pos++] });
	}
	
	public String nextU4toHexString() {
		return Util.byteToHexString(new byte[] { codes[pos++], codes[pos++], codes[pos++], codes[pos++] });
	}
	
}
