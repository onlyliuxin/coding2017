package week8.jvm.cmd;


import week8.jvm.clz.ClassFile;
import week8.jvm.constant.FieldRefInfo;
import week8.jvm.engine.ExecutorResult;
import week8.jvm.engine.Heap;
import week8.jvm.engine.JavaObject;
import week8.jvm.engine.StackFrame;

public class GetStaticFieldCmd extends TwoOperandCmd {

	protected GetStaticFieldCmd(ClassFile clzFile, String opCode) {
		super(clzFile, opCode);
	}

	@Override
	public String toString(){
		return super.getOperandAsField();
	}

	@Override
	public void execute(StackFrame stackFrame, ExecutorResult result) {
		
		FieldRefInfo fieldRef=(FieldRefInfo) this.getConstantInfo(this.getIndex());
	
		String className=fieldRef.getClassName();
		String fieldName=fieldRef.getFieldName();
		String fieldType=fieldRef.getFieldType();
		
		if("java/lang/System".equals(className) 
		    && "out".equals(fieldName)
		    && "Ljava/io/PrintStream;".equals(fieldType)){
			
			JavaObject jo=Heap.getInstance().newObject(className);
			stackFrame.getOperandStack().push(jo);
		}
		
		//TODO 处理非System.out的情况
	}
}