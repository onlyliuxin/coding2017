package com.dudy.learn01.coderising.jvm.loader;


import com.dudy.learn01.coderising.jvm.clz.AccessFlag;
import com.dudy.learn01.coderising.jvm.clz.ClassFile;
import com.dudy.learn01.coderising.jvm.clz.ClassIndex;
import com.dudy.learn01.coderising.jvm.constant.ConstantPool;

public class ClassFileParser {

	public ClassFile parse(byte[] codes) {

		
		
		
		return null;
	}

	private AccessFlag parseAccessFlag(ByteCodeIterator iter) {

		return null;
	}

	private ClassIndex parseClassInfex(ByteCodeIterator iter) {

		return null;

	}

	private ConstantPool parseConstantPool(ByteCodeIterator iter) {
		return null;
	}
	private void parseInterfaces(ByteCodeIterator iter) {
		int interfaceCount = iter.nextU2ToInt();

		System.out.println("interfaceCount:" + interfaceCount);

		// TODO : 如果实现了interface, 这里需要解析
	}

	private void parseFileds(ClassFile clzFile, ByteCodeIterator iter) {
		

	}

	private void parseMethods(ClassFile clzFile, ByteCodeIterator iter) {

		

	}
	
}
