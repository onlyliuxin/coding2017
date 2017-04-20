package com.coderising.jvm.attr;

import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.loader.ByteCodeIterator;

public class LocalVariableItem {
	private int startPC;
	private int length;
	private int nameIndex;
	private int descIndex;
	private int index;
	private ConstantPool constPool;
	
	public LocalVariableItem(ConstantPool pool){
		this.constPool = pool;
	}
	
	public int getStartPC() {
		return startPC;
	}
	public void setStartPC(int startPC) {
		this.startPC = startPC;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getNameIndex() {
		return nameIndex;
	}
	public void setNameIndex(int nameIndex) {
		this.nameIndex = nameIndex;
	}
	public int getDescIndex() {
		return descIndex;
	}
	public void setDescIndex(int descIndex) {
		this.descIndex = descIndex;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	
	
	public static LocalVariableItem parse(ByteCodeIterator iter, ConstantPool pool){
		LocalVariableItem item = new LocalVariableItem(pool);
		int startPc = iter.getNextNBytesInteger(2);
		int length = iter.getNextNBytesInteger(2);
		int nameIndex = iter.getNextNBytesInteger(2);
		int descriptorIndex = iter.getNextNBytesInteger(2);
		int index = iter.getNextNBytesInteger(2);
		item.setStartPC(startPc);
		item.setLength(length);
		item.setNameIndex(nameIndex);
		item.setDescIndex(descriptorIndex);
		item.setIndex(index);
		
		return item;
	}
	@Override
	public String toString() {
		return "LocalVariableItem [startPC=" + startPC + ", length=" + length
				+ ", nameIndex=" + nameIndex + " val: " + constPool.getUTF8String(nameIndex) + ", descIndex=" + descIndex
				+ " val: " + constPool.getUTF8String(descIndex) + ", index=" + index  + "]";
	}
}
