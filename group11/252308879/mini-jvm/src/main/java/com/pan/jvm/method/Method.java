package com.pan.jvm.method;


import com.pan.jvm.attr.AttributeInfo;
import com.pan.jvm.attr.CodeAttr;
import com.pan.jvm.clz.ClassFile;
import com.pan.jvm.loader.ByteCodeIterator;

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
		int accessFlags = iter.nextU2ToInt();
		int nameIndex = iter.nextU2ToInt();
		int descriptorIndex = iter.nextU2ToInt();
		int attrCount = iter.nextU2ToInt();

		System.out.println("Method Attributes Count: " + attrCount);
		Method method = new Method(clzFile, accessFlags, nameIndex, descriptorIndex);
		if (attrCount > 0){
			for (int i = 1; i <= attrCount; i++) {
				int attrNameIndex = iter.nextU2ToInt();
				String attrName = clzFile.getConstantPool().getUTF8String(attrNameIndex);
				iter.back(2); // 回退两个，便于Code 中读取属性
				if (AttributeInfo.CODE.equalsIgnoreCase(attrName)){
					CodeAttr codeAttr = CodeAttr.parse(clzFile, iter);
					method.setCodeAttr(codeAttr);
				}else {
					throw new RuntimeException("Current Has CODE. Not Support Other");
				}
			}
		}
		return method;
		
	}
}
