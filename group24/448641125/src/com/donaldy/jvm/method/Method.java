package com.donaldy.jvm.method;

import com.donaldy.jvm.attr.*;
import com.donaldy.jvm.clz.ClassFile;
import com.donaldy.jvm.constant.ConstantPool;
import com.donaldy.jvm.constant.UTF8Info;
import com.donaldy.jvm.loader.ByteCodeIterator;



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

		int attributeCount = iter.nextU2ToInt();
		System.out.println("attributeCount : " + attributeCount);

		int attrNameIndex = iter.nextU2ToInt();
		if (!"Code".equals(clzFile.getConstantPool().getUTF8String(attrNameIndex)))
			throw new RuntimeException("attributeInfo : " + attrNameIndex);

		int attrLen = iter.nextU4ToInt();
		int maxStack = iter.nextU2ToInt();
		int maxLocals = iter.nextU2ToInt();
		int codeLen = iter.nextU4ToInt();
		String code = iter.nextUxToHexString(codeLen);

		CodeAttr codeAttr = new CodeAttr(attrNameIndex, attrLen, maxStack, maxLocals, codeLen, code);

		int exceptionLen = iter.nextU2ToInt();
		System.out.println("execptionLen : " + exceptionLen);

		int attributesCount = iter.nextU2ToInt();
		System.out.println("attributeCount : " + attributesCount);

		LineNumberTable lnTable = LineNumberTable.parse(iter);
		codeAttr.setLineNumberTable(lnTable);

		LocalVariableTable lvTable = LocalVariableTable.parse(iter);
		codeAttr.setLocalVariableTable(lvTable);


		Method method = new Method(clzFile, accessFlag, nameIndex, descriptorIndex);

		method.setCodeAttr(codeAttr);
		return method;
		
	}
}
