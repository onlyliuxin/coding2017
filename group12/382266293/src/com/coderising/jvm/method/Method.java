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
		
		Method method = new Method(clzFile, access_flags,name_index,descriptor_index);
		
		for (int i = 0; i < attrbutes_count; i++) {
			int attr_nameIndex = iter.nextU2ToInt();
			String att_name = clzFile.getConstantPool().getUTF8String(attr_nameIndex);
			iter.back(2);
			
			if (AttributeInfo.CODE.equalsIgnoreCase("Code")) {
				
				CodeAttr codeAttr = CodeAttr.parse(clzFile, iter);
				method.setCodeAttr(codeAttr);
				
			} else {
				throw new RuntimeException(att_name + " has not been implemented");
			}

		}
		

		
		return method;
		
	}
	
	
	
	



	public ByteCodeCommand[] getCmds() {		
		return this.getCodeAttr().getCmds();
	}
	
	public ClassFile getClzFile() {
		return clzFile;
	}
	
	public CodeAttr getCodeAttr() {
		return codeAttr;
	}

	public int getDescriptorIndex() {
		return descriptorIndex;
	}

	public int getNameIndex() {
		return nameIndex;
	}


	public void setCodeAttr(CodeAttr code) {
		this.codeAttr = code;
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
}
