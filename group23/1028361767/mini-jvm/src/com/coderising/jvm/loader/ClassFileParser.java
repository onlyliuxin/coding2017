package com.coderising.jvm.loader;

import java.util.ArrayList;
import java.util.List;

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
import com.coderising.jvm.field.Field;
import com.coderising.jvm.method.Method;

public class ClassFileParser {
	
	public ClassFile parse(byte[] codes) {
		ClassFile classFile = new ClassFile();
		ByteCodeIterator iter = new ByteCodeIterator(codes);
		
//		String magicNumber = iter.nextUxToHexString(4);
		iter.nextUxToHexString(4);
		
		classFile.setMinorVersion(iter.nextU2ToInt());
		classFile.setMajorVersion(iter.nextU2ToInt());
		
		ConstantPool pool = parseConstantPool(iter);
		classFile.setConstPool(pool);
		
		classFile.setAccessFlag(parseAccessFlag(iter));
		
		classFile.setClassIndex(parseClassInfex(iter));
		
		parseInterfaces(iter);
		
		classFile.setFields(prarseField(iter, pool));
		classFile.setMethods(parseMethod(iter, classFile));
		return classFile;
	}

	private List<Method> parseMethod(ByteCodeIterator iter, ClassFile classFile) {
		List<Method> methods = new ArrayList<>();
		int mthodsCount = iter.nextU2ToInt();
		for(int i=0;i<mthodsCount;i++) {
			methods.add(Method.parse(classFile, iter));
		}
		return methods;
	}

	private List<Field> prarseField(ByteCodeIterator iter, ConstantPool pool) {
		List<Field> fields = new ArrayList<>();
		int fieldsCount = iter.nextU2ToInt();
		for(int i=0;i<fieldsCount;i++) {
			fields.add(Field.parse(pool, iter));
		}
		return fields;
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
	
	private void parseInterfaces(ByteCodeIterator iter) {
		int interfaceCount = iter.nextU2ToInt();

		System.out.println("interfaceCount:" + interfaceCount);

		// TODO : 如果实现了interface, 这里需要解析
	}
}
