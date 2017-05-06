package com.coderising.jvm.clz;

public class ClassIndex {
	private int thisClassIndex;
	private int superClassIndex;

	public int getSuperClassIndex() {
		return superClassIndex;
	}

	public int getThisClassIndex() {
		return thisClassIndex;
	}

	public void setSuperClassIndex(int superClassIndex) {
		this.superClassIndex = superClassIndex;
	}

	public void setThisClassIndex(int thisClassIndex) {
		this.thisClassIndex = thisClassIndex;
	}
}