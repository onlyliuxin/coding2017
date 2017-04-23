package com.coderising.jvm.print;

import com.coderising.jvm.constant.ClassInfo;
import com.coderising.jvm.constant.ConstantInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.FieldRefInfo;
import com.coderising.jvm.constant.MethodRefInfo;
import com.coderising.jvm.constant.NameAndTypeInfo;
import com.coderising.jvm.constant.StringInfo;
import com.coderising.jvm.constant.UTF8Info;

public class ConstantPoolPrinter {
	ConstantPool pool;

	ConstantPoolPrinter(ConstantPool pool) {
		this.pool = pool;
	}

	public void print() {

		System.out.println("Constant Pool:");
		int size = pool.getSize();
		for (int i = 1; i <= size; i++) {
			ConstantInfo constantInfo = pool.getConstantInfo(i);
			String index = "#" + i;
			System.out.printf("%5s = ", index);
			constantInfo.print();
		}
	}
}
