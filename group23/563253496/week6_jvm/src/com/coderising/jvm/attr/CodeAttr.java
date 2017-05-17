package com.coderising.jvm.attr;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.loader.ByteCodeIterator;
import com.coderising.jvm.loader.ClassFileLoader;
import com.coderising.jvm.cmd.*;
import sun.text.CodePointIterator;

import java.io.UnsupportedEncodingException;


public class CodeAttr extends AttributeInfo {
    private int maxStack;
    private int maxLocals;
    private int codeLen;
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

    public CodeAttr(int attrNameIndex, int attrLen, int maxStack, int maxLocals, int codeLen, String code ,ByteCodeCommand[] cmds) {
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

        int attribute_name_index = iter.nextU2ToInt();
        if (!clzFile.getConstantPool().getUTF8String(attribute_name_index).equalsIgnoreCase(CODE)) {
            throw new RuntimeException("CODE属性的attributenameindex解析错误");
        }

        int attribute_len = iter.nextU4ToInt();
        byte[] bytes = iter.getBytes(attribute_len);
        ByteCodeIterator codeIter = new ByteCodeIterator(bytes);

        int max_stack = codeIter.nextU2ToInt();
        int max_locals = codeIter.nextU2ToInt();
        int code_len = codeIter.nextU4ToInt();
        /*byte[] codes = codeIter.getBytes(code_len);

        String code = null;
        try {

            code = new String(codes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
*/

        String code = codeIter.nextUxToHexString(code_len);

        ByteCodeCommand[] cmds = CommandParser.parse(clzFile, code);

        CodeAttr codeAttr = new CodeAttr(attribute_name_index, attribute_len, max_stack, max_locals, code_len, code, cmds);

        int exception_table_len = codeIter.nextU2ToInt();
        if (exception_table_len > 0) {
            throw new RuntimeException("方法的code属性中有异常未进行解析");
        }

        //code中的属性
        int attribute_count = codeIter.nextU2ToInt();
        /*byte[] bytes1 = codeIter.getBytes(attribute_length);
        ByteCodeIterator codeAttrIter = new ByteCodeIterator(bytes1);

        while (codeAttrIter.isNotEnd()) {
            int attributenameindex = codeAttrIter.nextU2ToInt();
            if (clzFile.getConstantPool().getUTF8String(attributenameindex).equalsIgnoreCase(LINE_NUM_TABLE)) {
                LineNumberTable table = LineNumberTable.parse(codeAttrIter, attributenameindex);
                codeAttr.setLineNumberTable(table);
            } else if (clzFile.getConstantPool().getUTF8String(attributenameindex).equalsIgnoreCase(LOCAL_VAR_TABLE)) {
                LocalVariableTable table = LocalVariableTable.parse(codeAttrIter,attributenameindex);
                codeAttr.setLocalVariableTable(table);
            }else if(clzFile.getConstantPool().getUTF8String(attributenameindex).equalsIgnoreCase(STACK_MAP_TABLE)){
                codeIter.back(2);
                StackMapTable table = StackMapTable.parse(codeIter);
                codeAttr.setStackMapTable(table);
            }
        }*/
        for (int i = 0; i < attribute_count; i++) {
            int attributenameindex = codeIter.nextU2ToInt();
            if (clzFile.getConstantPool().getUTF8String(attributenameindex).equalsIgnoreCase(LINE_NUM_TABLE)) {
                LineNumberTable table = LineNumberTable.parse(codeIter, attributenameindex);
                codeAttr.setLineNumberTable(table);
            } else if (clzFile.getConstantPool().getUTF8String(attributenameindex).equalsIgnoreCase(LOCAL_VAR_TABLE)) {
                LocalVariableTable table = LocalVariableTable.parse(codeIter,attributenameindex);
                codeAttr.setLocalVariableTable(table);
            }else if(clzFile.getConstantPool().getUTF8String(attributenameindex).equalsIgnoreCase(STACK_MAP_TABLE)){
                codeIter.back(2);
                StackMapTable table = StackMapTable.parse(codeIter);
                codeAttr.setStackMapTable(table);
            }
            
        }




            return codeAttr;
    }

    private void setStackMapTable(StackMapTable t) {
        this.stackMapTable = t;

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

}
