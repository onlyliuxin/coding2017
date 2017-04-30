package task8.jvm.engine;

import task8.jvm.method.Method;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class ExecutorEngine {
    private Stack<StackFrame> stack = new Stack<>();

    public ExecutorEngine() {

    }

    public void execute(Method mainMethod) {

        StackFrame frame = StackFrame.create(mainMethod);
        stack.push(frame);

        while (!stack.empty()) {
            StackFrame sf = stack.peek();
            ExecutionResult result = sf.execute();
            if (result.isPauseAndRunNewFrame()) {
                Method nextMethod = result.getNextMethod();
                StackFrame nextMethodStackFrame = StackFrame.create(nextMethod);
                nextMethodStackFrame.setCallerFrame(sf);
                setupFunctionCallParams(sf, nextMethodStackFrame);
                stack.push(nextMethodStackFrame);
            } else stack.pop();
        }

    }

    private void setupFunctionCallParams(StackFrame currentFrame, StackFrame nextFrame) {
        Method nextMethod = nextFrame.getMethod();
        List<String> paramList = nextMethod.getParameterList();
        List<JavaObject> values = new ArrayList<>();
        int paramNumber = paramList.size() + 1;
        while (paramNumber > 0) {
            values.add(currentFrame.getOprandStack().pop());
            paramNumber--;
        }
        Collections.reverse(values);
        nextFrame.setLocalVariableTable(values);

    }
}
