package com.coderising.jvm.method;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.attr.AttributeInfo;
import com.coderising.jvm.attr.CodeAttr;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.UTF8Info;
import com.coderising.jvm.field.Field;
import com.coderising.jvm.loader.ByteCodeIterator;
import com.sun.org.apache.bcel.internal.classfile.Code;



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
		
		int access_flags = iter.nextU2ToInt();
		int name_index = iter.nextU2ToInt();
		int descriptor_index = iter.nextU2ToInt();
		int attrbutes_count = iter.nextU2ToInt();
		System.out.println("count1 = " + attrbutes_count);
		
		Method method = new Method(clzFile, access_flags,name_index,descriptor_index);
		
		for (int i = 0; i < attrbutes_count; i++) {
			int attr_nameIndex = iter.nextU2ToInt();
			String att_name = clzFile.getConstantPool().getUTF8String(attr_nameIndex);
			iter.back(2);
			
			if (AttributeInfo.CODE.equalsIgnoreCase("Code")) {
				CodeAttr codeAttr = CodeAttr.parse(clzFile, iter);
				method.setCodeAttr(codeAttr);
			} else {
				throw new RuntimeException(att_name + "has not been implemented");
			}

		}
		

		
		return method;
		
	}
}
