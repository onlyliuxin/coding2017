package jvm.engine;

import jvm.classfile.ConstantPool;
import jvm.classfile.constant.item.impl.MethodRefInfo;
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
            index++;
            result.setNextCmdOffset(commands[index].getOffset());

            if (result.isExitCurrentFrame()) {
                return result;
            } else if (result.isPauseAndRunNewFrame()) {
                TwoOperandCmd invokeCmd = (TwoOperandCmd) cmd;
                int methodIndex = (invokeCmd.getOperand1() << 8) | invokeCmd.getOperand2();
                ConstantPool constantPool = getMethod().getConstantPool();
                MethodRefInfo next = (MethodRefInfo) constantPool.getConstantInfo(methodIndex);
                Method nextMethod = MethodArea.getInstance().getMethod(next);
                result.setNextMethod(nextMethod);
                return result;
            } else if (result.isJump()) {
                TwoOperandCmd jumpCmd = (TwoOperandCmd) cmd;
                int offset = (jumpCmd.getOperand1() << 8) | jumpCmd.getOperand2();
                index = getNextCommandIndex(offset);
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
}
