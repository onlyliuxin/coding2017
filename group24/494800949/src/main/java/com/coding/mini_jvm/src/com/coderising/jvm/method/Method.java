package com.coding.mini_jvm.src.com.coderising.jvm.method;


import com.coding.mini_jvm.src.com.coderising.jvm.attr.CodeAttr;
import com.coding.mini_jvm.src.com.coderising.jvm.clz.ClassFile;
import com.coding.mini_jvm.src.com.coderising.jvm.cmd.ByteCodeCommand;
import com.coding.mini_jvm.src.com.coderising.jvm.constant.UTF8Info;
import com.coding.mini_jvm.src.com.coderising.jvm.loader.ByteCodeIterator;

import java.util.ArrayList;
import java.util.List;

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

	public List<String> getParameterList() {
		String desc = clzFile.getConstantPool().getUTF8String(descriptorIndex);
		int startIndex = desc.indexOf("(") + 1;
		int endIndex = desc.lastIndexOf(")") ;
		String paramStr = desc.substring(startIndex, endIndex);
		List<String> params = new ArrayList<>();
		if ("".equals(paramStr)) {
			return params;
		}
		int pos = 0;
		int length = paramStr.length();
		while (pos < length) {
			if ('L' == paramStr.charAt(pos) || '['== paramStr.charAt(pos)) {
				for (int i = pos; i < length; i++) {
					if (paramStr.charAt(i) == ';') {
						params.add(paramStr.substring(pos, i));
						pos = ++i;
						break;
					}
				}
			} else if ('I' == paramStr.charAt(pos)) {
				params.add("int");
				pos++;
			} else if ('F' ==  paramStr.charAt(pos)) {
				params.add("float");
				pos++;
			} else {
				throw new RuntimeException("not impl param type ["+ paramStr.charAt(pos) +"] yet");
			}
		}
		return params;
	}

	public boolean isStatic() {
		return (accessFlag & 0x0004) != 0;
	}

	public String getName() {
		UTF8Info utf8Info = (UTF8Info)clzFile.getConstantPool().getConstantInfo(nameIndex);
		return utf8Info.getValue();
	}
}
