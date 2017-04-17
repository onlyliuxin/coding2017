package org.wsc.coderising.jvm.loader;

import java.io.UnsupportedEncodingException;

import org.wsc.coderising.jvm.clz.AccessFlag;
import org.wsc.coderising.jvm.clz.ClassFile;
import org.wsc.coderising.jvm.clz.ClassIndex;
import org.wsc.coderising.jvm.constant.ClassInfo;
import org.wsc.coderising.jvm.constant.ConstantPool;
import org.wsc.coderising.jvm.constant.FieldRefInfo;
import org.wsc.coderising.jvm.constant.MethodRefInfo;
import org.wsc.coderising.jvm.constant.NameAndTypeInfo;
import org.wsc.coderising.jvm.constant.NullConstantInfo;
import org.wsc.coderising.jvm.constant.StringInfo;
import org.wsc.coderising.jvm.constant.UTF8Info;

public class ClassFileParser {

	public ClassFile parse(byte[] codes) {
		ClassFile classFile = new ClassFile();
		ByteCodeIterator iter = new ByteCodeIterator(codes);
		String magicNumber = iter.nextU4ToHexString();
		if (!"cafebabe".equals(magicNumber))
			return null;
		classFile.setMinorVersion(iter.nextU2ToInt());// 次版本
		classFile.setMajorVersion(iter.nextU2ToInt());// 主版本
		//解析常量池
		ConstantPool pool = parseConstantPool(iter);
		classFile.setConstPool(pool);
		//访问标识
		classFile.setAccessFlag(parseAccessFlag(iter));
		//类及其父类
		classFile.setClassIndex(parseClassIndex(iter));
		return classFile;
	}

	private AccessFlag parseAccessFlag(ByteCodeIterator iter) {
		return new AccessFlag(iter.nextU2ToInt());
	}

	private ClassIndex parseClassIndex(ByteCodeIterator iter) {
		ClassIndex classIndex = new ClassIndex();
		classIndex.setThisClassIndex(iter.nextU2ToInt());
		classIndex.setSuperClassIndex(iter.nextU2ToInt());
		return classIndex;

	}

	private ConstantPool parseConstantPool(ByteCodeIterator iter) {
		// 常量池计数值
		int constantPoolCount = iter.nextU2ToInt();
		System.out.println("constant pool count：" + constantPoolCount);
		// 常量池
		ConstantPool pool = new ConstantPool();
		pool.addConstantInfo(new NullConstantInfo());// 常量池索引从1开始
		for (int i = 1; i < constantPoolCount; i++) {
			int tag = iter.nextU1ToInt();
			switch (tag) {
			case 1://UTF-8 String
				int length = iter.nextU2ToInt();
				String utf8Str = null;
				try {
					utf8Str = new String(iter.getBytes(length),"UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				UTF8Info utf8Info = new UTF8Info(pool);
				utf8Info.setValue(utf8Str);
				utf8Info.setLength(length);
				pool.addConstantInfo(utf8Info);
				break;
			case 7://类或接口的符号引用
				ClassInfo classInfo = new ClassInfo(pool);
				classInfo.setUtf8Index(iter.nextU2ToInt());
				pool.addConstantInfo(classInfo);
				break;
			case 8://字符串类型字面量
				StringInfo stringInfo = new StringInfo(pool);
				stringInfo.setIndex(iter.nextU2ToInt());
				pool.addConstantInfo(stringInfo);
				break;
			case 9://字段的符号引用
				FieldRefInfo fieldRefInfo = new FieldRefInfo(pool);
				fieldRefInfo.setClassInfoIndex(iter.nextU2ToInt());
				fieldRefInfo.setNameAndTypeIndex(iter.nextU2ToInt());
				pool.addConstantInfo(fieldRefInfo);
				break;
			case 10://类中方法的符号引用
				MethodRefInfo methodRefInfo = new MethodRefInfo(pool);
				methodRefInfo.setClassInfoIndex(iter.nextU2ToInt());
				methodRefInfo.setNameAndTypeIndex(iter.nextU2ToInt());
				pool.addConstantInfo(methodRefInfo);
				break;
			case 12://字段或方法的部门符号引用
				NameAndTypeInfo nameAndType = new NameAndTypeInfo(pool);
				nameAndType.setIndex1(iter.nextU2ToInt());
				nameAndType.setIndex2(iter.nextU2ToInt());
				pool.addConstantInfo(nameAndType);
				break;
			default:
				throw new RuntimeException("the constant pool tag:" + tag + " is not implements");
			}
		}
		return pool;
	}

}
