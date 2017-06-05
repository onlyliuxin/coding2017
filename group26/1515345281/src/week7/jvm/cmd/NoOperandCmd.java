package week7.jvm.cmd;

import week7.jvm.clz.ClassFile;

public class NoOperandCmd extends ByteCodeCommand{

	public NoOperandCmd(ClassFile clzFile, String opCode) {
		super(clzFile,opCode);
	}

	@Override
	public String toString() {
		return this.getOffset()+":" +this.getOpCode() + " "+ this.getReadableCodeText();
	}
	
	@Override
	public int getLength() {
		return 1;
	}
}
