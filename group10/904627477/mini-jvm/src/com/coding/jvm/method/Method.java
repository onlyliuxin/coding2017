package com.coding.jvm.method;

import com.coding.jvm.attr.AttributeInfo;
import com.coding.jvm.attr.CodeAttr;
import com.coding.jvm.clz.ClassFile;
import com.coding.jvm.loader.ByteCodeIterator;




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
		Method method = new Method(clzFile, iter.nextU2ToInt(), iter.nextU2ToInt(), iter.nextU2ToInt());
		int attrCount = iter.nextU2ToInt();
		for (int i = 0; i < attrCount; i++) {
			String attrInfoName = clzFile.getConstantPool().getUTF8String(iter.readU2ToInt());
			switch (attrInfoName) {
			case AttributeInfo.CODE:
				method.setCodeAttr(CodeAttr.parse(clzFile, iter));
				break;
			default:
				AttributeInfo.parse(iter);
				break;
			}
		}
		return method;
		
	}
}
