package com.github.wdn.coding2017.jvm.loader;

import com.github.wdn.coding2017.jvm.clz.*;
import com.github.wdn.coding2017.jvm.constant.*;

import java.util.ArrayList;
import java.util.List;

public class ClassFileParser {
	ConstantPool pool = new ConstantPool();
	public ClassFile parse(byte[] codes) {
		ByteCodeIterator iter = new ByteCodeIterator(codes);
		String magic = iter.readU4ToString();
		if(!"cafebabe".equals(magic)){
			throw new Error();
		}
		ClassFile classFile = new ClassFile();
		classFile.setMinorVersion(iter.readU2ToInt());
		classFile.setMajorVersion(iter.readU2ToInt());
		classFile.setConstantPool(parseConstantPool(iter));
		classFile.setAccessFlag(parseAccessFlag(iter));
		classFile.setClassIndex(parseClassIndex(iter));
		parseInterface(iter);
		classFile.setFields(parseField(iter));
		classFile.setMethods(parseMethod(iter));
		return classFile;
	}

	private List<Method> parseMethod(ByteCodeIterator iter) {
		int methodCount = iter.readU2ToInt();
		List<Method> methods = new ArrayList<>(methodCount);
		for (int i = 0; i < methodCount; i++) {
			Method method = Method.parse(pool, iter);
			method.setPool(pool);
			methods.add(method);
		}
		return methods;
	}

	private List<Field> parseField(ByteCodeIterator iter) {
		int fieldsCount = iter.readU2ToInt();
		List<Field> fields = new ArrayList<>(fieldsCount);
		for (int i = 0; i < fieldsCount; i++) {
			Field field = Field.parse(iter);
			field.setPool(pool);
			fields.add(field);
		}
		return fields;
	}

	private void parseInterface(ByteCodeIterator iter){
		int interfaceNum = iter.readU2ToInt();
		if (interfaceNum > 0) {
			throw new RuntimeException("接口数量>0");
		}
	}
	private AccessFlag parseAccessFlag(ByteCodeIterator iter) {
		return new AccessFlag(iter.readU2ToInt());
	}

	private ClassIndex parseClassIndex(ByteCodeIterator iter) {
		return new ClassIndex(iter.readU2ToInt(),iter.readU2ToInt());
	}

	public ConstantPool parseConstantPool(ByteCodeIterator iter) {

		int constantPoolNum = iter.readU2ToInt();
		for (int i = 0; i < constantPoolNum-1; i++) {
			int type = iter.readToInt();
			if(type==7){// class
				ClassInfo classInfo = new ClassInfo(pool);
				classInfo.setNameIndex(iter.readU2ToInt());
				pool.put(classInfo);
			}else if(type==9){// Fieldref
				FieldRefInfo fieldRefInfo = new FieldRefInfo(pool);
				fieldRefInfo.setClassInfoIndex(iter.readU2ToInt());
				fieldRefInfo.setNameAndTypeIndex(iter.readU2ToInt());
				pool.put(fieldRefInfo);
			}else if(type==10){// Methodref
				MethodRefInfo methodRefInfo = new MethodRefInfo(pool);
				methodRefInfo.setClassInfoIndex(iter.readU2ToInt());
				methodRefInfo.setNameAndTypeIndex(iter.readU2ToInt());
				pool.put(methodRefInfo);
			}else if(type==1){// Utf8
				int length = iter.readU2ToInt();
				String value = iter.readCustomToString(length);
				UTF8Info utf8Info = new UTF8Info(pool);
				utf8Info.setValue(value);
				pool.put(utf8Info);
			}else if(type==8){// String
				StringInfo stringInfo = new StringInfo(pool);
				stringInfo.setStringIndex(iter.readU2ToInt());
				pool.put(stringInfo);
			}else if(type==12){// NameAndType
				NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(pool);
				nameAndTypeInfo.setNameIndex(iter.readU2ToInt());
				nameAndTypeInfo.setDescriptorIndex(iter.readU2ToInt());
				pool.put(nameAndTypeInfo);
			}else{
				throw new RuntimeException("未知类型"+type);
			}
		}
		return pool;
	}
}
