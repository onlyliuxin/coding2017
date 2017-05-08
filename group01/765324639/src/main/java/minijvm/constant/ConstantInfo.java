package minijvm.constant;

public abstract class ConstantInfo {
	public static final int UTF8_INFO = 1;
	public static final int FLOAT_INFO = 4;
	public static final int CLASS_INFO = 7;
	public static final int STRING_INFO = 8;
	public static final int FIELD_INFO = 9;
	public static final int METHOD_INFO = 10;
	public static final int NAME_AND_TYPE_INFO = 12;
	protected ConstantPool constantPool;
	
	public ConstantInfo(){
		
	}
	
	public ConstantInfo(ConstantPool pool) {
		this.constantPool = pool;
	}
	public abstract int getType();
	
	public ConstantPool getConstantPool() {
		return constantPool;
	}
	public ConstantInfo getConstantInfo(int index){
		return this.constantPool.getConstantInfo(index);
	}
	
}
