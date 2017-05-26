package main.coding_170430.jvm.attr;

import main.coding_170430.jvm.clz.ClassFile;
import main.coding_170430.jvm.cmd.ByteCodeCommand;
import main.coding_170430.jvm.constant.ConstantPool;
import main.coding_170430.jvm.loader.ByteCodeIterator;

/**
 * Created by peter on 2017/4/21.
 */
public class CodeAttr extends AttributeInfo {
    private int maxStack ;
    private int maxLocals ;
    private int codeLen ;
    private String code;
    public String getCode() {
        return code;
    }

    private ByteCodeCommand[] cmds ;
    public ByteCodeCommand[] getCmds() {
        return cmds;
    }
    private LineNumberTable lineNumTable;
    private LocalVariableTable localVarTable;
    private StackMapTable stackMapTable;

    public CodeAttr(int attrNameIndex, int attrLen, int maxStack, int maxLocals, int codeLen,String code ,ByteCodeCommand[] cmds) {
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

    public static CodeAttr parse(ClassFile clzFile, ByteCodeIterator iter){

        return null;
    }


    public String toString(ConstantPool pool){
        StringBuilder buffer = new StringBuilder();
        //buffer.append("Code:").append(code).append("\n");
        for(int i=0;i<cmds.length;i++){
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
