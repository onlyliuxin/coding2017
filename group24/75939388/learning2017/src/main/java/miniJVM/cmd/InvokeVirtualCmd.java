package miniJVM.cmd;

import miniJVM.clz.ClassFile;
import miniJVM.constant.ConstantPool;

public class InvokeVirtualCmd extends TwoOperandCmd {

	public InvokeVirtualCmd(ClassFile clzFile, String opCode) {
		super(clzFile,opCode);
	}

	@Override
	public String toString(ConstantPool pool) {
		
		return super.getOperandAsMethod(pool);
	}

	
	

}
