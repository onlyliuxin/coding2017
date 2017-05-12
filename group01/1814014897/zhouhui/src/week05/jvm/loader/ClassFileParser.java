package week05.jvm.loader;

import java.io.UnsupportedEncodingException;

import week05.jvm.clz.AccessFlag;
import week05.jvm.clz.ClassFile;
import week05.jvm.clz.ClassIndex;
import week05.jvm.constant.ClassInfo;
import week05.jvm.constant.ConstantPool;
import week05.jvm.constant.FieldRefInfo;
import week05.jvm.constant.MethodRefInfo;
import week05.jvm.constant.NameAndTypeInfo;
import week05.jvm.constant.NullConstantInfo;
import week05.jvm.constant.StringInfo;
import week05.jvm.constant.UTF8Info;

public class ClassFileParser {

	public ClassFile parse(byte[] codes) {

		ClassFile clzFile = new ClassFile();

		ByteCodeIterator iter = new ByteCodeIterator(codes);

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

		ClassIndex clzIndex = parseClassInfex(iter);
		clzFile.setClassIndex(clzIndex);

		return clzFile;
	}

	private AccessFlag parseAccessFlag(ByteCodeIterator iter) {
		int flagValue = iter.nextU2ToInt();
		AccessFlag accessFlag = new AccessFlag(flagValue);
		return accessFlag;
	}

	private ClassIndex parseClassInfex(ByteCodeIterator iter) {
		ClassIndex classIndex = new ClassIndex();
		int thisClassIndex = iter.nextU2ToInt();
		int thisSuperIndex = iter.nextU2ToInt();

		classIndex.setThisClassIndex(thisClassIndex);
		classIndex.setSuperClassIndex(thisSuperIndex);

		return classIndex;

	}

	private ConstantPool parseConstantPool(ByteCodeIterator iter) {
		int constPoolCount = iter.nextU2ToInt();

		System.out.println("Constant Pool Counts:" + constPoolCount);

		ConstantPool pool = new ConstantPool();

		pool.addConstantInfo(new NullConstantInfo());

		for (int i = 1; i <= constPoolCount - 1; i++) {

			int tag = iter.nextU1ToInt();

			if (tag == 7) {
				// Class Info
				int utf8Index = iter.nextU2ToInt();
				ClassInfo clzInfo = new ClassInfo(pool);
				clzInfo.setUtf8Index(utf8Index);

				pool.addConstantInfo(clzInfo);
			} else if (tag == 1) {
				// UTF-8 String
				int len = iter.nextU2ToInt();
				byte[] data = iter.getBytes(len);
				String value = null;
				try {
					value = new String(data, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}

				UTF8Info utf8Str = new UTF8Info(pool);
				utf8Str.setLength(len);
				utf8Str.setValue(value);
				pool.addConstantInfo(utf8Str);
			} else if (tag == 8) {
				//String
				StringInfo info = new StringInfo(pool);
				info.setIndex(iter.nextU2ToInt());
				pool.addConstantInfo(info);
			} else if (tag == 9) {
				//FieldRef
				FieldRefInfo field = new FieldRefInfo(pool);
				field.setClassInfoIndex(iter.nextU2ToInt());
				field.setNameAndTypeIndex(iter.nextU2ToInt());
				pool.addConstantInfo(field);
			} else if (tag == 10) {
				// MethodRef
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
				throw new RuntimeException("the constant pool tag " + tag + "has not been implemented yet.");
			}

		}

		return pool;

	}

}
