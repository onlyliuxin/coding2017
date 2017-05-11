package com.coderising.jvm.loader;

import java.util.Arrays;
import com.coderising.jvm.util.Util;



public class ByteCodeIterator {
	byte[] codes;
	int cursor=0; 
	public ByteCodeIterator(byte[] codes) {
		this.codes=codes;
	}
	public ByteCodeIterator(byte[] codes,int cursor) {
		this.codes=codes;
		this.cursor=cursor;
	}
	public boolean hasNext() {
	    return cursor != codes.length;
	}


	public int next() {
	    
	    int i = cursor;
	    if (i >= codes.length)
	        throw new ArrayIndexOutOfBoundsException();
	    cursor = i + 1;
	    return  codes[i]&0xFF;
	}
	public int nextU2ToInt() {
		
		return Util.byteToInt(new byte[]{codes[cursor++],codes[cursor++]});	
	}
	public int nextU4ToInt() {
		
		return Util.byteToInt(new byte[]{codes[cursor++],codes[cursor++],
				codes[cursor++],codes[cursor++]});
	}
	public String nextU4ToHexString() {
		return Util.byteToHexString(new byte[]{codes[cursor++],codes[cursor++],
				codes[cursor++],codes[cursor++]});
		
	}
	public byte[] getByte(int length) {
		int i=cursor;
		cursor=cursor+length;
		return Arrays.copyOfRange(codes,i, cursor);
	}

}
