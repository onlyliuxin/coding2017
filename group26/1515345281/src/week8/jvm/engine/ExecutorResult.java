package week8.jvm.engine;

import week8.jvm.method.Method;

public class ExecutorResult {

	public static final int RUN_NEXT_CMD=1;
	public static final int JUMP=2;
	public static final int EXIT_CURRENT_FRAME=3;
	public static final int PAUSE_AND_RUN_NEW_FRAME=4;
	
	private int nextAction=RUN_NEXT_CMD;
	
	private int nextCmdOffset=0;
	
	private Method nextMethod;
	
	public boolean isRunNextCmd(){
		return this.nextAction == RUN_NEXT_CMD;
	}
	
	public boolean isJump(){
		return this.nextAction == JUMP;
	}
	
	public boolean isExitCurrentFrame(){
		return this.nextAction == EXIT_CURRENT_FRAME;
	}
	
	public boolean isPauseAndRunNewFrame(){
		return this.nextAction == PAUSE_AND_RUN_NEW_FRAME;
	}
	
	public Method getNextMethod() {
		return nextMethod;
	}
	public void setNextMethod(Method nextMethod) {
		this.nextMethod = nextMethod;
	}
	public int getNextAction() {
		return nextAction;
	}
	public void setNextAction(int nextAction) {
		this.nextAction = nextAction;
	}
	public int getNextCmdOffset() {
		return nextCmdOffset;
	}
	public void setNextCmdOffset(int nextCmdOffset) {
		this.nextCmdOffset = nextCmdOffset;
	}
}
