package com.coderising.jvm.loader;

import com.coderising.jvm.util.Util;

public class ByteCodeIterator {
	
	private byte[] code = null;
	private int pos = 0;
	
	public ByteCodeIterator(byte[] code){
		this.code = code;
	}
	
	public int nextU1Int(){
		return Util.byteToInt(getByteBySize(1));
	}
	
	public int nextU2Int(){
		
		return Util.byteToInt(getByteBySize(2));
	}
	
	public String nextU4HexString(){
		return Util.byteToHexString(getByteBySize(4));
	}
	
	public float nextU4Float(){
		return Util.byteToFloat(getByteBySize(4));
	}
	
	public int nextU4Integer(){
		return Util.byteToInt(getByteBySize(4));
	}
	
	public byte[] getByteByLength(int length){
		if(pos+length>code.length){
			throw new RuntimeException("长度超出字节数组最大长度");
		}else{
			byte[] by = getByteBySize(length);
			return by;
		}
	}
	
	private byte[] getByteBySize(int size){
		byte[] by = new byte[size];
		for(int i=0;i<size;i++){
			by[i] = code[pos++];
		}
		return by;
	}

	public String nextUxToHexString(int len) {
		byte[] tmp = new byte[len];

		for (int i = 0; i < len; i++) {
			tmp[i] = code[pos++];
		}
		return Util.byteToHexString(tmp).toLowerCase();

	}
	public int getPos(){
		return pos;
	}
	public void back(int n) {
		this.pos -= n;
	}
}
