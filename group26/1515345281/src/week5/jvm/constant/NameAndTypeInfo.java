package week5.jvm.constant;

public class NameAndTypeInfo extends ConstantInfo {

	private int type=ConstantInfo.NAME_AND_TYPE_INFO;
	private int nameIndex;
	private int typeIndex;
	
	public NameAndTypeInfo(ConstantPool pool){
		super(pool);
	}
	
	@Override
	public int getType() {
		
		return this.type;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitNameAndType(this);
	}
	
	@Override
	public String toString(){
		return "("+getName()+","+getType()+")";
	}
	
	public String getName(){
		UTF8Info utf8Info=(UTF8Info) this.getConstantInfo(this.getNameIndex());
	    return utf8Info.getValue();
	}

	public String getTypeInfo(){
		UTF8Info utf8Info=(UTF8Info)this.getConstantInfo(this.getTypeIndex());
		return utf8Info.getValue();
	}
	
	public int getNameIndex() {
		return nameIndex;
	}

	public void setNameIndex(int nameIndex) {
		this.nameIndex = nameIndex;
	}

	public int getTypeIndex() {
		return typeIndex;
	}

	public void setTypeIndex(int typeIndex) {
		this.typeIndex = typeIndex;
	}
}
