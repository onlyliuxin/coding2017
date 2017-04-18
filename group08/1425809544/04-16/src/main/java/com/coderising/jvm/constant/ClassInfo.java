package com.coderising.jvm.constant;

/**
 *  CONSTANT_Class_info 结构用于表示类或接口
 */
public class ClassInfo extends ConstantInfo {
	/**
	 * CONSTANT_Class_info 结构的 tag 项的值为 CONSTANT_Class（7）/u1
	 */
	private int type = ConstantInfo.CLASS_INFO;
	/**
	 *  name_index 项的值，必须是对常量池的一个有效索引。常量池在该索引处的项必须是CONSTANT_Utf8_info（§4.4.7）结构，代表一个有效的类或接口二进制名称的内部形式。/u2
	 */
	private int utf8Index ;

	public ClassInfo(ConstantPool pool) {
		super(pool);
	}
	public int getUtf8Index() {
		return utf8Index;
	}
	public void setUtf8Index(int utf8Index) {
		this.utf8Index = utf8Index;
	}
	public int getType() {
		return type;
	}
	
	public String getClassName() {		
		int index = getUtf8Index();
		UTF8Info utf8Info = (UTF8Info) this.constantPool.getConstantInfo(index);
		return utf8Info.getValue();		
	}
}
