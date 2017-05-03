package miniJVM.cmd;


import miniJVM.clz.ClassFile;
import miniJVM.constant.ClassInfo;
import miniJVM.constant.ConstantPool;
import miniJVM.engine.ExecutionResult;
import miniJVM.engine.Heap;
import miniJVM.engine.StackFrame;

public class NewObjectCmd extends TwoOperandCmd{
	
	public NewObjectCmd(ClassFile clzFile, String opCode){
		super(clzFile,opCode);
	}

	@Override
	public String toString(ConstantPool pool) {
		
		return super.getOperandAsClassInfo(pool);
	}

	@Override
	public void execute(StackFrame frame, ExecutionResult result) {
		ClassInfo clazz = (ClassInfo) this.getConstantInfo(this.getIndex());
		String clazzName = clazz.getClassName();
		frame.getOperandStack().push(Heap.getInstance().newObject(clazzName));
	}
}
