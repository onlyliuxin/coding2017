package task7.jvm.cmd;

import task7.jvm.clz.ClassFile;
import task7.jvm.constant.ConstantPool;


public class InvokeVirtualCmd extends TwoOperandCmd {

	public InvokeVirtualCmd(ClassFile clzFile,String opCode) {
		super(clzFile,opCode);
	}

	@Override
	public String toString(ConstantPool pool) {
		
		return super.getOperandAsMethod(pool);
	}

	
	

}
