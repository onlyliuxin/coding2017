package com.coderising.jvm.constant;

/**
 * CONSTANT_String_info 用于表示 java.lang.String 类型的常量对象，
 */
public class StringInfo extends ConstantInfo{
	/**
	 * CONSTANT_String_info 结构的 tag 项的值为 CONSTANT_String（8）。
	 */
	private int type = ConstantInfo.STRING_INFO;
	/**
	 * string_index 项的值必须是对常量池的有效索引，常量池在该索引处的项必须是CONSTANT_Utf8_info（§4.4.7）结构，表示一组 Unicode 码点序列，
	 * 这组 Unicode码点序列最终会被初始化为一个 String 对象。
	 */
	private int index;

	public StringInfo(ConstantPool pool) {
		super(pool);
	}

	public int getType() {
		return type;
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
