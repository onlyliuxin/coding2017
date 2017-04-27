package cmd;

import clz.ClassFile;
import constant.ConstantInfo;
import constant.ConstantPool;
import constant.StringInfo;
import engine.FrameResult;
import engine.StackFrame;

/**
 * Created by IBM on 2017/4/17.
 */
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
    public void execute(StackFrame frame, FrameResult result) {

    }
}
