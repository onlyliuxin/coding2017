package com.coderising.jvm.constant;

import java.io.UnsupportedEncodingException;

import com.coderising.jvm.loader.ByteCodeIterator;
import com.coderising.jvm.util.Util;

public class ConstantInfoFactory {
	public ByteCodeIterator iter = null;
	public ConstantPool pool = null;
	public ConstantInfoFactory(ByteCodeIterator iter, ConstantPool pool){
		this.iter = iter;
		this.pool = pool;
	}
	
	public void fillNextConstantInfo() throws InvalidConstantInfoTypeException, UnsupportedEncodingException{
		int constantIdentifier = iter.getNextByte();
		ConstantInfo var = null;
		//System.out.println("constant identifyer: " + constantIdentifier);
			var = classify(constantIdentifier);
			pool.addConstantInfo(var);
	}
	
	public ConstantInfo classify(int constantInfoTypeId) throws InvalidConstantInfoTypeException, UnsupportedEncodingException{
		ConstantInfo ret = null;
		switch(constantInfoTypeId){
		case ConstantInfo.CLASS_INFO:
			ClassInfo classInfo = new ClassInfo(pool);
			byte[] utf8Index = iter.getNextNBytes(2);
			//System.out.println("constant classInfo: utf index: " + Util.byteToInt(utf8Index)); 
			classInfo.setUtf8Index(Util.byteToInt(utf8Index));
			ret = classInfo;
			
		break;
		case ConstantInfo.FIELD_INFO:
			FieldRefInfo fieldInfo = new FieldRefInfo(pool);
			int classInfoIndex = Util.byteToInt(iter.getNextNBytes(2));
			fieldInfo.setClassInfoIndex(classInfoIndex);
			int nameAndTypeIndex = Util.byteToInt(iter.getNextNBytes(2));
			fieldInfo.setNameAndTypeIndex(nameAndTypeIndex);
			ret = fieldInfo;
		break;
		case ConstantInfo.INTEGER_INFO:
			IntegerInfo integerInfo = new IntegerInfo(pool);
			int val = Util.byteToInt(iter.getNextNBytes(4));
			integerInfo.setInteger(val);
			ret = integerInfo;
		break;
		case ConstantInfo.FLOAT_INFO:
			FloatRefInfo floatInfo = new FloatRefInfo(pool);
			ret = floatInfo;
			throw new InvalidConstantInfoTypeException("Flat info has not been properly implemented yet");
		//break;
		case ConstantInfo.METHOD_INFO:
			MethodRefInfo methodInfo = new MethodRefInfo(pool);
			int methodClassInfoIndex = Util.byteToInt(iter.getNextNBytes(2));
			methodInfo.setClassInfoIndex(methodClassInfoIndex);
			int methodNameAndTypeIndex = Util.byteToInt(iter.getNextNBytes(2));
			methodInfo.setNameAndTypeIndex(methodNameAndTypeIndex);
			ret = methodInfo;
		break;
		case ConstantInfo.NAME_AND_TYPE_INFO:
			NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(pool);
			int nameIndex = Util.byteToInt(iter.getNextNBytes(2));
			nameAndTypeInfo.setIndex1(nameIndex);
			int descriptorIndex = Util.byteToInt(iter.getNextNBytes(2));
			nameAndTypeInfo.setIndex2(descriptorIndex);
			ret = nameAndTypeInfo;
		break;
		case ConstantInfo.STRING_INFO:
			StringInfo stringInfo = new StringInfo(pool);
			int index = Util.byteToInt(iter.getNextNBytes(2));
			stringInfo.setIndex(index);
			ret = stringInfo;
		break;
		case ConstantInfo.UTF8_INFO:
			UTF8Info utfInfo = new UTF8Info(pool);
			int length = Util.byteToInt(iter.getNextNBytes(2));
			utfInfo.setLength(length);

			String utf8Val = new String(iter.getNextNBytes(length), "UTF-8");
			System.out.println("UTF 8 content " + utf8Val); 
			
			utfInfo.setValue(utf8Val);
			ret = utfInfo;
		break;
		
		default:
			throw new InvalidConstantInfoTypeException();
		}
		return ret;
	}
}
