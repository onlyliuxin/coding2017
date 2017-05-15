package com.coderising.jvm.cmd;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.engine.ExecutionResult;
import com.coderising.jvm.engine.JavaObject;
import com.coderising.jvm.engine.StackFrame;

public class ComparisonCmd extends TwoOperandCmd {

	protected ComparisonCmd(ClassFile clzFile, String opCode) {
		super(clzFile, opCode);
		
	}

	
	@Override
	public void execute(StackFrame frame,ExecutionResult result) {		
		
		if(ByteCodeCommand.if_icmp_ge.equals(this.getOpCode())){
			//注意次序
			JavaObject jo2 = frame.getOprandStack().pop();
			JavaObject jo1 = frame.getOprandStack().pop();
			
			if(jo1.getIntValue() >= jo2.getIntValue()){
				
				this.setJumpResult(result);
				
			}
			
		} else if(ByteCodeCommand.if_icmple.equals(this.getOpCode())){
			//注意次序
			JavaObject jo2 = frame.getOprandStack().pop();
			JavaObject jo1 = frame.getOprandStack().pop();
			
			if(jo1.getIntValue() <= jo2.getIntValue()){
				this.setJumpResult(result);
			}
			
		} else if(ByteCodeCommand.goto_no_condition.equals(this.getOpCode())){
			this.setJumpResult(result);
			
		} else{
			throw new RuntimeException(this.getOpCode() + "has not been implemented");
		}
		
		
		
		
	}

	private int getOffsetFromStartCmd(){
		//If the comparison succeeds, the unsigned branchbyte1 and branchbyte2
		//are used to construct a signed 16-bit offset, where the offset is calculated
		//to be (branchbyte1 << 8) | branchbyte2. Execution then proceeds at that
		//offset from the address of the opcode of this if_icmp<cond> instruction
		
	
		int index1 = this.getOprand1();
		int index2 = this.getOprand2();
		short offsetFromCurrent = (short)(index1 << 8 | index2);				
		return this.getOffset() + offsetFromCurrent ;
	}
	private void setJumpResult(ExecutionResult result){
		
		int offsetFromStartCmd = this.getOffsetFromStartCmd();		
		
		result.setNextAction(ExecutionResult.JUMP);
		result.setNextCmdOffset(offsetFromStartCmd);
	}

	@Override
	public String toString() {
		int index = this.getIndex();
		String text = this.getReadableCodeText();
		return this.getOffset()+":"+ this.getOpCode() + " "+text + " " + this.getOffsetFromStartCmd();
	}

}
