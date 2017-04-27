package com.coderising.jvm.method;

import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.attr.AttributeInfo;
import com.coderising.jvm.attr.CodeAttr;
import com.coderising.jvm.cmd.ByteCodeCommand;
import com.coderising.jvm.constant.ConstantPool;
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
