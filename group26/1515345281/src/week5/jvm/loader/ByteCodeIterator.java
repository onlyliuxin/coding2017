package week5.jvm.loader;

import java.util.Arrays;

import week5.jvm.util.Util;

public class ByteCodeIterator {

	byte[] codes;
	int pos = 0;

	public ByteCodeIterator(byte[] codes) {
		this.codes = codes;
	}

	public byte[] getBytes(int len) {
		if (pos + len > codes.length) {
			throw new IndexOutOfBoundsException();
		}

		byte[] data = Arrays.copyOfRange(codes, pos, pos + len);
		pos += len;
		return data;
	}

	public int nextU1ToInt() {
		return Util.byteToInt(new byte[] { codes[pos++] });
	}

	public int nextU2ToInt() {
		return Util.byteToInt(new byte[] { codes[pos++], codes[pos++] });
	}

	public int nextU4ToInt() {
		return Util.byteToInt(new byte[] { codes[pos++], codes[pos++],
				codes[pos++], codes[pos++] });
	}
	
	public String nextU4ToString(){
		return  Util.byteToHexString(new byte[]{ codes[pos++],codes[pos++],codes[pos++],codes[pos++]});
	}
	
	public String nextUxToString(int len) {
		byte[] tmp = new byte[len];

		for (int i = 0; i < len; i++) {
			tmp[i] = codes[pos++];
		}
		return Util.byteToHexString(tmp).toLowerCase();
	}

	public void back(int len) {
		this.pos -= len;
	}
}
