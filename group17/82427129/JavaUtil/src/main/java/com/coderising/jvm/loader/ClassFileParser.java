package com.coderising.jvm.loader;

import com.coderising.jvm.clz.AccessFlag;
import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.clz.ClassIndex;
import com.coderising.jvm.constant.ClassInfo;
import com.coderising.jvm.constant.ConstantInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.FieldRefInfo;
import com.coderising.jvm.constant.MethodRefInfo;
import com.coderising.jvm.constant.NameAndTypeInfo;
import com.coderising.jvm.constant.NullConstantInfo;
import com.coderising.jvm.constant.StringInfo;
import com.coderising.jvm.constant.UTF8Info;

public class ClassFileParser {
	/**
	 * 读取ClassFile对象
	 * @param codes
	 * @return
	 */
	public ClassFile parse(byte[] codes) {
		ClassFile cf = new ClassFile();
		ByteCodeIterator itr = new ByteCodeIterator(codes);
		String magicNumber = itr.nextUxtoHexString(4);//1读取魔数
		if (!magicNumber.equals("cafebabe")) {
			throw new RuntimeException("magicNum not ok");
		}
		cf.setMajorVersion(itr.nextU2toInt());//2读取主版本号
		cf.setMinorVersion(itr.nextU2toInt());//3读取次版本号

		ConstantPool pool = parseConstantPool(itr);//4、5读取常量池
		cf.setPool(pool);
		
		cf.setAccessFlag(new AccessFlag(itr.nextU2toInt()));//6读取访问标志
		
		ClassIndex classIndex = parseClassIndex(itr);//读取类引用7this和8super
		cf.setClzIndex(classIndex);
		
		return cf;
	}

	/**
	 * 读取this super对象引用
	 * @param itr
	 * @return
	 */
	public ClassIndex parseClassIndex(ByteCodeIterator itr) {
		ClassIndex classIndex = new ClassIndex();
		classIndex.setThisClassIndex(itr.nextU2toInt());
		classIndex.setSuperClassIndex(itr.nextU2toInt());
		return classIndex;
	}
	/**
	 * 读取常量池
	 * @param itr
	 * @return
	 */
	public ConstantPool parseConstantPool(ByteCodeIterator itr) {
		int count = itr.nextU2toInt();
		ConstantPool pool = new ConstantPool();
		pool.setConstant_pool_size(count);
		pool.addConstantInfo(new NullConstantInfo());// 占住常量池的第一个，常量池是的index从1开始

		for (int i = 1; i < count; i++) {
			int tag = itr.nextU1toInt();
			switch (tag) {
			case ConstantInfo.CLASS_INFO:
				ClassInfo classInfo = new ClassInfo();
				classInfo.setName_index(itr.nextU2toInt());
				pool.addConstantInfo(classInfo);
				break;

			case ConstantInfo.UTF8_INFO:
				UTF8Info utf8Info = new UTF8Info(pool);
				int length = itr.nextU2toInt();
				String value = itr.nextUxtoHexString(length);
				utf8Info.setLength(length);
				utf8Info.setValue(value);
				pool.addConstantInfo(utf8Info);
				break;

			case ConstantInfo.STRING_INFO:
				StringInfo si = new StringInfo();
				si.setString_index(itr.nextU2toInt());
				pool.addConstantInfo(si);
				break;

			case ConstantInfo.NAME_AND_TYPE_INFO:
				NameAndTypeInfo nt = new NameAndTypeInfo();
				nt.setName_index(itr.nextU2toInt());
				nt.setDescriptor_index(itr.nextU2toInt());
				pool.addConstantInfo(nt);
				break;

			case ConstantInfo.METHOD_INFO:
				MethodRefInfo m = new MethodRefInfo();
				m.setClass_index(itr.nextU2toInt());
				m.setName_and_type_index(itr.nextU2toInt());
				pool.addConstantInfo(m);
				break;

			case ConstantInfo.FIELD_INFO:
				FieldRefInfo f = new FieldRefInfo();
				f.setClass_index(itr.nextU2toInt());
				f.setName_and_type_index(itr.nextU2toInt());
				pool.addConstantInfo(f);
				break;

			/*case ConstantInfo.FLOAT_INFO:

				break;

			case ConstantInfo.INTEGER_INFO:
				break;

			case ConstantInfo.DOUBLE_INFO:
				break;

			case ConstantInfo.LONG_INFO:
				break;*/

			default:
				throw new RuntimeException("the constant pool tag " + tag
						+ " has not been implemented yet.");
			}
		}
		return pool;
	}
}
