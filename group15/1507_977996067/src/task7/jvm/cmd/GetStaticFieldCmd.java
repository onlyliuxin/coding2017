package task7.jvm.cmd;

import task7.jvm.clz.ClassFile;
import task7.jvm.constant.ClassInfo;
import task7.jvm.constant.ConstantPool;
import task7.jvm.constant.FieldRefInfo;
import task7.jvm.constant.UTF8Info;


public class GetStaticFieldCmd extends TwoOperandCmd {

	public GetStaticFieldCmd(ClassFile clzFile,String opCode) {
		super(clzFile,opCode);
		
	}

	@Override
	public String toString(ConstantPool pool) {
		
		return super.getOperandAsField(pool);
	}

}
