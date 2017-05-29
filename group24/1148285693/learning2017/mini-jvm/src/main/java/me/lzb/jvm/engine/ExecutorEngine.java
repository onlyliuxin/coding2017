package me.lzb.jvm.engine;


import me.lzb.jvm.method.Method;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author LZB
 */
public class ExecutorEngine {

    //虚拟机栈，用一个stack表示
    private Stack<StackFrame> stack = new Stack<>();

    public ExecutorEngine() {

    }

    //开始执行的时候，传入main方法
    public void execute(Method mainMethod) {
        //执行一个函数，就要new一个函数栈帧
        StackFrame stackFrame = StackFrame.create(mainMethod);
        //把栈帧放入虚拟机栈
        stack.push(stackFrame);

        //当statck不为空时，开始循环执行
        while (!stack.isEmpty()) {
            //取出顶部栈帧
            StackFrame frame = stack.peek();
            //执行栈帧
            ExecutionResult result = frame.execute();

            //如果执行结果是，暂停当前栈帧，执行一个新的函数，就new一个新栈帧，（函数调用别的函数的情况）
            if (result.isPauseAndRunNewFrame()) {
                Method nextMethod = result.getNextMethod();
                StackFrame nextFrame = StackFrame.create(nextMethod);
                //设置调用他的函数栈帧
                nextFrame.setCallerFrame(frame);
                //设置传过去的参数，第一个参数都是this
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
