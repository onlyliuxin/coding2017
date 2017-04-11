package com.coderising.jvm.loader;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

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
import com.coderising.jvm.field.Field;
import com.coderising.jvm.method.Method;
import com.coderising.jvm.util.Util;

public class ClassFileParser {

	public ClassFile parse(byte[] codes) {

		ClassFile clzFile = new ClassFile();

		ByteCodeIterator iter = new ByteCodeIterator(codes);

		String magicNumber = iter.nextU4ToHexString();
		if ("cafebabe".equals(magicNumber) == false) {
			throw new RuntimeException("invalide class file!" + magicNumber);
		}

		int minorVersion = iter.nextU2ToInt();
		System.out.println("minorVersion is " + minorVersion);
		clzFile.setMinorVersion(minorVersion);

		int majorVersion = iter.nextU2ToInt();
		System.out.println("majorVersion is " + majorVersion);
		clzFile.setMajorVersion(majorVersion);


		ConstantPool pool = parseConstantPool(iter);
		clzFile.setConstPool(pool);

		
		AccessFlag accessFlag = parseAccessFlag(iter);
		clzFile.setAccessFlag(accessFlag);
		
		ClassIndex classIndex = parseClassIndex(iter);
		clzFile.setClassIndex(classIndex);
		
		parseInterfaces(iter);

		parseFields(clzFile, iter);

		parseMethods(clzFile, iter);
		
		return clzFile;
	}

	private void parseMethods(ClassFile clzFile, ByteCodeIterator iter) {
		
		int methodNum = iter.nextU2ToInt();

		ConstantPool pool = clzFile.getConstantPool(); 
		for (int i = 0; i < methodNum; i++) {
			Method method = Method.parse(clzFile,iter);
			clzFile.addMethod(method);
		}
		
	}

	private void parseFields(ClassFile clzFile, ByteCodeIterator iter) {
		
		int fieldNum = iter.nextU2ToInt();
		
		ConstantPool pool = clzFile.getConstantPool(); 
		for (int i = 0; i < fieldNum; i++) {
			Field field = Field.parse(pool,iter);
			clzFile.addField(field);
		}
		
	}

	private void parseInterfaces(ByteCodeIterator iter) {
		int interfaceNum = iter.nextU2ToInt();
		
		if (0 != interfaceNum) {
			throw new RuntimeException("interface parser not finsihed yet, pls check!");
		}
		
	}

	private AccessFlag parseAccessFlag(ByteCodeIterator iter) {
	
		return AccessFlag.parseAccessFlag(iter);
	}

	private ClassIndex parseClassIndex(ByteCodeIterator iter) {

		int thisClassIndex = iter.nextU2ToInt();
		int superClassIndex = iter.nextU2ToInt();
		ClassIndex classIndex = new ClassIndex();
		classIndex.setThisClassIndex(thisClassIndex);
		classIndex.setSuperClassIndex(superClassIndex);
		return classIndex;

	}

	private ConstantPool parseConstantPool(ByteCodeIterator iter) {

		int constantsNum = iter.nextU2ToInt();
		System.out.println("constantsNum is " + constantsNum);
		
		ConstantPool pool = new ConstantPool();

		pool.addConstantInfo(new NullConstantInfo());

		for (int i = 1; i < constantsNum; i++) {

			int tag = iter.nextU1ToInt();

			if (tag == 7) {
				// Class info
				ClassInfo classInfo = new ClassInfo(pool);
				int utf8Index = iter.nextU2ToInt();
				classInfo.setUtf8Index(utf8Index);
				

			} else if (tag == 1) {

				// utf8-info
				UTF8Info utf8Info = new UTF8Info(pool);
				int length = iter.nextU2ToInt();
				System.out.println("length is " + length);
				utf8Info.setLength(length);
				byte[] bytes = iter.nextNbytesToHexString(length);
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
				int stringIndex = iter.nextU2ToInt();
				stringInfo.setIndex(stringIndex);
			} else if (tag == 9) {

				// FieldRefInfo
				FieldRefInfo fieldRefInfo = new FieldRefInfo(pool);
				int classIndex = iter.nextU2ToInt();
				fieldRefInfo.setClassInfoIndex(classIndex);
				int nameAndTypeIndex = iter.nextU2ToInt();
				fieldRefInfo.setNameAndTypeIndex(nameAndTypeIndex);

			} else if (tag == 10) {

				// MethodRefInfo
				MethodRefInfo methodRefInfo = new MethodRefInfo(pool);
				int classIndex = iter.nextU2ToInt();
				methodRefInfo.setClassInfoIndex(classIndex);
				int nameAndTypeIndex = iter.nextU2ToInt();
				methodRefInfo.setNameAndTypeIndex(nameAndTypeIndex);

			} else if (tag == 12) {

				// NameAndTypeInfo
				NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(pool);
				int index1 = iter.nextU2ToInt();
				nameAndTypeInfo.setIndex1(index1);
				int index2 = iter.nextU2ToInt();
				nameAndTypeInfo.setIndex2(index2);

			}

		}

		return pool;
	}

}
