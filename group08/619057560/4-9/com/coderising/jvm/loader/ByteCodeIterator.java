package com.coderising.jvm.loader;

public class ByteCodeIterator {
	
	private byte[] bytes;
	private int offset;
	private int length;
	
	public ByteCodeIterator(byte[] bytes) {
		this.bytes = bytes;
		this.offset = 0;
		this.length = bytes.length;
	}
	
	private int nextUNToInt(int N) {
		int result = 0;
		if (offset + N > length) {
			throw new IndexOutOfBoundsException();
		}
		
		for (int i = N-1; i >= 0; i--) {
			result |= (bytes[offset++]&0xFF)<<(8*i);
		}
		return result;
	}
	
	public int nextU1ToInt() {
		return nextUNToInt(1);
	}
	
	public int nextU2ToInt() {
		return nextUNToInt(2);
	}
	
	public int nextU4ToInt() {
		return nextUNToInt(4);
	}
	
	public String nextUTF8ToString(int nBytes) {
		if (offset + nBytes > length) {
			throw new IndexOutOfBoundsException();
		}
		
		StringBuilder sb = new StringBuilder();
		while (nBytes-- > 0) {
			sb.append((char)bytes[offset++]);
		}
		return sb.toString();
	}

}
