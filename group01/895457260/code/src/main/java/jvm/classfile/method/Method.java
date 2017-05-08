package jvm.classfile.method;

import jvm.classfile.attribute.item.AttributeInfo;
import jvm.classfile.attribute.parser.AttributeParser;
import jvm.classfile.ConstantPool;
import jvm.util.ByteCodeIterator;

import java.util.ArrayList;
import java.util.List;


public class Method {
	private int accessFlag;
	private int nameIndex;
	private int descriptorIndex;
	private ConstantPool constantPool;
	private List<AttributeInfo> attributes = new ArrayList<>();

	public Method(int accessFlag, int nameIndex, int descriptorIndex, ConstantPool constantPool) {
		this.accessFlag = accessFlag;
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
		this.constantPool = constantPool;
	}

	public static Method parse(ByteCodeIterator iterator, ConstantPool constantPool) {
		int access = iterator.nextU2ToInt();
		int name = iterator.nextU2ToInt();
		int descriptor = iterator.nextU2ToInt();
		int attrCount = iterator.nextU2ToInt();
		Method result = new Method(access, name, descriptor, constantPool);
		for (int i = 0; i < attrCount; ++i) {
			result.attributes.add(AttributeParser.parse(iterator, constantPool));
		}
		return result;
	}

	public int getAccessFlag() {
		return accessFlag;
	}

	public ConstantPool getConstantPool() {
		return constantPool;
	}

	public int getNameIndex() {
		return nameIndex;
	}

	public List<AttributeInfo> getAttributes() {
		return attributes;
	}

	public int getDescriptorIndex() {
		return descriptorIndex;
	}
}
