package me.lzb.jvm.cmd;

import me.lzb.jvm.clz.ClassFile;
import me.lzb.jvm.constant.ClassInfo;
import me.lzb.jvm.engine.ExecutionResult;
import me.lzb.jvm.engine.Heap;
import me.lzb.jvm.engine.JavaObject;
import me.lzb.jvm.engine.StackFrame;
import me.lzb.jvm.print.ExecutionVisitor;

/**
 * @author LZB
 */
public class NewObjectCmd extends TwoOperandCmd {

    public NewObjectCmd(ClassFile clzFile, String opCode) {
        super(clzFile, opCode);
    }

    public String toString() {
        return super.getOperandAsClassInfo();
    }

    @Override
    public void execute(StackFrame frame, ExecutionResult result) {
        int index = this.getIndex();

        ClassInfo info = (ClassInfo) this.getConstantInfo(index);

        String clzName = info.getClassName();

        //在Java堆上创建一个实例
        JavaObject jo = Heap.getInstance().newObject(clzName);
        //压入栈顶
        frame.getOprandStack().push(jo);
    }

    @Override
    public void printExecute(ExecutionVisitor visitor) {
        visitor.visitNewObjectCmd(this);
    }


}
