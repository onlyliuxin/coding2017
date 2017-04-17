package com.coderising.jvm.method;

import com.coderising.jvm.clz.ClassFile;

import javax.management.RuntimeErrorException;

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
		int accessFlag = iter.nextU2toInt();
		int nameIndex = iter.nextU2toInt();
		int descriptorIndex = iter.nextU2toInt();
		int attributesCount = iter.nextU2toInt();
		
		System.out.println("Method AttributesCount:"+ attributesCount);
		
		Method m = new Method(clzFile, accessFlag, nameIndex, descriptorIndex);
		
		for (int i = 0; i < attributesCount; i++) {
			int attrNameIndex = iter.nextU2toInt();
			iter.back(2);		//解析attrNameIndex就是下面一个属性的一部分，所以下面要解析属性，指针应该回退。
			String attrName = clzFile.getConstantPool().getUTF8String(attrNameIndex);
			System.out.println("attrName:"+attrName);
			if (AttributeInfo.CODE.equals(attrName)) {
				CodeAttr codeAttr = CodeAttr.parse(clzFile, iter);
				m.setCodeAttr(codeAttr);
			} else {
				throw new RuntimeException("The attribute '"+ attrName +"' has not been implemented, "
						+ "the only implemented attribute is Code attribute !");
			}
			
		}
		return m;
		
	}
}
