package task8.jvm.cmd;

import task8.jvm.clz.ClassFile;
import task8.jvm.constant.ClassInfo;
import task8.jvm.constant.ConstantPool;
import task8.jvm.engine.ExecutionResult;
import task8.jvm.engine.Heap;
import task8.jvm.engine.JavaObject;
import task8.jvm.engine.StackFrame;

public class NewObjectCmd extends TwoOperandCmd {

    public NewObjectCmd(ClassFile clzFile, String opCode) {
        super(clzFile, opCode);
    }

    @Override
    public String toString(ConstantPool pool) {

        return super.getOperandAsClassInfo(pool);
    }

    @Override
    public void execute(StackFrame frame, ExecutionResult result) {
        ClassInfo classInfo = (ClassInfo) this.getConstantInfo(this.getIndex());
        String className = classInfo.getClassName();
        JavaObject object = Heap.getInstance().newObject(className);
        frame.getOprandStack().push(object);
    }


}
