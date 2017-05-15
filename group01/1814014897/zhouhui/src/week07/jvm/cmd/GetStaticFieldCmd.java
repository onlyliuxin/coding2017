package week07.jvm.cmd;

import week07.jvm.clz.ClassFile;
import week07.jvm.constant.ClassInfo;
import week07.jvm.constant.ConstantPool;
import week07.jvm.constant.FieldRefInfo;
import week07.jvm.constant.UTF8Info;


public class GetStaticFieldCmd extends TwoOperandCmd {

	public GetStaticFieldCmd(ClassFile clzFile,String opCode) {
		super(clzFile,opCode);
		
	}

	@Override
	public String toString(ConstantPool pool) {
		
		return super.getOperandAsField(pool);
	}

}
