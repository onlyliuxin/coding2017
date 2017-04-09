package com.coderising.jvm.loader;

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

public class ClassFileParser {
	
	public ClassFile parse(byte[] codes) {
		ClassFile classFile = new ClassFile();
		ByteCodeIterator iter = new ByteCodeIterator(codes);
		
//		String magicNumber = iter.nextUxToHexString(4);
		iter.nextUxToHexString(4);
		
		classFile.setMinorVersion(iter.nextU2ToInt());
		classFile.setMajorVersion(iter.nextU2ToInt());
		
		classFile.setConstPool(parseConstantPool(iter));
		
		classFile.setAccessFlag(parseAccessFlag(iter));
		
		classFile.setClassIndex(parseClassInfex(iter));
		return classFile;
	}

	private AccessFlag parseAccessFlag(ByteCodeIterator iter) {
		AccessFlag accessFlag = new AccessFlag(iter.nextU2ToInt());
		return accessFlag;
	}

	private ClassIndex parseClassInfex(ByteCodeIterator iter) {
		ClassIndex classIndex = new ClassIndex();
		classIndex.setThisClassIndex(iter.nextU2ToInt());
		classIndex.setSuperClassIndex(iter.nextU2ToInt());
		return classIndex;

	}

	private ConstantPool parseConstantPool(ByteCodeIterator iter) {
		ConstantPool pool = new ConstantPool();
		pool.addConstantInfo(new NullConstantInfo());
		int length = iter.nextU2ToInt();
		int type;
		for(int i=1;i<length;i++){
			type = iter.nextU1ToInt();
			switch(type){
			case 7:
				ClassInfo classInfo = new ClassInfo(pool);
				classInfo.setUtf8Index(iter.nextU2ToInt());
				pool.addConstantInfo(classInfo);
				break;
			case 1:
				UTF8Info utf8Info = new UTF8Info(pool);
				utf8Info.setLength(iter.nextU2ToInt());
				utf8Info.setValue(iter.nextUxToString(utf8Info.getLength()));
				pool.addConstantInfo(utf8Info);
				break;
			case 8:
				StringInfo stringInfo = new StringInfo(pool);
				stringInfo.setIndex(iter.nextU2ToInt());
				pool.addConstantInfo(stringInfo);
				break;
			case 9:
				FieldRefInfo fieldRefInfo = new FieldRefInfo(pool);
				fieldRefInfo.setClassInfoIndex(iter.nextU2ToInt());
				fieldRefInfo.setNameAndTypeIndex(iter.nextU2ToInt());
				pool.addConstantInfo(fieldRefInfo);
				break;
			case 10:
				MethodRefInfo methodRefInfo = new MethodRefInfo(pool);
				methodRefInfo.setClassInfoIndex(iter.nextU2ToInt());
				methodRefInfo.setNameAndTypeIndex(iter.nextU2ToInt());
				pool.addConstantInfo(methodRefInfo);
				break;	
			case 12:
				NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(pool);
				nameAndTypeInfo.setIndex1(iter.nextU2ToInt());
				nameAndTypeInfo.setIndex2(iter.nextU2ToInt());
				pool.addConstantInfo(nameAndTypeInfo);
				break;
			default:
				System.out.println("常量池类型" + type + "还没实现");
				break;
			}
		}
		return pool;
	}
	
}
