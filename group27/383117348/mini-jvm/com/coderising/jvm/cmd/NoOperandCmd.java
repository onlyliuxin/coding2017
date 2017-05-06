package com.coderising.jvm.cmd;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.engine.ExecutionResult;
import com.coderising.jvm.engine.Heap;
import com.coderising.jvm.engine.JavaObject;
import com.coderising.jvm.engine.StackFrame;

public class NoOperandCmd extends ByteCodeCommand{

	public NoOperandCmd(ClassFile clzFile, String opCode) {
		super(clzFile, opCode);
	}

	@Override
	public String toString(ConstantPool pool) {
		return this.getOffset()+":" +this.getOpCode() + " "+ this.getReadableCodeText();
	}
	
	public  int getLength(){
		return 1;
	}

	@Override
	public void execute(StackFrame frame, ExecutionResult result) {

		String opCode = this.getOpCode();
		
		if(ByteCodeCommand.aload_0.equalsIgnoreCase(opCode)){
		
			JavaObject jo = frame.getLocalVariableValue(0);
			
			frame.getOprandStack().push(jo);
			
		} else if(ByteCodeCommand.aload_1.equalsIgnoreCase(opCode)){
			
			JavaObject jo = frame.getLocalVariableValue(1);
			
			frame.getOprandStack().push(jo);
			
		} else if(ByteCodeCommand.aload_2.equalsIgnoreCase(opCode)){
			
			JavaObject jo = frame.getLocalVariableValue(2);
			
			frame.getOprandStack().push(jo);
			
		}else if(ByteCodeCommand.iload_1.equalsIgnoreCase(opCode)){
			
			JavaObject jo = frame.getLocalVariableValue(1);
			
			frame.getOprandStack().push(jo);
			
		} else if (ByteCodeCommand.iload_2.equalsIgnoreCase(opCode)){
			
			JavaObject jo = frame.getLocalVariableValue(2);
			
			frame.getOprandStack().push(jo);
			
		}  else if (ByteCodeCommand.iload_3.equalsIgnoreCase(opCode)){
			
			JavaObject jo = frame.getLocalVariableValue(3);
			
			frame.getOprandStack().push(jo);
			
		}else if (ByteCodeCommand.fload_3.equalsIgnoreCase(opCode)){
			
			JavaObject jo = frame.getLocalVariableValue(3);
			
			frame.getOprandStack().push(jo);
			
		}
		else if (ByteCodeCommand.voidreturn.equalsIgnoreCase(opCode)){
			
			result.setNextAction(ExecutionResult.EXIT_CURRENT_FRAME);
			
		} else if(ByteCodeCommand.ireturn.equalsIgnoreCase(opCode)){
			StackFrame callerFrame = frame.getCallerFrame();
			JavaObject jo = frame.getOprandStack().pop();
			callerFrame.getOprandStack().push(jo);
			
		} else if(ByteCodeCommand.freturn.equalsIgnoreCase(opCode)){
			
			StackFrame callerFrame = frame.getCallerFrame();
			JavaObject jo = frame.getOprandStack().pop();
			callerFrame.getOprandStack().push(jo);
		}
		
		else if(ByteCodeCommand.astore_1.equalsIgnoreCase(opCode)){
			
			JavaObject jo = frame.getOprandStack().pop();
			
		    frame.setLocalVariableValue(1, jo);
		    
		} else if(ByteCodeCommand.dup.equalsIgnoreCase(opCode)){
			
			JavaObject jo = frame.getOprandStack().peek();
			frame.getOprandStack().push(jo);
			
		} else if(ByteCodeCommand.iconst_0.equalsIgnoreCase(opCode)){
			
			JavaObject jo = Heap.getInstance().newInt(0);
			
			frame.getOprandStack().push(jo);
			
		} else if(ByteCodeCommand.iconst_1.equalsIgnoreCase(opCode)){
			
			JavaObject jo = Heap.getInstance().newInt(1);
			
			frame.getOprandStack().push(jo);
			
		} else if(ByteCodeCommand.istore_1.equalsIgnoreCase(opCode)){
			
			JavaObject jo = frame.getOprandStack().pop();
			
			frame.setLocalVariableValue(1, jo);
			
		}  else if(ByteCodeCommand.istore_2.equalsIgnoreCase(opCode)){
			
			JavaObject jo = frame.getOprandStack().pop();
			
			frame.setLocalVariableValue(2, jo);
			
		} else if(ByteCodeCommand.iadd.equalsIgnoreCase(opCode)){
			
			JavaObject jo1 = frame.getOprandStack().pop();
			JavaObject jo2 = frame.getOprandStack().pop();
			
			JavaObject sum = Heap.getInstance().newInt(jo1.getIntValue()+jo2.getIntValue());
			
			frame.getOprandStack().push(sum);
			
		} else if (ByteCodeCommand.aconst_null.equalsIgnoreCase(opCode)){
			
			frame.getOprandStack().push(null);
			
		} 
		else{
			throw new RuntimeException("you must forget to implement the operation :" + opCode);
		}
		
		
	}

}
