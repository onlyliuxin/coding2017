package com.coderising.jvm.loader;

import com.coderising.jvm.utils.Util;

public class ByteCodeIterator {

	private byte[] codes;
	private int pos;
	
	public ByteCodeIterator(byte[] codes){
		this.codes = codes;
		pos = 0;
	}
	public int nextByteToInt(){
		if (pos < this.codes.length) {
			return Util.bytesToInt(new byte[]{codes[pos++]});
		}
		return -1;
	}
	public int next2BytesToInt(){
		if (pos < this.codes.length) {
			return Util.bytesToInt(new byte[]{codes[pos++],codes[pos++]});
		}
		return -1;
	}
	public String next2BytesToHexString(){
		if (pos < this.codes.length) {
			return Util.bytesToHexString(new byte[]{codes[pos++],codes[pos++]});
		}
		return null;
	}
	public String next4BytesToString(){
		if (pos < this.codes.length) {
			return Util.bytesToHexString(new byte[]{codes[pos++],codes[pos++],codes[pos++],codes[pos++]});
		}
		return null;
	}
	public byte[] getBytes(int length) {
		if ((pos + length) < this.codes.length) {
			byte[] by = new byte[length];
			for (int i = 0; i < by.length; i++) {
				by[i] = this.codes[pos++];
			}
			return by;
		}
		return null;
	}
}
