package com.coderising.jvm.loader;

import java.io.UnsupportedEncodingException;

import org.junit.Assert;

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
import com.coderising.jvm.util.Util;

public class ClassFileParser {

	public ClassFile parse(byte[] codes) {

		ClassFile clzFile = new ClassFile();

		ByteCodeIterator iter = new ByteCodeIterator(codes);

		String magicNumber = iter.nextU4toHexString();
		if ("cafebabe".equals(magicNumber) == false) {
			throw new RuntimeException("invalide class file!" + magicNumber);
		}

		int minorVersion = iter.nextU2toInt();
		System.out.println("minorVersion is " + minorVersion);
		clzFile.setMinorVersion(minorVersion);

		int majorVersion = iter.nextU2toInt();
		System.out.println("majorVersion is " + majorVersion);
		clzFile.setMajorVersion(majorVersion);

		int constantsNum = iter.nextU2toInt();
		System.out.println("constantsNum is " + constantsNum);
		ConstantPool pool = new ConstantPool();
		clzFile.setConstPool(pool);

		pool.addConstantInfo(new NullConstantInfo());

		for (int i = 1; i < constantsNum; i++) {

			int tag = iter.nextU1toInt();

			if (tag == 7) {
				// Class info
				ClassInfo classInfo = new ClassInfo(pool);
				int utf8Index = iter.nextU2toInt();
				classInfo.setUtf8Index(utf8Index);
				

			} else if (tag == 1) {

				// utf8-info
				UTF8Info utf8Info = new UTF8Info(pool);
				int length = iter.nextU2toInt();
				System.out.println("length is " + length);
				utf8Info.setLength(length);
				byte[] bytes = iter.nextNbytesToHexString(length);
				System.out.println(bytes.length);
				String value = "";
				try {
					value = new String(bytes, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				System.out.println("value is " + value);
				utf8Info.setValue(value);

			} else if (tag == 8) {

				// StringInfo
				StringInfo stringInfo = new StringInfo(pool);
				int stringIndex = iter.nextU2toInt();
				stringInfo.setIndex(stringIndex);
			} else if (tag == 9) {

				// FieldRefInfo
				FieldRefInfo fieldRefInfo = new FieldRefInfo(pool);
				int classIndex = iter.nextU2toInt();
				fieldRefInfo.setClassInfoIndex(classIndex);
				int nameAndTypeIndex = iter.nextU2toInt();
				fieldRefInfo.setNameAndTypeIndex(nameAndTypeIndex);

			} else if (tag == 10) {

				// MethodRefInfo
				MethodRefInfo methodRefInfo = new MethodRefInfo(pool);
				int classIndex = iter.nextU2toInt();
				methodRefInfo.setClassInfoIndex(classIndex);
				int nameAndTypeIndex = iter.nextU2toInt();
				methodRefInfo.setNameAndTypeIndex(nameAndTypeIndex);

			} else if (tag == 12) {

				// NameAndTypeInfo
				NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(pool);
				int index1 = iter.nextU2toInt();
				nameAndTypeInfo.setIndex1(index1);
				int index2 = iter.nextU2toInt();
				nameAndTypeInfo.setIndex2(index2);

			}

		}
		
		AccessFlag accessFlag = parseAccessFlag(iter);
		ClassIndex classIndex = parseClassIndex(iter);
		
		clzFile.setAccessFlag(accessFlag);
		clzFile.setClassIndex(classIndex);

		return clzFile;
	}

	private AccessFlag parseAccessFlag(ByteCodeIterator iter) {
		AccessFlag accessFlag = new AccessFlag(iter.nextU2toInt());

		return accessFlag;
	}

	private ClassIndex parseClassIndex(ByteCodeIterator iter) {

		int thisClassIndex = iter.nextU2toInt();
		int superClassIndex = iter.nextU2toInt();
		ClassIndex classIndex = new ClassIndex();
		classIndex.setThisClassIndex(thisClassIndex);
		classIndex.setSuperClassIndex(superClassIndex);
		return classIndex;

	}

	private ConstantPool parseConstantPool(ByteCodeIterator iter) {

		ConstantPool pool = new ConstantPool();

		return pool;
	}

}
