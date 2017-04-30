package jvm.engine;

import jvm.classfile.method.Method;
import jvm.exception.ReadClassException;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ExecutorEngine {
    private Stack<StackFrame> stack = new Stack<>();

    public void execute(Method main) throws ReadClassException {
        StackFrame mainFrame = StackFrame.create(main);
        stack.push(mainFrame);

        while (!stack.isEmpty()) {
            StackFrame top = stack.peek();
            ExecutionResult result = top.execute();
            if (result.isExitCurrentFrame()) {
                stack.pop();
            } else if (result.isPauseAndRunNewFrame()) {
                Method next = result.getNextMethod();
                StackFrame newFrame = StackFrame.create(next);
                setupFunctionCallParams(top, newFrame);
                stack.push(newFrame);
            }
        }
    }

    private void setupFunctionCallParams(StackFrame currentFrame, StackFrame nextFrame) {
        List<JavaObject> local = createLocalVariableTable(currentFrame, nextFrame.getMethod());
        nextFrame.setLocalVariableTable(local);
        nextFrame.setCallerFrame(currentFrame);
    }

    private List<JavaObject> createLocalVariableTable(StackFrame top, Method next) {
        int paramCount = next.getParamCount();
        Stack<JavaObject> params = new Stack<>();
        for (int i = 0; i < paramCount; ++i) {
            JavaObject param = top.getOperandStack().pop();
            params.push(param);
        }
        if (!next.getAccessFlag().isStatic()) {
            JavaObject param = top.getOperandStack().pop();
            params.push(param);
        }

        List<JavaObject> local = new ArrayList<>();
        while (!params.isEmpty()) {
            local.add(params.pop());
        }
        return local;
    }
}
