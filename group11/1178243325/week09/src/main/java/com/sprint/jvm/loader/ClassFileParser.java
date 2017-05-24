package com.sprint.jvm.loader;

import com.sprint.jvm.clz.ClassFile;
import com.sprint.jvm.clz.AccessFlag;
import com.sprint.jvm.clz.ClassIndex;
import com.sprint.jvm.constant.ClassInfo;
import com.sprint.jvm.constant.ConstantPool;
import com.sprint.jvm.constant.FieldRefInfo;
import com.sprint.jvm.constant.NameAndTypeInfo;
import com.sprint.jvm.constant.NullConstantInfo;
import com.sprint.jvm.constant.MethodRefInfo;
import com.sprint.jvm.constant.StringInfo;
import com.sprint.jvm.constant.UTF8Info;
import java.io.UnsupportedEncodingException;
public class ClassFileParser {
	public ClassFile parse(byte[] codes) {
		ClassFile clzFile = new ClassFile(); 		
		ByteCodeIterator iter = new ByteCodeIterator(codes);
		String magicNumber = iter.nextU4ToHexString();
		if (!"cafebabe".equals(magicNumber)) {
			return null;
		}
		clzFile.setMinorVersion(iter.nextU2ToInt());
		System.out.println("minor:" + clzFile.getMinorVersion());
		clzFile.setMajorVersion(iter.nextU2ToInt());
		System.out.println("marjor:" + clzFile.getMajorVersion());
		ConstantPool pool = parseConstantPool(iter);
		clzFile.setConstantPool(pool);
		
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
		System.out.println("Constant Pool Count :" + constPoolCount);
		ConstantPool pool = new ConstantPool();
		pool.addConstantInfo(new NullConstantInfo());
		for (int i = 1; i <= constPoolCount - 1; i++) {
			int tag = iter.nextU1ToInt();
			if (tag == 7) {
				int utf8Index = iter.nextU2ToInt();
				ClassInfo clzInfo = new ClassInfo(pool);
				clzInfo.setUtf8Index(utf8Index);
				pool.addConstantInfo(clzInfo);
			} else if (tag == 1) {
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
				StringInfo info = new StringInfo(pool);
				info.setIndex(iter.nextU2ToInt());
				pool.addConstantInfo(info);
			} else if (tag == 9) {
				FieldRefInfo field = new FieldRefInfo(pool);
				field.setClassInfoIndex(iter.nextU2ToInt());
				field.setNameAndTypeIndex(iter.nextU2ToInt());
				pool.addConstantInfo(field);
			} else if (tag == 10) {
				MethodRefInfo method = new MethodRefInfo(pool);
				method.setClassInfoIndex(iter.nextU2ToInt());
				method.setNameAndTypeIndex(iter.nextU2ToInt());
				pool.addConstantInfo(method);
			} else if (tag == 12) {
				NameAndTypeInfo nameType = new NameAndTypeInfo(pool);
				nameType.setIndex1(iter.nextU2ToInt());
				nameType.setIndex2(iter.nextU2ToInt());
				pool.addConstantInfo(nameType);
			} else {
				throw new RuntimeException("the constant pool tag:" + tag + "has no been implemented yet.");
			} 
		}	
		System.out.println("Finished reading Constant Pool");
		return pool;
	}


}
