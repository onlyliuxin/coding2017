package com.coderising.jvm.loader;

import com.coderising.jvm.util.Util;

public class ByteCodeIterator {

	private byte[] codes = null;
	private int currPos = 0;

	public ByteCodeIterator(byte[] codes) {
		this.codes = codes;
	}

	public String nextU4toHexString() {
		byte[] u4 = new byte[] { codes[currPos++], codes[currPos++], codes[currPos++], codes[currPos++] };
		return Util.byteToHexString(u4);
	}

	public int nextU1toInt() {
		byte[] u1 = new byte[] { codes[currPos++] };
		return Util.byteToInt(u1);

	}

	public int nextU2toInt() {
		byte[] u2 = new byte[] { codes[currPos++], codes[currPos++] };
		return Util.byteToInt(u2);
	}
	
	public byte[] nextNbytesToHexString(int length) {
		byte[] bytes = new byte[length];
		int len = currPos + length;
		for(int j = 0; currPos < len; j++) {
			bytes[j] = codes[currPos++];
		}
		
		return bytes;
	}

}
