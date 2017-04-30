package week06.jvm.field;

import week06.jvm.constant.ConstantPool;
import week06.jvm.constant.UTF8Info;
import week06.jvm.loader.ByteCodeIterator;

public class Field {
	private int accessFlag;
	private int nameIndex;
	private int descriptorIndex;

	private ConstantPool pool;

	public Field(int accessFlag, int nameIndex, int descriptorIndex, ConstantPool pool) {

		this.accessFlag = accessFlag;
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
		this.pool = pool;
	}

	public static Field parse(ConstantPool pool, ByteCodeIterator iter) {
		int accessFlag = iter.nextU2ToInt();
		int nameIndex = iter.nextU2ToInt();
		int descIndex = iter.nextU2ToInt();
		int attribCount = iter.nextU2ToInt();
		
		System.out.println("attribCount:"+attribCount);

		Field f = new Field(accessFlag, nameIndex, descIndex, pool);

		if (attribCount > 0) {
			throw new RuntimeException("Field Attribute has not been implemented");
		}
		return f;
	}
	
	public String toString(){
		String name = ((UTF8Info)pool.getConstantInfo(this.nameIndex)).getValue();
		String desc = ((UTF8Info)pool.getConstantInfo(this.descriptorIndex)).getValue();
		
		return name + ":" + desc;
	}

}
