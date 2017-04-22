package com.coderising.jvm.cmd;

public class TwoOperandCmd extends ByteCodeCommand {
	private int index;
	public void setIndex(int index) {
		this.index = index;
	}
	public int getIndex() {
		return this.index;
	}
	@Override
	public int getLength() {
		return 3;
	}

}
