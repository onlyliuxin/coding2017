package week8.jvm.cmd;

import week8.jvm.clz.ClassFile;
import week8.jvm.constant.ClassInfo;
import week8.jvm.constant.FieldRefInfo;
import week8.jvm.constant.MethodRefInfo;

public abstract class TwoOperandCmd extends ByteCodeCommand {

	int operand1;
	int operand2;
	
	protected TwoOperandCmd(ClassFile clzFile, String opCode) {
		super(clzFile, opCode);
	}

	
	public int getIndex(){
		int operand1=this.getOperand1();
		int operand2=this.getOperand2();
		
		int index= operand1 << 8 | operand2 ;
		return index;
	}
	
	protected String getOperandAsClassInfo(){
		int index=getIndex();
		String codeTxt=getReadableCodeText();
		ClassInfo info=(ClassInfo) this.getConstantInfo(index);
		
		return this.getOffset()+":"+opCode+" "+codeTxt+" "+info.getClassName();
		
	}
	
	protected String getOperandAsMethod(){
		int index=getIndex();
		String codeTxt=getReadableCodeText();
		MethodRefInfo info=(MethodRefInfo) this.getConstantInfo(index);
		return this.getOffset()+":"+opCode+" "+codeTxt+" "+info.toString();
	}
	
	protected String getOperandAsField(){
		int index=getIndex();
		String codeTxt=getReadableCodeText();
		FieldRefInfo info=(FieldRefInfo) this.getConstantInfo(index);
		return this.getOffset()+":"+opCode+" "+codeTxt+" "+info.toString();
	}
	
	@Override
	public int getLength(){
		return 3;
	}
	
	public int getOperand1() {
		return operand1;
	}

	public void setOperand1(int operand1) {
		this.operand1 = operand1;
	}

	public int getOperand2() {
		return operand2;
	}

	public void setOperand2(int operand2) {
		this.operand2 = operand2;
	}
	
}
