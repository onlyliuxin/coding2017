package com.github.HarryHook.coding2017.jvm.cmd;

import com.github.HarryHook.coding2017.jvm.clz.ClassFile;
import com.github.HarryHook.coding2017.jvm.constant.ClassInfo;
import com.github.HarryHook.coding2017.jvm.constant.ConstantPool;
import com.github.HarryHook.coding2017.jvm.engine.ExecutionResult;
import com.github.HarryHook.coding2017.jvm.engine.Heap;
import com.github.HarryHook.coding2017.jvm.engine.JavaObject;
import com.github.HarryHook.coding2017.jvm.engine.StackFrame;

public class NewObjectCmd extends TwoOperandCmd {

    public NewObjectCmd(ClassFile clzFile, String opCode) {
	super(clzFile, opCode);
    }

    @Override
    public String toString() {

	return super.getOperandAsClassInfo();
    }

    public void execute(StackFrame frame, ExecutionResult result) {

	int index = this.getIndex();

	ClassInfo info = (ClassInfo) this.getConstantInfo(index);

	String clzName = info.getClassName();

	// 在Java堆上创建一个实例
	JavaObject jo = Heap.getInstance().newObject(clzName);

	frame.getOprandStack().push(jo);

    }

}
