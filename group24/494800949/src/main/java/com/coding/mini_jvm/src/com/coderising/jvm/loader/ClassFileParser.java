package com.coding.mini_jvm.src.com.coderising.jvm.loader;

import com.coding.mini_jvm.src.com.coderising.jvm.clz.AccessFlag;
import com.coding.mini_jvm.src.com.coderising.jvm.clz.ClassFile;
import com.coding.mini_jvm.src.com.coderising.jvm.clz.ClassIndex;
import com.coding.mini_jvm.src.com.coderising.jvm.constant.*;


public class ClassFileParser {

	public ClassFile parse(byte[] codes) {
		ClassFile classFile = new ClassFile();
		ByteCodeIterator iterator = new ByteCodeIterator(codes);
		//跳过魔数
		iterator.skip(4);
		//次版本号
		classFile.setMinorVersion(iterator.readTwoBytesToInt());
		//主版本号
		classFile.setMajorVersion(iterator.readTwoBytesToInt());
		//解析常量池
		classFile.setConstPool(parseConstantPool(iterator));
		//访问限制符
		classFile.setAccessFlag(parseAccessFlag(iterator));
		//当前类/父类
		classFile.setClassIndex(parseClassIndex(iterator));
		return classFile;
	}

	private AccessFlag parseAccessFlag(ByteCodeIterator iter) {
		return new AccessFlag(iter.readTwoBytesToInt());
	}

	private ClassIndex parseClassIndex(ByteCodeIterator iter) {
		ClassIndex classIndex = new ClassIndex();
		classIndex.setThisClassIndex(iter.readTwoBytesToInt());
		classIndex.setSuperClassIndex(iter.readTwoBytesToInt());
		return classIndex;
	}

	private ConstantPool parseConstantPool(ByteCodeIterator iterator) {
		ConstantPool constantPool = new ConstantPool();
		//读取常量个数
		int constantPoolCount = iterator.readTwoBytesToInt();
		constantPool.addConstantInfo(new NullConstantInfo());
		for (int i = 1; i < constantPoolCount; i++) {
			int flag = iterator.readByteToInt();
			int utf8Index;
			int clzIndex;
			int nameAndTypeIndex;
			switch (flag) {
				case 1:
					int length = iterator.readTwoBytesToInt();
					String val = iterator.readBytesToString(length);
					UTF8Info utf8Info = new UTF8Info(constantPool);
					utf8Info.setLength(length);
					utf8Info.setValue(val);
					constantPool.addConstantInfo(utf8Info);
					break;
				case 7:
					utf8Index = iterator.readTwoBytesToInt();
					ClassInfo classInfo = new ClassInfo(constantPool);
					classInfo.setUtf8Index(utf8Index);
					constantPool.addConstantInfo(classInfo);
					break;
				case 8:
					utf8Index = iterator.readTwoBytesToInt();
					StringInfo stringInfo = new StringInfo(constantPool);
					stringInfo.setIndex(utf8Index);
					constantPool.addConstantInfo(stringInfo);
					break;
				case 9:
					clzIndex = iterator.readTwoBytesToInt();
					nameAndTypeIndex = iterator.readTwoBytesToInt();
					FieldRefInfo fieldRefInfo = new FieldRefInfo(constantPool);
					fieldRefInfo.setNameAndTypeIndex(nameAndTypeIndex);
					fieldRefInfo.setClassInfoIndex(clzIndex);
					constantPool.addConstantInfo(fieldRefInfo);
					break;
				case 10:
					clzIndex = iterator.readTwoBytesToInt();
					nameAndTypeIndex = iterator.readTwoBytesToInt();
					MethodRefInfo methodRefInfo = new MethodRefInfo(constantPool);
					methodRefInfo.setClassInfoIndex(clzIndex);
					methodRefInfo.setNameAndTypeIndex(nameAndTypeIndex);
					constantPool.addConstantInfo(methodRefInfo);
					break;
				case 12:
					utf8Index = iterator.readTwoBytesToInt();
					int utf8Index1 = iterator.readTwoBytesToInt();
					NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(constantPool);
					nameAndTypeInfo.setIndex1(utf8Index);
					nameAndTypeInfo.setIndex2(utf8Index1);
					constantPool.addConstantInfo(nameAndTypeInfo);
					break;
				default:
					throw new RuntimeException("flag "+ flag +" is not exists");
			}
		}
		return constantPool;
	}

	
}
