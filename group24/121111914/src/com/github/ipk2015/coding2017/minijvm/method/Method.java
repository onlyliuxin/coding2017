package com.github.ipk2015.coding2017.minijvm.method;

import java.util.ArrayList;
import java.util.List;

import com.github.ipk2015.coding2017.minijvm.attr.AttributeInfo;
import com.github.ipk2015.coding2017.minijvm.attr.CodeAttr;
import com.github.ipk2015.coding2017.minijvm.clz.ClassFile;
import com.github.ipk2015.coding2017.minijvm.cmd.ByteCodeCommand;
import com.github.ipk2015.coding2017.minijvm.constant.ConstantPool;
import com.github.ipk2015.coding2017.minijvm.constant.UTF8Info;
import com.github.ipk2015.coding2017.minijvm.loader.ByteCodeIterator;

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
		int accessFlag = iter.nextUNToInt(2);
		int nameIndex = iter.nextUNToInt(2);
		int descriptorIndex = iter.nextUNToInt(2);
		Method method = new Method(clzFile,accessFlag,nameIndex,descriptorIndex);
		int attrCount = iter.nextUNToInt(2);
		for(int i = 0;i < attrCount;i++){
			addAttr(clzFile,method,iter);
		}
		return method;
	}
	private static void addAttr(ClassFile clzFile,Method method,ByteCodeIterator iter){
		int nameIndex = iter.nextUNToInt(2);
		iter.back(2);
		String attrName = clzFile.getConstantPool().getUTF8String(nameIndex);
		if(AttributeInfo.CODE.equalsIgnoreCase(attrName)){
			method.setCodeAttr(CodeAttr.parse(clzFile, iter));
		}else{
			throw new RuntimeException("方法的此属性不存在："+attrName);
		}
	}
	public ByteCodeCommand[] getCmds() {		
		return this.getCodeAttr().getCmds();
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
	private String getParamAndReturnType(){
		UTF8Info  nameAndTypeInfo = (UTF8Info)this.getClzFile()
				.getConstantPool().getConstantInfo(this.getDescriptorIndex());
		return nameAndTypeInfo.getValue();
	}
	public List<String> getParameterList(){
		
		// e.g. (Ljava/util/List;Ljava/lang/String;II)V
		String paramAndType = getParamAndReturnType();
		
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
