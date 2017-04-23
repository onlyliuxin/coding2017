package minijvm.cmd;

import minijvm.clz.ClassFile;
import minijvm.constant.ConstantPool;

public class NoOperandCmd extends ByteCodeCommand{

	public NoOperandCmd(ClassFile clzFile, String opCode) {
		super(clzFile, opCode);
	}

	@Override
	public String toString(ConstantPool pool) {
		return this.getOffset()+":" +this.getOpCode() + " "+ this.getReadableCodeText();
	}

	
	
	@Override
    public  int getLength(){
		return 1;
	}

}
