package com.coderising.jvm.cmd;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.constant.ConstantPool;

// bipush: 将单字节的常量值(-128 - 127) 推送至栈顶
public class BiPushCmd extends OneOperandCmd{
	
	protected BiPushCmd(ClassFile clzFile, String opCode) {
		super(clzFile, opCode);
	}

	@Override
	public String toString(ConstantPool pool) {
		return this.getOffset()+": "+ this.getOpCode()+" " + this.getReadableCodeText() + " " + this.getOperand();
	}

}
