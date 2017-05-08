package org.xukai.jvm.method;


import org.xukai.jvm.attr.AttributeInfo;
import org.xukai.jvm.attr.CodeAttr;
import org.xukai.jvm.clz.ClassFile;
import org.xukai.jvm.constant.UTF8Info;
import org.xukai.jvm.loader.ByteCodeIterator;

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

	public String toString(){
		return getMethodName() +":"+  getMethodDescription();
	}

	public String getMethodName(){
		return ((UTF8Info)clzFile.getConstantPool().getConstantInfo(this.nameIndex)).getValue();
	}

	public String getMethodDescription(){
		return ((UTF8Info)clzFile.getConstantPool().getConstantInfo(this.descriptorIndex)).getValue();
	}
	
	
	
	public static Method parse(ClassFile clzFile, ByteCodeIterator iter){
		int flages = iter.nextToInt(2);
		int nameIndex = iter.nextToInt(2);
		int descriptorIndex = iter.nextToInt(2);
		Method method = new Method(clzFile, flages, nameIndex, descriptorIndex);
		int attributeCount = iter.nextToInt(2);
		if (attributeCount > 0) {
			for (int i = 0; i < attributeCount; i++) {
				AttributeInfo info = AttributeInfo.parseAttribute(iter, clzFile);
				if (info instanceof CodeAttr) {
					method.setCodeAttr((CodeAttr) info);
				}
			}
		}
		clzFile.addMethod(method);
		return method;
		
	}

	public static void main(String[] args) {
		CodeAttr codeAttr = new CodeAttr(1, 1, 1, 1, 1, "");
		System.out.println(codeAttr instanceof AttributeInfo);
		AttributeInfo codeAttr2 = new CodeAttr(1, 1, 1, 1, 1, "");
		System.out.println(codeAttr2 instanceof CodeAttr);
	}


}
