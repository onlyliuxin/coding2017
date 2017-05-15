package main.coding_170430.jvm.engine;

import main.coding_170430.jvm.method.Method;

/**
 * Created by peterchen on 2017/5/5.
 */
public class ExecutionResult {
    private static final int RUN_NEXT_CMD = 1;
    private static final int JUMP = 2;
    private static final int EXIT_CURRENT_FRAME = 3;
    private static final int PAUSE_AND_RUN_NEW_FRAME = 4;

    private int nextAction = RUN_NEXT_CMD;
    private int nextCmdOffset = 0;
    private Method nextMethod;

    public Method getNextMethod(){
        return nextMethod;
    }
    public void setNextMethod(Method method){
        this.nextMethod = nextMethod;
    }
    public void setNextAction(int action){
        this.nextAction = action;
    }
    public boolean isPauseAndRunNewFrame(){
        return this.nextAction==PAUSE_AND_RUN_NEW_FRAME;
    }
    public boolean isExitCurrentFrame(){
        return this.nextAction == EXIT_CURRENT_FRAME;
    }
    public boolean isRunNextCmd(){
        return this.nextAction == RUN_NEXT_CMD;
    }
    public boolean isJump(){
        return this.nextAction == JUMP;
    }
    public int getNextCmdOffset(){
        return nextCmdOffset;
    }
    public void setNextCmdOffset(int nextCmdOffset){
        this.nextCmdOffset = nextCmdOffset;
    }
}

