package com.coderising.jvm.loader;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.coderising.jvm.attr.InvalidAttributeInfoException;
import com.coderising.jvm.clz.AccessFlag;
import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.clz.ClassIndex;
import com.coderising.jvm.constant.ClassInfo;
import com.coderising.jvm.constant.ConstantInfo;
import com.coderising.jvm.constant.ConstantInfoFactory;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.FieldRefInfo;
import com.coderising.jvm.constant.InvalidConstantInfoTypeException;
import com.coderising.jvm.constant.MethodRefInfo;
import com.coderising.jvm.constant.NameAndTypeInfo;
import com.coderising.jvm.constant.NullConstantInfo;
import com.coderising.jvm.constant.StringInfo;
import com.coderising.jvm.constant.UTF8Info;
import com.coderising.jvm.field.Field;
import com.coderising.jvm.interfaze.Interfaze;
import com.coderising.jvm.interfaze.InvalidInterfaceException;
import com.coderising.jvm.method.InvalidMethodInfoException;
import com.coderising.jvm.method.Method;

public class ClassFileParser {
	ClassFile clz = null;
	public ClassFile parse(byte[] codes) {
		clz = new ClassFile();
		ByteCodeIterator iter = new ByteCodeIterator(codes);
		String magicHex = iter.getNextNHexString(4);
		System.out.println("Magic Number: "+magicHex);
		try{
			String minorHex = iter.getNextHexString() + iter.getNextHexString();
			int minorVersionInt = Integer.parseInt(minorHex, 16);
			String majorHex = iter.getNextHexString() + iter.getNextHexString();
			int majorVersionInt = Integer.parseInt(majorHex, 16);
			clz.setMajorVersion(majorVersionInt);clz.setMinorVersion(minorVersionInt);
			System.out.println("Major version: " + majorVersionInt + " minor version: " + minorVersionInt);
			clz.setConstPool(parseConstantPool(iter));
			fillClassInfo(clz, clz.getConstantPool());
			System.out.println(iter.peekNextNHex(8));
			parseAccessFlag(iter);
			parseClassInfex(iter);parseClassInfex(iter);
			parseInterface(iter);
			parseFields(iter);
			parseMethods(iter);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return clz;
	}

	private AccessFlag parseAccessFlag(ByteCodeIterator iter) {

		ConstantPool cp = clz.getConstantPool();
		int visitFlagIndex = iter.getNextNBytesInteger(2);
		System.out.println("visit flag index: " + visitFlagIndex + " variable value: " + cp.getUTF8String(visitFlagIndex));
		return null;
	}

	private ClassIndex parseClassInfex(ByteCodeIterator iter) {
		int classInfIndex = iter.getNextNBytesInteger(2);
		System.out.println("class info: " + classInfIndex);
		return null;

	}
	
	private List<Interfaze> parseInterface(ByteCodeIterator iter) throws InvalidInterfaceException{
		int interfaceCount = iter.getNextNBytesInteger(2);
		if(interfaceCount>0){
			throw new InvalidInterfaceException("Unimplemented interface parser: " + interfaceCount);
		}
		return null;
	}
	
	private void parseFields(ByteCodeIterator iter){
		int fieldsCount = iter.getNextNBytesInteger(2);
		System.out.println("Fields count: " + fieldsCount);
		List<Field> fields = new LinkedList<>();
		for(int i = 0; i<fieldsCount; i++){
			Field f = null;
			try {
				f = Field.parse(clz.getConstantPool(), iter);
			} catch (InvalidAttributeInfoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fields.add(f);
		}
		clz.setFields(fields);
	}
	
	private void parseMethods(ByteCodeIterator iter){
		int methodCount = iter.getNextNBytesInteger(2);
		System.out.println("Methods count: " + methodCount);
		List<Method> methods = new ArrayList<>();
		for(int i = 0; i<methodCount; i++){
			Method m = null;
			try{
				m = Method.parse(clz, iter);
				System.out.println("dumpping method: " + m);
				methods.add(m);
				
			} catch(InvalidMethodInfoException e){
				e.printStackTrace();
			}
		}
		for(int i = 0; i<methods.size(); i++){
			System.out.println("class method dump: " + methods.get(i) );
		}
		clz.setMethods(methods);
	}
	
	private void fillClassInfo(ClassFile clz, ConstantPool pool){
		ClassIndex newClassIndex = new ClassIndex();
		clz.setClassIndex(newClassIndex);
		for(int i = 0; i< pool.getSize(); i++){
			if(pool.getConstantInfo(i) instanceof ClassInfo){
				if(clz.getClzIndex().getThisClassIndex() == 0){
					System.out.println("class");
					clz.getClzIndex().setThisClassIndex(i);
				}
				else if(clz.getClzIndex().getSuperClassIndex() == 0){
					clz.getClzIndex().setSuperClassIndex(i);
				}
				else{
					break;
				}
			}
		}
	}

	private ConstantPool parseConstantPool(ByteCodeIterator iter) {
		String constantPoolSizeHex = iter.getNextHexString() + iter.getNextHexString();
		//System.out.println("hex string: " + constantPoolSizeHex + " integer value: " + Integer.parseInt(constantPoolSizeHex, 16));
		int constantCount = Integer.parseInt(constantPoolSizeHex, 16);
		ConstantPool constantPool = new ConstantPool();
		constantPool.addConstantInfo(new NullConstantInfo());
		ConstantInfoFactory constantInfoFactory = new ConstantInfoFactory(iter, constantPool);
		while(constantCount-- > 1){
			try {
				constantInfoFactory.fillNextConstantInfo();
			} catch ( Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
			
		}
		
//		for(int i = 0; i<constantPool.getSize(); i++){
//			System.out.println("constant pool variable: " + i );
//			ConstantInfo c = constantPool.getConstantInfo(i);
//			System.out.println(c.getClass().getName());
//		}
		return constantPool;
	}

	
}
