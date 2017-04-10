package com.bruce.homework0409.jvm.loader;

import com.bruce.homework0409.jvm.clz.AccessFlag;
import com.bruce.homework0409.jvm.clz.ClassFile;
import com.bruce.homework0409.jvm.clz.ClassIndex;
import com.bruce.homework0409.jvm.constant.ClassInfo;
import com.bruce.homework0409.jvm.constant.ConstantPool;
import com.bruce.homework0409.jvm.constant.FieldRefInfo;
import com.bruce.homework0409.jvm.constant.MethodRefInfo;
import com.bruce.homework0409.jvm.constant.NameAndTypeInfo;
import com.bruce.homework0409.jvm.constant.NullConstantInfo;
import com.bruce.homework0409.jvm.constant.StringInfo;
import com.bruce.homework0409.jvm.constant.UTF8Info;

import java.util.Arrays;

public class ClassFileParser {

	public ClassFile parse(byte[] codes) {
		ClassFile clzFile = new ClassFile();
		ByteCodeIterator iter = new ByteCodeIterator(codes);
        String magicNumber = iter.nextU4ToHexString();
        if (!"cafebabe".equalsIgnoreCase(magicNumber)) {
            return null;
        }
        clzFile.setMinorVersion(iter.nextU2ToInt());
        clzFile.setMajorVersion(iter.nextU2ToInt());

        ConstantPool pool = parseConstantPool(iter);
        clzFile.setConstPool(pool);

        AccessFlag flag = parseAccessFlag(iter);
        clzFile.setAccessFlag(flag);

        ClassIndex index = parseClassInfex(iter);
        clzFile.setClassIndex(index);
        return clzFile;
	}

	private AccessFlag parseAccessFlag(ByteCodeIterator iter) {
        int flagValue = iter.nextU1ToInt();
        AccessFlag flag = new AccessFlag(flagValue);
		return flag;
	}

	private ClassIndex parseClassInfex(ByteCodeIterator iter) {
        ClassIndex  clzIndex = new ClassIndex();
        int thisClassIndex = iter.nextU2ToInt();
        int superClassIndex = iter.nextU2ToInt();
        clzIndex.setThisClassIndex(thisClassIndex);
        clzIndex.setSuperClassIndex(superClassIndex);
        return clzIndex;

	}

	private ConstantPool parseConstantPool(ByteCodeIterator iter) {
		int constantPoolCount = iter.nextU2ToInt();
		ConstantPool pool = new ConstantPool();
		pool.addConstantInfo(new NullConstantInfo());
		for (int i = 1; i < constantPoolCount - 1; i++) {
			int tag = iter.nextU1ToInt();
			if (tag == 7) {//class_info
				int utf8Index = iter.nextU2ToInt();
				ClassInfo clzInfo = new ClassInfo(pool);
				clzInfo.setUtf8Index(utf8Index);
				pool.addConstantInfo(clzInfo);
			} else if (tag == 1) {//utf8_info
				int len = iter.nextU2ToInt();
				byte[] data = iter.getBytes(len);
				UTF8Info utf8Info = new UTF8Info(pool);
				utf8Info.setValue(Arrays.toString(data));
				pool.addConstantInfo(utf8Info);
			} else if (tag == 9) {//fieldref_info
                int clzIndex = iter.nextU2ToInt();
                int nameAndTypeIndex = iter.nextU2ToInt();
                FieldRefInfo fieldRefInfo = new FieldRefInfo(pool);
                fieldRefInfo.setClassInfoIndex(clzIndex);
                fieldRefInfo.setNameAndTypeIndex(nameAndTypeIndex);
                pool.addConstantInfo(fieldRefInfo);
            } else if (tag == 10) {//methodref_info
                int clzIndex = iter.nextU2ToInt();
                int nameAndTypeIndex = iter.nextU2ToInt();
                MethodRefInfo methodRefInfo = new MethodRefInfo(pool);
                methodRefInfo.setClassInfoIndex(clzIndex);
                methodRefInfo.setNameAndTypeIndex(nameAndTypeIndex);
                pool.addConstantInfo(methodRefInfo);
            } else if (tag == 12) {//nameAndType_info
                int nameIndex = iter.nextU2ToInt();
                int descriptorIndex = iter.nextU2ToInt();
                NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(pool);
                nameAndTypeInfo.setIndex1(nameIndex);
                nameAndTypeInfo.setIndex2(descriptorIndex);
                pool.addConstantInfo(nameAndTypeInfo);
            } else if (tag == 8) {//string_info
                int stringIndex = iter.nextU2ToInt();
                StringInfo stringInfo = new StringInfo(pool);
                stringInfo.setIndex(stringIndex);
                pool.addConstantInfo(stringInfo);
            } else {
                throw new RuntimeException("The constant "+ tag + " has not been build");
            }
		}
		return null;
	}
}