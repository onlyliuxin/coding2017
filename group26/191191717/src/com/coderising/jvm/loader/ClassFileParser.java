package com.coderising.jvm.loader;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.StringUtils;

import com.coderising.jvm.clz.AccessFlag;
import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.clz.ClassIndex;
import com.coderising.jvm.constant.ClassInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.FieldRefInfo;
import com.coderising.jvm.constant.MethodRefInfo;
import com.coderising.jvm.constant.NameAndTypeInfo;
import com.coderising.jvm.constant.StringInfo;
import com.coderising.jvm.constant.UTF8Info;
import com.coderising.jvm.field.Field;
import com.coderising.jvm.method.Method;

public class ClassFileParser {

	public ClassFile parse(byte[] codes) {
		ClassFile classFile = new ClassFile();
		ByteCodeIterator byteCodeIterator = new ByteCodeIterator(codes);
		String magicNum = byteCodeIterator.nextU4ToHexString();
		if (StringUtils.contains("cafebabe", magicNum)) {
			return null;
		}

		classFile.setMinorVersion(byteCodeIterator.nextU2ToInt());
		classFile.setMajorVersion(byteCodeIterator.nextU2ToInt());

		ConstantPool constantPool = parseConstantPool(byteCodeIterator);
		classFile.setConstPool(constantPool);

		AccessFlag accessFlag = parseAccessFlag(byteCodeIterator);
		classFile.setAccessFlag(accessFlag);

		ClassIndex classIndex = parseClassInfex(byteCodeIterator);
		classFile.setClassIndex(classIndex);

		parseInterfaces(byteCodeIterator);

		parseFileds(classFile, byteCodeIterator);

		parseMethods(classFile, byteCodeIterator);
		return classFile;
	}

	/**
	 * constantPool的读取
	 * 
	 * @param iter
	 * @return
	 */
	private ConstantPool parseConstantPool(ByteCodeIterator iter) {
		int constPoolCount = iter.nextU2ToInt();// 常量池个数
		ConstantPool pool = new ConstantPool();
		// 因常量池也包括常量池个数这一项，所以从1开始遍历
		for (int i = 1; i < constPoolCount; i++) {
			int tag = iter.nextU1toInt();
			// 1-utf-8 4-FLOAT_INFO 7-CLASS_INFO 8-STRING_INFO 9-FIELD_INFO
			// 10-METHOD_INFO
			// 12-NAME_AND_TYPE_INFO
			if (tag == 1) {
				int len = iter.nextU2ToInt();// 字节数
				byte[] bs = iter.getBytes(len);// 字节
				String value = null;
				try {
					value = new String(bs, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				UTF8Info utf8Info = new UTF8Info(pool);
				utf8Info.setLength(len);
				utf8Info.setValue(value);
				pool.addConstantInfo(utf8Info);
			} else if (tag == 7) {
				int utf8Index = iter.nextU2ToInt();// 指向全限定名常量项的索引
				ClassInfo classInfo = new ClassInfo(pool);
				classInfo.setUtf8Index(utf8Index);
				pool.addConstantInfo(classInfo);
			} else if (tag == 8) {
				StringInfo stringInfo = new StringInfo(pool);
				stringInfo.setIndex(iter.nextU2ToInt());
				pool.addConstantInfo(stringInfo);
			} else if (tag == 9) {
				FieldRefInfo fieldRefInfo = new FieldRefInfo(pool);
				fieldRefInfo.setClassInfoIndex(iter.nextU2ToInt());
				fieldRefInfo.setNameAndTypeIndex(iter.nextU2ToInt());
				pool.addConstantInfo(fieldRefInfo);
			} else if (tag == 10) {
				MethodRefInfo methodRefInfo = new MethodRefInfo(pool);
				methodRefInfo.setClassInfoIndex(iter.nextU2ToInt());
				methodRefInfo.setNameAndTypeIndex(iter.nextU2ToInt());
				pool.addConstantInfo(methodRefInfo);
			} else if (tag == 12) {
				NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(pool);
				nameAndTypeInfo.setIndex1(iter.nextU2ToInt());// 指向该字段或方法的索引
				nameAndTypeInfo.setIndex2(iter.nextU2ToInt());// 指向该字段或方法的描述符的索引
				pool.addConstantInfo(nameAndTypeInfo);
			} else {
				throw new RuntimeException("ConstPool tag " + tag + " is not implements!");
			}
		}
		System.out.println("read constPool is finished~!");
		return pool;
	}

	/**
	 * 访问标志 标志这个文件是class还是interface 还是 abstract
	 * 
	 * @param iter
	 * @return
	 */
	private AccessFlag parseAccessFlag(ByteCodeIterator iter) {
		AccessFlag flag = new AccessFlag(iter.nextU2ToInt());
		return flag;
	}

	private ClassIndex parseClassInfex(ByteCodeIterator iter) {
		ClassIndex classIndex = new ClassIndex();
		classIndex.setThisClassIndex(iter.nextU2ToInt());// 类索引
		classIndex.setSuperClassIndex(iter.nextU2ToInt());// 父类索引
		return classIndex;
	}

	private void parseInterfaces(ByteCodeIterator iter) {
		int interfaceCount = iter.nextU2ToInt();// 接口个数及接口
		// 这里如果实现了接口就要解析
		System.out.println("interfaceCount:" + interfaceCount);
	}

	private void parseFileds(ClassFile clzFile, ByteCodeIterator iter) {
		int fieldCount = iter.nextU2ToInt();
		for (int i = 0; i < fieldCount; i++) {
			Field field = Field.parse(clzFile.getConstantPool(), iter);
			clzFile.addField(field);
		}

	}

	private void parseMethods(ClassFile clzFile, ByteCodeIterator iter) {
		int methodCount = iter.nextU2ToInt();
		for (int i = 0; i < methodCount; i++) {
			Method method = Method.parse(clzFile, iter);
			clzFile.addMethod(method);
		}
	}

	public static void main(String[] args) {
		int[] nums = { 10, 1, 2, 4, 5, 7, 90, 11, 12, 13 };
		for (int i = 1; i <= nums.length - 1; i++) {
			System.out.printf("%d ", nums[i]);
		}
	}
}
