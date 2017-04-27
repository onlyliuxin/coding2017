package week567_miniJVM.method;

import week567_miniJVM.clz.ClassFile;
import week567_miniJVM.attr.AttributeInfo;
import week567_miniJVM.attr.CodeAttr;
import week567_miniJVM.constant.ConstantPool;
import week567_miniJVM.constant.UTF8Info;
import week567_miniJVM.loader.ByteCodeIterator;



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
		return null;
		
	}
}

