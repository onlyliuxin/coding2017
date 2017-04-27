package com.github.HarryHook.coding2017.jvm.loader;

import java.io.UnsupportedEncodingException;


import com.github.HarryHook.coding2017.jvm.clz.AccessFlag;
import com.github.HarryHook.coding2017.jvm.clz.ClassFile;
import com.github.HarryHook.coding2017.jvm.clz.ClassIndex;
import com.github.HarryHook.coding2017.jvm.constant.ClassInfo;
import com.github.HarryHook.coding2017.jvm.constant.ConstantPool;
import com.github.HarryHook.coding2017.jvm.constant.FieldRefInfo;
import com.github.HarryHook.coding2017.jvm.constant.MethodRefInfo;
import com.github.HarryHook.coding2017.jvm.constant.NameAndTypeInfo;
import com.github.HarryHook.coding2017.jvm.constant.NullConstantInfo;
import com.github.HarryHook.coding2017.jvm.constant.StringInfo;
import com.github.HarryHook.coding2017.jvm.constant.UTF8Info;
import com.github.HarryHook.coding2017.jvm.field.Field;
import com.github.HarryHook.coding2017.jvm.method.Method;

public class ClassFileParser {

    public ClassFile parse(byte[] codes) {

	ClassFile clzFile = new ClassFile();
	ByteCodeIterator iter = new ByteCodeIterator(codes);
	String magicNumber = iter.nextU4ToHexString();
	if (!"cafebabe".equals(magicNumber)) {
	    return null;
	}

	clzFile.setMinorVersion(iter.nextU2ToInt());
	clzFile.setMajorVersion(iter.nextU2ToInt());

	ConstantPool pool = parseConstantPool(iter);
	clzFile.setConstantPool(pool);

	AccessFlag flag = parseAccessFlag(iter);
	clzFile.setAccessFlag(flag);
	
	ClassIndex clzIndex = parseClassIndex(iter);
	clzFile.setClzIndex(clzIndex);
	
	parseInterfaces(iter);
	
	parseField(clzFile, iter);
	
	parseMethod(clzFile, iter);
	return clzFile;
    }

    private AccessFlag parseAccessFlag(ByteCodeIterator iter) {
	
	AccessFlag flag = new AccessFlag(iter.nextU2ToInt());
	return flag;
    }

    private ClassIndex parseClassIndex(ByteCodeIterator iter) {
	
	int thisClassIndex = iter.nextU2ToInt();
	int superClassIndex = iter.nextU2ToInt();
	ClassIndex clzIndex = new ClassIndex();
	clzIndex.setThisClassIndex(thisClassIndex);
	clzIndex.setSuperClassIndex(superClassIndex);

	return clzIndex;

    }

    private ConstantPool parseConstantPool(ByteCodeIterator iter) {
	int constPoolCount = iter.nextU2ToInt();
	System.out.println("const Pool Count : " + constPoolCount);
	ConstantPool pool = new ConstantPool();
	pool.addConstantInfo(new NullConstantInfo());
	for (int i = 1; i <= constPoolCount - 1; i++) {
	    int tag = iter.nextU1ToInt();
	    if (tag == 7) {
		// Class Info
		int utf8Index = iter.nextU2ToInt();
		ClassInfo clzInfo = new ClassInfo(pool);
		clzInfo.setUtf8Index(utf8Index);
		pool.addConstantInfo(clzInfo);
	    } else if (tag == 1) {
		// UTF8 String
		int len = iter.nextU2ToInt();
		byte[] data = iter.getByte(len);
		String value = null;
		try {
		    value = new String(data, "UTF-8");
		} catch (UnsupportedEncodingException e) {
		    e.printStackTrace();

		}
		UTF8Info utf8Str = new UTF8Info(pool);
		utf8Str.setLength(len);
		utf8Str.setValue(value);
		pool.addConstantInfo(utf8Str);
	    } else if (tag == 8) {
		StringInfo info = new StringInfo(pool);
		info.setIndex(iter.nextU2ToInt());
		pool.addConstantInfo(info);
	    } else if (tag == 9) {
		FieldRefInfo field = new FieldRefInfo(pool);
		field.setClassInfoIndex(iter.nextU2ToInt());
		field.setNameAndTypeIndex(iter.nextU2ToInt());
		pool.addConstantInfo(field);
	    } else if (tag == 10) {
		MethodRefInfo method = new MethodRefInfo(pool);
		method.setClassInfoIndex(iter.nextU2ToInt());
		method.setNameAndTypeIndex(iter.nextU2ToInt());
		pool.addConstantInfo(method);
	    } else if (tag == 12) {
		NameAndTypeInfo nameType = new NameAndTypeInfo(pool);
		nameType.setIndex1(iter.nextU2ToInt());
		nameType.setIndex2(iter.nextU2ToInt());
		pool.addConstantInfo(nameType);
	    } else {
		throw new RuntimeException("the constant pool tag" + tag + "has not complemented");
	    }

	}
	System.out.println("Finished reading Constant Pool ");
	
	return pool;
    }
    private void parseInterfaces(ByteCodeIterator iter) {
	int interfaceCount = iter.nextU2ToInt();

	System.out.println("interfaceCount:" + interfaceCount);

	// TODO : 如果实现了interface, 这里需要解析
    }
    
    private void parseField(ClassFile clzFile, ByteCodeIterator iter){
	int fieldCount = iter.nextU2ToInt();
	for(int i=1; i<=fieldCount; i++) {
	    Field f = Field.parse(clzFile.getConstantPool(), iter);
	    clzFile.addField(f);
	    
	}
    }
    
    private void parseMethod(ClassFile clzFile, ByteCodeIterator iter){
	int methodCount = iter.nextU2ToInt();
	for(int i=1; i<=methodCount; i++) {
	    Method m = Method.parse(clzFile, iter);
	    clzFile.addMethod(m);
	}
    }


}
