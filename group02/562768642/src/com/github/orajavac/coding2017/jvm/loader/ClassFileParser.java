package com.github.orajavac.coding2017.jvm.loader;

import com.github.orajavac.coding2017.jvm.clz.AccessFlag;
import com.github.orajavac.coding2017.jvm.clz.ClassFile;
import com.github.orajavac.coding2017.jvm.clz.ClassIndex;
import com.github.orajavac.coding2017.jvm.constant.ClassInfo;
import com.github.orajavac.coding2017.jvm.constant.ConstantPool;
import com.github.orajavac.coding2017.jvm.constant.FieldRefInfo;
import com.github.orajavac.coding2017.jvm.constant.MethodRefInfo;
import com.github.orajavac.coding2017.jvm.constant.NameAndTypeInfo;
import com.github.orajavac.coding2017.jvm.constant.NullConstantInfo;
import com.github.orajavac.coding2017.jvm.constant.StringInfo;
import com.github.orajavac.coding2017.jvm.constant.UTF8Info;
import com.github.orajavac.coding2017.jvm.field.Field;
import com.github.orajavac.coding2017.jvm.method.Method;

public class ClassFileParser {
	
	public ClassFile parse(byte[] codes) {
		ClassFile clzFile = new ClassFile();
		ByteCodeIterator iter = new ByteCodeIterator(codes);
		String magicNumber = iter.nextU4ToHexString();
		System.out.println("magicNumber="+magicNumber);
		clzFile.setMinorVersion(iter.nextU2ToInt());
		clzFile.setMajorVersion(iter.nextU2ToInt());
		
		ConstantPool pool = parseConstantPool(iter);
		clzFile.setConstPool(pool);

		AccessFlag flag = parseAccessFlag(iter);
		clzFile.setAccessFlag(flag);

		ClassIndex clzIndex = parseClassInfex(iter);
		clzFile.setClassIndex(clzIndex);

		parseInterfaces(iter);
		
		parseFileds(clzFile,iter);
		
		parseMethods(clzFile,iter);
		return clzFile;
	}

	private AccessFlag parseAccessFlag(ByteCodeIterator iter) {
		AccessFlag flag = new AccessFlag(iter.nextU2ToInt());
		return flag;
	}

	private ClassIndex parseClassInfex(ByteCodeIterator iter) {

		int thisClassIndex = iter.nextU2ToInt();
		int superClassIndex = iter.nextU2ToInt();

		ClassIndex clzIndex = new ClassIndex();

		clzIndex.setThisClassIndex(thisClassIndex);
		clzIndex.setSuperClassIndex(superClassIndex);

		return clzIndex;

	}

	private ConstantPool parseConstantPool(ByteCodeIterator iter) {
		int constPoolCount = iter.nextU2ToInt();
		System.out.println("Constant Pool Count :"+constPoolCount);
		ConstantPool pool = new ConstantPool();
		pool.addConstantInfo(new NullConstantInfo());
		for (int i=1;i<constPoolCount-1;i++){
			int tag = iter.nextU1ToInt();
			if (tag == 7){	//class info
				int uft8Index = iter.nextU2ToInt();
				ClassInfo clzInfo = new ClassInfo(pool);
				clzInfo.setUtf8Index(uft8Index);
				pool.addConstantInfo(clzInfo);
			}else if (tag == 1){	//utf-8 string
				int len = iter.nextU2ToInt();
				byte[] data = iter.getBytes(len);
				String value = null;
				try{
					value = new String(data,"UTF-8");
				}catch(Exception e){
					e.printStackTrace();
				}
				UTF8Info utf8info = new UTF8Info(pool);
				utf8info.setLength(len);
				utf8info.setValue(value);
				pool.addConstantInfo(utf8info);
			}else if (tag == 8){
				StringInfo info = new StringInfo(pool);
				info.setIndex(iter.nextU2ToInt());
				pool.addConstantInfo(info);
			}else if (tag == 9){
				FieldRefInfo field = new FieldRefInfo(pool);
				field.setClassInfoIndex(iter.nextU2ToInt());
				field.setNameAndTypeIndex(iter.nextU2ToInt());
				pool.addConstantInfo(field);
			}else if (tag == 10){
				MethodRefInfo method = new MethodRefInfo(pool);
				method.setClassInfoIndex(iter.nextU2ToInt());
				method.setNameAndTypeIndex(iter.nextU2ToInt());
				pool.addConstantInfo(method);
			}else if (tag == 12){
				NameAndTypeInfo nameType = new NameAndTypeInfo(pool);
				nameType.setIndex1(iter.nextU2ToInt());
				nameType.setIndex2(iter.nextU2ToInt());
				pool.addConstantInfo(nameType);
			}else{
				throw new RuntimeException("the constant pool tag " + tag + " has not been implemented yet.");
			}
		}
		System.out.println("Finished reading Constant pool ");
		return pool;
	}
	
	private void parseInterfaces(ByteCodeIterator iter) {
		int interfaceCount = iter.nextU2ToInt();

		System.out.println("interfaceCount:" + interfaceCount);

		// TODO : 如果实现了interface, 这里需要解析
	}
	
	private void parseFileds(ClassFile clzFile,ByteCodeIterator iter){
		int filedCount = iter.nextU2ToInt();
		System.out.println("Field count:" + filedCount);
		for (int i=1;i<=filedCount;i++){
			Field f = Field.parse(clzFile.getConstantPool(), iter);
			clzFile.addField(f);
		}
	}
	
	private void parseMethods(ClassFile clzFile,ByteCodeIterator iter){
		int methodCount = iter.nextU2ToInt();
		for (int i=1;i<=methodCount;i++){
			Method m = Method.parse(clzFile, iter);
			clzFile.addMethod(m);
		}
	}
}
