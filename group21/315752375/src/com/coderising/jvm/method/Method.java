package com.coderising.jvm.method;

import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.cmd.ByteCodeCommand;
import com.coderising.jvm.attr.AttributeInfo;
import com.coderising.jvm.attr.CodeAttr;
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

	public Method(ClassFile clzFile, int accessFlag, int nameIndex,
			int descriptorIndex) {
		this.clzFile = clzFile;
		this.accessFlag = accessFlag;
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
	}

	public static Method parse(ClassFile clzFile, ByteCodeIterator iter) {
		int accessFlag = iter.nextU2ToInt();
		int nameIndex = iter.nextU2ToInt();
		int descriptorIndex = iter.nextU2ToInt();
		int attributesCount = iter.nextU2ToInt();
		Method method = new Method(clzFile, accessFlag, nameIndex,
				descriptorIndex);
		for (int i = 0; i < attributesCount; i++) {
			int attrNameIndex = iter.nextU2ToInt();
			String attrName = clzFile.getConstantPool().getUTF8String(
					attrNameIndex);
			iter.back(2);
			if (AttributeInfo.CODE.equals(attrName)) {
				CodeAttr codeAttr = CodeAttr.parse(clzFile, iter);
				method.setCodeAttr(codeAttr);
			} else {
				throw new RuntimeException("attribute has not implemented yet");
			}
		}

		return method;

	}

	public ByteCodeCommand[] getCmds() {
		return this.getCodeAttr().getCmds();
	}
	private String getParamAndReturnType() {
		UTF8Info nameAndTypeInfo=(UTF8Info)this.getClzFile().getConstantPool().getConstantInfo(this.getDescriptorIndex());
		return nameAndTypeInfo.getValue();
	}
	public List<String> getParameterList() {
		String paramAndType=getParamAndReturnType();
		int first =paramAndType.indexOf("(");
		int last=paramAndType.lastIndexOf(")");
		String param=paramAndType.substring(first+1,last);
		List<String> paramList=new ArrayList<String>();
		if(param==null||param.equals("")){
			return paramList;
		}
		
		while(!param.equals("")){
			int pos=0;
			if(param.charAt(pos)=='L'){
				int end=param.indexOf(";");
				if(end==-1){
					throw new RuntimeException("can't find ;");
				}
				paramList.add(param.substring(pos+1,end));
				pos=end+1;
			}
			else if(param.charAt(pos)=='I'||param.charAt(pos)=='F'){
				paramList.add(""+param.charAt(pos));
				pos++;
			}else {
				throw new RuntimeException("param"+param.charAt(pos)+" is not supported yet");
			}
			param = param.substring(pos);
		}
		return paramList;
	}
	public String toString() {

		ConstantPool pool = this.clzFile.getConstantPool();
		StringBuilder buffer = new StringBuilder();

		String name = ((UTF8Info) pool.getConstantInfo(this.nameIndex))
				.getValue();

		String desc = ((UTF8Info) pool.getConstantInfo(this.descriptorIndex))
				.getValue();

		buffer.append(name).append(":").append(desc).append("\n");

		buffer.append(this.codeAttr.toString(pool));

		return buffer.toString();
	}
}
