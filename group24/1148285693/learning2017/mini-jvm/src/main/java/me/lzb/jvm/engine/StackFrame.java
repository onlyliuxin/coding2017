package me.lzb.jvm.engine;

import me.lzb.jvm.cmd.ByteCodeCommand;
import me.lzb.jvm.method.Method;
import me.lzb.jvm.print.ExecutionFormat;
import me.lzb.jvm.print.ExecutionVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author LZB
 */
public class StackFrame {

    private List<JavaObject> localVariableTable = new ArrayList<>();
    private Stack<JavaObject> oprandStack = new Stack<>();

    /**
     * 用来记录当前栈帧帧，已经执行到哪一步，以便恢复的时候，继续执行
     */
    int index = 0;

    private Method m = null;

    private StackFrame callerFrame = null;

    public StackFrame getCallerFrame() {
        return callerFrame;
    }

    public void setCallerFrame(StackFrame callerFrame) {
        this.callerFrame = callerFrame;
    }


    public static StackFrame create(Method m) {

        StackFrame frame = new StackFrame(m);

        return frame;
    }


    private StackFrame(Method m) {
        this.m = m;

    }


    public JavaObject getLocalVariableValue(int index) {
        return this.localVariableTable.get(index);
    }

    public Stack<JavaObject> getOprandStack() {
        return this.oprandStack;
    }

    public int getNextCommandIndex(int offset) {

        ByteCodeCommand[] cmds = m.getCodeAttr().getCmds();
        for (int i = 0; i < cmds.length; i++) {
            if (cmds[i].getOffset() == offset) {
                return i;
            }
        }
        throw new RuntimeException("Can't find next command");
    }

    /**
     * 执行栈帧
     * @return
     */
    public ExecutionResult execute() {
        //取出所有jvm指令
        ByteCodeCommand[] commands = m.getCmds();


        while (index < commands.length) {

            ExecutionResult result = new ExecutionResult();
            //缺省值是执行下一条命令
            result.setNextAction(ExecutionResult.RUN_NEXT_CMD);

            //在控制台打印，执行的命令，这里用了visitor设计模式
            ExecutionVisitor format = ExecutionFormat.getInstance();
            commands[index].printExecute(format);

            //执行指令，这里是一个多态，具体执行的内容在各个指令的内部
            commands[index].execute(this, result);


            if (result.isRunNextCmd()) { //当前指令结果是执行下一条指令，就++，继续循环
                index++;
            } else if (result.isExitCurrentFrame()) {   //退出当前栈帧，就是return
                return result;
            } else if (result.isPauseAndRunNewFrame()) {    //暂停当前栈帧的执行，创建新的栈帧
                index++;
                return result;

            } else if (result.isJump()) {   //跳转，要注意偏移量
                int offset = result.getNextCmdOffset();
                this.index = getNextCommandIndex(offset);
            } else {
                index++;
            }

        }

        //当前StackFrmae的指令全部执行完毕，可以退出了
        ExecutionResult result = new ExecutionResult();
        result.setNextAction(ExecutionResult.EXIT_CURRENT_FRAME);
        return result;
    }


    public void setLocalVariableTable(List<JavaObject> values) {
        this.localVariableTable = values;
    }

    public void setLocalVariableValue(int index, JavaObject jo) {
        //问题： 为什么要这么做？？
        if (this.localVariableTable.size() - 1 < index) {
            for (int i = this.localVariableTable.size(); i <= index; i++) {
                this.localVariableTable.add(null);
            }
        }
        this.localVariableTable.set(index, jo);


    }

    public Method getMethod() {
        return m;
    }


}
