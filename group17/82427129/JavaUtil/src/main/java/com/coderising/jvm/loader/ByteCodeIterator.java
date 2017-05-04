package com.coderising.jvm.loader;

import com.coderising.jvm.util.Util;

public class ByteCodeIterator {
	private byte[] codes;
	private int pos = 0;

	public void back(int length) {
		if (pos - length < 0) {
			throw new RuntimeException("back length is too long:" + length
					+ ",pos:" + pos);
		}
		pos -= length;
	}

	public ByteCodeIterator(byte[] codes) {
		this.codes = codes;
	}

	public int nextU1toInt() {
		return Util.byteToInt(new byte[] { codes[pos++] });
	}

	public int nextU2toInt() {
		return Util.byteToInt(new byte[] { codes[pos++], codes[pos++] });
	}

	public int nextU4toInt() {
		return Util.byteToInt(new byte[] { codes[pos++], codes[pos++],
				codes[pos++], codes[pos++] });
	}

	public String nextUxtoHexString(int len) {
		byte[] bytes = new byte[len];
		for (int i = 0; i < len; i++) {
			bytes[i] = codes[pos++];
		}
		return Util.byteToHexString(bytes);
	}
	public String nextUxtoAscii(int len){
		byte[] bytes = new byte[len];
		for (int i = 0; i < len; i++) {
			bytes[i] = codes[pos++];
		}
		return Util.byteToAscii(bytes);
	}
}
