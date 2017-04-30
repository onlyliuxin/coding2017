package task8.jvm.cmd;

import task8.jvm.clz.ClassFile;
import task8.jvm.constant.ConstantInfo;
import task8.jvm.constant.ConstantPool;
import task8.jvm.constant.StringInfo;
import task8.jvm.engine.ExecutionResult;
import task8.jvm.engine.Heap;
import task8.jvm.engine.JavaObject;
import task8.jvm.engine.StackFrame;

public class LdcCmd extends OneOperandCmd {

    public LdcCmd(ClassFile clzFile, String opCode) {
        super(clzFile, opCode);
    }

    @Override
    public String toString(ConstantPool pool) {

        ConstantInfo info = (ConstantInfo) pool.getConstantInfo(this.getOperand());

        String value = "TBD";
        if (info instanceof StringInfo) {
            StringInfo strInfo = (StringInfo) info;
            value = strInfo.toString();
        }

        return this.getOffset() + ":" + this.getOpCode() + " " + this.getReadableCodeText() + " " + value;

    }

    @Override
    public void execute(StackFrame frame, ExecutionResult result) {
        ConstantInfo info = this.getConstantPool().getConstantInfo(this.getOperand());
        if (info instanceof StringInfo) {
            JavaObject object = Heap.getInstance().newString(info.toString());
            frame.getOprandStack().push(object);
        } else {
            // ...
        }
    }

}
