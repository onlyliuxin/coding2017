package com.ace.jvm.loader;

import java.io.UnsupportedEncodingException;

import com.ace.jvm.clz.AccessFlag;
import com.ace.jvm.clz.ClassFile;
import com.ace.jvm.clz.ClassIndex;
import com.ace.jvm.constant.ClassInfo;
import com.ace.jvm.constant.ConstantPool;
import com.ace.jvm.constant.FieldRefInfo;
import com.ace.jvm.constant.MethodRefInfo;
import com.ace.jvm.constant.NameAndTypeInfo;
import com.ace.jvm.constant.NullConstantInfo;
import com.ace.jvm.constant.StringInfo;
import com.ace.jvm.constant.UTF8Info;

public class ClassFileParser {

	public ClassFile parse(byte[] codes) {
		ClassFile clzFile = new ClassFile();
		ByteCodeIterator iter = new ByteCodeIterator(codes);

		String magicNum = iter.nextU4ToHexString();
		if (!"cafebabe".equals(magicNum)) {
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
		int flagValue = iter.nextU2ToInt();
		AccessFlag accessFlag = new AccessFlag(flagValue);
		return accessFlag;
	}

	private ClassIndex parseClassInfex(ByteCodeIterator iter) {
		ClassIndex classIndex = new ClassIndex();
		int thisClassIndex = iter.nextU2ToInt();
		classIndex.setThisClassIndex(thisClassIndex);
		
		int superClassIndex = iter.nextU2ToInt();
		classIndex.setSuperClassIndex(superClassIndex);
		
		return classIndex;

	}

	private ConstantPool parseConstantPool(ByteCodeIterator iter) {
		ConstantPool pool = new ConstantPool();
		int poolCount = iter.nextU2ToInt();

		pool.addConstantInfo(new NullConstantInfo());

		for (int i = 1; i < poolCount - 1; i++) {
			int tag = iter.nextU1ToInt();
			
			if(tag == 7){
				int utf8Index = iter.nextU2ToInt();
				ClassInfo classInfo = new ClassInfo(pool);
				classInfo.setUtf8Index(utf8Index);
				
				pool.addConstantInfo(classInfo);
			} else if(tag == 1){
				int len = iter.nextU2ToInt();
				byte[] data = iter.getBytes(len);
				String str = null;
				try {
					 str = new String(data, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				UTF8Info utf8Info = new UTF8Info(pool);
				utf8Info.setLength(len);
				utf8Info.setValue(str);
				
				pool.addConstantInfo(utf8Info);
			} else if(tag == 8){
				StringInfo stringInfo = new StringInfo(pool);
				int index = iter.nextU2ToInt();
				stringInfo.setIndex(index);
				
				pool.addConstantInfo(stringInfo);
			} else if(tag == 9){
				FieldRefInfo fieldRefInfo = new FieldRefInfo(pool);
				int classInfoIndex = iter.nextU2ToInt();
				int nameAndTypeIndex = iter.nextU2ToInt();
				
				fieldRefInfo.setClassInfoIndex(classInfoIndex);
				fieldRefInfo.setNameAndTypeIndex(nameAndTypeIndex);
				
				pool.addConstantInfo(fieldRefInfo);
			} else if(tag == 10){
				MethodRefInfo methodRefInfo = new MethodRefInfo(pool);
				int classInfoIndex = iter.nextU2ToInt();
				int nameAndTypeIndex = iter.nextU2ToInt();
				
				methodRefInfo.setClassInfoIndex(classInfoIndex);
				methodRefInfo.setNameAndTypeIndex(nameAndTypeIndex);
				
				pool.addConstantInfo(methodRefInfo);
			} else if(tag == 12){
				NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(pool);
				int index1 = iter.nextU2ToInt();
				int index2 = iter.nextU2ToInt();
				
				nameAndTypeInfo.setIndex1(index1);
				nameAndTypeInfo.setIndex2(index2);
				
				pool.addConstantInfo(nameAndTypeInfo);
			} else {
				throw new RuntimeException("the constant pool tag " + tag + " is not exist.");
			}
			
		}

		return pool;
	}

}
