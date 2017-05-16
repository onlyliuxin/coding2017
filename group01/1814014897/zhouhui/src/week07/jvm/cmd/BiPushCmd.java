package week07.jvm.cmd;

import week07.jvm.clz.ClassFile;
import week07.jvm.constant.ConstantInfo;
import week07.jvm.constant.ConstantPool;


public class BiPushCmd extends OneOperandCmd {

	public BiPushCmd(ClassFile clzFile,String opCode) {
		super(clzFile,opCode);
		
	}

	@Override
	public String toString(ConstantPool pool) {
	
		return this.getOffset()+": "+ this.getOpCode()+" " + this.getReadableCodeText() + " " + this.getOperand();
	}
	
	

}
