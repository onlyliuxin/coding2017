package coderising.jvm.loader;

import java.util.Arrays;

import coderising.jvm.util.Util;

public class ByteCodeIterator {
	private byte[] codes;
	private int pos =0;
	public ByteCodeIterator(byte[] codes){
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
	public String nextU4ToHexString() {		
		byte[] bys = new byte[]{codes[pos++],codes[pos++],codes[pos++],codes[pos++]};		
		return Util.byteToHexString(bys);
	}
	
	public int nextU1ToInt(){
		byte[] bys = new byte[]{codes[pos++]};
		return Util.byteToInt(bys);
	}
	public int nextU2ToInt(){
		byte[] bys = new byte[]{codes[pos++],codes[pos++]};
		return Util.byteToInt(bys);
	}
	public String nextUxToHexString(int len) {
		byte[] tmp = new byte[len];

		for (int i = 0; i < len; i++) {
			tmp[i] = codes[pos++];
		}
		return Util.byteToHexString(tmp).toLowerCase();

	}
	public void back(int n) {
		this.pos -= n;
	}
	public int nextU4ToInt() {
		return Util.byteToInt(new byte[] { codes[pos++], codes[pos++], codes[pos++], codes[pos++] });
	}
}
