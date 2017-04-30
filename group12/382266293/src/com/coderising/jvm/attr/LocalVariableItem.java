package com.coderising.jvm.attr;

public class LocalVariableItem {
	private int startPC;
	private int length;
	private int nameIndex;
	private int descIndex;
	private int index;
	public int getDescIndex() {
		return descIndex;
	}
	public int getIndex() {
		return index;
	}
	public int getLength() {
		return length;
	}
	public int getNameIndex() {
		return nameIndex;
	}
	public int getStartPC() {
		return startPC;
	}
	public void setDescIndex(int descIndex) {
		this.descIndex = descIndex;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public void setNameIndex(int nameIndex) {
		this.nameIndex = nameIndex;
	}
	public void setStartPC(int startPC) {
		this.startPC = startPC;
	}
}
