package week6.jvm.constant;

public class UTF8Info extends ConstantInfo{

	private int type=ConstantInfo.UTF8_INFO;
	private int length;
	private String value;
	
	public UTF8Info(ConstantPool pool){
		super(pool);
	}
	
	@Override
	public String toString() {
		return "UTF8Info [type=" + type + ", length=" + length + ", value="
				+ value + "]";
	}

	@Override
	public int getType() {
		return this.type;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitUTF8(this);
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
