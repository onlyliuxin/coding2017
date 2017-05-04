package week8.jvm.cmd;


import week8.jvm.clz.ClassFile;
import week8.jvm.engine.ExecutorResult;
import week8.jvm.engine.Heap;
import week8.jvm.engine.JavaObject;
import week8.jvm.engine.StackFrame;

public class NoOperandCmd extends ByteCodeCommand{

	public NoOperandCmd(ClassFile clzFile, String opCode) {
		super(clzFile,opCode);
	}

	
	@Override
	public void execute(StackFrame frame,ExecutorResult result) {
		
		String opCode = this.getOpCode();
		
		if(ByteCodeCommand.aload_0.equals(opCode)){
		
			JavaObject jo = frame.getLocalVariableValue(0);
			
			frame.getOperandStack().push(jo);
			
		} else if(ByteCodeCommand.aload_1.equals(opCode)){
			
			JavaObject jo = frame.getLocalVariableValue(1);
			
			frame.getOperandStack().push(jo);
			
		} else if(ByteCodeCommand.aload_2.equals(opCode)){
			
			JavaObject jo = frame.getLocalVariableValue(2);
			
			frame.getOperandStack().push(jo);
			
		}else if(ByteCodeCommand.iload_1.equals(opCode)){
			
			JavaObject jo = frame.getLocalVariableValue(1);
			
			frame.getOperandStack().push(jo);
			
		} else if (ByteCodeCommand.iload_2.equals(opCode)){
			
			JavaObject jo = frame.getLocalVariableValue(2);
			
			frame.getOperandStack().push(jo);
			
		}  else if (ByteCodeCommand.iload_3.equals(opCode)){
			
			JavaObject jo = frame.getLocalVariableValue(3);
			
			frame.getOperandStack().push(jo);
			
		}else if (ByteCodeCommand.fload_3.equals(opCode)){
			
			JavaObject jo = frame.getLocalVariableValue(3);
			
			frame.getOperandStack().push(jo);
			
		}
		else if (ByteCodeCommand.voidreturn.equals(opCode)){
			
			result.setNextAction(ExecutorResult.EXIT_CURRENT_FRAME);
			
		} else if(ByteCodeCommand.ireturn.equals(opCode)){
			StackFrame callerFrame = frame.getCallerFrame();
			JavaObject jo = frame.getOperandStack().pop();
			callerFrame.getOperandStack().push(jo);
			
		} else if(ByteCodeCommand.freturn.equals(opCode)){
			
			StackFrame callerFrame = frame.getCallerFrame();
			JavaObject jo = frame.getOperandStack().pop();
			callerFrame.getOperandStack().push(jo);
		}
		
		else if(ByteCodeCommand.astore_1.equals(opCode)){
			
			JavaObject jo = frame.getOperandStack().pop();
			
		    frame.setLocalVariableValue(1, jo);
		    
		} else if(ByteCodeCommand.dup.equals(opCode)){
			
			JavaObject jo = frame.getOperandStack().peek();
			frame.getOperandStack().push(jo);
			
		} else if(ByteCodeCommand.iconst_0.equals(opCode)){
			
			JavaObject jo = Heap.getInstance().newInt(0);
			
			frame.getOperandStack().push(jo);
			
		} else if(ByteCodeCommand.iconst_1.equals(opCode)){
			
			JavaObject jo = Heap.getInstance().newInt(1);
			
			frame.getOperandStack().push(jo);
			
		} else if(ByteCodeCommand.istore_1.equals(opCode)){
			
			JavaObject jo = frame.getOperandStack().pop();
			
			frame.setLocalVariableValue(1, jo);
			
		}  else if(ByteCodeCommand.istore_2.equals(opCode)){
			
			JavaObject jo = frame.getOperandStack().pop();
			
			frame.setLocalVariableValue(2, jo);
			
		} else if(ByteCodeCommand.iadd.equals(opCode)){
			
			JavaObject jo1 = frame.getOperandStack().pop();
			JavaObject jo2 = frame.getOperandStack().pop();
			
			JavaObject sum = Heap.getInstance().newInt(jo1.getIntValue()+jo2.getIntValue());
			
			frame.getOperandStack().push(sum);
			
		} else if (ByteCodeCommand.aconst_null.equals(opCode)){
			
			frame.getOperandStack().push(null);
			
		} 
		else{
			throw new RuntimeException("you must forget to implement the operation :" + opCode);
		}
		
		
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
