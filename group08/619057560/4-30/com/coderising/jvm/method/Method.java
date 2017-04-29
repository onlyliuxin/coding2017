package com.coderising.jvm.method;

import java.util.ArrayList;
import java.util.List;

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

	
	
	
	
	public String toString() {
		
		ConstantPool pool = this.clzFile.getConstantPool();
		StringBuilder buffer = new StringBuilder();
		
		String name = ((UTF8Info)pool.getConstantInfo(this.nameIndex)).getValue();
		
		String desc = ((UTF8Info)pool.getConstantInfo(this.descriptorIndex)).getValue();
		
		buffer.append(name).append(":").append(desc).append("\n");
		
		buffer.append(this.codeAttr.toString(pool));
		
		return buffer.toString();
	}
	
	public static Method parse(ClassFile clzFile, ByteCodeIterator iter){
		int accessFlag = iter.nextU2ToInt();
		int nameIndex = iter.nextU2ToInt();
		int descriptorIndex = iter.nextU2ToInt();
		int attribCount = iter.nextU2ToInt();
		Method method = new Method(clzFile, accessFlag, nameIndex, descriptorIndex);
		
		for (int i = 1; i <= attribCount; i++) {
			int attrNameIndex = iter.nextU2ToInt();
			iter.back(2);
			String attrName = clzFile.getConstantPool().getUTF8String(attrNameIndex);
			if (attrName.equalsIgnoreCase(AttributeInfo.CODE)) {
				method.setCodeAttr(CodeAttr.parse(clzFile, iter));
			} else {
				throw new RuntimeException("only CODE attribute is implemented , please implement the "+ attrName);
			}
		}
		return method;
		
	}

	public ByteCodeCommand[] getCmds() {		
		return this.getCodeAttr().getCmds();
	}
	
	public List<String> getParameterList() {
		ConstantPool pool = this.clzFile.getConstantPool();
		String desc = ((UTF8Info)pool.getConstantInfo(this.descriptorIndex)).getValue();
		
		List<String> paramList = new ArrayList<>();
		
		// e.g. Ljava/util/List;Ljava/lang/String;II
		String param = desc.substring(desc.indexOf('(') + 1, desc.lastIndexOf(')'));
		if (param == null || param.isEmpty()) {
			return paramList;
		}
		while (!param.isEmpty()) {
			switch (param.charAt(0)) {
			case 'L':
				int end = param.indexOf(';');
				if (end == -1) {
					throw new RuntimeException("Cannot find ; ");
				}
				paramList.add(param.substring(1, end));
				param = param.substring(end+1);
				break;
			case 'I':
				paramList.add("I");
				param = param.substring(1);
				break;
			case 'F':
				paramList.add("F");
				param = param.substring(1);
				break;
			default:
				throw new RuntimeException(param.charAt(0) + " cannot be supported");
			}
		}
		
		return paramList;
	}
}
