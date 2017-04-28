package coderising.jvm.cmd;

import coderising.jvm.clz.ClassFile;
import coderising.jvm.constant.ConstantInfo;
import coderising.jvm.constant.ConstantPool;


public class BiPushCmd extends OneOperandCmd {

	public BiPushCmd(ClassFile clzFile,String opCode) {
		super(clzFile,opCode);
		
	}

	@Override
	public String toString(ConstantPool pool) {
	
		return this.getOffset()+": "+ this.getOpCode()+" " + this.getReadableCodeText() + " " + this.getOperand();
	}
	
	

}
