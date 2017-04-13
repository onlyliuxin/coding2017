package com.coderising.jvm.method;

import java.util.List;

import com.coderising.jvm.attr.AttributeInfo;
import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.loader.ByteCodeIterator;

public class Method {
	private int accessFlag;// u2 access_flags
	private int nameIndex;// u2 name_index
	private int descriptorIndex;// u2 descriptor_index
	private int attrCount;//u2 attributes_count
	private List<AttributeInfo> attributeInfos;//attribute_info attributes[attributes_count];

	private ClassFile clzFile;

	/**
	 * 读取方法 method_info {<br/>
	 * u2 access_flags,<br/>
	 * u2 name_index,<br/>
	 * u2 descriptor_index,<br/>
	 * u2 attributes_count,<br/>
	 * attribute_info attributes[attributes_count]<br/>
	 * }
	 * 
	 * @param itr
	 * @return
	 */
	public static Method parse(ConstantPool cp, ByteCodeIterator itr) {
		int accessFlag = itr.nextU2toInt();
		int nameIndex= itr.nextU2toInt();
		int descriptorIndex = itr.nextU2toInt();
		int attrCount = itr.nextU2toInt();
		
		return null;
	}

	/*
	 * getter setter
	 */
	public int getAccessFlag() {
		return accessFlag;
	}

	public void setAccessFlag(int accessFlag) {
		this.accessFlag = accessFlag;
	}

	public int getNameIndex() {
		return nameIndex;
	}

	public void setNameIndex(int nameIndex) {
		this.nameIndex = nameIndex;
	}

	public int getDescriptorIndex() {
		return descriptorIndex;
	}

	public void setDescriptorIndex(int descriptorIndex) {
		this.descriptorIndex = descriptorIndex;
	}

	public List<AttributeInfo> getAttributeInfos() {
		return attributeInfos;
	}

	public void setAttributeInfos(List<AttributeInfo> attributeInfos) {
		this.attributeInfos = attributeInfos;
	}

	public ClassFile getClzFile() {
		return clzFile;
	}

	public int getAttrCount() {
		return attrCount;
	}

	public void setAttrCount(int attrCount) {
		this.attrCount = attrCount;
	}

}
