package com.github.eulerlcs.jmr.jvm.method;

import com.github.eulerlcs.jmr.jvm.attr.CodeAttr;
import com.github.eulerlcs.jmr.jvm.clz.ClassFile;
import com.github.eulerlcs.jmr.jvm.loader.ByteCodeIterator;

public class Method {

	private int accessFlag;
	private int nameIndex;
	private int descriptorIndex;

	private com.github.eulerlcs.jmr.jvm.attr.CodeAttr codeAttr;

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

	public static Method parse(ClassFile clzFile, ByteCodeIterator iter) {
		return null;

	}
}
