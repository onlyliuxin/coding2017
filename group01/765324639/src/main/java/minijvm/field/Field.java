package minijvm.field;

import minijvm.constant.ConstantPool;
import minijvm.constant.UTF8Info;
import minijvm.loader.ByteCodeIterator;


public class Field {
	private int accessFlag;
	private int nameIndex;
	private int descriptorIndex;
	
	
	
	private ConstantPool pool;
	
	public Field( int accessFlag, int nameIndex, int descriptorIndex,ConstantPool pool) {
		
		this.accessFlag = accessFlag;
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
		this.pool = pool;
	}

	
	
	
	public static Field parse(ConstantPool pool,ByteCodeIterator iter){
		
		return null;
	}

	@Override
	public String toString() {
	    String fieldName = ((UTF8Info)pool.getConstantInfo(nameIndex)).getValue();
	    String fieldDescription = ((UTF8Info)pool.getConstantInfo(descriptorIndex)).getValue();
	    return fieldName + ":" + fieldDescription;
	}
}
