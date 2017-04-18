package org.xukai.jvm.loader;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.xukai.jvm.clz.AccessFlag;
import org.xukai.jvm.clz.ClassFile;
import org.xukai.jvm.clz.ClassIndex;
import org.xukai.jvm.constant.ClassInfo;
import org.xukai.jvm.constant.ConstantPool;
import org.xukai.jvm.constant.FieldRefInfo;
import org.xukai.jvm.constant.MethodRefInfo;
import org.xukai.jvm.constant.NameAndTypeInfo;
import org.xukai.jvm.constant.NullConstantInfo;
import org.xukai.jvm.constant.StringInfo;
import org.xukai.jvm.constant.UTF8Info;
import org.xukai.jvm.field.Field;
import org.xukai.jvm.method.Method;

import java.util.ArrayList;
import java.util.List;


public class ClassFileParser {

	public ClassFile parse(byte[] codes) {

		ByteCodeIterator iter = new ByteCodeIterator(codes, 0);
		String magic = iter.nextToString(4);
		Preconditions.checkArgument(magic.equals("cafebabe"),"无法解析此class文件");

		ClassFile classFile = new ClassFile();
		int minorVersion = iter.nextToInt(2);
		classFile.setMinorVersion(minorVersion);
		int majorVersion = iter.nextToInt(2);
		classFile.setMajorVersion(majorVersion);

		ConstantPool pool = parseConstantPool(iter);
		AccessFlag accessFlag = parseAccessFlag(iter);
		ClassIndex classIndex = parseClassInfex(iter);
		parseInterfaces(iter);

		classFile.setConstPool(pool);
		classFile.setAccessFlag(accessFlag);
		classFile.setClassIndex(classIndex);

		parseFields(pool, iter, classFile);
		parseMethod(pool, iter, classFile);

		return classFile;
	}

	private List<Field> parseFields(ConstantPool pool, ByteCodeIterator iter, ClassFile classFile) {
		int fieldsCount = iter.nextToInt(2);
		ArrayList<Field> fields = new ArrayList<>(fieldsCount);
		for (int i = 0; i < fieldsCount; i++) {
			int flages = iter.nextToInt(2);
			int nameIndex = iter.nextToInt(2);
			int descriptorIndex = iter.nextToInt(2);
			int attributeCount = iter.nextToInt(2);
			if (attributeCount > 0) {
				System.out.println("jeixi");
			}
			Field field = new Field(flages, nameIndex, descriptorIndex, pool);
			classFile.addField(field);
		}

		return fields;
	}

	private void parseMethod(ConstantPool pool, ByteCodeIterator iter, ClassFile classFile) {
		int methodsCount = iter.nextToInt(2);
		for (int i = 0; i < methodsCount; i++) {
			Method.parse(classFile,iter);
		}
	}

	private AccessFlag parseAccessFlag(ByteCodeIterator iter) {
		return new AccessFlag(iter.nextToInt(2));
	}

	private ClassIndex parseClassInfex(ByteCodeIterator iter) {
		ClassIndex classIndex = new ClassIndex();
		classIndex.setThisClassIndex(iter.nextToInt(2));
		classIndex.setSuperClassIndex(iter.nextToInt(2));
		return classIndex;

	}

	private ConstantPool parseConstantPool(ByteCodeIterator iter) {
		int constantCount = iter.nextToInt(2);
		System.out.println(constantCount);
		Preconditions.checkArgument(constantCount > 0, "无法解析此class文件");

		ConstantPool pool = new ConstantPool();
		pool.addConstantInfo(new NullConstantInfo());
		for (int i = 0; i < constantCount - 1; i++) {
			int tag = iter.nextToInt(1);
			switch (tag) {
				case 1:
					UTF8Info info = new UTF8Info(pool);
					int length = iter.nextToInt(2);
					String value = iter.nextToUTF(length);
					Preconditions.checkNotNull(value);
					info.setLength(length);
					info.setValue(value);
					pool.addConstantInfo(info);
					break;
				case 4:
					throw new RuntimeException();
				case 7:
					ClassInfo classInfo = new ClassInfo(pool);
					int ut8Index = iter.nextToInt(2);
					Preconditions.checkArgument(-1 != ut8Index);
					classInfo.setUtf8Index(ut8Index);
					pool.addConstantInfo(classInfo);
					break;
				case 8:
					StringInfo stringInfo = new StringInfo(pool);
					int index = iter.nextToInt(2);
					Preconditions.checkArgument(-1 != index);
					stringInfo.setIndex(index);
					pool.addConstantInfo(stringInfo);
					break;
				case 9:
					FieldRefInfo fieldRefInfo = new FieldRefInfo(pool);
					int classIndex = iter.nextToInt(2);
					int nameAndType = iter.nextToInt(2);
					Preconditions.checkArgument(-1 != classIndex);
					Preconditions.checkArgument(-1 != nameAndType);
					fieldRefInfo.setClassInfoIndex(classIndex);
					fieldRefInfo.setNameAndTypeIndex(nameAndType);
					pool.addConstantInfo(fieldRefInfo);
					break;
				case 10:
					MethodRefInfo methodRefInfo = new MethodRefInfo(pool);
					int classIndex2 = iter.nextToInt(2);
					int nameAndType2 = iter.nextToInt(2);
					Preconditions.checkArgument(-1 != classIndex2);
					Preconditions.checkArgument(-1 != nameAndType2);
					methodRefInfo.setClassInfoIndex(classIndex2);
					methodRefInfo.setNameAndTypeIndex(nameAndType2);
					pool.addConstantInfo(methodRefInfo);
					break;
				case 12:
					NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(pool);
					int nameIndex = iter.nextToInt(2);
					int descriptorIndex = iter.nextToInt(2);
					Preconditions.checkArgument(-1 != nameIndex);
					Preconditions.checkArgument(-1 != descriptorIndex);
					nameAndTypeInfo.setIndex1(nameIndex);
					nameAndTypeInfo.setIndex2(descriptorIndex);
					pool.addConstantInfo(nameAndTypeInfo);
					break;
				default:
					throw new RuntimeException();
			}
		}
		return pool;
	}

	private void parseInterfaces(ByteCodeIterator iter) {
		int interfaceCount = iter.nextToInt(2);

		System.out.println("interfaceCount:" + interfaceCount);

		// TODO : 如果实现了interface, 这里需要解析
	}
	
}
