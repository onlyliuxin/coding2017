package com.coderising.jvm.attr;

public class LocalVariableItem {

	private int startPc; 
	private int length; 
	private int nameIndex; 
	private int descIndex; 
	private int index;
	public int getStartPc() {
		return startPc;
	}
	public void setStartPc(int startPc) {
		this.startPc = startPc;
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
	public LocalVariableItem(int startPc, int length, int nameIndex, int descIndex, int index) {
		super();
		this.startPc = startPc;
		this.length = length;
		this.nameIndex = nameIndex;
		this.descIndex = descIndex;
		this.index = index;
	}
	public LocalVariableItem() {
		super();
		// TODO Auto-generated constructor stub
	} 
	
	
}
