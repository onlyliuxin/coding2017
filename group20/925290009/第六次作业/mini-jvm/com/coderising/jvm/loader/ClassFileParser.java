package com.coderising.jvm.loader;

import java.io.UnsupportedEncodingException;

import com.coderising.jvm.clasfile.AccessFlag;
import com.coderising.jvm.clasfile.ClassFile;
import com.coderising.jvm.clasfile.ClassIndex;
import com.coderising.jvm.constant.ClassInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.FieldRefInfo;
import com.coderising.jvm.constant.MethodInfo;
import com.coderising.jvm.constant.NameAndTypeInfo;
import com.coderising.jvm.constant.NullConstantInfo;
import com.coderising.jvm.constant.StringInfo;
import com.coderising.jvm.constant.Utf8Info;
import com.coderising.jvm.field.JField;
import com.coderising.jvm.method.JMethod;

public class ClassFileParser {

	public ClassFile parse(byte[] codes) {

		ClassFile clzFile = new ClassFile();

		ByteCodeIterator iterator = new ByteCodeIterator(codes);

		// Magic Number
		String Magic = iterator.next4BytesToString();
		if (!(Magic.equals("cafebabe"))) {
			return null;
		}
		clzFile.setMagicNumer(Magic);
		// Version Number
		int MinorVersion = iterator.next2BytesToInt();
		int MajorVersion = iterator.next2BytesToInt();
		clzFile.setMinorVersion(MinorVersion);
		clzFile.setMajorVersion(MajorVersion);

		clzFile.setPool(parseConstantPool(iterator));
		clzFile.setAccessFlag(parseAccessFlag(iterator));
		clzFile.setClassIndex(parseClassIndex(iterator));

		parseInterface(iterator);
		parseJFields(clzFile, iterator);
		parseJMethods(clzFile, iterator);
		
		return clzFile;
	}

	private void parseJMethods(ClassFile classFile, ByteCodeIterator iterator){
		
		int jmethodCount = iterator.next2BytesToInt();
		System.out.println("JMethod Count:" + jmethodCount);
		for (int i = 0; i < jmethodCount; i++) {
			JMethod jMethod = JMethod.parse(classFile, iterator);
			classFile.addJMethod(jMethod);
		}
	}
	private void parseJFields(ClassFile classFile, ByteCodeIterator iterator){
		
		int jfieldCount = iterator.next2BytesToInt();
		System.out.println("JField Count :" + jfieldCount);
		
		for (int i = 0; i < jfieldCount; i++) {
			classFile.addJField(JField.parse(classFile.getPool(), iterator));
		}
		
	}
	private void parseInterface(ByteCodeIterator iterator) {
		int interfaceCount = iterator.next2BytesToInt();
		System.out.println("InterfaceCount:" + interfaceCount);
	}

	private AccessFlag parseAccessFlag(ByteCodeIterator iterator) {
		AccessFlag accessFlag = new AccessFlag();
		int flagValue = iterator.next2BytesToInt();
		accessFlag.setFlag(flagValue);

		return accessFlag;
	}

	private ClassIndex parseClassIndex(ByteCodeIterator iterator) {

		ClassIndex classIndex = new ClassIndex();
		int thisClassIndex = iterator.next2BytesToInt();
		int superClassIndex = iterator.next2BytesToInt();
		classIndex.setThisClassIndex(thisClassIndex);
		classIndex.setSuperClassIndex(superClassIndex);

		return classIndex;
	}

	private ConstantPool parseConstantPool(ByteCodeIterator iterator) {

		ConstantPool pool = new ConstantPool();
		int ConstantNumber = iterator.next2BytesToInt();
		pool.addConstantInfo(new NullConstantInfo());

		for (int i = 1; i <= ConstantNumber - 1; i++) {
			int tag = iterator.nextByteToInt();

			if (tag == 7) {
				
				ClassInfo clzInfo = new ClassInfo(pool);
				int utf8Index = iterator.next2BytesToInt();
				clzInfo.setUtf8Index(utf8Index);
				pool.addConstantInfo(clzInfo);
				
			} else if (tag == 1) {
				Utf8Info utf8Info = new Utf8Info(pool);
				int length = iterator.next2BytesToInt();
				utf8Info.setLength(length);
				byte[] utf8Bytes = iterator.getBytes(length);
				String value = null;
				try {
					value = new String(utf8Bytes, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				utf8Info.setValue(value);
				pool.addConstantInfo(utf8Info);
				
			} else if (tag == 12) {
				NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(pool);

				int Index_Name = iterator.next2BytesToInt();
				int Index_Describe = iterator.next2BytesToInt();

				nameAndTypeInfo.setIndex_Name(Index_Name);
				nameAndTypeInfo.setIndex_Describe(Index_Describe);
				pool.addConstantInfo(nameAndTypeInfo);
			}

			else if (tag == 10) {
				MethodInfo methofInfo = new MethodInfo(pool);
				int Index_ClassInfo = iterator.next2BytesToInt();
				int Index_NameAndType = iterator.next2BytesToInt();

				methofInfo.setIndex_ClassInfo(Index_ClassInfo);
				methofInfo.setIndex_NameAndType(Index_NameAndType);
				pool.addConstantInfo(methofInfo);

			}

			else if (tag == 9) {

				FieldRefInfo fieldRefInfo = new FieldRefInfo(pool);
				int Index_ClassInfo = iterator.next2BytesToInt();
				int Index_NameAndType = iterator.next2BytesToInt();

				fieldRefInfo.setIndex_ClassInfo(Index_ClassInfo);
				fieldRefInfo.setIndex_NameAndType(Index_NameAndType);
				pool.addConstantInfo(fieldRefInfo);
			} else if (tag == 8) {

				StringInfo stringInfo = new StringInfo(pool);
				int index = iterator.next2BytesToInt();

				stringInfo.setIndex(index);
				pool.addConstantInfo(stringInfo);
			}
			else {
				throw new RuntimeException("the constant pool tag " + tag + "has been not immplemented yet!");
			}
		}
		return pool;

	}
}
