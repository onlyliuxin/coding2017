package org.wsc.coderising.jvm.loader;

import java.util.ConcurrentModificationException;

import org.wsc.coderising.jvm.util.Util;

/**
 *
 * 字节迭代器
 * @author Administrator
 * @date 2017年4月12日下午1:16:39
 * @version v1.0
 *
 */
public class ByteCodeIterator{

	private byte[] codes;
	
	private int cursor=0;
	
	ByteCodeIterator(byte[] byteCodes) {
		super();
		this.codes = byteCodes;
	}

	public boolean hasNext() {
		return cursor != codes.length;
	}

	public byte next() {
		if(cursor > codes.length)
			throw new ConcurrentModificationException();
		return codes[cursor++];
	}
	
	public byte[] getBytes(int count) {
		byte[] bytes = new byte[count];
		for (int i = 0; i < count; i++) {
			bytes[i] = next();
		}
		return bytes;
	}
	
	public int nextU1ToInt(){
		return Util.byteToInt(getBytes(1));
	}
	
	public int nextU2ToInt(){
		return Util.byteToInt(getBytes(2));
	}
	
	public int nextU4ToInt(){
		return Util.byteToInt(getBytes(4));
	}
	
	public String nextU4ToHexString(){
		return Util.byteToHexString(getBytes(4));
	}
}
