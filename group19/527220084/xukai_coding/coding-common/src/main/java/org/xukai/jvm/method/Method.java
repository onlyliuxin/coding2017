package org.xukai.jvm.method;


import org.xukai.jvm.attr.AttributeInfo;
import org.xukai.jvm.attr.CodeAttr;
import org.xukai.jvm.clz.ClassFile;
import org.xukai.jvm.cmd.ByteCodeCommand;
import org.xukai.jvm.constant.UTF8Info;
import org.xukai.jvm.loader.ByteCodeIterator;

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

	public String toString(){
		return getMethodName() +":"+  getMethodDescription();
	}

	public String getMethodName(){
		return ((UTF8Info)clzFile.getConstantPool().getConstantInfo(this.nameIndex)).getValue();
	}

	public String getMethodDescription(){
		return ((UTF8Info)clzFile.getConstantPool().getConstantInfo(this.descriptorIndex)).getValue();
	}

	public ByteCodeCommand[] getCmds() {
		return this.getCodeAttr().getCmds();
	}
	
	
	
	public static Method parse(ClassFile clzFile, ByteCodeIterator iter){
		int flages = iter.nextToInt(2);
		int nameIndex = iter.nextToInt(2);
		int descriptorIndex = iter.nextToInt(2);
		Method method = new Method(clzFile, flages, nameIndex, descriptorIndex);
		int attributeCount = iter.nextToInt(2);
		if (attributeCount > 0) {
			for (int i = 0; i < attributeCount; i++) {
				AttributeInfo info = AttributeInfo.parseAttribute(iter, clzFile);
				if (info instanceof CodeAttr) {
					method.setCodeAttr((CodeAttr) info);
				}
			}
		}
		clzFile.addMethod(method);
		return method;
		
	}

	public List<String> getParameterList(){

		// e.g. (Ljava/util/List;Ljava/lang/String;II)V
		String paramAndType = getMethodDescription();

		int first = paramAndType.indexOf("(");
		int last = paramAndType.lastIndexOf(")");
		// e.g. Ljava/util/List;Ljava/lang/String;II
		String param = paramAndType.substring(first+1, last);

		List<String> paramList = new ArrayList<String>();

		if((null == param) || "".equals(param)){
			return paramList;
		}

		while(!param.equals("")){

			int pos = 0;
			// 这是一个对象类型
			if(param.charAt(pos) == 'L'){

				int end = param.indexOf(";");

				if(end == -1){
					throw new RuntimeException("can't find the ; for a object type");
				}
				paramList.add(param.substring(pos+1,end));

				pos = end + 1;

			}
			else if(param.charAt(pos) == 'I'){
				// int
				paramList.add("I");
				pos ++;

			}
			else if(param.charAt(pos) == 'F'){
				// float
				paramList.add("F");
				pos ++;

			} else{
				throw new RuntimeException("the param has unsupported type:" + param);
			}

			param = param.substring(pos);

		}
		return paramList;

	}


}
