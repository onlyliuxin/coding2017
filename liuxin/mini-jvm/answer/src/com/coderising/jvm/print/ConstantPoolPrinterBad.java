package com.coderising.jvm.print;

import com.coderising.jvm.constant.ClassInfo;
import com.coderising.jvm.constant.ConstantInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.UTF8Info;

public class ConstantPoolPrinterBad {
	ConstantPool pool;
	ConstantPoolPrinterBad(ConstantPool pool){
		this.pool = pool;
	}
	public void print(){
		
		System.out.println("Constant Pool:");		
		
		for(int i=1; i<=pool.getSize(); i++){
			ConstantInfo cnstInfo = pool.getConstantInfo(i);
			
			System.out.print("#"+i+"=");
			if(cnstInfo instanceof ClassInfo){
				ClassInfo info = (ClassInfo)cnstInfo;
				// Class    #2  com/coderising/jvm/test/EmployeeV1
				StringBuilder buffer = new StringBuilder();
				buffer.append("Class    #").append(info.getUtf8Index())
				.append("  ").append(info.getClassName());
				
				System.out.println(buffer);
			}
			if(cnstInfo instanceof UTF8Info){
				//UTF8    com/coderising/jvm/test/EmployeeV1
				UTF8Info info = (UTF8Info)cnstInfo;
				StringBuilder buffer = new StringBuilder();
				buffer.append("UTF8    ").append(info.getValue());
				System.out.println(buffer);
			}
			//其他的if else
		}
	}
}
