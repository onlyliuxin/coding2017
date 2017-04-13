package com.coderising.jvm.field;

import java.util.List;

import com.coderising.jvm.attr.AttributeInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.loader.ByteCodeIterator;

public class Field {
	private int accessFlag;// u2 access_flags
	private int nameIndex;// u2 name_index
	private int descriptorIndex;// u2 descriptor_index
	private int attrCount;//u2 attributes_count
	private List<AttributeInfo> attributeInfos;//attribute_info attributes[attributes_count];

	public Field(){}
	public Field(int accessFlag, int nameIndex, int descriptorIndex,
			int attrCount) {
		super();
		this.accessFlag = accessFlag;
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
		this.attrCount = attrCount;
	}

	/**
	 * 读取字段<br/>
	 * field_info {<br/>
	 * u2 access_flags,<br/>
	 * u2 name_index,<br/>
	 * u2 descriptor_index,<br/>
	 * u2 attributes_count,<br/>
	 * attribute_info attributes[attributes_count]<br/>
	 * }
	 * 
	 * @param itr
	 * @return Field
	 */
	public static Field parse(ConstantPool pool, ByteCodeIterator itr) {
		int accessFlag = itr.nextU2toInt();
		int nameIndex = itr.nextU2toInt();
		int descriptorIndex = itr.nextU2toInt();
		int attrCount = itr.nextU2toInt();
		Field field = new Field(accessFlag, nameIndex, descriptorIndex, attrCount);
		for (int i = 0; i < attrCount; i++) {
			field.addAttributeInfo(AttributeInfo.parse(pool, itr));
		}
		return field;
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

	public int getAttrCount() {
		return attrCount;
	}

	public void setAttrCount(int attrCount) {
		this.attrCount = attrCount;
	}

	public List<AttributeInfo> getAttributeInfos() {
		return attributeInfos;
	}

	public void setAttributeInfos(List<AttributeInfo> attributeInfos) {
		this.attributeInfos = attributeInfos;
	}

}
