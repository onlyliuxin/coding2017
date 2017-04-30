package com.coderising.jvm.loader;

import com.coderising.jvm.exception.NoNextByteCodeException;
import com.coderising.jvm.util.Util;

public class ByteCodeIterator {

	private byte[] codes;
	private int cursor; 
	
	public static int numberOne = 1;
	public static int numberTwo = 2;
	public static int numberFour = 4;
	
	public ByteCodeIterator(byte[] codes){
		this.codes = codes;
	}
	
	public boolean hasNext(int len) {
		return (cursor+len) <= codes.length;
	}
	
	public byte[] next(int len){
		
		byte[] data = new byte[len];
		
		int j = 0;
		for (int i = cursor; j < len; i++) {
			data[j] = codes[i];
			j++;
		}
		cursor += len;
		
		return data;
	}
	
    public int nextU2ToInt(){
    	
    	if(!hasNext(numberTwo)){
    		try {
				throw new NoNextByteCodeException(numberTwo);
			} catch (NoNextByteCodeException e) {
				e.printStackTrace();
			}
    	}
    	return Util.byteToInt(next(numberTwo));
    }
    
    public int nextU4ToInt(){
    	
    	if(!hasNext(numberFour)){
    		try {
				throw new NoNextByteCodeException(numberFour);
			} catch (NoNextByteCodeException e) {
				e.printStackTrace();
			}
    	}
    	return Util.byteToInt(next(numberFour));
    }
    
    public int nextInt(){
    	
    	if(!hasNext(numberOne)){
    		try {
				throw new NoNextByteCodeException(numberOne);
			} catch (NoNextByteCodeException e) {
				e.printStackTrace();
			}
    	}
    	return Util.byteToInt(next(numberOne));
    }
    
    public String nextStr(int len){
    	
    	if(!hasNext(len)){
    		try {
				throw new NoNextByteCodeException(len);
			} catch (NoNextByteCodeException e) {
				e.printStackTrace();
			}
    	}
    	char[] arr = new char[len];
    	for (int i = 0; i < len; i++) {
    		arr[i] = (char)nextInt();
		}
    	return new String(arr);
    }
    
    public String nextUxToHexString(int len) {
		
    	if(!hasNext(len)){
    		try {
				throw new NoNextByteCodeException(len);
			} catch (NoNextByteCodeException e) {
				e.printStackTrace();
			}
    	}
		return Util.byteToHexString(next(len)).toLowerCase();

	}
    
    /**
     * 回退
     */
    public void back(int backLen){
    	
    	if( backLen<= this.cursor ){
    		this.cursor -= backLen;
    	}else{
    		this.cursor = 0;
    	}
    }
}
