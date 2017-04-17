package com.ace.jvm.loader;

import java.util.Arrays;

import com.ace.jvm.util.Util;

public class ByteCodeIterator {
	private int pos = 0;
	private byte[] codes;

	public ByteCodeIterator(byte[] codes) {
		this.codes = codes;
	}

	public byte[] getBytes(int len) {
		if (pos + len > codes.length) {
			throw new ArrayIndexOutOfBoundsException();
		}

		byte[] data = Arrays.copyOfRange(codes, pos, pos + len);
		pos = pos + len;
		return data;
	}

	public int nextU1ToInt() {
		return Util.byteToInt(new byte[] { codes[pos++] });
	}

	public int nextU2ToInt() {
		return Util.byteToInt(new byte[] { codes[pos++], codes[pos++] });
	}

	public int nextU4ToInt() {
		return Util.byteToInt(new byte[] { codes[pos++], codes[pos++], codes[pos++], codes[pos++] });
	}

	public String nextU4ToHexString() {
		return Util.byteToHexString(new byte[] { codes[pos++], codes[pos++], codes[pos++], codes[pos++] });
	}
}
