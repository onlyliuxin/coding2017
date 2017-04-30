package com.coderising.jvm.constant;

public class NameAndTypeInfo extends ConstantInfo {
	private int type = NAME_AND_TYPE_INFO;// u1 tag
	private int name_index;// u2 name_index
	private int descriptor_index;// u2 descriptor_index

	public NameAndTypeInfo(ConstantPool pool) {
		super(pool);
	}

	@Override
	public int getType() {
		return type;
	}

	@Override
	public void print() {
		System.out.println("u1 tag:" + getType() + " NameAndTypeInfo"
				+ ",u2 name_index:" + getName_index() + ",u2 descriptor_index:"
				+ getDescriptor_index());
	}

	@Override
	public String toString() {
		return "(" + getName() + "," + getTypeInfo() + ")";
	}

	public String getName() {
		UTF8Info u = (UTF8Info) this.constantPool.getConstantInfo(name_index);
		return u.getValue();
	}

	public String getTypeInfo() {
		UTF8Info u = (UTF8Info) this.constantPool
				.getConstantInfo(descriptor_index);
		return u.getValue();
	}

	/*
	 * getter setter
	 */
	public int getName_index() {
		return name_index;
	}

	public void setName_index(int name_index) {
		this.name_index = name_index;
	}

	public int getDescriptor_index() {
		return descriptor_index;
	}

	public void setDescriptor_index(int descriptor_index) {
		this.descriptor_index = descriptor_index;
	}
}
