package com.coderising.jvm.method;

import com.coderising.jvm.clz.ClassFile;

import java.io.UnsupportedEncodingException;

import com.coderising.jvm.attr.AttributeInfo;
import com.coderising.jvm.attr.CodeAttr;
import com.coderising.jvm.constant.ConstantInfo;
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
		Method method=new Method(clzFile,iter.nextU2toInt(),iter.nextU2toInt(),iter.nextU2toInt());
		int atrrNum=iter.nextU2toInt();
		for(int i=0;i<atrrNum;i++){
			String attrName=clzFile.getConstantPool().getUTF8String(iter.nextU2toInt());
			iter.back(2);
			if(AttributeInfo.CODE.equals(attrName)){
				method.setCodeAttr(CodeAttr.parse(clzFile, iter));
			}
			else{
				throw new RuntimeException("only CODE attribute is implemented , please implement the "+ attrName);
			}
		}
		return method;
		
	}
}
