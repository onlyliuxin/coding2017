package com.coding.mini_jvm.src.com.coderising.jvm.method;


import com.coding.mini_jvm.src.com.coderising.jvm.attr.CodeAttr;
import com.coding.mini_jvm.src.com.coderising.jvm.clz.ClassFile;
import com.coding.mini_jvm.src.com.coderising.jvm.cmd.ByteCodeCommand;
import com.coding.mini_jvm.src.com.coderising.jvm.loader.ByteCodeIterator;

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
		int accessFlag = iter.readTwoBytesToInt();
		int nameIndex = iter.readTwoBytesToInt();
		int descIndex = iter.readTwoBytesToInt();
		Method method = new Method(clzFile, accessFlag, nameIndex, descIndex);
		int attrCount = iter.readTwoBytesToInt();
		if (attrCount > 1)
			throw new RuntimeException("other attrbute not impl yet");
		for (int i = 0; i < attrCount; i++) {
			int attrNameIndex = iter.readTwoBytesToInt();
			if ("Code".equals(clzFile.getConstantPool().getUTF8String(attrNameIndex))) {
				CodeAttr codeAttr = CodeAttr.parse(clzFile, iter);
				method.setCodeAttr(codeAttr);
			} else
				throw new RuntimeException(" attribute[ " + attrNameIndex + " ] not impl yet");
		}
		return method;
	}

	public ByteCodeCommand[] getCmds() {
		return this.getCodeAttr().getCmds();
	}
}
