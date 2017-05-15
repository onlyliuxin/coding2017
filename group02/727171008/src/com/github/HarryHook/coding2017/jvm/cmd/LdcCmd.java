package com.github.HarryHook.coding2017.jvm.cmd;

import com.github.HarryHook.coding2017.jvm.clz.ClassFile;
import com.github.HarryHook.coding2017.jvm.constant.ConstantInfo;
import com.github.HarryHook.coding2017.jvm.constant.ConstantPool;
import com.github.HarryHook.coding2017.jvm.constant.StringInfo;
import com.github.HarryHook.coding2017.jvm.engine.ExecutionResult;
import com.github.HarryHook.coding2017.jvm.engine.Heap;
import com.github.HarryHook.coding2017.jvm.engine.JavaObject;
import com.github.HarryHook.coding2017.jvm.engine.StackFrame;

public class LdcCmd extends OneOperandCmd {

    public LdcCmd(ClassFile clzFile, String opCode) {
	super(clzFile, opCode);
    }

    @Override
    public String toString() {

	ConstantInfo info = (ConstantInfo) getConstantInfo(this.getOperand());

	String value = "TBD";
	if (info instanceof StringInfo) {
	    StringInfo strInfo = (StringInfo) info;
	    value = strInfo.toString();
	}

	return this.getOffset() + ":" + this.getOpCode() + " " + this.getReadableCodeText() + " " + value;

    }

    public void execute(StackFrame frame, ExecutionResult result) {

	ConstantPool pool = this.getConstantPool();
	ConstantInfo info = (ConstantInfo) pool.getConstantInfo(this.getOperand());

	if (info instanceof StringInfo) {
	    StringInfo strInfo = (StringInfo) info;
	    String value = strInfo.toString();
	    JavaObject jo = Heap.getInstance().newString(value);
	    frame.getOprandStack().push(jo);
	} else {
	    // TBD 处理其他类型
	    throw new RuntimeException("Only support StringInfo constant");
	}

    }

}