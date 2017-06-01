package jvm.field;

import jvm.attr.AttributeInfo;
import jvm.attr.ConstantValue;
import jvm.constant.ConstantPool;
import jvm.constant.UTF8Info;
import jvm.loader.ByteCodeIterator;


public class Field {
	private int accessFlag;
	private int nameIndex;
	private int descriptorIndex;
	
	
	
	private ConstantPool pool;
	private ConstantValue constValue;
	
	public Field( int accessFlag, int nameIndex, int descriptorIndex,ConstantPool pool) {
		
		this.accessFlag = accessFlag;
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
		this.pool = pool;
	}

	public String toString() {
		String name = ((UTF8Info)pool.getConstantInfo(this.nameIndex)).getValue();
		
		String desc = ((UTF8Info)pool.getConstantInfo(this.descriptorIndex)).getValue();
		return name +":"+ desc;
	}
	
	
	public static Field parse(ConstantPool pool,ByteCodeIterator iter){
		
		int accessFlag = iter.nextU2ToInt();
		int nameIndex = iter.nextU2ToInt();
		int descIndex = iter.nextU2ToInt();
		int attribCount = iter.nextU2ToInt();
		
		Field f = new Field(accessFlag, nameIndex, descIndex,pool);
		
		for( int i=1; i<= attribCount; i++){
			int attrNameIndex = iter.nextU2ToInt();
			String attrName = pool.getUTF8String(attrNameIndex);
						
			if(AttributeInfo.CONST_VALUE.equals(attrName)){
				int attrLen = iter.nextU4ToInt();
				ConstantValue constValue = new ConstantValue(attrNameIndex, attrLen);
				constValue.setConstValueIndex(iter.nextU2ToInt());				
				f.setConstantValue(constValue);
			} else{
				throw new RuntimeException("the attribute " + attrName + " has not been implemented yet.");
			}
		}
		
		
		return f;
	}
	
	public void setConstantValue(ConstantValue constValue) {
		this.constValue = constValue;
	}

}
