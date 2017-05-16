package com.coderising.jvm.method;

import com.coderising.jvm.attr.AttributeInfo;
import com.coderising.jvm.attr.CodeAttr;
import com.coderising.jvm.attr.LineNumberTable;
import com.coderising.jvm.attr.LocalVariableTable;
import com.coderising.jvm.attr.StackMapTable;
import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.cmd.ByteCodeCommand;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.loader.ByteCodeIterator;



public class Method {
	
	private int accessFlag;
	private int nameIndex;
	private int descriptorIndex;
	
	private CodeAttr codeAttr;
	
	private ClassFile clzFile;
	
	public int getAccessFlag() {
		return accessFlag;
	}
	
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

	public Method(ClassFile clzFile,int accessFlag, int nameIndex, int descriptorIndex) {
		this.clzFile = clzFile;
		this.accessFlag = accessFlag;
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
	}

	
	
	
	
	public static Method parse(ClassFile clzFile, ByteCodeIterator iter){
		int accessFlag = iter.nextU2ToInt();
		int nameIndex = iter.nextU2ToInt();
		int descriptorIndex = iter.nextU2ToInt();
		Method method = new Method(clzFile, accessFlag, nameIndex, descriptorIndex);
		int attrCount = iter.nextU2ToInt();
		ConstantPool pool = clzFile.getConstantPool();
		for (int i = 1; i <= attrCount; i++) {
			int attrNameIndex = iter.nextU2ToInt();
			String attrName = pool.getUTF8String(attrNameIndex);
			iter.back(2);
			if(AttributeInfo.CODE.equalsIgnoreCase(attrName)){
				method.setCodeAttr(CodeAttr.parse(clzFile, iter));
			}else {
				//TODO
				throw new RuntimeException("Method.parse not implement " + attrName);
			}
		}
		return method;
		
	}

	public ByteCodeCommand[] getCmds() {
		return this.getCodeAttr().getCmds();
	}
}
