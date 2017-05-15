package com.github.HarryHook.coding2017.jvm.cmd;

import com.github.HarryHook.coding2017.jvm.clz.ClassFile;
import com.github.HarryHook.coding2017.jvm.constant.ClassInfo;
import com.github.HarryHook.coding2017.jvm.constant.ConstantPool;
import com.github.HarryHook.coding2017.jvm.constant.FieldRefInfo;
import com.github.HarryHook.coding2017.jvm.constant.NameAndTypeInfo;
import com.github.HarryHook.coding2017.jvm.engine.ExecutionResult;
import com.github.HarryHook.coding2017.jvm.engine.JavaObject;
import com.github.HarryHook.coding2017.jvm.engine.StackFrame;

public class PutFieldCmd extends TwoOperandCmd {

    public PutFieldCmd(ClassFile clzFile, String opCode) {
	super(clzFile, opCode);
    }

    @Override
    public String toString() {

	return super.getOperandAsField();
    }

    @Override
    public void execute(StackFrame frame, ExecutionResult result) {

	FieldRefInfo fieldRef = (FieldRefInfo) this.getConstantInfo(this.getIndex());

	ClassInfo clzInfo = (ClassInfo) this.getConstantInfo(fieldRef.getClassInfoIndex());
	NameAndTypeInfo nameTypeInfo = (NameAndTypeInfo) this.getConstantInfo(fieldRef.getNameAndTypeIndex());
	// for example : name
	String fieldName = nameTypeInfo.getName();
	// for example : Ljava/lang/String : 注意：我们不再检查类型
	String fieldType = nameTypeInfo.getTypeInfo();

	JavaObject fieldValue = frame.getOprandStack().pop();
	JavaObject objectRef = frame.getOprandStack().pop();

	objectRef.setFieldValue(fieldName, fieldValue);

    }

}