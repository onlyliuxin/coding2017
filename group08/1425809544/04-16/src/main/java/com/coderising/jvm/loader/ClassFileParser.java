package com.coderising.jvm.loader;

import com.coderising.jvm.clz.AccessFlag;
import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.clz.ClassIndex;
import com.coderising.jvm.constant.*;
import com.coderising.jvm.field.Field;
import com.coderising.jvm.method.Method;

import java.io.UnsupportedEncodingException;

public class ClassFileParser {

	//解析字节码数组
	public ClassFile parse(byte[] codes) {
		ClassFile classFile = new ClassFile();
		//自定义字节码数组迭代器
		ByteCodeIterator iterator = new ByteCodeIterator(codes);

		String magicNumber = iterator.nextU4ToHexString();
		if (!"cafebabe".equals(magicNumber)) {
			return null;
		}
		classFile.setMagic(magicNumber);//魔数
		classFile.setMinorVersion(iterator.nextU2ToInt());//次版本号
		classFile.setMajorVersion(iterator.nextU2ToInt() );//主版本号

		ConstantPool pool = parseConstantPool(iterator);
		classFile.setConstantPool(pool);//常量池

		AccessFlag flag = parseAccessFlag(iterator);
		classFile.setAccessFlag(flag);//类访问标志

		ClassIndex clzIndex = parseClassIndex(iterator);
		classFile.setClzIndex(clzIndex);//类和父类索引

		parseInterfaces(iterator);//接口

		praseFields(classFile, iterator);//字段

		prrseMethods(classFile, iterator);//方法


		return classFile;
	}

	private void prrseMethods(ClassFile classFile, ByteCodeIterator iterator) {
		int methodCount = iterator.nextU2ToInt();

		for (int i = 0; i < methodCount; i++) {
			Method method = Method.parse(classFile, iterator);
			classFile.getMethods().add(method);
		}

	}


	private void praseFields(ClassFile classFile, ByteCodeIterator iterator) {

		int fieldCount = iterator.nextU2ToInt();

		for (int i = 0; i < fieldCount; i++) {
			Field field = Field.parse(classFile.getConstantPool(), iterator);
			classFile.getFields().add(field);
		}
	}

	//解析常量池
	private ConstantPool parseConstantPool(ByteCodeIterator iter) {


		int constantSize =  iter.nextU2ToInt();
		System.out.println("常量池数量:" + constantSize);
		ConstantPool pool = new ConstantPool();
		pool.addConstantInfo(new NullConstantInfo());


		for (int i = 1; i <=constantSize-1; i++) {
			int tag = iter.nextU1ToInt();
			if (tag == 7) {
				// Class Info
				int utf8Index = iter.nextU2ToInt();
				ClassInfo clzInfo = new ClassInfo(pool);
				clzInfo.setUtf8Index(utf8Index);

				pool.addConstantInfo(clzInfo);
			} else if (tag == 1) {
				// UTF-8 String
				int len = iter.nextU2ToInt();
				byte[] data = iter.getBytes(len);
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
			}else if (tag == 8) {
				StringInfo info = new StringInfo(pool);
				info.setIndex(iter.nextU2ToInt());
				pool.addConstantInfo(info);
			} else if (tag == 9) {
				FieldRefInfo field = new FieldRefInfo(pool);
				field.setClassInfoIndex(iter.nextU2ToInt());
				field.setNameAndTypeIndex(iter.nextU2ToInt());
				pool.addConstantInfo(field);
			} else if (tag == 10) {
				// MethodRef
				MethodRefInfo method = new MethodRefInfo(pool);
				method.setClassInfoIndex(iter.nextU2ToInt());
				method.setNameAndTypeIndex(iter.nextU2ToInt());
				pool.addConstantInfo(method);
			} else if (tag == 12) {
				// Name and Type Info
				NameAndTypeInfo nameType = new NameAndTypeInfo(pool);
				nameType.setIndex1(iter.nextU2ToInt());
				nameType.setIndex2(iter.nextU2ToInt());
				pool.addConstantInfo(nameType);
			} else {
				throw new RuntimeException("the constant pool tag " + tag + " has not been implemented yet.");
			}
		}

		System.out.println("Finished reading Constant pool ");
		return pool;
	}


	private AccessFlag parseAccessFlag(ByteCodeIterator iter) {
		AccessFlag flag = new AccessFlag(iter.nextU2ToInt());
		return  flag;
	}

	private ClassIndex parseClassIndex(ByteCodeIterator iter) {
		int thisClassIndex = iter.nextU2ToInt();
		int superClassIndex = iter.nextU2ToInt();

		ClassIndex classIndex = new ClassIndex();

		classIndex.setThisClassIndex(thisClassIndex);
		classIndex.setSuperClassIndex(superClassIndex);

		return classIndex;
	}

	private void parseInterfaces(ByteCodeIterator iter) {
		int interfaceCount = iter.nextU2ToInt();
		System.out.println("interfaceCount:" + interfaceCount);
		// TODO : 如果实现了interface, 这里需要解析
	}





	
}
