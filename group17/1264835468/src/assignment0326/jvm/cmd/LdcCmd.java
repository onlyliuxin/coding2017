package assignment0326.jvm.cmd;


import assignment0326.jvm.clz.ClassFile;
import assignment0326.jvm.constant.ConstantInfo;
import assignment0326.jvm.constant.ConstantPool;
import assignment0326.jvm.constant.StringInfo;

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

}
