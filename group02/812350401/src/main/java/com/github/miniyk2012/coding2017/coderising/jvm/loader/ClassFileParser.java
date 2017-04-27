package com.github.miniyk2012.coding2017.coderising.jvm.loader;

import com.github.miniyk2012.coding2017.coderising.jvm.clz.AccessFlag;
import com.github.miniyk2012.coding2017.coderising.jvm.clz.ClassFile;
import com.github.miniyk2012.coding2017.coderising.jvm.clz.ClassIndex;
import com.github.miniyk2012.coding2017.coderising.jvm.constant.*;


public class ClassFileParser {

	public ClassFile parse(byte[] codes) {

		ClassFile clzFile = new ClassFile();
        ByteCodeIterator byteCodeIterator = new ByteCodeIterator(codes);
        byteCodeIterator.skip(4);  // skip magic
        int minorVersion = byteCodeIterator.nextU2toInt();
        int majorVersion = byteCodeIterator.nextU2toInt();
        ConstantPool constantPool = parseConstantPool(byteCodeIterator);
        AccessFlag accessFlag = parseAccessFlag(byteCodeIterator);
        ClassIndex classIndex = parseClassInfex(byteCodeIterator);

        clzFile.setMinorVersion(minorVersion);
        clzFile.setMajorVersion(majorVersion);
        clzFile.setConstPool(constantPool);
        clzFile.setAccessFlag(accessFlag);
        clzFile.setClassIndex(classIndex);
        return clzFile;
	}

	protected AccessFlag parseAccessFlag(ByteCodeIterator iter) {
	    int access_flags = iter.nextU2toInt();
        AccessFlag accessFlag = new AccessFlag(access_flags);
		return accessFlag;
	}

	protected ClassIndex parseClassInfex(ByteCodeIterator iter) {
        ClassIndex classIndex = new ClassIndex();
        int thisClassIndex = iter.nextU2toInt();
        int superClassIndex = iter.nextU2toInt();
        classIndex.setThisClassIndex(thisClassIndex);
        classIndex.setSuperClassIndex(superClassIndex);
		return classIndex;
	}

	protected ConstantPool parseConstantPool(ByteCodeIterator iter) {
        ConstantPool constantPool = new ConstantPool();
        int constant_pool_count = iter.nextU2toInt();
        ConstantInfo constantInfo;
        constantPool.addConstantInfo(new NullConstantInfo());
        for (int i=1; i<constant_pool_count; i++) {
            int tag = iter.nextU1toInt();
            constantInfo = getConstantInfo(iter, tag, constantPool);
            constantPool.addConstantInfo(constantInfo);
        }
        return constantPool;
	}

    protected ConstantInfo getConstantInfo(ByteCodeIterator iter, int tag, ConstantPool pool) {
	    switch (tag) {
            case ConstantInfo.UTF8_INFO: {
                UTF8Info utf8Info = new UTF8Info(pool);
                int length = iter.nextU2toInt();
                String value = iter.readUtf8(length);
                utf8Info.setLength(length);
                utf8Info.setValue(value);
                return utf8Info;
            }
            case ConstantInfo.FLOAT_INFO: {
                throw new RuntimeException(String.format("FLOAT_INFO常量池类型[%d]还未实现", tag));
            }
            case ConstantInfo.CLASS_INFO: {
                ClassInfo classInfo = new ClassInfo(pool);
                int utf8Index = iter.nextU2toInt();
                classInfo.setUtf8Index(utf8Index);
                return classInfo;
            }
            case ConstantInfo.FIELD_INFO: {
                FieldRefInfo fieldRefInfo = new FieldRefInfo(pool);
                int classInfoIndex = iter.nextU2toInt();
                fieldRefInfo.setClassInfoIndex(classInfoIndex);
                int nameAndTypeIndex = iter.nextU2toInt();
                fieldRefInfo.setNameAndTypeIndex(nameAndTypeIndex);
                return fieldRefInfo;
            }
            case ConstantInfo.METHOD_INFO: {
                MethodRefInfo methodRefInfo = new MethodRefInfo(pool);
                int classInfoIndex = iter.nextU2toInt();
                int nameAndTypeIndex = iter.nextU2toInt();
                methodRefInfo.setClassInfoIndex(classInfoIndex);
                methodRefInfo.setNameAndTypeIndex(nameAndTypeIndex);
                return methodRefInfo;
            }
            case ConstantInfo.NAME_AND_TYPE_INFO: {
                NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(pool);
                int index1 = iter.nextU2toInt();
                int index2 = iter.nextU2toInt();
                nameAndTypeInfo.setIndex1(index1);
                nameAndTypeInfo.setIndex2(index2);
                return nameAndTypeInfo;
            }
            case ConstantInfo.STRING_INFO: {
                StringInfo stringInfo = new StringInfo(pool);
                int index = iter.nextU2toInt();
                stringInfo.setIndex(index);
                return stringInfo;
            }
            default: {
                throw new RuntimeException(String.format("该常量池类型[%d]还未实现", tag));
            }
        }
    }
}
