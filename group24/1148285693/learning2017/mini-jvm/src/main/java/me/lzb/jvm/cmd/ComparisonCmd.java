package me.lzb.jvm.cmd;

import me.lzb.jvm.clz.ClassFile;
import me.lzb.jvm.engine.ExecutionResult;
import me.lzb.jvm.engine.JavaObject;
import me.lzb.jvm.engine.StackFrame;
import me.lzb.jvm.print.ExecutionVisitor;

/**
 * @author LZB
 * @date 2017/5/12
 */
public class ComparisonCmd extends TwoOperandCmd {

    private int goOffset;

    public ComparisonCmd(ClassFile clzFile, String opCode) {
        super(clzFile, opCode);
    }

    @Override
    public void execute(StackFrame frame, ExecutionResult result) {

        if (ByteCodeCommand.if_icmp_ge.equals(this.getOpCode())) {
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
        //TODO getOffsetFromStartCmd
        return 0;
    }

    public int getGoOffset() {
        return goOffset;
    }

    public void setGoOffset(int goOffset) {
        this.goOffset = goOffset;
    }
}
