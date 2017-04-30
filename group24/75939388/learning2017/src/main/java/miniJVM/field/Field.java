package miniJVM.field;

import miniJVM.constant.ConstantPool;
import miniJVM.constant.UTF8Info;

public class Field {
	private int accessFlag;
	private int nameIndex;
	private int descriptorIndex;
	private int attributeCount;

	private ConstantPool pool;
	
	public Field( int accessFlag, int nameIndex, int descriptorIndex, int attributeCount, ConstantPool pool) {
		this.accessFlag = accessFlag;
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
		this.attributeCount = attributeCount;
		this.pool = pool;
	}

	public String toString() {
		String name = ((UTF8Info)pool.getConstantInfo(this.nameIndex)).getValue();
		String desc = ((UTF8Info)pool.getConstantInfo(this.descriptorIndex)).getValue();
		return name +":"+ desc;
	}
	
	
//	public static Field parse(ConstantPool pool,ByteCodeIterator iter){
//
//		int accessFlag = iter.nextU2ToInt();
//		int nameIndex = iter.nextU2ToInt();
//		int descIndex = iter.nextU2ToInt();
//		int attrCount = iter.nextU2ToInt();
//		//System.out.println("field attribute count:"+ attribCount);
//
//		Field f = new Field(accessFlag, nameIndex, descIndex,pool);
//
//		if(attrCount > 0){
//			throw new RuntimeException("Field Attribute has not been implemented");
//		}
//
//		return f;
//	}

}
