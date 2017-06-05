package com.coderising.jvm.constant;


/**
 * CONSTANT_Utf8_info 结构用于表示字符串常量的值：
 */
public class UTF8Info extends ConstantInfo{
	/**
	 * CONSTANT_Utf8_info 结构的 tag 项的值为 CONSTANT_Utf8（1）。
	 */
	private int type = ConstantInfo.UTF8_INFO;
	/**
	 * length 项的值指明了 bytes[]数组的长度（注意，不能等同于当前结构所表示的String 对象的长度），CONSTANT_Utf8_info 结构中的内容是以 length 属性确定长度而不是以 null 作为字符串的终结符。
	 */
	private int length ;
	/**
	 * bytes[length]是表示字符串值的byte数组，bytes[]数组中每个成员的byte值都不会是0，也不在 0xf0 至 0xff 范围内。
	 */
	private String value;

	public UTF8Info(ConstantPool pool) {
		super(pool);
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getType() {
		return type;
	}
	@Override
	public String toString() {
		return "UTF8Info [type=" + type + ", length=" + length + ", value=" + value +")]";
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	

}
