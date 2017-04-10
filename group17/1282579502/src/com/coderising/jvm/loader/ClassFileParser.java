package com.coderising.jvm.loader;

import java.io.UnsupportedEncodingException;

import com.coderising.jvm.clz.AccessFlag;
import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.clz.ClassIndex;
import com.coderising.jvm.constant.ClassInfo;
import com.coderising.jvm.constant.ConstantInfo;
import com.coderising.jvm.constant.ConstantInfoFactory;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.FieldRefInfo;
import com.coderising.jvm.constant.InvalidConstantInfoTypeException;
import com.coderising.jvm.constant.MethodRefInfo;
import com.coderising.jvm.constant.NameAndTypeInfo;
import com.coderising.jvm.constant.NullConstantInfo;
import com.coderising.jvm.constant.StringInfo;
import com.coderising.jvm.constant.UTF8Info;

public class ClassFileParser {
	ClassFile clz = null;
	public ClassFile parse(byte[] codes) {
		clz = new ClassFile();
		ByteCodeIterator iter = new ByteCodeIterator(codes);
		String magicHex = iter.getNextNHexString(4);
		System.out.println("Magic Number: "+magicHex);
		
		String minorHex = iter.getNextHexString() + iter.getNextHexString();
		int minorVersionInt = Integer.parseInt(minorHex, 16);
		String majorHex = iter.getNextHexString() + iter.getNextHexString();
		int majorVersionInt = Integer.parseInt(majorHex, 16);
		clz.setMajorVersion(majorVersionInt);clz.setMinorVersion(minorVersionInt);
		System.out.println("Major version: " + majorVersionInt + " minor version: " + minorVersionInt);
		clz.setConstPool(parseConstantPool(iter));
		fillClassInfo(clz, clz.getConstantPool());
		return clz;
	}

	private AccessFlag parseAccessFlag(ByteCodeIterator iter) {

		return null;
	}

	private ClassIndex parseClassInfex(ByteCodeIterator iter) {
		
		return null;

	}
	
	private void fillClassInfo(ClassFile clz, ConstantPool pool){
		ClassIndex newClassIndex = new ClassIndex();
		clz.setClassIndex(newClassIndex);
		for(int i = 0; i< pool.getSize(); i++){
			if(pool.getConstantInfo(i) instanceof ClassInfo){
				if(clz.getClzIndex().getThisClassIndex() == 0){
					System.out.println("class");
					clz.getClzIndex().setThisClassIndex(i);
				}
				else if(clz.getClzIndex().getSuperClassIndex() == 0){
					clz.getClzIndex().setSuperClassIndex(i);
				}
				else{
					break;
				}
			}
		}
	}

	private ConstantPool parseConstantPool(ByteCodeIterator iter) {
		String constantPoolSizeHex = iter.getNextHexString() + iter.getNextHexString();
		System.out.println("hex string: " + constantPoolSizeHex + " integer value: " + Integer.parseInt(constantPoolSizeHex, 16));
		int constantCount = Integer.parseInt(constantPoolSizeHex, 16);
		ConstantPool constantPool = new ConstantPool();
		constantPool.addConstantInfo(new NullConstantInfo());
		ConstantInfoFactory constantInfoFactory = new ConstantInfoFactory(iter, constantPool);
		while(constantCount-- > 1){
			try {
				constantInfoFactory.fillNextConstantInfo();
			} catch ( Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
			
		}
		
		for(int i = 0; i<constantPool.getSize(); i++){
			System.out.println("constant pool variable: " + i );
			ConstantInfo c = constantPool.getConstantInfo(i);
			System.out.println(c.getClass().getName());
		}
		return constantPool;
	}

	
}
