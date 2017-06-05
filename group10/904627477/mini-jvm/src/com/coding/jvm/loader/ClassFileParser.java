package com.coding.jvm.loader;

import java.util.ArrayList;
import java.util.List;

import com.coding.jvm.clz.AccessFlag;
import com.coding.jvm.clz.ClassFile;
import com.coding.jvm.clz.ClassIndex;
import com.coding.jvm.clz.InterfaceIndex;
import com.coding.jvm.constant.ClassInfo;
import com.coding.jvm.constant.ConstantInfo;
import com.coding.jvm.constant.ConstantPool;
import com.coding.jvm.constant.DoubleInfo;
import com.coding.jvm.constant.FieldRefInfo;
import com.coding.jvm.constant.FloatInfo;
import com.coding.jvm.constant.IntegerInfo;
import com.coding.jvm.constant.InterfaceMethodRefInfo;
import com.coding.jvm.constant.LongInfo;
import com.coding.jvm.constant.MethodRefInfo;
import com.coding.jvm.constant.NameAndTypeInfo;
import com.coding.jvm.constant.NullConstantInfo;
import com.coding.jvm.constant.StringInfo;
import com.coding.jvm.constant.UTF8Info;
import com.coding.jvm.field.Field;
import com.coding.jvm.method.Method;
import com.coding.util.Util;


public class ClassFileParser {

	//Packages a byte array into a ClassFile object
	public ClassFile parse(byte[] codes) {
		if(codes==null){
			return null;
		}
		ByteCodeIterator codeIter = new ByteCodeIterator(codes);
		if(!isClassByteArray(codeIter)){
			return null;
		}
		ClassFile claFile = new ClassFile();
		claFile.setMinorVersion(codeIter.nextU2ToInt());
		claFile.setMajorVersion(codeIter.nextU2ToInt());
		ConstantPool pool = parseConstantPool(codeIter);
		claFile.setConstPool(pool);
		claFile.setAccessFlag(parseAccessFlag(codeIter));
		claFile.setClassIndex(parseClassInfex(codeIter));
		claFile.setInterfaceIndex(parseInterfaces(codeIter));
		int fieldCount = codeIter.nextU2ToInt();
		for (int i = 0; i < fieldCount; i++) {
			claFile.addField(Field.parse(pool, codeIter));
		}
		int methodCount = codeIter.nextU2ToInt();
		for (int i = 0; i < methodCount; i++) {
			claFile.addMethod(Method.parse(claFile, codeIter));
		}
		return claFile;
	}

	private boolean isClassByteArray(ByteCodeIterator codeIte) {
		if(codeIte.has(4)){
			String magic = codeIte.nextUxToHexString(4);
			if("cafebabe".equals(magic.toLowerCase())){
				return true;
			}
		}
		return false;
	}

	private AccessFlag parseAccessFlag(ByteCodeIterator iter) {
		AccessFlag accessFlag = new AccessFlag(iter.nextU2ToInt());
		return accessFlag;
	}

	private ClassIndex parseClassInfex(ByteCodeIterator iter) {
		ClassIndex claIndex = new ClassIndex();
		claIndex.setThisClassIndex(iter.nextU2ToInt());
		claIndex.setSuperClassIndex(iter.nextU2ToInt());
		return claIndex;
	}

	private ConstantPool parseConstantPool(ByteCodeIterator iter) {
		int constantNum = iter.nextU2ToInt()-1;
		ConstantPool pool = new ConstantPool();
		pool.addConstantInfo(new NullConstantInfo());
		while(constantNum>0){
			int type = iter.nextU1ToInt();
			switch (type) {
			case 1:
				UTF8Info utf8Info = new UTF8Info(pool);
				int len = iter.nextU2ToInt();
				utf8Info.setLength(len);
				utf8Info.setValue(iter.nextUxToString(len));
				pool.addConstantInfo(utf8Info);
				break;
			case 3:
				IntegerInfo intInfo = new IntegerInfo(pool);
				intInfo.setValue(iter.nextU4ToInt());
				pool.addConstantInfo(intInfo);
				break;
			case 4:
				FloatInfo floatInfo = new FloatInfo(pool);
				floatInfo.setValue(Util.getFloat(iter.nextUx(4)));
				pool.addConstantInfo(floatInfo);
				break;
			case 5:
				LongInfo longInfo = new LongInfo(pool);
				longInfo.setValue(Util.getLong(iter.nextUx(8)));
				pool.addConstantInfo(longInfo);
				break;
			case 6:
				DoubleInfo doubleInfo = new DoubleInfo(pool);
				doubleInfo.setValue(Util.getDouble(iter.nextUx(8)));
				pool.addConstantInfo(doubleInfo);
				break;
			case 7:
				ClassInfo classInfo = new ClassInfo(pool);
				classInfo.setUtf8Index(iter.nextU2ToInt());
				pool.addConstantInfo(classInfo);
				break;	
			case 8:
				StringInfo strInfo = new StringInfo(pool);
				strInfo.setIndex(iter.nextU2ToInt());
				pool.addConstantInfo(strInfo);
				break;
			case 9:
				FieldRefInfo frefInfo = new FieldRefInfo(pool);
				frefInfo.setClassInfoIndex(iter.nextU2ToInt());
				frefInfo.setNameAndTypeIndex(iter.nextU2ToInt());
				pool.addConstantInfo(frefInfo);
				break;
			case 10:
				MethodRefInfo mrefInfo = new MethodRefInfo(pool);
				mrefInfo.setClassInfoIndex(iter.nextU2ToInt());
				mrefInfo.setNameAndTypeIndex(iter.nextU2ToInt());
				pool.addConstantInfo(mrefInfo);
				break;
			case 11:
				InterfaceMethodRefInfo imrefInfo = new InterfaceMethodRefInfo(pool);
				imrefInfo.setClassInfoIndex(iter.nextU2ToInt());
				imrefInfo.setNameAndTypeIndex(iter.nextU2ToInt());
				pool.addConstantInfo(imrefInfo);
				break;
			case 12:
				NameAndTypeInfo ntInfo = new NameAndTypeInfo(pool);
				ntInfo.setIndex1(iter.nextU2ToInt());
				ntInfo.setIndex2(iter.nextU2ToInt());
				pool.addConstantInfo(ntInfo);
				break;
			default:
				break;
			}
			constantNum--;
		}
		return pool;
	}

	private InterfaceIndex parseInterfaces(ByteCodeIterator iter) {
		InterfaceIndex interfaceIndex = new InterfaceIndex();
		int interfaceCount = iter.nextU2ToInt();
		System.out.println("interfaceCount:" + interfaceCount);
		for (int i = 0; i < interfaceCount; i++) {
			interfaceIndex.addInterfaceIndex(iter.nextU2ToInt());
		}
		return interfaceIndex;
	}
	
	
	
}
