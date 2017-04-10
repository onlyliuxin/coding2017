package com.donaldy.jvm.loader;

import com.donaldy.jvm.clz.AccessFlag;
import com.donaldy.jvm.clz.ClassFile;
import com.donaldy.jvm.clz.ClassIndex;
import com.donaldy.jvm.constant.*;

import javax.lang.model.element.Name;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;


public class ClassFileParser {

	public ClassFile parse(byte[] codes) {

		ByteCodeIterator iter = new ByteCodeIterator(codes);
		ClassFile clzFile = new ClassFile();

		String magicNumber = iter.nextU4ToHexString();

		if (!"cafebabe".equals(magicNumber)) {
			return null;
		}

		clzFile.setMinorVersion(iter.nextU2ToInt());
		clzFile.setMajorVersion(iter.nextU2ToInt());

		ConstantPool pool = parseConstantPool(iter);
		clzFile.setConstPool(pool);

		AccessFlag flag = parseAccessFlag(iter);
		clzFile.setAccessFlag(flag);

		ClassIndex clzIndex = parseClassIndex(iter);
		clzFile.setClassIndex(clzIndex);

		return clzFile;
	}

	private AccessFlag parseAccessFlag(ByteCodeIterator iter) {
		AccessFlag flag = new AccessFlag(iter.nextU2ToInt());
		return flag;
	}

	private ClassIndex parseClassIndex(ByteCodeIterator iter) {

		int thisClassIndex = iter.nextU2ToInt();
		int superClassIndex = iter.nextU2ToInt();

		ClassIndex clzIndex = new ClassIndex();

		clzIndex.setThisClassIndex(thisClassIndex);
		clzIndex.setSuperClassIndex(superClassIndex);

		return clzIndex;

	}

	private ConstantPool parseConstantPool(ByteCodeIterator iter) {

		int constPoolCount = iter.nextU2ToInt();

		System.out.println("Constant Pool Count : " + constPoolCount);

		ConstantPool pool = new ConstantPool();

		pool.addConstantInfo(new NullConstantInfo());

		for (int i = 1; i <= constPoolCount - 1; ++i) {

			int tag = iter.nextU1toInt();

			if (tag == 7) {
				//Class Info
				int utf8Index = iter.nextU2ToInt();
				ClassInfo clzInfo = new ClassInfo(pool);
				clzInfo.setUtf8Index(utf8Index);

				pool.addConstantInfo(clzInfo);
			} else if (tag == 1) {
				//UTF-8 String
				int len = iter.nextU2ToInt();
				byte [] data = iter.getBytes(len);
				String value = null;
				try {
					value = new String(data, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}

				UTF8Info utf8Info = new UTF8Info(pool);
				utf8Info.setLength(len);
				utf8Info.setValue(value);
				pool.addConstantInfo(utf8Info);
			} else if (tag == 8) {
				StringInfo info = new StringInfo(pool);
				info.setIndex(iter.nextU2ToInt());
				pool.addConstantInfo(info);
			} else if (tag == 9) {
				FieldRefInfo field = new FieldRefInfo(pool);
				field.setClassInfoIndex(iter.nextU2ToInt());
				field.setNameAndTypeIndex(iter.nextU2ToInt());
				pool.addConstantInfo(field);

			} else if (tag == 10) {
				//MethodRef
				MethodRefInfo method = new MethodRefInfo(pool);
				method.setClassInfoIndex(iter.nextU2ToInt());
				method.setNameAndTypeIndex(iter.nextU2ToInt());
				pool.addConstantInfo(method);
			} else if (tag == 12) {
				// Name and Type Info
				NameAndTypeInfo nameType = new NameAndTypeInfo(pool);
				nameType.setIndex1(iter.nextU2ToInt());
				nameType.setIndex2(iter.nextU2ToInt());
				pool.addConstantInfo(nameType);
			} else {
				throw new RuntimeException("the constant pool tag" + tag);
			}
		}

		return pool;
	}

	
}
