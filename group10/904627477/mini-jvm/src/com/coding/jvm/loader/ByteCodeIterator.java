package com.coding.jvm.loader;

import com.coding.util.Util;

public class ByteCodeIterator {
	
	private byte[] codes;
	private int index;
	
	
	public ByteCodeIterator(byte[] codes) {
		super();
		this.codes = codes;
		this.index = 0;
	}
	
	public void skip(int len){
		throwIndexOutOfBounds(index + len);
		this.index = index + len;
	}
	
	public void back(int len) {
		throwIndexOutOfBounds(index - len);
		index = index - len;
	}
	
	public boolean has(int len) {
		return index+len<codes.length;
	}
	
	public byte[] read(int length){
		if(codes==null||length<0){
			throw new IllegalArgumentException();
		}
		throwIndexOutOfBounds(index + length);
		byte[] result = new byte[length];
		for(int i=0;i<length;i++){
			result[i] = codes[index+i];
		}
		return result;
	}
	
	public int readU2ToInt(){
		return Util.getInt(read(2));
	}

	public byte[] nextUx(int length){
		byte[] result = read(length);
		index = index + length;
		return result;
	}
	
	public void throwIndexOutOfBounds(int length){
		if(length<0||length>codes.length){
			System.out.println("indexOutOfBoundsException");
			throw new IndexOutOfBoundsException();
		}
	}
	
	public String nextUxToString(int x){
		return new String(nextUx(x));
	}
	
	public int nextU1ToInt(){
		return Util.getInt(nextUx(1));
	}
	
	public int nextU2ToInt(){
		return Util.getInt(nextUx(2));
	}

	public int nextU4ToInt() {
		return Util.getInt(nextUx(4));
	}

	public String nextUxToHexString(int len) {
		return Util.getHexString(nextUx(len));
	}

}
