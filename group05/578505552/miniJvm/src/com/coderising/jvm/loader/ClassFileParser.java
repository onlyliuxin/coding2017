package com.coderising.jvm.loader;

import com.coderising.jvm.clz.AccessFlag;
import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.clz.ClassIndex;
import com.coderising.jvm.constant.ConstantInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.NullConstantInfo;
import com.coderising.jvm.field.Field;
import com.coderising.jvm.method.Method;
import com.coderising.jvm.parser.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassFileParser {

	private static final Map<Integer, ConstantInfoParser> constantPoolParserMap = new HashMap<Integer, ConstantInfoParser>();

	static {
		constantPoolParserMap.put(ConstantInfo.UTF8_INFO, new UTF8InfoParser());
//		constantPoolParserMap.put(ConstantInfo.INTEGER_INFO, new IntegerInfoParser());
//		constantPoolParserMap.put(ConstantInfo.FLOAT_INFO, new FloatInfoParser());
//		constantPoolParserMap.put(ConstantInfo.LONG_INFO, new LongInfoParser());
//		constantPoolParserMap.put(ConstantInfo.DOUBLE_INFO, new DoubleInfoParser());
		constantPoolParserMap.put(ConstantInfo.CLASS_INFO, new ClassInfoParser());
		constantPoolParserMap.put(ConstantInfo.STRING_INFO, new StringInfoParser());
		constantPoolParserMap.put(ConstantInfo.FIELDREF_INFO, new FieldRefInfoParser());
		constantPoolParserMap.put(ConstantInfo.METHODREF_INFO, new MethodRefInfoParser());
//		constantPoolParserMap.put(ConstantInfo.INTERFACE_METHODREF_INFO, new InterfaceMethodrefInfoParser());
		constantPoolParserMap.put(ConstantInfo.NAMEANDTYPE_INFO, new NameAndTypeInfoParser());
//		constantPoolParserMap.put(ConstantInfo.METHODHANDLE_INFO, new MethodHandleInfoParser());
//		constantPoolParserMap.put(ConstantInfo.METHODTYPE_INFO, new MethodTypeInfoParser());
//		constantPoolParserMap.put(ConstantInfo.INVOKEDYNAMIC_INFO, new InvokeDynamicInfoParser());
	}

	public ClassFile parse(byte[] codes) {

		ByteCodeIterator iterator = new ByteCodeIterator(codes);

		String magicNum = iterator.nextU4ToHexString();
		if (!"cafebabe".equals(magicNum)){
			return null;
		}

		ClassFile clzFile = new ClassFile();

		clzFile.setMinorVersion(iterator.nextU2ToInt());
		clzFile.setMajorVersion(iterator.nextU2ToInt());
		clzFile.setConstPool(parseConstantPool(iterator));
		clzFile.setAccessFlag(parseAccessFlag(iterator));
		clzFile.setClassIndex(parseClassInfex(iterator));

		parseInterfaces(iterator);

		parseFileds(clzFile, iterator);
		parseMethods(clzFile, iterator);

		return clzFile;
	}

	private AccessFlag parseAccessFlag(ByteCodeIterator iter) {

		return new AccessFlag(iter.nextU2ToInt());
	}

	private ClassIndex parseClassInfex(ByteCodeIterator iter) {

		ClassIndex classIndex = new ClassIndex();
		classIndex.setThisClassIndex(iter.nextU2ToInt());
		classIndex.setSuperClassIndex(iter.nextU2ToInt());

		return classIndex;
	}

	private ConstantPool parseConstantPool(ByteCodeIterator iter) {

		int constantPoolCount = iter.nextU2ToInt();

		ConstantPool constantPool = new ConstantPool();
		constantPool.addConstantInfo(new NullConstantInfo());

		for (int i = 1; i < constantPoolCount; i++) {

			int tag = iter.nextU1toInt();
			ConstantInfoParser parser = constantPoolParserMap.get(tag);
			if (parser == null){
				throw new RuntimeException("the constant pool tag " + tag + " has not been implemented yet.");
			}
			constantPool.addConstantInfo(parser.parser(constantPool, iter));
		}

		return constantPool;
	}

	private void parseInterfaces(ByteCodeIterator iter) {
		int interfaceCount = iter.nextU2ToInt();

		System.out.println("interfaceCount:" + interfaceCount);

//		throw new RuntimeException("interfaceParse has not been implemented");

		// TODO : 如果实现了interface, 这里需要解析
	}

	private void parseFileds(ClassFile clzFile, ByteCodeIterator iter) {

		int fieldCount = iter.nextU2ToInt();
		for (int i = 0; i < fieldCount; i++) {
			clzFile.addField(Field.parse(clzFile.getConstantPool(), iter));
		}
	}

	private void parseMethods(ClassFile clzFile, ByteCodeIterator iter) {

		int methodCount = iter.nextU2ToInt();

		for (int i = 0; i < methodCount; i++) {
			clzFile.addMethod(Method.parse(clzFile, iter));
		}
	}

}
