package com.coderising.jvm.loader;

public class ByteCodeIterator {
	private byte[] bytes;
	private int pos = 0;
	public ByteCodeIterator(byte[] byteCodes){
		this.bytes = byteCodes;
	}
	
	public boolean hasNext(){
		return this.pos < bytes.length-1;
	}
	
	public byte next(){
		byte b = bytes[pos];
		pos ++;
		return b;
	}
	
	public byte[] getBytes(int len){
		if(pos+len>bytes.length){
			throw new RuntimeException("Index out of bounds:"+(pos+len));
		}
		byte[] bytes = new byte[len];
		int idx = 0;
		while(hasNext() && idx<len){
			bytes[idx] = next();
			idx++;
		}
		return bytes;
	}
	
	public String getBytesAsString(int len){
		return byteToString(getBytes(len));
	}
	
	public String getBytesAsHexString(int len){
		return byteToHexString(getBytes(len));
	}
	
	public int nextU1AsInt() {
		return byteToInt(getBytes(1));
	}
	
	public int nextU2AsInt(){
		return byteToInt(getBytes(2));
	}
	public int nextU4AsInt(){
		return byteToInt(getBytes(4));
	}
	
	public String nextU1AsString() {
		return byteToString(getBytes(1));
	}
	
	public String nextU2AsString(){
		return byteToString(getBytes(2));
	}
	public String nextU4AsString(){
		return byteToString(getBytes(4));
	}
	
	//Util Methods for type conversion
	private int byteToInt(byte[] codes){
    	String s1 = byteToHexString(codes);
    	return Integer.valueOf(s1, 16).intValue();
    }
    
	private String byteToString(byte[] codes){
    	String s1 = byteToHexString(codes);
    	return new String(codes);
    }
    
	private String byteToHexString(byte[] codes ){
		StringBuffer buffer = new StringBuffer();
		for(int i=0;i<codes.length;i++){
			byte b = codes[i];
			int value = b & 0xFF;
			String strHex = Integer.toHexString(value);
			if(strHex.length()< 2){
				strHex = "0" + strHex;
			}		
			buffer.append(strHex);
		}
		return buffer.toString();
	}
}
