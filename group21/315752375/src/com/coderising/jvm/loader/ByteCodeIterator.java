package com.coderising.jvm.loader;

import java.util.Arrays;

import com.coderising.jvm.util.Util;

public class ByteCodeIterator {
	private byte[] codes;
	private int pos;

	public ByteCodeIterator(byte[] codes) {
		this.codes = codes;
		this.pos = 0;
	}

	public byte[] getBytes(int len) {
		if (pos + len > codes.length) {
			throw new ArrayIndexOutOfBoundsException();
		}
		byte[] answer = Arrays.copyOfRange(codes, pos, pos + len);
		pos += len;
		return answer;
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

	public String nextU4ToHexString() {
		return Util.byteToHexString(new byte[] {codes[pos++],codes[pos++],codes[pos++],codes[pos++]});
	}

	public String nextUxToHexString(int len) {
		byte[] tmp=getBytes(len);
		return Util.byteToHexString(tmp);
	}
	public void back(int len){
		if(pos-len<0)throw new ArrayIndexOutOfBoundsException();
		pos-=len;
	}

}
