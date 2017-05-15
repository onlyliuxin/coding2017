package com.github.HarryHook.coding2017.jvm.cmd;

import com.github.HarryHook.coding2017.jvm.clz.ClassFile;
import com.github.HarryHook.coding2017.jvm.constant.ConstantPool;
import com.github.HarryHook.coding2017.jvm.constant.FieldRefInfo;
import com.github.HarryHook.coding2017.jvm.engine.ExecutionResult;
import com.github.HarryHook.coding2017.jvm.engine.Heap;
import com.github.HarryHook.coding2017.jvm.engine.JavaObject;
import com.github.HarryHook.coding2017.jvm.engine.StackFrame;

public class GetStaticFieldCmd extends TwoOperandCmd {

    public GetStaticFieldCmd(ClassFile clzFile, String opCode) {
	super(clzFile, opCode);

    }

    @Override
    public String toString() {

	return super.getOperandAsField();
    }

    @Override
    public void execute(StackFrame frame, ExecutionResult result) {
	FieldRefInfo info = (FieldRefInfo) this.getConstantInfo(this.getIndex());
	String className = info.getClassName();
	String fieldName = info.getFieldName();
	String fieldType = info.getFieldType();

	if ("java/lang/System".equals(className) && "out".equals(fieldName)
		&& "Ljava/io/PrintStream;".equals(fieldType)) {
	    JavaObject jo = Heap.getInstance().newObject(className);
	    frame.getOprandStack().push(jo);
	}
	// TODO 处理非System.out的情况
    }

}