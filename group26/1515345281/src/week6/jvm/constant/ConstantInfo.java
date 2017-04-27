package week6.jvm.constant;

import java.io.UnsupportedEncodingException;

import week6.jvm.loader.ByteCodeIterator;

public abstract class ConstantInfo {

	public static final int UTF8_INFO=1;
	public static final int FLOAT_INTO=4;
	public static final int CLASS_INFO=7;
	public static final int STRING_INFO=8;
	public static final int Field_INFO=9;
	public static final int METHOD_INFO=10;
	public static final int NAME_AND_TYPE_INFO=12;
	
	protected ConstantPool constantPool;
	
	public ConstantInfo(){};
	
	public ConstantInfo(ConstantPool constantPool){
		this.constantPool=constantPool;
	}
	
	public abstract int getType();
	
	public ConstantPool getConstantPool(){
		return this.constantPool;
	}
	
	public ConstantInfo getConstantInfo(int index){
		return this.constantPool.getConstantInfo(index);
	}
	
	public abstract void accept(Visitor visitor);
	
	public static interface Visitor{
		public void visitUTF8(UTF8Info info);
		public void visitClassInfo(ClassInfo info);
		public void visitString(StringInfo info);
		public void visitFieldRef(FieldRefInfo info);
		public void visitMethodRef(MethodRefInfo info);
		public void visitNameAndType(NameAndTypeInfo info);
	}

	public static ConstantInfo parseConstantInfo(ConstantPool pool, int tag,
			ByteCodeIterator iter) {
		 
		ConstantInfo info=null;
		
		switch(tag){
        case 1:
        	int len=iter.nextU2ToInt();
        	byte[] data=iter.getBytes(len);
        	
        	String value=null;
        	try {
				value=new String(data,"UTF-8");
				
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
        	
        	 info=new UTF8Info(pool);
        	( (UTF8Info) info).setLength(len);
        	((UTF8Info) info).setValue(value);      	
        	break;
        case 7:
        	info=new ClassInfo(pool);
        	((ClassInfo) info).setUtf8Index(iter.nextU2ToInt());
        	break;
        case 8:
        	
        	info=new StringInfo(pool);
        	((StringInfo) info).setIndex(iter.nextU2ToInt());       
        	break;
        case 9:
            info=new FieldRefInfo(pool);
            ((FieldRefInfo) info).setClassInfoIndex(iter.nextU2ToInt());
            ((FieldRefInfo) info).setNameAndTypeIndex(iter.nextU2ToInt());     
        	break;
        case 10:
        	info=new MethodRefInfo(pool);
        	((MethodRefInfo) info).setClassInfoIndex(iter.nextU2ToInt());
        	((MethodRefInfo) info).setNameAndTypeIndex(iter.nextU2ToInt());       	
        	break;
        case 12:
        	info=new NameAndTypeInfo(pool);
        	((NameAndTypeInfo) info).setNameIndex(iter.nextU2ToInt());
        	((NameAndTypeInfo) info).setTypeIndex(iter.nextU2ToInt());
        	break;
        default:
        	throw new RuntimeException("the constant pool tag " + tag + " has not been implemented yet.");
        }
		
		return info;
	}

		
}





















