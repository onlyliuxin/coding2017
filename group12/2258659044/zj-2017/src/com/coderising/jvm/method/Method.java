package com.coderising.jvm.method;

import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.attr.AttributeInfo;
import com.coderising.jvm.attr.CodeAttr;
import com.coderising.jvm.cmd.ByteCodeCommand;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.UTF8Info;
import com.coderising.jvm.loader.ByteCodeIterator;

public class Method {
	
	private int accessFlag;
	private int nameIndex;
	private int descriptorIndex;
	private ConstantPool pool;	
	private List<AttributeInfo> attributeInfos = new ArrayList<>();
	
	
	public Method(ConstantPool pool,int accessFlag, int nameIndex, int descriptorIndex) {
		this.pool = pool;
		this.accessFlag = accessFlag;
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
	}
	
	public static Method parse(ConstantPool  pool, ByteCodeIterator iter){
		
		int accessFlag = iter.nextU2ToInt();
		int nameIndex = iter.nextU2ToInt();
		int descriptorIndex = iter.nextU2ToInt();
		
		Method method = new Method(pool,accessFlag,nameIndex,descriptorIndex);
		method.setAttributeInfos(AttributeInfo.parseAttributes(pool,iter));
		
		return method;		
	}
	
	public ByteCodeCommand[] getCmds() {
		
		for (AttributeInfo attributeInfo : attributeInfos) {
			if(attributeInfo instanceof CodeAttr){
				return ((CodeAttr) attributeInfo).getCmds();
			}
		}
		return null;
	}
	
	private String getParamAndReturnType(){
		UTF8Info  nameAndTypeInfo = (UTF8Info)pool.getClzFile()
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
	
	public int getAccessFlag() {
		return accessFlag;
	}

	public void setAccessFlag(int accessFlag) {
		this.accessFlag = accessFlag;
	}
	
	public ConstantPool getPool() {
		return pool;
	}

	public int getNameIndex() {
		return nameIndex;
	}
	public int getDescriptorIndex() {
		return descriptorIndex;
	}
	
	
	public List<AttributeInfo> getAttributeInfos() {
		return attributeInfos;
	}

	public void setAttributeInfos(List<AttributeInfo> attributeInfos) {
		this.attributeInfos = attributeInfos;
	}
}
