package week8.jvm.cmd;

import week8.jvm.clz.ClassFile;
import week8.jvm.constant.MethodRefInfo;
import week8.jvm.engine.ExecutorResult;
import week8.jvm.engine.JavaObject;
import week8.jvm.engine.MethodArea;
import week8.jvm.engine.StackFrame;
import week8.jvm.method.Method;

public class InvokeVirtualCmd extends TwoOperandCmd{

	protected InvokeVirtualCmd(ClassFile clzFile, String opCode) {
		super(clzFile, opCode);
	}

	@Override
	public String toString(){
		return super.getOperandAsMethod();
	}

	@Override
	public void execute(StackFrame stackFrame, ExecutorResult result) {
		
	    //先得到对该方法的描述
		MethodRefInfo methodRefInfo=(MethodRefInfo) this.getConstantInfo(getIndex());
		
		String className=methodRefInfo.getClassName();
	    String methodName=methodRefInfo.getMethodName();
	    		
	    //没有实现System.out.println方法，  所以也不用建立新的栈帧， 直接调用Java的方法， 打印出来即可。
	    if(isSystemOutPrintlnMethod(className,methodName)){
	    	JavaObject jo=stackFrame.getOperandStack().pop();
	    	System.err.println("-------------"+jo+"--------------");
	    	
	    	// 这里就是那个out对象， 因为是个假的，直接pop出来
	    	stackFrame.getOperandStack().pop();
	    	return ;
	    }
	    
	  //注意：多态， 这才是真正的对象, 先从该对象的class 中去找对应的方法，找不到的话再去找父类的方法
	   JavaObject jo=stackFrame.getOperandStack().peek();
	   
	   String currentClassName=jo.getClassName();
	   MethodArea methodArea=MethodArea.getInstance();
	   
	   Method method=null;
	   
	   while(currentClassName != null){
		   
		  ClassFile clzFile=methodArea.findClassFile(currentClassName);
		  
		  method=clzFile.getMethod(methodRefInfo.getMethodName(), methodRefInfo.getParamAndReturnType());
		  
		  if(method != null){
			  break;
		  }else{
			  currentClassName=clzFile.getSuperClassName();
		  }
	   }
	   
		if(method == null){
			throw new RuntimeException("Can't find method for :" + methodRefInfo.toString());
		}
	   
	   result.setNextAction(ExecutorResult.PAUSE_AND_RUN_NEW_FRAME);
	   result.setNextMethod(method);
	}

	private boolean isSystemOutPrintlnMethod(String className, String methodName) {
		
		return "java/io/PrintStream".equals(className)
				&& "println".equals(methodName);
	}
}
