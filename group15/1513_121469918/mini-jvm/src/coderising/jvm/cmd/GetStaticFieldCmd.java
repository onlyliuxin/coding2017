package coderising.jvm.cmd;

import coderising.jvm.clz.ClassFile;
import coderising.jvm.constant.ClassInfo;
import coderising.jvm.constant.ConstantPool;
import coderising.jvm.constant.FieldRefInfo;
import coderising.jvm.constant.UTF8Info;


public class GetStaticFieldCmd extends TwoOperandCmd {

	public GetStaticFieldCmd(ClassFile clzFile,String opCode) {
		super(clzFile,opCode);
		
	}

	@Override
	public String toString(ConstantPool pool) {
		
		return super.getOperandAsField(pool);
	}

}
