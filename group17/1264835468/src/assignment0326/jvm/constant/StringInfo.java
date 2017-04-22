package assignment0326.jvm.constant;

public class StringInfo extends ConstantInfo{
	private int type = ConstantInfo.STRING_INFO;
	private int index;
	public StringInfo(ConstantPool pool) {
		super(pool);
	}

	public int getType() {
		return type;
	}

	@Override
	public String typeDescription() {
		return "String";
	}

	@Override
	public String contentDescription() {
		return "#" + index;
	}

	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}

	
	public String toString(){
		return this.getConstantPool().getUTF8String(index);
	}
	
}
