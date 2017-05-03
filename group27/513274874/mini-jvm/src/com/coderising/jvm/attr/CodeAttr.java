package com.coderising.jvm.attr;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.cmd.ByteCodeCommand;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.loader.ByteCodeIterator;


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

    public CodeAttr(int attrNameIndex, int attrLen, int maxStack, int maxLocals, int codeLen, String code /*ByteCodeCommand[] cmds*/) {
        super(attrNameIndex, attrLen);
        this.maxStack = maxStack;
        this.maxLocals = maxLocals;
        this.codeLen = codeLen;
        this.code = code;
        //this.cmds = cmds;
    }

    public void setLineNumberTable(LineNumberTable t) {
        this.lineNumTable = t;
    }

    public void setLocalVariableTable(LocalVariableTable t) {
        this.localVarTable = t;
    }

    public static CodeAttr parse(ClassFile clzFile, ByteCodeIterator iter) {

        int attributeNameIndex = iter.nextU2Int();
        int attributeLength = iter.nextU4Int();
        int maxStack = iter.nextU2Int();
        int maxLocals = iter.nextU2Int();

        int codeLength = iter.nextU4Int();
        String code = iter.nextUxToHexString(codeLength);

        CodeAttr codeAttr = new CodeAttr(attributeNameIndex, attributeLength, maxStack, maxLocals, codeLength, code);

        //exception
        int exceptionTableLength = iter.nextU2Int();
        if (exceptionTableLength > 0) {
            String exceptionTable = iter.nextUxToHexString(exceptionTableLength);
            throw new RuntimeException("there's exception to be implemented !!");
        }

        //attribute table of the code
        int attributesCount = iter.nextU2Int();
        for (int i = 0; i < attributesCount; i++) {
            int subAttributeNameIndex = iter.nextU2Int();
            iter.back(2);

            String subAttributeName = clzFile.getConstantPool().getUTF8String(subAttributeNameIndex);

            if (null != subAttributeName && subAttributeName.equalsIgnoreCase(AttributeInfo.LINE_NUM_TABLE)) {

                LineNumberTable lineNumberTable = LineNumberTable.parse(iter);
                codeAttr.setLineNumberTable(lineNumberTable);

            } else if (null != subAttributeName && subAttributeName.equalsIgnoreCase(AttributeInfo.LOCAL_VAR_TABLE)) {

                LocalVariableTable localVariableTable = LocalVariableTable.parse(iter);
                codeAttr.setLocalVariableTable(localVariableTable);

            } else {
                throw new RuntimeException("there's other sub attribute to added : name = " + subAttributeName);
            }

        }

        return codeAttr;
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
