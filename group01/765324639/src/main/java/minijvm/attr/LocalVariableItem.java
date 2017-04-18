package minijvm.attr;

public class LocalVariableItem {
	private int startPC;
	private int length;
	private int nameIndex;
	private int descIndex;
	private int index;
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
}
