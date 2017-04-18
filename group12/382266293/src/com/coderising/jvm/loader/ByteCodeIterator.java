package com.coderising.jvm.loader;

import com.coderising.jvm.util.Util;

public class ByteCodeIterator {

	private byte[] codes = null;
	private int currPos = 0;

	public ByteCodeIterator(byte[] codes) {
		this.codes = codes;
	}

	public String nextU4ToHexString() {
		byte[] u4 = new byte[] { codes[currPos++], codes[currPos++], codes[currPos++], codes[currPos++] };
		return Util.byteToHexString(u4);
	}

	public int nextU1ToInt() {
		byte[] u1 = new byte[] { codes[currPos++] };
		return Util.byteToInt(u1);

	}

	public int nextU2ToInt() {
		byte[] u2 = new byte[] { codes[currPos++], codes[currPos++] };
		return Util.byteToInt(u2);
	}
	
	public int nextU4ToInt() {
		byte[] u4 = new byte[] { codes[currPos++], codes[currPos++], codes[currPos++], codes[currPos++] };
		return Util.byteToInt(u4);
	}
	
	
	public byte[] nextNbytesToHexString(int length) {
		byte[] bytes = new byte[length];
		int len = currPos + length;
		for(int j = 0; currPos < len; j++) {
			bytes[j] = codes[currPos++];
		}
		
		return bytes;
	}
	
	public String nextUxToHexString(int length) {
		byte[] codes = nextNbytesToHexString(length);
		return byteToHexString(codes);
	}
	
	public String byteToHexString(byte[] codes) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < codes.length; i++) {
			byte b = codes[i];
			int value = b & 0xFF;
			String strHex = Integer.toHexString(value);
			if (strHex.length() < 2) {
				strHex = "0" + strHex;
			}
			buffer.append(strHex);
		}
		return buffer.toString();
	}

	public void back(int i) {
		currPos = currPos - 2;
	}

}
