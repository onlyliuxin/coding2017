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

	public String toString() {
		
		ConstantPool pool = this.clzFile.getConstantPool();
		StringBuilder buffer = new StringBuilder();
		
		String name = ((UTF8Info)pool.getConstantInfo(this.nameIndex)).getValue();
		
		String desc = ((UTF8Info)pool.getConstantInfo(this.descriptorIndex)).getValue();
		
		buffer.append(name).append(":").append(desc).append("\n");
		
		buffer.append(this.codeAttr.toString(pool));
		
		return buffer.toString();
	}
	
	public static Method parse(ClassFile clzFile, ByteCodeIterator iter){

		int accessFlags = iter.nextU2ToInt();
		int nameIndex = iter.nextU2ToInt();
		int descriptorIndex = iter.nextU2ToInt();
		int attributesCount = iter.nextU2ToInt();

		String name = clzFile.getConstantPool().getUTF8String(nameIndex);
		System.out.println("method <" + name + "> attribute count : "+ attributesCount);

		String descriptor = clzFile.getConstantPool().getUTF8String(descriptorIndex);
		System.out.println("method <" + name + "> descriptor :" + descriptor);

		Method method = new Method(clzFile, accessFlags, nameIndex, descriptorIndex);
		for (int i = 0; i < attributesCount; i++) {

			int attrNameIndex = iter.nextU2ToInt();
			String attrName = clzFile.getConstantPool().getUTF8String(attrNameIndex);
			iter.back(2);

			if (AttributeInfo.CODE.equals(attrName)){
				CodeAttr codeAttr = CodeAttr.parse(clzFile, iter);
				method.setCodeAttr(codeAttr);
			} else {
				throw new RuntimeException(attrName + " attribute has not been implemented");
			}
		}

		return method;
	}

	public ByteCodeCommand[] getCmds() {
		return this.getCodeAttr().getCmds();
	}
}
