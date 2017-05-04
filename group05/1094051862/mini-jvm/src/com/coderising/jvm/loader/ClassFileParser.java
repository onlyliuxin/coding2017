package com.coderising.jvm.loader;

import java.io.UnsupportedEncodingException;

import com.coderising.jvm.clz.AccessFlag;
import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.clz.ClassIndex;
import com.coderising.jvm.constant.ClassInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.FieldRefInfo;
import com.coderising.jvm.constant.MethodRefInfo;
import com.coderising.jvm.constant.NameAndTypeInfo;
import com.coderising.jvm.constant.NullConstantInfo;
import com.coderising.jvm.constant.StringInfo;
import com.coderising.jvm.constant.UTF8Info;
import com.coderising.jvm.field.Field;
import com.coderising.jvm.method.Method;

public class ClassFileParser {

	public ClassFile parse(byte[] codes) {
		ClassFile clzFile = new ClassFile();
		ByteCodeIterator iter = new ByteCodeIterator(codes);
		
		String magicNumber = iter.nextU4toHexString();
		if (!"cafebabe".equals(magicNumber)) {
			return null;
		}
		
		clzFile.setMinorVersion(iter.nextU2toInt());
		clzFile.setMajorVersion(iter.nextU2toInt());
		
		ConstantPool pool = parseConstantPool(iter);
		clzFile.setConstPool(pool);
		
		clzFile.setAccessFlag(parseAccessFlag(iter));
		clzFile.setClassIndex(parseClassIndex(iter));
		
		parseInterfaces(iter);//如果实现接口，需要进一步解析
		
		parseFields(clzFile, iter);
		
		parseMethods(clzFile, iter);
		return clzFile;
	}

	private void parseMethods(ClassFile clzFile, ByteCodeIterator iter) {
		int methodCount = iter.nextU2toInt();
		System.out.println("methodCount:" + methodCount);
		for (int i = 0; i < methodCount; i++) {
			System.out.println("method :" + (i+1));
			Method m = Method.parse(clzFile, iter);
			clzFile.addMethod(m);
		}
	}

	private void parseFields(ClassFile clzFile, ByteCodeIterator iter) {
		int fieldCount = iter.nextU2toInt();
		System.out.println("fieldCount:" + fieldCount);
		for (int i = 0; i < fieldCount; i++) {
			Field f = Field.parse(clzFile.getConstantPool(), iter);
			clzFile.addField(f);
		}
	}

	private AccessFlag parseAccessFlag(ByteCodeIterator iter) {
		//int flagValue = iter.nextU2toInt();
		return new AccessFlag(iter.nextU2toInt());
	}

	private ClassIndex parseClassIndex(ByteCodeIterator iter) {
		ClassIndex classIndex = new ClassIndex();
		classIndex.setThisClassIndex(iter.nextU2toInt());
		classIndex.setSuperClassIndex(iter.nextU2toInt());
		
		return classIndex;
	}

	private ConstantPool parseConstantPool(ByteCodeIterator iter) {
		int constantPoolIndexCount = iter.nextU2toInt();
		System.out.println("Constant Pool Index Count:" + constantPoolIndexCount);
		ConstantPool pool = new ConstantPool();
		
		pool.addConstantInfo(new NullConstantInfo());
		
		for (int i = 1; i <= constantPoolIndexCount - 1; i++) {
			int tag = iter.nextU1toInt();
			if (tag == 7) {//Class Info
				int nameIndex = iter.nextU2toInt();
				ClassInfo clzInfo = new ClassInfo(pool);
				clzInfo.setUtf8Index(nameIndex);
				
				pool.addConstantInfo(clzInfo);
			} else if (tag == 1) {//Utf8 Info
				int length = iter.nextU2toInt();
				byte[] data = iter.getBytes(length);
				String value = null;
				try {
					value = new String(data, "utf-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				UTF8Info utf8Info = new UTF8Info(pool);
				utf8Info.setLength(length);
				utf8Info.setValue(value);
				
				pool.addConstantInfo(utf8Info);
			} else if (tag == 8) {// String Info
				int strIndex = iter.nextU2toInt();
				StringInfo stringInfo = new StringInfo(pool);
				stringInfo.setIndex(strIndex);
				
				pool.addConstantInfo(stringInfo);
			} else if (tag == 9) {// Fieldref Info
				FieldRefInfo fieldRefInfo = new FieldRefInfo(pool);
				fieldRefInfo.setClassInfoIndex(iter.nextU2toInt());
				fieldRefInfo.setNameAndTypeIndex(iter.nextU2toInt());
				
				pool.addConstantInfo(fieldRefInfo);
			} else if (tag == 10) {// Methodref Info
				int classIndex = iter.nextU2toInt();
				int nameAndTypeIndex = iter.nextU2toInt();
				MethodRefInfo methodRefInfo = new MethodRefInfo(pool);
				methodRefInfo.setClassInfoIndex(classIndex);
				methodRefInfo.setNameAndTypeIndex(nameAndTypeIndex);
				
				pool.addConstantInfo(methodRefInfo);
			} else if (tag == 12) { //NameAndType Info
				int nameIndex = iter.nextU2toInt();
				int descriptorIndex = iter.nextU2toInt();
				NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(pool);
				nameAndTypeInfo.setIndex1(nameIndex);
				nameAndTypeInfo.setIndex2(descriptorIndex);
				
				pool.addConstantInfo(nameAndTypeInfo);
			}
			else {
				throw new RuntimeException("The constant pool has not realized at tag=" + tag);
			}
		}
		return pool;
	}

	private void parseInterfaces(ByteCodeIterator iter) {
		int interfaceCount = iter.nextU2toInt();

		System.out.println("interfaceCount:" + interfaceCount);

		// TODO : 如果实现了interface, 这里需要解析
	}

	
}
