package me.lzb.jvm.cmd;


import me.lzb.jvm.clz.ClassFile;
import me.lzb.jvm.constant.FieldRefInfo;
import me.lzb.jvm.engine.ExecutionResult;
import me.lzb.jvm.engine.JavaObject;
import me.lzb.jvm.engine.StackFrame;
import me.lzb.jvm.print.ExecutionVisitor;

/**
 * @author LZB
 */
public class GetFieldCmd extends TwoOperandCmd {

    public GetFieldCmd(ClassFile clzFile, String opCode) {
        super(clzFile, opCode);
    }

    public String toString() {

        return super.getOperandAsField();
    }


    @Override
    public void execute(StackFrame frame, ExecutionResult result) {
        FieldRefInfo fieldRef = (FieldRefInfo) this.getConstantInfo(this.getIndex());
        String fieldName = fieldRef.getFieldName();
        JavaObject jo = frame.getOprandStack().pop();
        JavaObject fieldValue = jo.getFieldValue(fieldName);

        frame.getOprandStack().push(fieldValue);
    }

    @Override
    public void printExecute(ExecutionVisitor visitor) {
        visitor.visitGetFieldCmd(this);
    }


}
