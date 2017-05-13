package minijvm.cmd;

import minijvm.clz.ClassFile;
import minijvm.constant.FieldRefInfo;
import minijvm.engine.ExecutionResult;
import minijvm.engine.Heap;
import minijvm.engine.JavaObject;
import minijvm.engine.StackFrame;

public class GetStaticFieldCmd extends TwoOperandCmd {

	public GetStaticFieldCmd(ClassFile clzFile,String opCode) {
		super(clzFile,opCode);
		
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
        
        if ("java/lang/System".equals(className)
                && "out".equals(fieldName)
                && "Ljava/io/PrintStream;".equals(fieldType)) {
            JavaObject jo = Heap.getInstance().newObject(className);
            frame.getOprandStack().push(jo);
        }
    }

}
