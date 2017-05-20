package org.wsc.coderising.jvm.constant;

/**
 *
 * 描述符
 * @author Administrator
 * @date 2017年4月9日下午2:46:19
 * @version v1.0
 *
 */
public abstract class ConstantInfo {
	/** UTF-8编码的字符串 */
	public static final int UTF8_INFO = 1;
	/** 浮点型字面量 */
	public static final int FLOAT_INFO = 4;
	/** 类或接口的符号引用 */
	public static final int CLASS_INFO = 7;
	/** 字符串类型字面量 */
	public static final int STRING_INFO = 8;
	/** 字段的符号引用 */
	public static final int FIELD_INFO = 9;
	/** 类中方法的符号引用 */
	public static final int METHOD_INFO = 10;
	/** 字段或方法的部门符号引用 */
	public static final int NAME_AND_TYPE_INFO = 12;
	/** 常量池 */
	protected ConstantPool constantPool;

	public ConstantInfo() {

	}

	public ConstantInfo(ConstantPool pool) {
		this.constantPool = pool;
	}

	public abstract int getType();

	public ConstantPool getConstantPool() {
		return constantPool;
	}

	public ConstantInfo getConstantInfo(int index) {
		return this.constantPool.getConstantInfo(index);
	}
}
