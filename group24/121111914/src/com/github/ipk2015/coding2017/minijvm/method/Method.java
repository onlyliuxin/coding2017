package com.github.ipk2015.coding2017.minijvm.method;

import com.github.ipk2015.coding2017.minijvm.attr.AttributeInfo;
import com.github.ipk2015.coding2017.minijvm.attr.CodeAttr;
import com.github.ipk2015.coding2017.minijvm.clz.ClassFile;
import com.github.ipk2015.coding2017.minijvm.loader.ByteCodeIterator;

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
		int accessFlag = iter.nextUNToInt(2);
		int nameIndex = iter.nextUNToInt(2);
		int descriptorIndex = iter.nextUNToInt(2);
		Method method = new Method(clzFile,accessFlag,nameIndex,descriptorIndex);
		int attrCount = iter.nextUNToInt(2);
		for(int i = 0;i < attrCount;i++){
			addAttr(clzFile,method,iter);
		}
		return method;
	}
	private static void addAttr(ClassFile clzFile,Method method,ByteCodeIterator iter){
		int nameIndex = iter.nextUNToInt(2);
		iter.back(2);
		String attrName = clzFile.getConstantPool().getUTF8String(nameIndex);
		if(AttributeInfo.CODE.equalsIgnoreCase(attrName)){
			method.setCodeAttr(CodeAttr.parse(clzFile, iter));
		}else{
			throw new RuntimeException("方法的此属性不存在："+attrName);
		}
	}
}
