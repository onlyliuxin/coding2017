package com.coderising.jvm.method;

import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.attr.AttributeInfo;
import com.coderising.jvm.attr.CodeAttr;
import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.loader.ByteCodeIterator;

public class Method {
	private int accessFlag;// u2 access_flags
	private int nameIndex;// u2 name_index
	private int descriptorIndex;// u2 descriptor_index
	private int attrCount;// u2 attributes_count
	/*
	 * attributes[attributes_count];
	 */
	private List<AttributeInfo> attributeInfos = new ArrayList<AttributeInfo>();

	public Method() {}

	public Method(int accessFlag, int nameIndex, int descriptorIndex,
			int attrCount) {
		super();
		this.accessFlag = accessFlag;
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
		this.attrCount = attrCount;
	}

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
	 * @return Method
	 */
	public static Method parse(ConstantPool cp, ByteCodeIterator itr) {
		int accessFlag = itr.nextU2toInt();
		int nameIndex = itr.nextU2toInt();
		int descriptorIndex = itr.nextU2toInt();
		int attrCount = itr.nextU2toInt();
		Method method = new Method(accessFlag, nameIndex, descriptorIndex,
				attrCount);
		for (int i = 0; i < attrCount; i++) {
			method.addAttributeInfo(AttributeInfo.parse(cp, itr));
		}
		return method;
	}

	/**
	 * 专门用来获取code属性的方法
	 * 
	 * @return
	 */
	public CodeAttr getCodeAttr() {
		for (int i = 0; i < attributeInfos.size(); i++) {
			AttributeInfo attributeInfo = attributeInfos.get(i);
			if(AttributeInfo.CODE.equals(attributeInfo.getType())){
				return (CodeAttr)attributeInfo;
			}
		}
		return null;
	}

	public void addAttributeInfo(AttributeInfo a) {
		this.attributeInfos.add(a);
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
