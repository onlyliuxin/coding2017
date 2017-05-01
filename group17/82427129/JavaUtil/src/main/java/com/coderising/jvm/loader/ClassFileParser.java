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
import com.coderising.jvm.field.Field;
import com.coderising.jvm.method.Method;

public class ClassFileParser {
	/**
	 * 读取ClassFile对象
	 * 
	 * @param codes
	 * @return
	 */
	public ClassFile parse(byte[] codes) {
		ClassFile cf = new ClassFile();
		ByteCodeIterator itr = new ByteCodeIterator(codes);
		String magicNumber = itr.nextUxtoHexString(4);// 1读取魔数
		if (!magicNumber.equals("cafebabe")) {
			throw new RuntimeException("magicNum not ok");
		}
		cf.setMinorVersion(itr.nextU2toInt());// 3读取次版本号
		cf.setMajorVersion(itr.nextU2toInt());// 2读取主版本号

		ConstantPool pool = parseConstantPool(itr);// 4、5读取常量池
		pool.print();// 打印常量池
		cf.setPool(pool);

		cf.setAccessFlag(new AccessFlag(itr.nextU2toInt()));// 6读取访问标志

		ClassIndex classIndex = parseClassIndex(itr);// 读取类引用7this和8super
		cf.setClzIndex(classIndex);

		int interfaceCount = itr.nextU2toInt();//读取接口信息
		if (interfaceCount != 0) {
			throw new RuntimeException(
					"the interface isn't 0,parser has not been implemented yet.");
		}

		int FieldCount = itr.nextU2toInt();//读取字段信息
		for (int i = 0; i < FieldCount; i++) {
			Field f = Field.parse(pool, itr);
			cf.addFields(f);
		}

		int MethodCount = itr.nextU2toInt();
		for (int i = 0; i < MethodCount; i++) {//读取方法信息
			Method m = Method.parse(pool, itr);
			cf.addMethods(m);
		}

		return cf;
	}

	/**
	 * 读取this super对象引用<br/>
	 * classIndex_Info {<br/>
	 * u2 this_class,<br/>
	 * u2 super_class<br/>
	 * }
	 * 
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
	 * 
	 * @param itr
	 * @return
	 */
	public ConstantPool parseConstantPool(ByteCodeIterator itr) {
		int count = itr.nextU2toInt();
		ConstantPool pool = new ConstantPool();
		pool.addConstantInfo(new NullConstantInfo());// 占住常量池的第一个，常量池是的index从1开始

		for (int i = 1; i < count; i++) {
			int tag = itr.nextU1toInt();
			switch (tag) {
			case ConstantInfo.CLASS_INFO:
				ClassInfo classInfo = new ClassInfo(pool);
				classInfo.setUtf8Index(itr.nextU2toInt());
				pool.addConstantInfo(classInfo);

				/*
				 * System.out.println(i + ". u1 tag:" +
				 * classInfo.getType()+" ClassInfo" + ",u2 name_index:" +
				 * classInfo.getUtf8Index());
				 */
				break;

			case ConstantInfo.UTF8_INFO:
				UTF8Info utf8Info = new UTF8Info(pool);
				int length = itr.nextU2toInt();
				String value = itr.nextUxtoAscii(length);
				utf8Info.setLength(length);
				utf8Info.setValue(value);
				pool.addConstantInfo(utf8Info);

				/*
				 * System.out.println(i + ". u1 tag:" + utf8Info.getType() +
				 * " UTF8Info" + ",u2 length:" + utf8Info.getLength() +
				 * ",u1 bytes[" + utf8Info.getLength() + "] " +
				 * utf8Info.getValue());
				 */
				break;

			case ConstantInfo.STRING_INFO:
				StringInfo si = new StringInfo(pool);
				si.setString_index(itr.nextU2toInt());
				pool.addConstantInfo(si);

				/*
				 * System.out.println(i + ". u1 tag:" +
				 * si.getType()+" StringInfo" + ",u2 string_index:" +
				 * si.getString_index());
				 */
				break;

			case ConstantInfo.NAME_AND_TYPE_INFO:
				NameAndTypeInfo nt = new NameAndTypeInfo(pool);
				nt.setName_index(itr.nextU2toInt());
				nt.setDescriptor_index(itr.nextU2toInt());
				pool.addConstantInfo(nt);

				/*
				 * System.out.println(i + ". u1 tag:" + nt.getType()+
				 * " NameAndTypeInfo" + ",u2 name_index:" + nt.getName_index() +
				 * ",u2 descriptor_index:" + nt.getDescriptor_index());
				 */
				break;

			case ConstantInfo.METHOD_INFO:
				MethodRefInfo m = new MethodRefInfo(pool);
				m.setClassInfoIndex(itr.nextU2toInt());
				m.setNameAndTypeIndex(itr.nextU2toInt());
				pool.addConstantInfo(m);

				/*
				 * System.out.println(i + ". u1 tag:" + m.getType()+
				 * " MethodInfo" + ",u2 class_index:" + m.getClassInfoIndex() +
				 * ",u2 name_and_type_index" + m.getNameAndTypeIndex());
				 */
				break;

			case ConstantInfo.FIELD_INFO:
				FieldRefInfo f = new FieldRefInfo(pool);
				f.setClass_index(itr.nextU2toInt());
				f.setName_and_type_index(itr.nextU2toInt());
				pool.addConstantInfo(f);

				/*
				 * System.out.println(i + ". u1 tag:" + f.getType()+
				 * " FieldInfo" + ",u2 class_index:" + f.getClass_index() +
				 * ",u2 name_and_type_index:" + f.getName_and_type_index());
				 */
				break;

			/*
			 * case ConstantInfo.FLOAT_INFO:
			 * 
			 * break;
			 * 
			 * case ConstantInfo.INTEGER_INFO: break;
			 * 
			 * case ConstantInfo.DOUBLE_INFO: break;
			 * 
			 * case ConstantInfo.LONG_INFO: break;
			 */

			default:
				throw new RuntimeException("the constant pool tag " + tag
						+ " has not been implemented yet.");
			}
		}
		return pool;
	}

}
