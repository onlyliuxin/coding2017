package com.coderising.jvm.attr;

import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.exception.AddAnotherParserException;
import com.coderising.jvm.loader.ByteCodeIterator;

public abstract class AttributeInfo {
	
	public static final String CODE = "Code";
	public static final String CONST_VALUE = "ConstantValue";
	public static final String EXCEPTIONS = "Exceptions";
	public static final String LINE_NUM_TABLE = "LineNumberTable";
	public static final String LOCAL_VAR_TABLE = "LocalVariableTable";
	public static final String STACK_MAP_TABLE = "StackMapTable";
	
	int attrNameIndex;				
	int attrLen ;
	
	public AttributeInfo(int attrNameIndex, int attrLen) {
		
		this.attrNameIndex = attrNameIndex;
		this.attrLen = attrLen;
	}
	
	/**
	 * 解析属性
	 * @param pool
	 * @param iter
	 * @return
	 */
	public static List<AttributeInfo>  parseAttributes(ConstantPool pool,ByteCodeIterator iter){
		
		List<AttributeInfo> attributeInfos = new ArrayList<AttributeInfo>();
		try {
			
			int attrCount = iter.nextU2ToInt();		
			for (int i = 0; i < attrCount; i++) {
				
				AttributeInfo attr = null;
				String attrName = pool.getUTF8String(iter.nextU2ToInt());
				iter.back(ByteCodeIterator.numberTwo);
				
				switch (attrName) {
				case AttributeInfo.CONST_VALUE:				
					attr = ConstantValueAttr.parse(iter);
					break;
				case AttributeInfo.CODE:				
					attr = CodeAttr.parse(pool,iter);
					break;
				case AttributeInfo.EXCEPTIONS:				
					//TODE
					break;
				case AttributeInfo.LINE_NUM_TABLE:				
					attr = LineNumberTable.parse(iter);
					break;
				case AttributeInfo.LOCAL_VAR_TABLE:				
					attr = LocalVariableTable.parse(iter);
					break;
				case AttributeInfo.STACK_MAP_TABLE:				
					attr = StackMapTable.parse(iter);
					break;
				default:
					throw new AddAnotherParserException();
				}
				attributeInfos.add(attr);
			
			}
		} catch (AddAnotherParserException e) {
			e.printStackTrace();
		}
		return attributeInfos;
	} 
}
