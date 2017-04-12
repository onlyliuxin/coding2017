package jvm.field;

import jvm.attr.AttributeInfo;
import jvm.attr.AttributeParser;
import jvm.classfile.ConstantPool;
import jvm.classfile.constant.item.impl.UTF8Info;
import jvm.util.ByteCodeIterator;

import java.util.ArrayList;
import java.util.List;

public class Field {
	private int accessFlag;
	private int nameIndex;
	private int descriptorIndex;

	private ConstantPool constantPool;

	private List<AttributeInfo> attributes;
	
	public Field(int accessFlag, int nameIndex, int descriptorIndex,
				 ConstantPool constantPool, List<AttributeInfo> attributes) {
		this.accessFlag = accessFlag;
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
		this.constantPool = constantPool;
		this.attributes = attributes;
	}

	public static Field parse(ConstantPool constantPool, ByteCodeIterator iterator) {
		int access = iterator.nextU2ToInt();
		int name = iterator.nextU2ToInt();
		int descriptor = iterator.nextU2ToInt();
		int attrCount = iterator.nextU2ToInt();
		List<AttributeInfo> attributes = new ArrayList<>();
		for (int i = 0; i < attrCount; ++i) {
			attributes.add(AttributeParser.parse(iterator, constantPool));
		}
		return new Field(access, name, descriptor, constantPool, attributes);
	}

	@Override
	public String toString() {
		String name = ((UTF8Info) constantPool.getConstantInfo(nameIndex)).getValue();
		String desc = ((UTF8Info) constantPool.getConstantInfo(descriptorIndex)).getValue();
		return name + ":" + desc;
	}
}
