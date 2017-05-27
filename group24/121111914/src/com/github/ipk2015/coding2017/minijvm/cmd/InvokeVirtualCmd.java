package com.github.ipk2015.coding2017.minijvm.cmd;

import com.github.ipk2015.coding2017.minijvm.clz.ClassFile;
import com.github.ipk2015.coding2017.minijvm.constant.ConstantPool;
import com.github.ipk2015.coding2017.minijvm.constant.MethodRefInfo;
import com.github.ipk2015.coding2017.minijvm.engine.ExecutionResult;
import com.github.ipk2015.coding2017.minijvm.engine.JavaObject;
import com.github.ipk2015.coding2017.minijvm.engine.MethodArea;
import com.github.ipk2015.coding2017.minijvm.engine.StackFrame;
import com.github.ipk2015.coding2017.minijvm.method.Method;

public class InvokeVirtualCmd extends TwoOperandCmd {

	public InvokeVirtualCmd(ClassFile clzFile,String opCode) {
		super(clzFile,opCode);
	}

	@Override
	public String toString(ConstantPool pool) {
		
		return super.getOperandAsMethod(pool);
	}

	@Override
	public void execute(StackFrame frame, ExecutionResult result) {
		MethodRefInfo methodRefInfo = (MethodRefInfo)this.getConstantInfo(this.getIndex());
		String className = methodRefInfo.getClassName();
		String methodName = methodRefInfo.getMethodName();
		// 我们没有实现System.out.println方法，  所以也不用建立新的栈帧， 直接调用Java的方法， 打印出来即可。
		if("java/io/PrintStream".equals(className) && "println".equals(methodName)){
			JavaObject jo = (JavaObject)frame.getOprandStack().pop();
			System.err.println("-------------------"+jo.toString()+"----------------");
			// 这里就是那个out对象， 因为是个假的，直接pop出来
			frame.getOprandStack().pop();
			return;
		}
		//注意：多态， 这才是真正的对象, 先从该对象的class 中去找对应的方法，找不到的话再去找父类的方法
		JavaObject javaObject = frame.getOprandStack().peek();
		MethodArea methodArea = MethodArea.getInstance();
		Method m = null;
		String currentClassName = javaObject.getClassName();
		while(null != currentClassName){
			ClassFile currentClassFile = methodArea.findClassFile(currentClassName);
			m = currentClassFile.getMethod(methodRefInfo.getMethodName(), methodRefInfo.getParamAndReturnType());
			if(null != m){
				break;
			}else{
				currentClassName = currentClassFile.getSuperClassName();
			}
		}
		if(null == m){
			throw new RuntimeException("Can't find method for :" + methodRefInfo.toString());
		}
		result.setNextAction(ExecutionResult.PAUSE_AND_RUN_NEW_FRAME);
		result.setNextMethod(m);
	}

	
	

}
