package com.coderising.jvm.loader;

import com.coderising.jvm.clz.AccessFlag;
import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.clz.ClassIndex;
import com.coderising.jvm.constant.*;

public class ClassFileParser {

	public ClassFile parse(byte[] codes) {

		ClassFile classFile = new ClassFile();
		ByteCodeIterator iter = new ByteCodeIterator(codes);

		if (!"cafebabe".equals(iter.nextU4ToHexString())) {
			return null;
		}

		if (iter.hasNext(4)) {
			classFile.setMinorVersion(iter.nextUnToInt(2));
			classFile.setMajorVersion(iter.nextUnToInt(2));
		}

		classFile.setConstPool(parseConstantPool(iter));
		classFile.setAccessFlag(parseAccessFlag(iter));
		classFile.setClassIndex(parseClassInfex(iter));

		return classFile;
	}

	private AccessFlag parseAccessFlag(ByteCodeIterator iter) {
		AccessFlag accessFlag = new AccessFlag(iter.nextUnToInt(2));
		return accessFlag;
	}

	private ClassIndex parseClassInfex(ByteCodeIterator iter) {
		ClassIndex classIndex = new ClassIndex();
		classIndex.setThisClassIndex(iter.nextUnToInt(2));
		classIndex.setSuperClassIndex(iter.nextUnToInt(2));
		return classIndex;

	}

	private ConstantPool parseConstantPool(ByteCodeIterator iter) {
		ConstantPool constantPool = new ConstantPool();
		int sum = iter.nextUnToInt(2);
		constantPool.addConstantInfo(new NullConstantInfo());
		while (sum-- > 1) {
			int tag = iter.nextUnToInt(1);
			constantPool.addConstantInfo(getConInfo(tag, constantPool, iter));
		}
		return constantPool;
	}

	private ConstantInfo getConInfo(int tag, ConstantPool constantPool, ByteCodeIterator iter) {
		switch (tag) {
			case ConstantInfo.CLASS_INFO :
				ClassInfo classInfo = new ClassInfo(constantPool);
				classInfo.setUtf8Index(iter.nextUnToInt(2));
				return classInfo;
			case ConstantInfo.FIELD_INFO :
				FieldRefInfo fieldRefInfo = new FieldRefInfo(constantPool);
				fieldRefInfo.setClassInfoIndex(iter.nextUnToInt(2));
				fieldRefInfo.setNameAndTypeIndex(iter.nextUnToInt(2));
				return fieldRefInfo;
			case ConstantInfo.METHOD_INFO :
				MethodRefInfo methodRefInfo = new MethodRefInfo(constantPool);
				methodRefInfo.setClassInfoIndex(iter.nextUnToInt(2));
				methodRefInfo.setNameAndTypeIndex(iter.nextUnToInt(2));
				return methodRefInfo;
			case ConstantInfo.NAME_AND_TYPE_INFO :
				NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(constantPool);
				nameAndTypeInfo.setIndex1(iter.nextUnToInt(2));
				nameAndTypeInfo.setIndex2(iter.nextUnToInt(2));
				return nameAndTypeInfo;
			case ConstantInfo.STRING_INFO :
				StringInfo stringInfo = new StringInfo(constantPool);
				stringInfo.setIndex(iter.nextUnToInt(2));
				return stringInfo;
			case ConstantInfo.UTF8_INFO :
				UTF8Info utf8Info = new UTF8Info(constantPool);
				int lenth = iter.nextUnToInt(2);
				utf8Info.setLength(lenth);
				StringBuffer sb = new StringBuffer();
				for (int i=0; i<lenth; i++) {
					byte b = (byte)iter.next();
					sb.append((char)b);
				}
				utf8Info.setValue(sb.toString());
				return utf8Info;
			default:
				return null;
		}
	}

	
}
