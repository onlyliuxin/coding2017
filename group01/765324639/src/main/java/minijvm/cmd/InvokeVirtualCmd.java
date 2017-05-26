package minijvm.cmd;

import minijvm.clz.ClassFile;
import minijvm.constant.MethodRefInfo;
import minijvm.engine.ExecutionResult;
import minijvm.engine.JavaObject;
import minijvm.engine.MethodArea;
import minijvm.engine.StackFrame;
import minijvm.method.Method;

public class InvokeVirtualCmd extends TwoOperandCmd {

	public InvokeVirtualCmd(ClassFile clzFile,String opCode) {
		super(clzFile,opCode);
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
        MethodRefInfo methodRefInfo = (MethodRefInfo) this.getConstantInfo(this.getIndex());
        
        String className = methodRefInfo.getClassName();
        String methodName = methodRefInfo.getMethodName();
        
        if (isSystemOutPrintlnMethod(className, methodName)) {
            JavaObject jo = frame.getOprandStack().pop();
            String value = jo.toString();
            System.err.println("--------------------" + value + "-------------------");
            
            frame.getOprandStack().pop();
            return;
        }
        
        JavaObject jo = frame.getOprandStack().peek();
        MethodArea ma = MethodArea.getInstance();
        
        Method m = null;
        String currentClassName = jo.getClassName();
        while (currentClassName != null) {
            ClassFile cuClassFile = ma.findClassFile(currentClassName);
            m = cuClassFile.getMethod(methodRefInfo.getMethodName(), methodRefInfo.getParamAndReturnType());
            if (m != null) {
                break;
            } else {
                currentClassName = cuClassFile.getSuperClassName();
            }
        }
        
        if (m == null) {
            throw new RuntimeException("找不到对应的方法：" + methodRefInfo.toString());
        }
        result.setNextAction(ExecutionResult.PAUSE_AND_RUN_NEW_FRAME);
        result.setNextMethod(m);
    }

	
	

}
