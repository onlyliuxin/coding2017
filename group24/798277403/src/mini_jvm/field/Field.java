package mini_jvm.field;


import mini_jvm.constant.ConstantPool;
import mini_jvm.constant.UTF8Info;
import mini_jvm.loader.ByteCodeIterator;

public class Field {
	private int accessFlag; //字段的修饰符，u2类型
	private int nameIndex; //一个索引，对常量池的引用，代表字段的简单名称
	private int descriptorIndex; //一个索引，对常量池的引用，代表字段的描述符

	private ConstantPool pool;

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
		//System.out.println("field attribute count:"+ attribCount);

		Field f = new Field(accessFlag, nameIndex, descIndex,pool);

		if(attribCount > 0){
			throw new RuntimeException("Field Attribute has not been implemented");
		}

		return f;
	}

}
