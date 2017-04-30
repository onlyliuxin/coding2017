package jvm.engine;

import jvm.classfile.method.Method;
import jvm.command.item.ByteCodeCommand;
import jvm.command.item.TwoOperandCmd;
import jvm.exception.ReadClassException;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class StackFrame {
    private List<JavaObject> localVariableTable = new ArrayList<>();
    private Stack<JavaObject> operandStack = new Stack<>();

    private int index = 0;
    private Method method = null;
    private StackFrame callerFrame = null;

    private StackFrame(Method method) {
        this.method = method;
    }

    public ExecutionResult execute() throws ReadClassException {
        ByteCodeCommand[] commands = getMethod().getCommands();

        while (index < commands.length) {
            ByteCodeCommand cmd = commands[index];
            ExecutionResult result = new ExecutionResult();
            cmd.execute(this, result);

            if (result.isExitCurrentFrame()) {
                return result;
            } else if (result.isPauseAndRunNewFrame()) {
                index++;
                return result;
            } else if (result.isJump()) {
                int offset = result.getNextCmdOffset();
                index = getNextCommandIndex(offset);
            } else {
                index++;
            }
        }
        ExecutionResult frameResult = new ExecutionResult();
        frameResult.setNextAction(ExecutionResult.EXIT_CURRENT_FRAME);
        return frameResult;
    }

    public StackFrame getCallerFrame() {
        return callerFrame;
    }

    public void setCallerFrame(StackFrame callerFrame) {
        this.callerFrame = callerFrame;
    }

    public static StackFrame create(Method m) {
        return new StackFrame(m);
    }

    public JavaObject getLocalVariableValue(int index) {
        return this.localVariableTable.get(index);
    }

    public Stack<JavaObject> getOperandStack() {
        return this.operandStack;
    }

    public int getNextCommandIndex(int offset) {
        ByteCodeCommand[] commands = method.getCommands();
        for (int i = 0; i < commands.length; i++) {
            if (commands[i].getOffset() == offset) {
                return i;
            }
        }
        throw new RuntimeException("Can't find next command");
    }

    public void setLocalVariableTable(List<JavaObject> values) {
        this.localVariableTable = values;
    }

    public void setLocalVariableValue(int index, JavaObject jo) {
        if (this.localVariableTable.size() - 1 < index) {
            for (int i = this.localVariableTable.size(); i <= index; i++) {
                this.localVariableTable.add(null);
            }
        }
        this.localVariableTable.set(index, jo);
    }

    public Method getMethod() {
        return method;
    }

    @Override
    public String toString() {
        return getMethod().toString();
    }
}
