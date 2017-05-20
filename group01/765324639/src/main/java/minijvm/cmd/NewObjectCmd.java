package minijvm.cmd;

import minijvm.clz.ClassFile;
import minijvm.constant.ClassInfo;
import minijvm.engine.ExecutionResult;
import minijvm.engine.Heap;
import minijvm.engine.JavaObject;
import minijvm.engine.StackFrame;

public class NewObjectCmd extends TwoOperandCmd{
	
	public NewObjectCmd(ClassFile clzFile, String opCode){
		super(clzFile,opCode);
	}

	@Override
	public String toString() {
		
		return super.getOperandAsClassInfo();
	}

    @Override
    public void execute(StackFrame frame, ExecutionResult result) {
        int index = this.getIndex();
        ClassInfo info = (ClassInfo) this.getConstantInfo(index);
        String className = info.getClassName();
        JavaObject jo = Heap.getInstance().newObject(className);
        frame.getOprandStack().push(jo);
        
    }

	
}
