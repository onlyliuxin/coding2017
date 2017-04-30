package week07.jvm.cmd;

import week07.jvm.clz.ClassFile;
import week07.jvm.constant.ConstantPool;
import week07.jvm.constant.MethodRefInfo;


public class InvokeSpecialCmd extends TwoOperandCmd {

	public InvokeSpecialCmd(ClassFile clzFile,String opCode) {
		super(clzFile,opCode);
		
	}

	@Override
	public String toString(ConstantPool pool) {
		
		return super.getOperandAsMethod(pool);
	}

	

}
