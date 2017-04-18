package org.wsc.coderising.jvm.constant;

/**
 *
 * 字符串描述符
 * @author Administrator
 * @date 2017年4月9日下午2:54:31
 * @version v1.0
 *
 */
public class StringInfo extends ConstantInfo {
	private int type = ConstantInfo.STRING_INFO;
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

	public String toString() {
		return this.getConstantPool().getUTF8String(index);
	}

}
