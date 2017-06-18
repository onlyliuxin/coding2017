package com.github.eulerlcs.jmr.jvm.field;

import com.github.eulerlcs.jmr.jvm.constant.ConstantPool;
import com.github.eulerlcs.jmr.jvm.loader.ByteCodeIterator;

public class Field {
	private int accessFlag;
	private int nameIndex;
	private int descriptorIndex;

	private ConstantPool pool;

	public Field(int accessFlag, int nameIndex, int descriptorIndex, ConstantPool pool) {

		this.accessFlag = accessFlag;
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
		this.pool = pool;
	}

	public static Field parse(ConstantPool pool, ByteCodeIterator iter) {

		return null;
	}

}
