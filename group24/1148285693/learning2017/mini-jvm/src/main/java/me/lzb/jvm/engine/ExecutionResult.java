package me.lzb.jvm.engine;


import me.lzb.jvm.method.Method;

/**
 * 定义了返回结果，有时候执行结束了是返回，或者，执行下一个方法，等等
 * @author LZB
 */
public class ExecutionResult {
    public static final int RUN_NEXT_CMD = 1;
    public static final int JUMP = 2;
    public static final int EXIT_CURRENT_FRAME = 3;
    public static final int PAUSE_AND_RUN_NEW_FRAME = 4;

    private int nextAction = RUN_NEXT_CMD;

    private int nextCmdOffset = 0;

    private Method nextMethod;

    public Method getNextMethod() {
        return nextMethod;
    }

    public void setNextMethod(Method nextMethod) {
        this.nextMethod = nextMethod;
    }


    public void setNextAction(int action) {
        this.nextAction = action;
    }

    public boolean isPauseAndRunNewFrame() {
        return this.nextAction == PAUSE_AND_RUN_NEW_FRAME;
    }

    public boolean isExitCurrentFrame() {
        return this.nextAction == EXIT_CURRENT_FRAME;
    }

    public boolean isRunNextCmd() {
        return this.nextAction == RUN_NEXT_CMD;
    }

    public boolean isJump() {
        return this.nextAction == JUMP;
    }

    public int getNextCmdOffset() {
        return nextCmdOffset;
    }

    public void setNextCmdOffset(int nextCmdOffset) {
        this.nextCmdOffset = nextCmdOffset;
    }


}
