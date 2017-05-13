package main.coding_170430.jvm.cmd;

import main.coding_170430.jvm.clz.ClassFile;

/**
 * Created by peterchen on 2017/4/26.
 */
public class InvokeVirtualCmd extends TwoOperandCmd {
    public InvokeVirtualCmd(ClassFile clzFile, String opCode) {
        super(clzFile,opCode);
    }

    @Override
    public String toString() {

        return super.getOperandAsMethod();
    }

    private boolean isSystemOutPrintlnMethod(String className, String methodName){
        return "java/io/PrintStream".equals(className)
                && "println".equals(methodName);
    }

}
