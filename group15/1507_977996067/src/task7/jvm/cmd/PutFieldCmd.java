package task7.jvm.cmd;

import task7.jvm.clz.ClassFile;
import task7.jvm.constant.ConstantPool;

public class PutFieldCmd extends TwoOperandCmd {

	public PutFieldCmd(ClassFile clzFile,String opCode) {
		super(clzFile,opCode);		
	}

	@Override
	public String toString(ConstantPool pool) {
		
		return super.getOperandAsField(pool);
	}


}
