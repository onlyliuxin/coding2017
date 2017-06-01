package com.vvv.jvm.loader;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import com.vvv.jvm.clz.AccessFlag;
import com.vvv.jvm.clz.ClassFile;
import com.vvv.jvm.clz.ClassIndex;
import com.vvv.jvm.constant.ClassInfo;
import com.vvv.jvm.constant.ConstantPool;
import com.vvv.jvm.constant.FieldRefInfo;
import com.vvv.jvm.constant.MethodRefInfo;
import com.vvv.jvm.constant.NameAndTypeInfo;
import com.vvv.jvm.constant.NullConstantInfo;
import com.vvv.jvm.constant.StringInfo;
import com.vvv.jvm.constant.UTF8Info;
import com.vvv.jvm.util.Util;

public class ClassFileParser {
	
	public ClassFile parse(byte[] codes) {
		
		if(codes==null){
			return null;
		}
		ClassFile cf = new ClassFile();
		String magicNumber = Util.byteToHexString(Arrays.copyOfRange(codes, 0, 4));
		if (!"cafebabe".equals(magicNumber)) {
				return null;
		}
		
		System.out.println("0,4 "+magicNumber);
		System.out.println("4,6 "+Util.byteToHexString(Arrays.copyOfRange(codes, 4, 6)));
		System.out.println("6,8 "+Util.byteToHexString(Arrays.copyOfRange(codes, 6, 8)));
		cf.setMinorVersion(Util.byteToInt(Arrays.copyOfRange(codes, 4, 6)));
		cf.setMajorVersion(Util.byteToInt(Arrays.copyOfRange(codes, 6, 8)));
		
		int pool_count = Util.byteToInt(Arrays.copyOfRange(codes, 8,10));
		if(pool_count >0){
			ByteCodeIterator it = new ByteCodeIterator(Arrays.copyOfRange(codes, 8, codes.length));
			ConstantPool pool = parseConstantPool(it);
	 		cf.setConstPool(pool);
	 		
			cf.setAccessFlag(parseAccessFlag(it));
			cf.setClassIndex(parseClassInfex(it));
		}
	
		return  cf;
	}

	private AccessFlag parseAccessFlag(ByteCodeIterator iter) {
		if(iter==null){
			return null;
		}
		return new AccessFlag(iter.pop16());
	}

	private ClassIndex parseClassInfex(ByteCodeIterator iter) {
		if(iter==null){
			return null;
		}
		
        ClassIndex ci= new ClassIndex();
        ci.setThisClassIndex(iter.pop16());
        ci.setSuperClassIndex(iter.pop16());
		return ci;

	}

	private ConstantPool parseConstantPool(ByteCodeIterator iter) {
		int constPoolCount = iter.pop16();
		 	System.out.println("Constant Pool Count :" + constPoolCount);
		 		ConstantPool pool = new ConstantPool();
		 		pool.addConstantInfo(new NullConstantInfo());
		 		
		 		for (int i = 1; i <= constPoolCount - 1; i++) {
		 
		 			int tag = iter.pop8();
		 			
		 			if (tag == 7) {
		 				// Class Info
		 				int utf8Index = iter.pop16();
		 				ClassInfo clzInfo = new ClassInfo(pool);
		 				clzInfo.setUtf8Index(utf8Index);
		 
		 				pool.addConstantInfo(clzInfo);
		 			} else if (tag == 1) {
		 				// UTF-8 String
		 				int len = iter.pop16();
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
		 				info.setIndex(iter.pop16());
		 				pool.addConstantInfo(info);
		 			} else if (tag == 9) {
		 				FieldRefInfo field = new FieldRefInfo(pool);
		 				field.setClassInfoIndex(iter.pop16());
		 				field.setNameAndTypeIndex(iter.pop16());
		 				pool.addConstantInfo(field);
		 			} else if (tag == 10) {
		 				MethodRefInfo method = new MethodRefInfo(pool);
		 				method.setClassInfoIndex(iter.pop16());
		 				method.setNameAndTypeIndex(iter.pop16());
		 				pool.addConstantInfo(method);
		 			} else if (tag == 12) {
		 				NameAndTypeInfo nameType = new NameAndTypeInfo(pool);
		 				nameType.setIndex1(iter.pop16());
		 				nameType.setIndex2(iter.pop16());
		 				pool.addConstantInfo(nameType);
		 			} else {
		 				throw new RuntimeException("the constant pool tag " + tag + " has not been implemented yet.");
		 			}
		 		}
		 
		 		System.out.println("Finished reading Constant pool ");
		
				return pool;
	}


		 	
		 }
