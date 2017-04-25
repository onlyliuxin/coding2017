package com.coding.basic.homework_04.jvm.method;

import com.coding.basic.homework_04.jvm.attr.AttributeInfo;
import com.coding.basic.homework_04.jvm.attr.CodeAttr;
import com.coding.basic.homework_04.jvm.clz.ClassFile;
import com.coding.basic.homework_04.jvm.cmd.ByteCodeCommand;
import com.coding.basic.homework_04.jvm.util.ByteCodeIterator;

public class Method {

	private int accessFlag;
	private int nameIndex;
	private int descriptorIndex;
	private CodeAttr codeAttr;
	private ClassFile clzFile;
	
	
	public int getAccessFlag() {
		return accessFlag;
	}


	public void setAccessFlag(int accessFlag) {
		this.accessFlag = accessFlag;
	}


	public int getNameIndex() {
		return nameIndex;
	}


	public void setNameIndex(int nameIndex) {
		this.nameIndex = nameIndex;
	}


	public int getDescriptorIndex() {
		return descriptorIndex;
	}


	public void setDescriptorIndex(int descriptorIndex) {
		this.descriptorIndex = descriptorIndex;
	}


	public CodeAttr getCodeAttr() {
		return codeAttr;
	}


	public void setCodeAttr(CodeAttr codeAttr) {
		this.codeAttr = codeAttr;
	}


	private Method(int accessFlag, int nameIndex, int descriptorIndex, ClassFile clzFile){
		this.accessFlag = accessFlag;
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
		this.clzFile = clzFile;
	}
	
	
	public static Method parse(ClassFile clzFile, ByteCodeIterator iterator) {
		int accessFlag = iterator.nextU2ToInt();
		int nameIndex = iterator.nextU2ToInt();
		int descriptorIndex = iterator.nextU2ToInt();
		Method method = new Method(accessFlag, nameIndex, descriptorIndex, clzFile);
		int attributeCount = iterator.nextU2ToInt();
		if(attributeCount > 0){
			int attrNameIndex = iterator.nextU2ToInt();
			String attrName = clzFile.getConstantPool().getUTF8String(attrNameIndex);
			if(AttributeInfo.CODE.equals(attrName)){
				iterator.back(2);
				CodeAttr codeAttr = CodeAttr.parse(clzFile, iterator);
				method.setCodeAttr(codeAttr);
			}
		}
		
		return method;
	}

	public ByteCodeCommand[] getCmds() {		
		return this.getCodeAttr().getCmds();
	}
	
}
