package mini_jvm.loader;


import mini_jvm.clz.AccessFlag;
import mini_jvm.clz.ClassFile;
import mini_jvm.clz.ClassIndex;
import mini_jvm.constant.*;
import mini_jvm.field.Field;
import mini_jvm.method.Method;

public class ClassFileParser {

	public ClassFile parse(byte[] byteCodes) {
		ByteCodeIterator iterator = new ByteCodeIterator(byteCodes);
		ClassFile clzFile = new ClassFile();

		//magic number
		String magicNumber = iterator.nextU4ToHexString();
		if (!"cafebabe".equals(magicNumber)) {
			throw new RuntimeException("not java .class file!");
		}
		int minorVersion = iterator.nextU2ToInt();		//次版本号
		int majorVersion = iterator.nextU2ToInt();		//主版本号
		clzFile.setMajorVersion(majorVersion);
		clzFile.setMinorVersion(minorVersion);

		ConstantPool constantPool = parseConstantPool(iterator); //常量池
		clzFile.setConstPool(constantPool);

		AccessFlag accessFlag = parseAccessFlag(iterator);		 //解析访问标识
		clzFile.setAccessFlag(accessFlag);

		ClassIndex clzIndex = parseClassIndex(iterator);		 //解析类索引
		clzFile.setClassIndex(clzIndex);

		//解析接口，此处暂不支持
		parseInterfaces(iterator);

		//解析字段
		parseField(clzFile,iterator);

		//解析方法
		parseMethod(clzFile,iterator);

		return clzFile;
	}

	private void parseMethod(ClassFile clzFile, ByteCodeIterator iterator) {
		//方法个数
		int methodCount = iterator.nextU2ToInt();
		for (int i = 0; i < methodCount; i++) {
			Method m = Method.parse(clzFile,iterator);
			clzFile.addMethod(m);
		}
	}

	//解析字段
	private void parseField(ClassFile clzFile, ByteCodeIterator iterator) {
		//字段个数
		int fieldsCount = iterator.nextU2ToInt();
		for (int i = 0; i < fieldsCount; i++) {
			Field f = Field.parse(clzFile.getConstantPool(),iterator);
			clzFile.addField(f);
		}
	}


	private AccessFlag parseAccessFlag(ByteCodeIterator iterator) {
		int accessValue = iterator.nextU2ToInt();
		AccessFlag accessFlag = new AccessFlag(accessValue);

		return accessFlag;
	}

	private ClassIndex parseClassIndex(ByteCodeIterator iterator) {
		ClassIndex clzIndex = new ClassIndex();

		int thisClzIndex = iterator.nextU2ToInt();
		int superClzIndex = iterator.nextU2ToInt();
		clzIndex.setThisClassIndex(thisClzIndex);
		clzIndex.setSuperClassIndex(superClzIndex);

		return clzIndex;
	}

	private ConstantPool parseConstantPool(ByteCodeIterator iterator) {
		//常量池
		ConstantPool constantPool = new ConstantPool();
		//常量池成员数
		int constantPoolLength = iterator.nextU2ToInt();
		//第0位补上一个占位符
		NullConstantInfo nullConstantInfo = new NullConstantInfo();
		constantPool.addConstantInfo(nullConstantInfo);
		for (int i = 1; i < constantPoolLength; i++) {
			int tag = iterator.nextU1ToInt();
			// tag = 1, UTF8_INFO
			if (tag == 1) {
				UTF8Info utf8Info = new UTF8Info(constantPool);
				int length = iterator.nextU2ToInt();
				String value = iterator.nextBytesLenAsString(length);
				utf8Info.setLength(length);
				utf8Info.setValue(value);
				constantPool.addConstantInfo(utf8Info);
			}
			// tag = 7, CLASS_INFO
			else if (tag == 7) {
				ClassInfo classInfo = new ClassInfo(constantPool);
				int nameIndex = iterator.nextU2ToInt();
				classInfo.setUtf8Index(nameIndex);
				constantPool.addConstantInfo(classInfo);
			}
			// tag = 8, STRING_INFO
			else if (tag == 8) {
				StringInfo stringInfo = new StringInfo(constantPool);
				int stringIndex = iterator.nextU2ToInt();
				stringInfo.setIndex(stringIndex);
				constantPool.addConstantInfo(stringInfo);
			}
			// tag = 9, Fieldref
			else if (tag == 9) {
				FieldRefInfo fieldRefInfo = new FieldRefInfo(constantPool);
				int classInfoIndex = iterator.nextU2ToInt();
				int nameAndTypeIndex = iterator.nextU2ToInt();
				fieldRefInfo.setClassInfoIndex(classInfoIndex);
				fieldRefInfo.setNameAndTypeIndex(nameAndTypeIndex);
				constantPool.addConstantInfo(fieldRefInfo);
			}
			// tag = 10, MethodRef
			else if (tag == 10) {
				MethodRefInfo methodRefInfo = new MethodRefInfo(constantPool);
				int classInfoIndex = iterator.nextU2ToInt();
				int nameAndTypeIndex = iterator.nextU2ToInt();
				methodRefInfo.setClassInfoIndex(classInfoIndex);
				methodRefInfo.setNameAndTypeIndex(nameAndTypeIndex);
				constantPool.addConstantInfo(methodRefInfo);
			}
			// tag = 12, NameAndType
			else if (tag == 12) {
				NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(constantPool);
				int nameAndTypeIndex = iterator.nextU2ToInt();
				int descriptorIndex = iterator.nextU2ToInt();
				nameAndTypeInfo.setIndex1(nameAndTypeIndex);
				nameAndTypeInfo.setIndex2(descriptorIndex);
				constantPool.addConstantInfo(nameAndTypeInfo);
			} else {
				throw new RuntimeException("not realized tag " + tag);
			}
		}

		return constantPool;
	}

	private void parseInterfaces(ByteCodeIterator iterator) {
		int interfaceCount = iterator.nextU2ToInt();
		// TODO : 如果实现了interface, 这里需要解析
		System.out.println("interfaceCount:" + interfaceCount);
	}
}

