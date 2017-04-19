package com.sprint.jvm.loader;

import com.sprint.jvm.clz.ClassFile;
public class ClassFileParser {
	public ClassFile parse(byte[] codes) {
		ClassFile clzFile = new ClassFile(); 		
		ByteCodeIterator iter = new ByteCodeIterator(codes);
		String magicNumber = iter.nextU4ToHexString();
		if (!"cafebabe".equals(magicNumber)) {
			return null;
		}
		return clzFile;
	}
}
