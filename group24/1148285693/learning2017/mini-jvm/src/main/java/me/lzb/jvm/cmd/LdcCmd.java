package me.lzb.jvm.cmd;

import me.lzb.jvm.clz.ClassFile;
import me.lzb.jvm.constant.ConstantInfo;
import me.lzb.jvm.constant.ConstantPool;
import me.lzb.jvm.constant.StringInfo;
import me.lzb.jvm.engine.ExecutionResult;
import me.lzb.jvm.engine.Heap;
import me.lzb.jvm.engine.JavaObject;
import me.lzb.jvm.engine.StackFrame;
import me.lzb.jvm.print.ExecutionVisitor;

/**
 * @author LZB
 */
public class LdcCmd extends OneOperandCmd {

    public LdcCmd(ClassFile clzFile, String opCode) {
        super(clzFile, opCode);
    }


    public String toString() {
        ConstantInfo info = getConstantInfo(this.getOperand());

        String value = "TBD";
        if (info instanceof StringInfo) {
            StringInfo strInfo = (StringInfo) info;
            value = strInfo.toString();
        }

        return this.getOffset() + ":" + this.getOpCode() + " " + this.getReadableCodeText() + "   #" + getOperand() + "  // String  " + value;
    }


    @Override
    public void execute(StackFrame frame, ExecutionResult result) {
        ConstantPool pool = this.getConstantPool();
        ConstantInfo info = pool.getConstantInfo(this.getOperand());

        if (info instanceof StringInfo) {
            StringInfo strInfo = (StringInfo) info;
            String value = strInfo.toString();
            JavaObject jo = Heap.getInstance().newString(value);
            frame.getOprandStack().push(jo);
        } else {
            //TBD 处理其他类型
            throw new RuntimeException("Only support StringInfo constant");
        }

    }

    @Override
    public void printExecute(ExecutionVisitor visitor) {
        visitor.visitLdcCmd(this);
    }

}
