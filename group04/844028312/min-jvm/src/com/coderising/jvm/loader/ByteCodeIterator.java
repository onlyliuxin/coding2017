package com.coderising.jvm.loader;

import com.coderising.jvm.util.Util;

public class ByteCodeIterator {
	private byte[] codes;
	private int pos;
	public ByteCodeIterator(byte[] codes){
		this.codes=codes;
		this.pos=0;
	}
	public byte[] getByte(int len){
		byte[] b=new byte[len];
		if(pos+len>codes.length){
			
		}
		for(int i=0;i<len;i++){
			b[i]=codes[pos++];
		}
		return b;
	}
	public void back(int num){
		pos=pos-num;
	}
	public String nextUxToHexString(int len){
		return Util.byteToHexString(getByte(len));
	}
	public int nextU1toInt(){
		return Util.byteToInt(new byte[]{codes[pos++]});
	}
	public int nextU2toInt(){
		return Util.byteToInt(new byte[]{codes[pos++],codes[pos++]});
	}
	public int nextU4toInt(){
		return Util.byteToInt(new byte[]{codes[pos++],codes[pos++],codes[pos++],codes[pos++]});
	}
	public String nextU4toString(){
		return Util.byteToHexString(new byte[]{codes[pos++],codes[pos++],codes[pos++],codes[pos++]});
	}
	public String nextU2toString(){
		return Util.byteToHexString(new byte[]{codes[pos++],codes[pos++]});
	}
}
