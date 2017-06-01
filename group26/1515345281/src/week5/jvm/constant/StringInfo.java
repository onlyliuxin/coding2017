package week5.jvm.constant;

public class StringInfo extends ConstantInfo{

	private int type=ConstantInfo.STRING_INFO;
	private int index;
	
	public StringInfo(ConstantPool pool){
		super(pool);
	}
	
	@Override
	public int getType() {
		return this.type;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitString(this);
	}
	
	

	@Override
	public String toString() {
		return this.getConstantPool().getUTF8String(index);
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
