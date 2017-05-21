package me.lzb.jvm.cmd;

import me.lzb.jvm.clz.ClassFile;
import me.lzb.jvm.constant.FieldRefInfo;
import me.lzb.jvm.engine.ExecutionResult;
import me.lzb.jvm.engine.Heap;
import me.lzb.jvm.engine.JavaObject;
import me.lzb.jvm.engine.StackFrame;
import me.lzb.jvm.print.ExecutionVisitor;

/**
 * @author LZB
 */
public class GetStaticFieldCmd extends TwoOperandCmd {

    public GetStaticFieldCmd(ClassFile clzFile, String opCode) {
        super(clzFile, opCode);

    }

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
        //TODO 处理非System.out的情况
    }

    @Override
    public void printExecute(ExecutionVisitor visitor) {
        visitor.visitGetStaticFieldCmd(this);
    }

}
