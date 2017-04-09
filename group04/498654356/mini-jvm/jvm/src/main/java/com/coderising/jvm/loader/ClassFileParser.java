package com.coderising.jvm.loader;

import com.coderising.jvm.clz.AccessFlag;
import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.clz.ClassIndex;
import com.coderising.jvm.constant.ClassInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.FieldRefInfo;
import com.coderising.jvm.constant.MethodRefInfo;
import com.coderising.jvm.constant.NULLConstantInfo;
import com.coderising.jvm.constant.NameAndTypeInfo;
import com.coderising.jvm.constant.StringInfo;
import com.coderising.jvm.constant.UTF8Info;

public class ClassFileParser {

	public ClassFile parse(byte[] codes) {
		if(codes == null || codes.length == 0){
			throw new RuntimeException("illegality byteCode");
		}
		ByteCodeIterator it = new ByteCodeIterator(codes);
		ClassFile clzFile = new ClassFile();
		it.skip(4);
		clzFile.setMinorVersion(it.next2ByteToInt());
		clzFile.setMajorVersion(it.next2ByteToInt());
		
		//constant_pool
		clzFile.setConstantPool(parseConstantPool(it));
		
		//access_flag
		clzFile.setAccessFlag(parseAccessFlag(it));
		
		//classIndex
		clzFile.setClassIndex(parseClassInfex(it));
		
		return clzFile;
	}

	private AccessFlag parseAccessFlag(ByteCodeIterator iter) {
		int value = iter.next2ByteToInt();
		AccessFlag accessFlag = new AccessFlag(value);
		return accessFlag;
	}

	private ClassIndex parseClassInfex(ByteCodeIterator iter) {
		int thisClassIndex = iter.next2ByteToInt();
		int superClassIndex = iter.next2ByteToInt();
		ClassIndex classIndex = new ClassIndex(thisClassIndex, superClassIndex);
		return classIndex;

	}

	private ConstantPool parseConstantPool(ByteCodeIterator iter) {
		int poolCount = iter.next2ByteToInt() - 1;
		ConstantPool constantPool = new ConstantPool();
		constantPool.setSize(poolCount);
		constantPool.addConstantInfo(new NULLConstantInfo());
		while(poolCount > 0) {
			int tag = iter.next1ByteToInt();
			if(tag == ConstantPool.C_CLASS_INFO) {
				int nameIndex = iter.next2ByteToInt();
				ClassInfo classInfo = new ClassInfo(nameIndex, constantPool);
				constantPool.addConstantInfo(classInfo);
			} else if(tag == ConstantPool.C_UTF8_INFO) {
				int length = iter.next2ByteToInt();
				String str = iter.nextLengthByteToString(length);
				UTF8Info utf8Info = new UTF8Info(str);
				constantPool.addConstantInfo(utf8Info);
			} else if(tag == ConstantPool.C_METHODREF_INFO) {
				int classInfoIndex = iter.next2ByteToInt();
				int nameAndTypeIndex = iter.next2ByteToInt();
				MethodRefInfo methodRefInfo = new MethodRefInfo(classInfoIndex, nameAndTypeIndex);
				constantPool.addConstantInfo(methodRefInfo);
			} else if(tag == ConstantPool.C_NAME_AND_TYPE_INFO) {
				int index1 = iter.next2ByteToInt();
				int index2 = iter.next2ByteToInt();
				NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(index1 , index2);
				constantPool.addConstantInfo(nameAndTypeInfo);
			} else if(tag == ConstantPool.C_FIELDREF_INFO) {
				int classInfoIndex = iter.next2ByteToInt();
				int nameAndTypeIndex = iter.next2ByteToInt();
				FieldRefInfo fieldRefInfo = new FieldRefInfo(classInfoIndex, nameAndTypeIndex) ;
				constantPool.addConstantInfo(fieldRefInfo);
			} else if(tag == ConstantPool.C_STRING_INFO) {
				int index = iter.next2ByteToInt();
				StringInfo stringInfo = new StringInfo(index);
				constantPool.addConstantInfo(stringInfo);
			} else {
				throw new RuntimeException("no implement tag = " + tag);
			}
			poolCount--;
		}
		return constantPool;
	}

}
