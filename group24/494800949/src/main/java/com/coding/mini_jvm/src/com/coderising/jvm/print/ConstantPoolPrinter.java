package com.coding.mini_jvm.src.com.coderising.jvm.print;


import com.coding.mini_jvm.src.com.coderising.jvm.constant.ConstantInfo;
import com.coding.mini_jvm.src.com.coderising.jvm.constant.ConstantPool;

import java.util.Formatter;

public class ConstantPoolPrinter {
	ConstantPool pool;

	ConstantPoolPrinter(ConstantPool pool) {
		this.pool = pool;
	}

	public void print() {

		System.out.println("Constant Pool:");

		for (int i = 1; i <= (int)pool.getSize(); i++){
			ConstantInfo constantInfo = pool.getConstantInfo(i);
			Formatter formatter = new Formatter(System.out);
			formatter.format("%5s", "#"+i);
			constantInfo.accept(new SimpleVistor());
		}
	}

	public static void main(String[] args) {
//		Formatter f = new Formatter(System.out);
//		f.format("%-15s %-5s %-10s\n", "Item", "Qty", "Price");
//		f.format("%-15s %-5s %-10s\n", "----", "----", "-----");

	}
}
