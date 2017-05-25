package coderising.jvm.cmd;

import coderising.jvm.clz.ClassFile;
import coderising.jvm.constant.ConstantPool;
import coderising.jvm.constant.MethodRefInfo;


public class InvokeSpecialCmd extends TwoOperandCmd {

	public InvokeSpecialCmd(ClassFile clzFile,String opCode) {
		super(clzFile,opCode);
		
	}

	@Override
	public String toString(ConstantPool pool) {
		
		return super.getOperandAsMethod(pool);
	}

	

}
