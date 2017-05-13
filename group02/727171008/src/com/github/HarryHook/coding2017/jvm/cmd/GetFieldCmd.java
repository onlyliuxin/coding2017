package com.github.HarryHook.coding2017.jvm.cmd;

import com.github.HarryHook.coding2017.jvm.clz.ClassFile;
import com.github.HarryHook.coding2017.jvm.constant.ConstantPool;
import com.github.HarryHook.coding2017.jvm.constant.FieldRefInfo;
import com.github.HarryHook.coding2017.jvm.engine.ExecutionResult;
import com.github.HarryHook.coding2017.jvm.engine.JavaObject;
import com.github.HarryHook.coding2017.jvm.engine.StackFrame;

public class GetFieldCmd extends TwoOperandCmd {

    public GetFieldCmd(ClassFile clzFile, String opCode) {
	super(clzFile, opCode);
    }

    @Override
    public String toString() {

	return super.getOperandAsField();
    }

    @Override
    public void execute(StackFrame frame, ExecutionResult result) {

	FieldRefInfo fieldRef = (FieldRefInfo) this.getConstantInfo(this.getIndex());
	String fieldName = fieldRef.getFieldName();
	JavaObject jo = frame.getOprandStack().pop();
	JavaObject fieldValue = jo.getFieldValue(fieldName);

	frame.getOprandStack().push(fieldValue);

    }

}
