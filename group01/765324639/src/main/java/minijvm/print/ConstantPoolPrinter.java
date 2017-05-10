package minijvm.print;

import minijvm.constant.ConstantInfo;
import minijvm.constant.ConstantInfo.Visitor;
import minijvm.constant.ConstantPool;

public class ConstantPoolPrinter {
	ConstantPool pool;
	ConstantPoolPrinter(ConstantPool pool){
		this.pool = pool;
	}
	public void print(Visitor visitor){
		
		System.out.println("Constant Pool:");
		
		int size = pool.getSize();
		System.out.println("size:" + size);
		for (int i = 1; i <= size; i++) {
		    ConstantInfo constantInfo = pool.getConstantInfo(i);
		    constantInfo.accept(visitor);
		}
		
		
	}
}
