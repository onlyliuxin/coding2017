package miniJVM.loader;


import miniJVM.clz.AccessFlag;
import miniJVM.clz.ClassFile;
import miniJVM.clz.ClassIndex;
import miniJVM.constant.ConstantPool;

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
//	private void parseInterfaces(ByteCodeIterator iter) {
//		int interfaceCount = iter.nextU2ToInt();
//
//		System.out.println("interfaceCount:" + interfaceCount);
//
//		// TODO : 如果实现了interface, 这里需要解析
//	}
//
//	private void parseFileds(ClassFile clzFile, ByteCodeIterator iter) {
//
//
//	}
//
//	private void parseMethods(ClassFile clzFile, ByteCodeIterator iter) {
//
//	}
	
}
