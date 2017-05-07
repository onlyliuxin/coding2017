package task7.jvm.cmd;

import task7.jvm.clz.ClassFile;
import task7.jvm.constant.ConstantInfo;
import task7.jvm.constant.ConstantPool;


public class BiPushCmd extends OneOperandCmd {

	public BiPushCmd(ClassFile clzFile,String opCode) {
		super(clzFile,opCode);
		
	}

	@Override
	public String toString(ConstantPool pool) {
	
		return this.getOffset()+": "+ this.getOpCode()+" " + this.getReadableCodeText() + " " + this.getOperand();
	}
	
	

}
