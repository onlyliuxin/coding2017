package me.lzb.jvm.cmd;


import me.lzb.jvm.clz.ClassFile;
import me.lzb.jvm.constant.ConstantPool;
import me.lzb.jvm.constant.FieldRefInfo;
import me.lzb.jvm.engine.ExecutionResult;
import me.lzb.jvm.engine.JavaObject;
import me.lzb.jvm.engine.StackFrame;

public class GetFieldCmd extends TwoOperandCmd {

    public GetFieldCmd(ClassFile clzFile, String opCode) {
        super(clzFile, opCode);
    }

    @Override
    public String toString(ConstantPool pool) {

        return super.getOperandAsField(pool);
    }

    @Override
    public void execute(StackFrame frame, ExecutionResult result) {
        FieldRefInfo fieldRef = (FieldRefInfo)this.getConstantInfo(this.getIndex());
        String fieldName = fieldRef.getFieldName();
        JavaObject jo = frame.getOprandStack().pop();
        JavaObject fieldValue = jo.getFieldValue(fieldName);

        frame.getOprandStack().push(fieldValue);
    }


}
