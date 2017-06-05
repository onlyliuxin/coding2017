package me.lzb.jvm.cmd;

import me.lzb.jvm.clz.ClassFile;
import me.lzb.jvm.constant.ClassInfo;
import me.lzb.jvm.constant.FieldRefInfo;
import me.lzb.jvm.constant.NameAndTypeInfo;
import me.lzb.jvm.engine.ExecutionResult;
import me.lzb.jvm.engine.JavaObject;
import me.lzb.jvm.engine.StackFrame;
import me.lzb.jvm.print.ExecutionVisitor;

/**
 * @author LZB
 */
public class PutFieldCmd extends TwoOperandCmd {

    public PutFieldCmd(ClassFile clzFile, String opCode) {
        super(clzFile, opCode);
    }

    public String toString() {
        return super.getOperandAsField();
    }

    @Override
    public void execute(StackFrame frame, ExecutionResult result) {
        FieldRefInfo fieldRef = (FieldRefInfo) this.getConstantInfo(this.getIndex());

        ClassInfo clzInfo = (ClassInfo) this.getConstantInfo(fieldRef.getClassInfoIndex());
        NameAndTypeInfo nameTypeInfo = (NameAndTypeInfo) this.getConstantInfo(fieldRef.getNameAndTypeIndex());
        // for example : name
        String fieldName = nameTypeInfo.getName();
        // for example : Ljava/lang/String :
        // TODO 这里不再检查类型
        String fieldType = nameTypeInfo.getTypeInfo();

        JavaObject fieldValue = frame.getOprandStack().pop();
        JavaObject objectRef = frame.getOprandStack().pop();

        objectRef.setFieldValue(fieldName, fieldValue);
    }

    @Override
    public void printExecute(ExecutionVisitor visitor) {
        visitor.visitPutFieldCmd(this);
    }


}
