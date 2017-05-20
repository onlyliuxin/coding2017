package cn.xl.jvm.loader;

import java.util.Arrays;

import cn.xl.jvm.util.Util;

public class ByteCodeIterator {

	private  byte[] bt;

	private  int size;

	public   int pos = 0;

	public ByteCodeIterator(byte[] b){

		if(b != null){
			this.bt = b;
			this.size = b.length;
		}
	}

	public boolean hasNext(){
		if(pos < size){
			return true;
		}else{
			return false;
		}
	}

	public Object next(){
		if(bt != null && pos < size){
			return bt[pos++];
		}else{
			return -1;
		}
	}

	public byte[] nextLenBytes(int len){

		if(pos+len >= size){
			throw new ArrayIndexOutOfBoundsException();
		}

		byte[] b = Arrays.copyOfRange(bt, pos, pos+len);
		pos = pos+len;

		return b;
	}

	public int nextU1ToInt(){

		return Util.byteToInt(new byte[]{bt[pos++]});
	}

	public int nextU2ToInt(){

		return Util.byteToInt(new byte[]{bt[pos++],bt[pos++]});
	}

	public int nextU4ToInt(){

		return Util.byteToInt(new byte[]{bt[pos++],bt[pos++],bt[pos++],bt[pos++]});
	}


	public String nextU4ToHexString(){

		return Util.byteToHexString(new byte[]{bt[pos++],bt[pos++],bt[pos++],bt[pos++]});
	}


	public static void main(String[] args){

		byte[] codes = new byte[]{1,2,3,4,5,6,7};

		byte[] b = Arrays.copyOfRange(codes, 0, 3);

		for(int i = 0; i < b.length; i++){
			System.out.println(b[i]);
		}
	}
}
