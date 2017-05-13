package com.github.ipk2015.coding2017.minijvm.cmd;

import com.github.ipk2015.coding2017.minijvm.clz.ClassFile;
import com.github.ipk2015.coding2017.minijvm.constant.ConstantInfo;
import com.github.ipk2015.coding2017.minijvm.constant.ConstantPool;
import com.github.ipk2015.coding2017.minijvm.constant.StringInfo;
import com.github.ipk2015.coding2017.minijvm.engine.ExecutionResult;
import com.github.ipk2015.coding2017.minijvm.engine.Heap;
import com.github.ipk2015.coding2017.minijvm.engine.JavaObject;
import com.github.ipk2015.coding2017.minijvm.engine.StackFrame;

public class LdcCmd extends OneOperandCmd {

	public LdcCmd(ClassFile clzFile,String opCode) {
		super(clzFile,opCode);		
	}
	
	@Override
	public String toString(ConstantPool pool) {
		
		ConstantInfo info = (ConstantInfo)pool.getConstantInfo(this.getOperand());
		
		String value = "TBD";
		if(info instanceof StringInfo){
			StringInfo strInfo = (StringInfo)info;
			value = strInfo.toString();
		}
		
		return this.getOffset()+":"+this.getOpCode()+" " + this.getReadableCodeText() + " "+  value;
		
	}

	@Override
	public void execute(StackFrame frame, ExecutionResult result) {
		ConstantPool pool = this.getConstantPool();
		ConstantInfo constantInfo = pool.getConstantInfo(this.getOperand());
		if(constantInfo instanceof StringInfo){
			StringInfo stringInfo = (StringInfo)constantInfo;
			String value = stringInfo.toString();
			JavaObject javaObject = Heap.getInstance().newString(value);
			frame.getOprandStack().push(javaObject);
		}else{
			//TBD 处理其他类型
			throw new RuntimeException("Only support StringInfo constant");
		}
	}
	
}
