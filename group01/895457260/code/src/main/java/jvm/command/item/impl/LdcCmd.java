package jvm.command.item.impl;

import jvm.classfile.ClassFile;
import jvm.classfile.ConstantPool;
import jvm.classfile.constant.item.Constant;
import jvm.classfile.constant.item.impl.StringInfo;
import jvm.command.CommandIterator;
import jvm.command.item.OneOperandCmd;

public class LdcCmd extends OneOperandCmd {

    public LdcCmd(ClassFile clzFile, String opCode, CommandIterator iterator) {
        super(clzFile, opCode, iterator);
    }

    @Override
    public String toString(ConstantPool pool) {
        Constant info = pool.getConstantInfo(this.getOperand());
        String value = "TBD";
        if (info instanceof StringInfo) {
            StringInfo strInfo = (StringInfo) info;
            value = strInfo.toString();
        }
        return this.getOffset() + ":" + this.getOpCode() + " " + this.getReadableCodeText() + " " + value;
    }

}
