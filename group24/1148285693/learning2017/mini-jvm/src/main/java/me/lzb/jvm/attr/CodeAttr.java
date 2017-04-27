package me.lzb.jvm.attr;

/**
 * Created by LZB on 2017/4/15.
 */
public class CodeAttr extends AttributeInfo {
    private int maxStack;
    private int maxLocals;
    private int codeLen;
    private String code;
    private LineNumberTable lineNumTable;
    private LocalVariableTable localVarTable;
    private StackMapTable stackMapTable;

    public CodeAttr(int attrNameIndex, int attrLen, int maxStack, int maxLocals, int codeLen, String code /*ByteCodeCommand[] cmds*/) {
        super(attrNameIndex, attrLen);
        this.maxStack = maxStack;
        this.maxLocals = maxLocals;
        this.codeLen = codeLen;
        this.code = code;
        //this.cmds = cmds;
    }


    public String getCode() {
        return code;
    }

    public LineNumberTable getLineNumTable() {
        return lineNumTable;
    }

    public void setLineNumTable(LineNumberTable lineNumTable) {
        this.lineNumTable = lineNumTable;
    }

    public LocalVariableTable getLocalVarTable() {
        return localVarTable;
    }

    public void setLocalVarTable(LocalVariableTable localVarTable) {
        this.localVarTable = localVarTable;
    }

    public StackMapTable getStackMapTable() {
        return stackMapTable;
    }

    public void setStackMapTable(StackMapTable stackMapTable) {
        this.stackMapTable = stackMapTable;
    }
}
