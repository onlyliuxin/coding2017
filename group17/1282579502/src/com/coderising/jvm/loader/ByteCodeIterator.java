package com.coderising.jvm.loader;

import com.coderising.jvm.util.Util;

public class ByteCodeIterator {
	
	byte[] source = null;
	int currentPos = 0;
	
	public ByteCodeIterator(byte[] source){
		this.source = source;
	}
	
	public byte getNextByte(){
		if(currentPos > source.length -1){
			throw new IndexOutOfBoundsException();
		}
		return getByteAt(currentPos++);
	}
	
	public byte[] getNextNBytes(int n){
		byte[] retArray = new byte[n];
		for(int i = 0; i<n; i++){
			retArray[i] = getNextByte();
		}
		return retArray;
	}
	
	public byte getByteAt(int n){
		if(n>source.length-1){
			throw new IndexOutOfBoundsException();
		}
		return source[n];
	}

	
	public String getNextHexString(){
		byte b1 = getNextByte();
		int i1 = b1 & 0xFF;
		String strVal = Integer.toHexString(i1);
		if(strVal.length() < 2){
			strVal = "0".concat(strVal);
		}
		return strVal;
	}
	
	public String getNextNHexString(int n){
		StringBuilder sb = new StringBuilder();
		while(n-->0){
			sb.append(getNextHexString());
		}
		
		return sb.toString();
	}
	
	public String peekNextNHex(int n){
		String val =  getNextNHexString(n);
		backOffNBytes(n);
		return val;
	}
	
	public void backOffNBytes(int n){
		if(n < currentPos){
		currentPos -=n;
		}
		else{
			System.err.println("Don't have enough bytes.");
		}
	}
	
	public int getNextNBytesInteger(int n){
		byte[] barray = getNextNBytes(n);
		return Util.byteToInt(barray);
	}
	
	
	public static void printByteInNumber(byte b){
		int tmp = b;
		System.out.println(Integer.toBinaryString(tmp));
		int tmp1 = tmp & 0xFF;
		System.out.println(Integer.toBinaryString(tmp1));
		System.out.println(Integer.toHexString(tmp1));
	}
}
