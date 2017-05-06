package attr;

import clz.ClassFile;
import cmd.ByteCodeCommand;
import cmd.CommandParser;
import constant.ConstantInfo;
import iterator.ByteCodeIterator;

/**
 * Created by IBM on 2017/4/12.
 */
public class CodeAttr extends AttributeInfo {
    private int maxStack;
    private int maxLocals;
    private int codeLen;
    private String code;

    public String getCode() {
        return code;
    }

    private ByteCodeCommand[] cmds;

    public ByteCodeCommand[] getCmds() {
        return cmds;
    }

    private LineNumberTable lineNumTable;
    private LocalVariableTable localVarTable;
    private StackMapTable stackMapTable;

    public CodeAttr(int attrNameIndex, int attrLen, int maxStack, int maxLocals, int codeLen, String code,
                    ByteCodeCommand[] cmds) {
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
        //按照属性表格式读取
        int attributeNameIndex = iter.nextU2ToInt();
        int attributeLength = iter.nextU4ToInt();
        int maxStack = iter.nextU2ToInt();
        int maxLocals = iter.nextU2ToInt();
        int codeLength = iter.nextU4ToInt();
        String code = iter.nextLengthString(codeLength);

        ByteCodeCommand[] cmds = CommandParser.parse(clzFile, code);

        int exceptionTableLength = iter.nextU2ToInt();//跳过异常表
//        System.out.println("Code属性表中的异常表元素大小：" + exceptionTableLength);
        CodeAttr codeAttr = new CodeAttr(attributeNameIndex, attributeLength, maxStack, maxLocals, codeLength, code, cmds);
        int attributesCount = iter.nextU2ToInt();
        //code属性表中又有属性
        for (int i = 0; i < attributesCount; i++) {
            int attrIndex = iter.nextU2ToInt();
            String utf8String = clzFile.getConstantPool().getUTF8String(attrIndex);
//            clzFile.getConstantPool().getConstantInfo(attrIndex).
            if ("LineNumberTable".equals(utf8String)) {
                codeAttr.setLineNumberTable(LineNumberTable.parse(iter, attrIndex));
            } else if ("LocalVariableTable".equals(utf8String)) {
                codeAttr.setLocalVariableTable(LocalVariableTable.parse(iter, attrIndex));
            } else if ("stackMapTable".equals(utf8String)) {
                codeAttr.setStackMapTable(StackMapTable.parse(iter, attrIndex));
            } else {
                throw new RuntimeException("other attribute in code table");
            }
        }
        return codeAttr;
    }

    private void setStackMapTable(StackMapTable t) {
        this.stackMapTable = t;

    }

}
