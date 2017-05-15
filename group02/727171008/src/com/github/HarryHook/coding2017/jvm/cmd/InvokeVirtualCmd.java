package com.github.HarryHook.coding2017.jvm.cmd;

import com.github.HarryHook.coding2017.jvm.clz.ClassFile;
import com.github.HarryHook.coding2017.jvm.constant.MethodRefInfo;
import com.github.HarryHook.coding2017.jvm.engine.ExecutionResult;
import com.github.HarryHook.coding2017.jvm.engine.JavaObject;
import com.github.HarryHook.coding2017.jvm.engine.MethodArea;
import com.github.HarryHook.coding2017.jvm.engine.StackFrame;
import com.github.HarryHook.coding2017.jvm.method.Method;

public class InvokeVirtualCmd extends TwoOperandCmd {

    public InvokeVirtualCmd(ClassFile clzFile, String opCode) {
	super(clzFile, opCode);
    }

    @Override
    public String toString() {

	return super.getOperandAsMethod();
    }

    private boolean isSystemOutPrintlnMethod(String className, String methodName) {
	return "java/io/PrintStream".equals(className) && "println".equals(methodName);
    }

    @Override
    public void execute(StackFrame frame, ExecutionResult result) {

	// 先得到对该方法的描述
	MethodRefInfo methodRefInfo = (MethodRefInfo) this.getConstantInfo(this.getIndex());

	String className = methodRefInfo.getClassName();
	String methodName = methodRefInfo.getMethodName();

	// 我们没有实现System.out.println方法， 所以也不用建立新的栈帧， 直接调用Java的方法， 打印出来即可。
	if (isSystemOutPrintlnMethod(className, methodName)) {
	    JavaObject jo = (JavaObject) frame.getOprandStack().pop();
	    String value = jo.toString();
	    System.err.println("-------------------" + value + "----------------");

	    // 这里就是那个out对象， 因为是个假的，直接pop出来
	    frame.getOprandStack().pop();

	    return;
	}

	// 注意：多态， 这才是真正的对象, 先从该对象的class 中去找对应的方法，找不到的话再去找父类的方法
	JavaObject jo = frame.getOprandStack().peek();

	MethodArea ma = MethodArea.getInstance();

	Method m = null;

	String currentClassName = jo.getClassName();

	while (currentClassName != null) {

	    ClassFile currentClassFile = ma.findClassFile(currentClassName);

	    m = currentClassFile.getMethod(methodRefInfo.getMethodName(), methodRefInfo.getParamAndReturnType());
	    if (m != null) {

		break;

	    } else {
		// 查找父类
		currentClassName = currentClassFile.getSuperClassName();
	    }
	}

	if (m == null) {
	    throw new RuntimeException("Can't find method for :" + methodRefInfo.toString());
	}

	result.setNextAction(ExecutionResult.PAUSE_AND_RUN_NEW_FRAME);

	result.setNextMethod(m);
    }

}