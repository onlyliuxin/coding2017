package main.coding_170423.jvm.attr;

import main.coding_170423.jvm.clz.ClassFile;
import main.coding_170423.jvm.constant.ConstantPool;
import main.coding_170423.jvm.loader.ByteCodeIterator;

/**
 * Created by peter on 2017/4/21.
 */
public class CodeAttr extends AttributeInfo {
    private int maxStack;
    private int maxLocals;
    private int codeLen;
    private String code;

    public String getCode() {
        return code;
    }

    private LineNumberTable lineNumberTable;
    private LocalVariableTable localVariableTable;
    private StackMapTable stackMapTable;

    public CodeAttr(int attrNameIndex, int attrLen, int maxStack, int maxLocals, int codeLen, String code) {
        super(attrNameIndex, attrLen);
        this.maxStack = maxStack;
        this.maxLocals = maxLocals;
        this.codeLen = codeLen;
        this.code = code;
    }

    public void setLineNumberTable(LineNumberTable table) {
        this.lineNumberTable = table;
    }

    public void setLocalVariableTable(LocalVariableTable table) {
        this.localVariableTable = table;
    }

    public static CodeAttr parse(ClassFile clzFile, ByteCodeIterator iterator) {
        int attrNameIndex = iterator.nextU2ToInt();
        int attrLen = iterator.nextU4ToInt();
        int maxStack = iterator.nextU2ToInt();
        int maxLocals = iterator.nextU2ToInt();
        int codeLen = iterator.nextU4ToInt();

        String code = iterator.nextUxToHexString(codeLen);

        System.out.println(code);

        CodeAttr codeAttr = new CodeAttr(attrNameIndex, attrLen, maxStack, maxLocals, codeLen, code);
        int exceptionTableLen = iterator.nextU2ToInt();
        //deal with exception
        if (exceptionTableLen > 0) {
            String exceptionTable = iterator.nextUxToHexString(exceptionTableLen);
            System.out.println("Encountered exception table:just ignore " + exceptionTable);
        }
        int subAttrCount = iterator.nextU2ToInt();
        for (int i = 1; i < subAttrCount; i++) {
            int subAttrIndex = iterator.nextU2ToInt();
            String subAttrName = clzFile.getConstantPool().getUTF8String(subAttrIndex);

            iterator.back(2);

            if (AttributeInfo.LINE_NUM_TABLE.equalsIgnoreCase(subAttrName)) {
                LineNumberTable t = LineNumberTable.parse(iterator);
                codeAttr.setLineNumberTable(t);
            } else if (AttributeInfo.LOCAL_VAR_TABLE.equalsIgnoreCase(subAttrName)) {
                LocalVariableTable t = LocalVariableTable.parse(iterator);
                codeAttr.setLocalVariableTable(t);
            } else if (AttributeInfo.STACK_MAP_TABLE.equalsIgnoreCase(subAttrName)) {
                StackMapTable t = StackMapTable.parse(iterator);
                codeAttr.setStackMapTable(t);
            } else {
                throw new RuntimeException("Need code to process " + subAttrName);
            }
        }
        return codeAttr;
    }

    public String toString(ConstantPool pool) {
        StringBuilder sb = new StringBuilder();
        sb.append("Code:").append(code).append("\n");
        sb.append("\n");
        sb.append(this.lineNumberTable.toString());
        sb.append(this.localVariableTable.toString(pool));
        return sb.toString();
    }

    public void setStackMapTable(StackMapTable stackMapTable) {
        this.stackMapTable = stackMapTable;
    }
}
