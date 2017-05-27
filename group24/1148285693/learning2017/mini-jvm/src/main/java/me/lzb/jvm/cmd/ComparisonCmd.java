package me.lzb.jvm.cmd;

import me.lzb.jvm.clz.ClassFile;
import me.lzb.jvm.engine.ExecutionResult;
import me.lzb.jvm.engine.JavaObject;
import me.lzb.jvm.engine.StackFrame;
import me.lzb.jvm.print.ExecutionVisitor;

/**
 * @author LZB
 */
public class ComparisonCmd extends TwoOperandCmd {

    private int goOffset;

    public ComparisonCmd(ClassFile clzFile, String opCode) {
        super(clzFile, opCode);
    }

    @Override
    public void execute(StackFrame frame, ExecutionResult result) {

        if (ByteCodeCommand.if_icmpge.equals(this.getOpCode())) {
            JavaObject jo2 = frame.getOprandStack().pop();
            JavaObject jo1 = frame.getOprandStack().pop();

            if (jo1.getIntValue() >= jo2.getIntValue()) {
                setJumpResult(result);
            }

        } else if (ByteCodeCommand.if_icmple.equals(this.getOpCode())) {

            JavaObject jo2 = frame.getOprandStack().pop();
            JavaObject jo1 = frame.getOprandStack().pop();

            if (jo1.getIntValue() <= jo2.getIntValue()) {
                setJumpResult(result);
            }

        } else if (ByteCodeCommand.if_icmpgt.equals(this.getOpCode())) {
            JavaObject jo2 = frame.getOprandStack().pop();
            JavaObject jo1 = frame.getOprandStack().pop();

            if (jo1.getIntValue() > jo2.getIntValue()) {
                setJumpResult(result);
            }
        } else if (ByteCodeCommand.goto_no_condition.equals(this.opCode)) {
            setJumpResult(result);
        }
    }

    @Override
    public void printExecute(ExecutionVisitor visitor) {
        visitor.visitComparisonCmd(this);
    }


    private void setJumpResult(ExecutionResult result) {
        int offsetFromStartCmd = getOffsetFromStartCmd();
        result.setNextAction(ExecutionResult.JUMP);
        result.setNextCmdOffset(offsetFromStartCmd);
    }

    private int getOffsetFromStartCmd() {
//      如果比较结果为真，那无符号 byte 型数据 branchbyte1 和 branchbyte2 用于构建一个 16 位有符号的分支偏移量，构建方式为（branchbyte1 << 8）| branchbyte2。
//      指令执行后，程序将会转到这个 if_acmp<cond>指令之后的，由上述偏移量确定的目标地址上继续执行。这个目标地址必须处于if_acmp<cond>指令所在的方法之中。
        int index1 = this.getOprand1();
        int index2 = this.getOprand2();
        short offsetFromCurrent = (short) (index1 << 8 | index2);
        return this.getOffset() + offsetFromCurrent;
    }

    public int getGoOffset() {
        return goOffset;
    }

    public void setGoOffset(int goOffset) {
        this.goOffset = goOffset;
    }
}
