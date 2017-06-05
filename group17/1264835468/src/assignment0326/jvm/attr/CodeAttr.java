package assignment0326.jvm.attr;


import assignment0326.jvm.clz.ClassFile;
import assignment0326.jvm.cmd.ByteCodeCommand;
import assignment0326.jvm.cmd.CommandParser;
import assignment0326.jvm.constant.ConstantPool;
import assignment0326.jvm.loader.ByteCodeIterator;

public class CodeAttr extends AttributeInfo {
    private int maxStack;
    private int maxLocals;
    private int codeLen;
    private String code;
    private ByteCodeCommand[] cmds;

    private LineNumberTable lineNumTable;
    private LocalVariableTable localVarTable;
    private StackMapTable stackMapTable;

    public String getCode() {
        return code;
    }

    public ByteCodeCommand[] getCmds() {
        return cmds;
    }

    public CodeAttr(int attrNameIndex, int attrLen, int maxStack, int maxLocals, int codeLen, String code, ByteCodeCommand[] cmds) {
        super(attrNameIndex, attrLen);
        this.maxStack = maxStack;
        this.maxLocals = maxLocals;
        this.codeLen = codeLen;
        this.code = code;
        this.cmds = cmds;
    }

    public void setLineNumberTable(LineNumberTable t) {
        this.lineNumTable = t;
    }

    public void setLocalVariableTable(LocalVariableTable t) {
        this.localVarTable = t;
    }

    public static CodeAttr parse(ClassFile clzFile, ByteCodeIterator iter) {
        int attrNameIndex = iter.nextU2ToInt();
        int attrLengh = iter.nextU4ToInt();
        int maxStack = iter.nextU2ToInt();
        int maxLocal = iter.nextU2ToInt();
        int codeLengh = iter.nextU4ToInt();
        String code = iter.nextUxToHexString(codeLengh);
        ByteCodeCommand[] cmds = CommandParser.parse(clzFile, code);
        CodeAttr codeAttr = new CodeAttr(attrNameIndex, attrLengh, maxStack, maxLocal, codeLengh, code, cmds);
        int exceptionLength = iter.nextU2ToInt();
        if (exceptionLength != 0) {
            throw new RuntimeException("exception table has not implemented yet.");
        }
        int attrCount = iter.nextU2ToInt();
        for (int i = 0; i < attrCount; i++) {
            int nameIndex = iter.nextU2ToInt();
            String name = clzFile.getConstantPool().getUTF8String(nameIndex);
            iter.back(2);
            if (name.equals(AttributeInfo.LINE_NUM_TABLE)) {
                codeAttr.setLineNumberTable(LineNumberTable.parse(iter));
            } else if (name.equals(AttributeInfo.LOCAL_VAR_TABLE)) {
                codeAttr.setLocalVariableTable(LocalVariableTable.parse(iter));
            } else if (name.equals(AttributeInfo.STACK_MAP_TABLE)) {
                codeAttr.setStackMapTable(StackMapTable.parse(iter));
            } else {
                throw new RuntimeException("code attribute:" + name + " has not implemented yet.");
            }
        }
        return codeAttr;
    }


    public String toString(ConstantPool pool) {
        StringBuilder buffer = new StringBuilder();
        //buffer.append("Code:").append(code).append("\n");
        for (int i = 0; i < cmds.length; i++) {
            buffer.append(cmds[i].toString(pool)).append("\n");
        }
        buffer.append("\n");
        buffer.append(this.lineNumTable.toString());
        buffer.append(this.localVarTable.toString(pool));
        return buffer.toString();
    }

    private void setStackMapTable(StackMapTable t) {
        this.stackMapTable = t;

    }


}
