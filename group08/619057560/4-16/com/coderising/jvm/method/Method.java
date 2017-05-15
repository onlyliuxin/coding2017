package com.coderising.jvm.method;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.attr.AttributeInfo;
import com.coderising.jvm.attr.CodeAttr;
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
		int attribCount = iter.nextU2ToInt();
		Method method = new Method(clzFile, accessFlag, nameIndex, descriptorIndex);
		
		for (int i = 1; i <= attribCount; i++) {
			int attrNameIndex = iter.nextU2ToInt();
			iter.back(2);
			String attrName = clzFile.getConstantPool().getUTF8String(attrNameIndex);
			if (attrName.equalsIgnoreCase(AttributeInfo.CODE)) {
				method.setCodeAttr(CodeAttr.parse(clzFile, iter));
			} else {
				throw new RuntimeException("only CODE attribute is implemented , please implement the "+ attrName);
			}
		}
		return method;
		
	}
}
