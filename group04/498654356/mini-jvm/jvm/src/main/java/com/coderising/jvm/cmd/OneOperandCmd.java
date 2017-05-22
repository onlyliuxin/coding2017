package com.coderising.jvm.cmd;

public class OneOperandCmd extends ByteCodeCommand {
	private int operand;
	public int getOperand() {
		return operand;
	}
	public void setOperand(int operand) {
		this.operand = operand;
	}
	@Override
	public int getLength() {
		return 2;
	}
}
