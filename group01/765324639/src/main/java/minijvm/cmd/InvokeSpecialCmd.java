package minijvm.cmd;

import minijvm.clz.ClassFile;
import minijvm.constant.ConstantPool;

public class InvokeSpecialCmd extends TwoOperandCmd {

	public InvokeSpecialCmd(ClassFile clzFile,String opCode) {
		super(clzFile,opCode);
		
	}

	@Override
	public String toString(ConstantPool pool) {
		
		return super.getOperandAsMethod(pool);
	}

	

}
