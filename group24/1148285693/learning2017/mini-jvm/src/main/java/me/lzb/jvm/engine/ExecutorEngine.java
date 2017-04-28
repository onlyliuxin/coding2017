package me.lzb.jvm.engine;


import me.lzb.jvm.method.Method;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class ExecutorEngine {

    private Stack<StackFrame> stack = new Stack<>();

    public ExecutorEngine() {

    }

    public void execute(Method mainMethod) {
        StackFrame stackFrame = StackFrame.create(mainMethod);
        stack.push(stackFrame);

        while (!stack.isEmpty()) {
            StackFrame frame = stack.peek();
            ExecutionResult result = frame.execute();

            if (result.isPauseAndRunNewFrame()) {
                Method nextMethod = result.getNextMethod();
                StackFrame nextFrame = StackFrame.create(nextMethod);
                nextFrame.setCallerFrame(frame);
                setupFunctionCallParams(frame, nextFrame);
                stack.push(nextFrame);
            } else {
                stack.pop();
            }
        }

    }


    private void setupFunctionCallParams(StackFrame currentFrame, StackFrame nextFrame) {
        Method method = nextFrame.getMethod();
        List<String> paramList = method.getParameterList();

        //把this传过去
        int paramNum = paramList.size() + 1;


        List<JavaObject> values = new ArrayList<>();

        //从调用函数的栈帧的操作数栈中，取出paramNum个参数，等下传入被调用函数
        for (int i = 0; i < paramNum; i++) {
            values.add(currentFrame.getOprandStack().pop());
        }

        //因为栈是先入后出，为了保证传入顺序一致，对list进行逆转

        List<JavaObject> params = new ArrayList<>();
        for (int i = values.size() - 1; i >= 0; i--) {
            params.add(values.get(i));
        }

        nextFrame.setLocalVariableTable(params);


    }

}
