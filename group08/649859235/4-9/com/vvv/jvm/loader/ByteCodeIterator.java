package com.vvv.jvm.loader;

import java.util.Arrays;

import com.vvv.jvm.util.Util;

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
	
	public byte[] getBytes(int position, int len) {
		if (position + len >= codes.length) {
			throw new ArrayIndexOutOfBoundsException();
		}

		byte[] data = Arrays.copyOfRange(codes, position, position + len);
		return data;
	}
	
	public int pop8(){
		return Util.byteToInt(new byte[] { codes[pos++] });
	}
	
	public int pop16(){
		return Util.byteToInt(new byte[] { codes[pos++], codes[pos++] });
	}
	
	public int pop32(){
		return Util.byteToInt(new byte[] { codes[pos++], codes[pos++],codes[pos++], codes[pos++] });
	}
	
	
}
