package com.coderising.jvm.method;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.cmd.ByteCodeCommand;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.attr.AttributeInfo;
import com.coderising.jvm.attr.CodeAttr;
import com.coderising.jvm.constant.ConstantInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.MethodRefInfo;
import com.coderising.jvm.constant.NameAndTypeInfo;
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
	public ByteCodeCommand[] getCmds() {		
		return this.getCodeAttr().getCmds();
	}
	public List<String> getParams(){
		List<String> params=new ArrayList<String>();
		MethodRefInfo methodInfo=(MethodRefInfo) this.clzFile.getConstantPool().getConstantInfo(descriptorIndex);
		
		String paramAndReturnType=methodInfo.getParamAndReturnType();
		//(Ljava/lang/String;I)V
		int frist=paramAndReturnType.indexOf("(");
		int last=paramAndReturnType.lastIndexOf(")");
		String param=paramAndReturnType.substring(frist+1, last);
		if(null==param|| param.equals("")){
			return null;
		}
		int pos=0;
		while(!param.equals("")){
			if(param.charAt(pos)=='L'){
				int end=param.indexOf(";");
				if(end==0){
					new Exception("not find this object");
				}
				params.add(param.substring(pos+1, end));
				pos=end+1;
			}
			else if(param.charAt(pos)=='F'){
				params.add("F");
				pos++;
				
			}
			else if(param.charAt(pos)=='I'){
				params.add("I");
				pos++;
			}
			param.substring(pos);
		}
		return params;
	}
}
