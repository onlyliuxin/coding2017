package jvm.method;

import jvm.classfile.ClassFile;
import jvm.attr.CodeAttr;
import jvm.util.ByteCodeIterator;


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

	public static Method parse(ClassFile clzFile, ByteCodeIterator iterator) {
		int access = iterator.nextU2ToInt();
		int name = iterator.nextU2ToInt();
		int descriptor = iterator.nextU2ToInt();
		return new Method(clzFile, access, name, descriptor);
	}
}
