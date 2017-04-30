package jvm.classfile.field;

import jvm.classfile.AccessFlag;
import jvm.classfile.attribute.item.AttributeInfo;
import jvm.classfile.attribute.parser.AttributeParser;
import jvm.classfile.ConstantPool;
import jvm.classfile.constant.item.impl.UTF8Info;
import jvm.util.ByteCodeIterator;

import java.util.ArrayList;
import java.util.List;

public class Field {
	private AccessFlag accessFlag;
	private int nameIndex;
	private int descriptorIndex;
	private ConstantPool constantPool;
	private List<AttributeInfo> attributes = new ArrayList<>();
	
	public Field(int accessFlag, int nameIndex, int descriptorIndex, ConstantPool constantPool) {
		this.accessFlag = new AccessFlag(accessFlag);
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
		this.constantPool = constantPool;
	}

	public static Field parse(ByteCodeIterator iterator, ConstantPool constantPool) {
		int access = iterator.nextU2ToInt();
		int name = iterator.nextU2ToInt();
		int descriptor = iterator.nextU2ToInt();
		int attrCount = iterator.nextU2ToInt();

		Field result = new Field(access, name, descriptor, constantPool);
		for (int i = 0; i < attrCount; ++i) {
			result.attributes.add(AttributeParser.parse(iterator, constantPool));
		}
		return result;
	}

	@Override
	public String toString() {
		return getName() + ":" + getDescriptor();
	}

	public AccessFlag getAccessFlag() {
		return accessFlag;
	}

	public int getNameIndex() {
		return nameIndex;
	}

	public int getDescriptorIndex() {
		return descriptorIndex;
	}

	public ConstantPool getConstantPool() {
		return constantPool;
	}

	public List<AttributeInfo> getAttributes() {
		return attributes;
	}

	public String getName() {
		return ((UTF8Info) constantPool.getConstantInfo(nameIndex)).getValue();
	}

	public String getDescriptor() {
		return ((UTF8Info) constantPool.getConstantInfo(descriptorIndex)).getValue();
	}
}
