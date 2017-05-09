package com.coderising.jvm.method;

import com.coderising.jvm.attr.AttributeInfo;
import com.coderising.jvm.attr.CodeAttr;
import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.cmd.ByteCodeCommand;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.UTF8Info;
import com.coderising.jvm.loader.ByteCodeIterator;


public class Method {

    private int accessFlag;
    private int nameIndex;
    private int descriptorIndex;

    private CodeAttr codeAttr;

    private ClassFile clzFile;


    public ClassFile getClzFile() {
        return clzFile;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }

    public CodeAttr getCodeAttr() {
        return codeAttr;
    }

    public void setCodeAttr(CodeAttr code) {
        this.codeAttr = code;
    }

    public Method(ClassFile clzFile, int accessFlag, int nameIndex, int descriptorIndex) {
        this.clzFile = clzFile;
        this.accessFlag = accessFlag;
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
    }


    public static Method parse(ClassFile clzFile, ByteCodeIterator iter) {

        int accessFlag = iter.nextU2Int();
        int nameIndex = iter.nextU2Int();
        int descriptorIndex = iter.nextU2Int();

        Method method = new Method(clzFile, accessFlag, nameIndex, descriptorIndex);

        int attirbutesCount = iter.nextU2Int();
        //解析属性
        for (int i = 0; i < attirbutesCount; i++) {
            int attributeNameIndex = iter.nextU2Int();
            iter.back(2);
            String attributeName = ((UTF8Info) clzFile.getConstantPool().getConstantInfo(attributeNameIndex)).getValue();
            //解析CODE
            if (null != attributeName && attributeName.equalsIgnoreCase(AttributeInfo.CODE)) {
                CodeAttr codeAttr = CodeAttr.parse(clzFile, iter);
                method.setCodeAttr(codeAttr);

            } else {
                throw new RuntimeException("there's other method attribute to be implemented:attributeName = " + attributeName);
            }
        }

        return method;

    }
	
	public ByteCodeCommand[] getCmds() {
		return this.getCodeAttr().getCmds();
	}
	
	public String toString() {
		
		ConstantPool pool = this.clzFile.getConstantPool();
		StringBuilder buffer = new StringBuilder();
		
		String name = ((UTF8Info)pool.getConstantInfo(this.nameIndex)).getValue();
		
		String desc = ((UTF8Info)pool.getConstantInfo(this.descriptorIndex)).getValue();
		
		buffer.append(name).append(":").append(desc).append("\n");
		
		buffer.append(this.codeAttr.toString(pool));
		
		return buffer.toString();
	}
}
