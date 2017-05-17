package com.github.chaoswang.learning.java.jvm.loader;

import com.github.chaoswang.learning.java.jvm.clz.AccessFlag;
import com.github.chaoswang.learning.java.jvm.clz.ClassFile;
import com.github.chaoswang.learning.java.jvm.clz.ClassIndex;
import com.github.chaoswang.learning.java.jvm.constant.ClassInfo;
import com.github.chaoswang.learning.java.jvm.constant.ConstantPool;
import com.github.chaoswang.learning.java.jvm.constant.UTF8Info;

public class ClassFileParser {
	
	
	public ClassFile parse(byte[] codes) {
		ByteCodeIterator codeIterator = new ByteCodeIterator(codes);
		ClassFile classFile = new ClassFile();
		//����ħ��
		codeIterator.getBytes(4);
		classFile.setMinorVersion(codeIterator.nextU2ToInt());
		classFile.setMajorVersion(codeIterator.nextU2ToInt());
		
		return classFile;
	}

	
	private AccessFlag parseAccessFlag(ByteCodeIterator iter) {
		//�����������أ������صĳ����Ƕ��٣�
		AccessFlag accessFlag = new AccessFlag(iter.nextU2ToInt());
		return accessFlag;
	}

	private ClassIndex parseClassIndex(ByteCodeIterator iter) {

		return null;

	}

	private ConstantPool parseConstantPool(ByteCodeIterator iter) {
		ConstantPool pool = new ConstantPool();
		ClassInfo classInfo = new ClassInfo(pool);
		//classInfo tagֵ
		iter.nextU1toInt();
		classInfo.setUtf8Index(iter.nextU2ToInt());
		pool.addConstantInfo(classInfo);
		
		UTF8Info utf8Info = new UTF8Info(pool);
		//UTF8Info tagֵ
		iter.nextU1toInt();
		//UTF8Info lengthֵ
		int length = iter.nextU2ToInt();
		utf8Info.setLength(length);
		utf8Info.setValue(iter.nextUxToHexString(length));
		pool.addConstantInfo(utf8Info);
		
		classInfo = new ClassInfo(pool);
		//classInfo tagֵ
		iter.nextU1toInt();
		classInfo.setUtf8Index(iter.nextU2ToInt());
		pool.addConstantInfo(classInfo);
		
		for(int i=0;i<8;i++){
			utf8Info = new UTF8Info(pool);
			//UTF8Info tagֵ
			iter.nextU1toInt();
			//UTF8Info lengthֵ
			length = iter.nextU2ToInt();
			utf8Info.setLength(length);
			utf8Info.setValue(iter.nextUxToHexString(length));
			pool.addConstantInfo(utf8Info);
		}
		
		return pool;
	}

}