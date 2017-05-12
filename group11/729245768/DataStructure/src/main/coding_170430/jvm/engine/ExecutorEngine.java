package main.coding_170430.jvm.engine;

import main.coding_170430.jvm.method.Method;

import java.util.Stack;

/**
 * Created by peterchen on 2017/5/5.
 */
public class ExecutorEngine {
    private Stack<StackFrame> stack = new Stack<>();
    public ExecutorEngine(){

    }
    public void execute(Method mainMethod){
    }
    private void setupFunctionCallParams(StackFrame currentFrame,StackFrame nextFrame){

    }
}
