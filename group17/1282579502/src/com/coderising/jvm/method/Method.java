package com.coderising.jvm.method;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.cmd.ByteCodeCommand;
import com.coderising.jvm.cmd.CommandParser;

import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.attr.AttributeInfo;
import com.coderising.jvm.attr.CodeAttr;
import com.coderising.jvm.attr.InvalidAttributeInfoException;
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

	//ByteCodeCommand [] cmds = initMethod.getCmds();
	public ByteCodeCommand[] getCmds(){
		//System.out.println(codeAttr.getCode());
		return CommandParser.parse(clzFile, codeAttr.getCode());
	}
	
	
	public static Method parse(ClassFile clzFile, ByteCodeIterator iter) throws InvalidMethodInfoException{
		
		int accessFlagIndex = iter.getNextNBytesInteger(2);
		int nameIndex = iter.getNextNBytesInteger(2);
		int descriptorIndex = iter.getNextNBytesInteger(2);
		int attributeCount = iter.getNextNBytesInteger(2);
		System.out.println("access flag index: " + accessFlagIndex ); 
		System.out.println("name Index: " + nameIndex + " name value: " + clzFile.getConstantPool().getUTF8String(nameIndex)); 
		System.out.println("descriptor Index: " + descriptorIndex + " descriptor value: " + clzFile.getConstantPool().getUTF8String(descriptorIndex)); 
		System.out.println("attributeCount: " + attributeCount ); 
		Method m = new Method(clzFile, accessFlagIndex, nameIndex, descriptorIndex);
		try{
			AttributeInfo attr = AttributeInfo.parse(clzFile, iter);
			if(attr instanceof CodeAttr){
				System.out.println("set code attribute.");
				m.setCodeAttr((CodeAttr)attr);
			}
			else{
				throw new InvalidAttributeInfoException("unsupported attribute type. Current only CodeAttr supported");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return m;
		
	}
	
	public List<String> getParameterList(){
		
		List<String> params = new ArrayList<>();
		String paramString  = clzFile.getConstantPool().getUTF8String(descriptorIndex);
		int l = paramString.indexOf('(');
		int r = paramString.indexOf(')');
		if(l >=0 && r >=0){
			paramString = paramString.substring(l+1, r);
		}
		String[] paramArray = paramString.split(";");
		for(String str: paramArray){
			if(str.startsWith("L")){
				params.add(str);
			}
			else if(str.startsWith("I")){
				char[] cArray = str.toCharArray();
				for(char c: cArray){
					params.add(String.valueOf(c));
				}
			}
			else{
				throw new RuntimeException("Uncatagorized parameter: " + str);
			}
		}
		return params;
	}

	@Override
	public String toString() {
		return "Method [accessFlag=" + accessFlag + ", nameIndex=" + nameIndex
				+ " name val: " + clzFile.getConstantPool().getUTF8String(nameIndex) +", descriptorIndex=" + descriptorIndex +" desc val: " + clzFile.getConstantPool().getUTF8String(descriptorIndex) + "]";
	}
}
