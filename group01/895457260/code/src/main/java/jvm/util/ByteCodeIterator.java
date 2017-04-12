package jvm.util;

import java.util.Arrays;

import jvm.util.ByteUtils;

public  class ByteCodeIterator {
	private byte[] codes;
	private int pos = 0;

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

		return ByteUtils.toInt(new byte[] { codes[pos++] }, 0, 1);
	}

	public int nextU2ToInt() {
		return ByteUtils.toInt(new byte[] { codes[pos++], codes[pos++] }, 0 ,2);
	}

	public int nextU4ToInt() {
		return ByteUtils.toInt(
				new byte[] { codes[pos++], codes[pos++], codes[pos++], codes[pos++] }, 0, 4);
	}

	public String nextU4ToHexString() {
		return ByteUtils.toHexString(
				new byte[] { codes[pos++], codes[pos++], codes[pos++], codes[pos++] }, 0, 4);
	}

	public String nextUxToHexString(int len) {
		byte[] tmp = new byte[len];

		for (int i = 0; i < len; i++) {
			tmp[i] = codes[pos++];
		}
		return ByteUtils.toHexString(tmp, 0, tmp.length).toLowerCase();

	}

	public void back(int n) {
		this.pos -= n;
	}
}
