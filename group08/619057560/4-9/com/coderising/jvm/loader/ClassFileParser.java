package com.coderising.jvm.loader;

import java.io.UnsupportedEncodingException;

import com.coderising.jvm.clz.AccessFlag;
import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.clz.ClassIndex;
import com.coderising.jvm.constant.ClassInfo;
import com.coderising.jvm.constant.ConstantInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.FieldRefInfo;
import com.coderising.jvm.constant.MethodRefInfo;
import com.coderising.jvm.constant.NameAndTypeInfo;
import com.coderising.jvm.constant.NullConstantInfo;
import com.coderising.jvm.constant.StringInfo;
import com.coderising.jvm.constant.UTF8Info;

public class ClassFileParser {

	public ClassFile parse(byte[] codes) throws UnsupportedEncodingException {
		ClassFile clzFile = new ClassFile();
		ByteCodeIterator iter = new ByteCodeIterator(codes);
		// magic number
		if (iter.nextU4ToInt() != 0xCAFEBABE) {
			throw new UnsupportedEncodingException();
		}
		clzFile.setMinorVersion(iter.nextU2ToInt());
		clzFile.setMajorVersion(iter.nextU2ToInt());
		clzFile.setConstPool(parseConstantPool(iter));
		clzFile.setAccessFlag(parseAccessFlag(iter));
		clzFile.setClassIndex(parseClassIndex(iter));
		return clzFile;
	}

	private AccessFlag parseAccessFlag(ByteCodeIterator iter) {
		AccessFlag flag = new AccessFlag(iter.nextU2ToInt());
		return flag;
	}

	private ClassIndex parseClassIndex(ByteCodeIterator iter) {
		ClassIndex clazIndex = new ClassIndex();
		clazIndex.setThisClassIndex(iter.nextU2ToInt());
		clazIndex.setSuperClassIndex(iter.nextU2ToInt());
		return clazIndex;

	}

	private ConstantPool parseConstantPool(ByteCodeIterator iter) throws UnsupportedEncodingException {
		ConstantPool pool = new ConstantPool();
		pool.addConstantInfo(new NullConstantInfo());
		int poolCount = iter.nextU2ToInt();
		for (int i = 1; i < poolCount; i++) {
			int tag = iter.nextU1ToInt();
			switch (tag) {
			case ConstantInfo.UTF8_INFO:
				UTF8Info utf8Info = new UTF8Info(pool);
				utf8Info.setLength(iter.nextU2ToInt());
				utf8Info.setValue(iter.nextUTF8ToString(utf8Info.getLength()));
				pool.addConstantInfo(utf8Info);
				break;
			case ConstantInfo.FLOAT_INFO:
				break;
			case ConstantInfo.CLASS_INFO:
				ClassInfo classInfo = new ClassInfo(pool);
				classInfo.setUtf8Index(iter.nextU2ToInt());
				pool.addConstantInfo(classInfo);
				break;
			case ConstantInfo.STRING_INFO:
				StringInfo stringInfo = new StringInfo(pool);
				stringInfo.setIndex(iter.nextU2ToInt());
				pool.addConstantInfo(stringInfo);
				break;
			case ConstantInfo.FIELD_INFO:
				FieldRefInfo fieldInfo = new FieldRefInfo(pool);
				fieldInfo.setClassInfoIndex(iter.nextU2ToInt());
				fieldInfo.setNameAndTypeIndex(iter.nextU2ToInt());
				pool.addConstantInfo(fieldInfo);
				break;
			case ConstantInfo.METHOD_INFO:
				MethodRefInfo methodInfo = new MethodRefInfo(pool);
				methodInfo.setClassInfoIndex(iter.nextU2ToInt());
				methodInfo.setNameAndTypeIndex(iter.nextU2ToInt());
				pool.addConstantInfo(methodInfo);
				break;
			case ConstantInfo.NAME_AND_TYPE_INFO:
				NameAndTypeInfo ntInfo = new NameAndTypeInfo(pool);
				ntInfo.setIndex1(iter.nextU2ToInt());
				ntInfo.setIndex2(iter.nextU2ToInt());
				pool.addConstantInfo(ntInfo);
				break;
			default:
				throw new UnsupportedEncodingException();
			}
		}
		return pool;
	}

	
}
