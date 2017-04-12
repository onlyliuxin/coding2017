package jvm.util;

import java.util.Arrays;

public  class ByteCodeIterator {
	private byte[] codes;
	private int pos = 0;

	public ByteCodeIterator(byte[] codes) {
		this.codes = codes;
	}

	public byte[] getBytes(int len) {
		if (pos + len > codes.length) {
			throw new ArrayIndexOutOfBoundsException();
		}

		byte[] data = Arrays.copyOfRange(codes, pos, pos + len);
		pos += len;
		return data;
	}

	public int currentIndex() {
		return pos;
	}

	public int nextU1ToInt() {
		return nextInt(1);
	}

	public int nextU2ToInt() {
		return nextInt(2);
	}

	public int nextU4ToInt() {
		return nextInt(4);
	}

	public String nextU4ToHexString() {
		return nextHexString(4);
	}

	public String nextHexString(int byteCount) {
		String result = ByteUtils.toHexString(codes, pos, byteCount).toLowerCase();
		pos += byteCount;
		return result;
	}

	public int nextInt(int byteCount) {
		int result = ByteUtils.toInt(codes, pos, byteCount);
		pos += byteCount;
		return result;
	}

	public void skip(int n) {
		this.pos += n;
	}

	public void back(int n) {
		this.pos -= n;
	}

	public void seekTo(int n) {
		this.pos = n;
	}

	public void reset() {
		this.pos = 0;
	}
}
