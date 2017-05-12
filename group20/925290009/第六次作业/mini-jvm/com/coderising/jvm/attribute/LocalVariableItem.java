package com.coderising.jvm.attribute;

public class LocalVariableItem{

	private int start;
	private int lenth;
	private int nameIndex;
	private int describeIndex;
	private int slotIndex;

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLenth() {
		return lenth;
	}

	public void setLenth(int lenth) {
		this.lenth = lenth;
	}

	public int getNameIndex() {
		return nameIndex;
	}

	public void setNameIndex(int nameIndex) {
		this.nameIndex = nameIndex;
	}

	public int getDescribeIndex() {
		return describeIndex;
	}

	public void setDescribeIndex(int describeIndex) {
		this.describeIndex = describeIndex;
	}

	public int getSlotIndex() {
		return slotIndex;
	}

	public void setSlotIndex(int slotIndex) {
		this.slotIndex = slotIndex;
	}

}
