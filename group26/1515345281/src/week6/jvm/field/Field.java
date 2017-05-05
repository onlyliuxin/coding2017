package week6.jvm.field;


import week6.jvm.clz.ClassFile;
import week6.jvm.constant.ConstantPool;
import week6.jvm.loader.ByteCodeIterator;

public class Field {

	private int accessFlags;
	private int nameIndex;
	private int descIndex;

	private ClassFile clzFile;
	
	
	public Field(int accessFlags, int nameIndex, int descIndex,ClassFile clzFile) {
		this.accessFlags=accessFlags;
		this.nameIndex=nameIndex;
		this.descIndex=descIndex;
		this.clzFile=clzFile;
	}

	@Override
	public String toString(){
		
		String name = clzFile.getConstantPool().getUTF8String(nameIndex);
		String desc = clzFile.getConstantPool().getUTF8String(descIndex);
		
		return name+":"+desc;
	}
	/**
	 * 用于转换字段表
	 * @param constantPool
	 * @param iter
	 * @return
	 */
	public static Field parseField(ClassFile clzFile,
			ByteCodeIterator iter) {
		
		int accessFlags=iter.nextU2ToInt();
		int nameIndex=iter.nextU2ToInt();
		int descIndex=iter.nextU2ToInt();
		int attributeCount=iter.nextU2ToInt();
		
		Field field=new Field(accessFlags,nameIndex,descIndex,clzFile);
		
		if(attributeCount > 0){
			throw new RuntimeException("Field Attribute has not been implemented");
		}
		
		return field;
	}
	
	public int getAccessFlags() {
		return accessFlags;
	}
	public void setAccessFlags(int accessFlags) {
		this.accessFlags = accessFlags;
	}
	public int getNameIndex() {
		return nameIndex;
	}
	public void setNameIndex(int nameIndex) {
		this.nameIndex = nameIndex;
	}
	public int getDescIndex() {
		return descIndex;
	}
	public void setDescIndex(int descIndex) {
		this.descIndex = descIndex;
	}
}
