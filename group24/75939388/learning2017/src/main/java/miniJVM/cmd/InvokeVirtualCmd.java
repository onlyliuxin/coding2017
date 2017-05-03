package miniJVM.cmd;

import miniJVM.clz.ClassFile;
import miniJVM.constant.ConstantPool;
import miniJVM.constant.MethodRefInfo;
import miniJVM.engine.ExecutionResult;
import miniJVM.engine.JavaObject;
import miniJVM.engine.MethodArea;
import miniJVM.engine.StackFrame;
import miniJVM.method.Method;

public class InvokeVirtualCmd extends TwoOperandCmd {

	public InvokeVirtualCmd(ClassFile clzFile, String opCode) {
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

		//不需要实现println方法
		if("java/io/PrintStream".equals(className)
				&& "println".equals(methodName)){
			JavaObject jo = frame.getOperandStack().pop();
			String value = jo.toString();
			System.err.println("-------------------"+value+"----------------");

			//假的对象，直接弹出无视掉
			frame.getOperandStack().pop();
			return;
		}

		//不停的至父类寻找同名方法，即实现多态
		JavaObject jo = frame.getOperandStack().peek();
		MethodArea ma = MethodArea.getInstance();
		Method m = null;
		String currentClassName = jo.getClassName();
		while(currentClassName != null){
			ClassFile currentClassFile = ma.findClassFile(currentClassName);
			m = currentClassFile.getMethod(methodRefInfo.getMethodName(), methodRefInfo.getParamAndReturnType());
			if(m != null){
				break;
			} else{
				currentClassName = currentClassFile.getSuperClassName();
			}
		}
		if(m == null){
			throw new RuntimeException("no method find :" + methodRefInfo.toString());
		}
		result.setNextAction(ExecutionResult.PAUSE_AND_RUN_NEW_FRAME);
		result.setNextMethod(m);
	}

}
