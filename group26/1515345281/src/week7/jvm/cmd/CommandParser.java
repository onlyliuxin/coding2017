package week7.jvm.cmd;

import java.util.ArrayList;
import java.util.List;


import week7.jvm.cmd.ByteCodeCommand;
import week7.jvm.clz.ClassFile;

public class CommandParser {

	public static ByteCodeCommand[] parse(ClassFile clzFile, String codes) {
	
	    if(codes.length() == 0 || codes == null || (codes.length() % 2)!=0){
	    	throw new RuntimeException("the orignal code is not correct");
	    }
	    
	    codes=codes.toUpperCase();
	    
	    CommandIterator iter=new CommandIterator(codes);
	    List<ByteCodeCommand> cmds=new ArrayList<ByteCodeCommand>();
	    
	    while(iter.hasNext()){
	    	
	    	String opCode=iter.next2CharAsString();
	    	
	    	if(ByteCodeCommand.new_object.equals(opCode)){
	    		
	    		NewObjectCmd cmd=new NewObjectCmd(clzFile,opCode);
	    		cmd.setOperand1(iter.next2CharAsInt());
	    		cmd.setOperand2(iter.next2CharAsInt());    		
	    		cmds.add(cmd);  
	    		
	    	}else if(ByteCodeCommand.invokespecial.equals(opCode)){
	    		
	    		InvokeSpecialCmd cmd=new InvokeSpecialCmd(clzFile,opCode);
	    		cmd.setOperand1(iter.next2CharAsInt());
	    		cmd.setOperand2(iter.next2CharAsInt());	    		
	    		cmds.add(cmd);
	    		
	    	}else if(ByteCodeCommand.invokevirtual.equals(opCode)){
	    		
	    		InvokeVirtualCmd cmd=new InvokeVirtualCmd(clzFile,opCode);
	    		cmd.setOperand1(iter.next2CharAsInt());
	    		cmd.setOperand2(iter.next2CharAsInt());
	    		cmds.add(cmd);
	    		
	    	}else if(ByteCodeCommand.getfield.equals(opCode)){
	    		
	    		GetFieldCmd cmd=new GetFieldCmd(clzFile,opCode);
	    		cmd.setOperand1(iter.next2CharAsInt());
	    		cmd.setOperand2(iter.next2CharAsInt());
	    		cmds.add(cmd);
	    		
	    	}else if(ByteCodeCommand.getstatic.equals(opCode)){
	    		
	    		GetStaticFieldCmd cmd=new GetStaticFieldCmd(clzFile,opCode);
	    		cmd.setOperand1(iter.next2CharAsInt());
	    		cmd.setOperand2(iter.next2CharAsInt());
	    		cmds.add(cmd);
	    		
	    	}else if(ByteCodeCommand.putfield.equals(opCode)){
	    		
	    		PutFieldCmd cmd=new PutFieldCmd(clzFile,opCode);
	    		cmd.setOperand1(iter.next2CharAsInt());
	    		cmd.setOperand2(iter.next2CharAsInt());
	    		cmds.add(cmd);
	    		
	    	}else if(ByteCodeCommand.ldc.equals(opCode)){
	    		
	    		LdcCmd cmd=new LdcCmd(clzFile,opCode);
	    		cmd.setOperand(iter.next2CharAsInt());
	    		cmds.add(cmd);
	    		
	    	}else if(ByteCodeCommand.bipush.equals(opCode)){
	    		
	    		BiPushCmd cmd=new BiPushCmd(clzFile,opCode);
	    		cmd.setOperand(iter.next2CharAsInt());
	    		cmds.add(cmd);
	    		
	    	}else if(ByteCodeCommand.dup.equals(opCode) 
					|| ByteCodeCommand.aload_0.equals(opCode) 
					|| ByteCodeCommand.aload_1.equals(opCode) 
					|| ByteCodeCommand.aload_2.equals(opCode)
					|| ByteCodeCommand.iload_1.equals(opCode) 
					|| ByteCodeCommand.iload_2.equals(opCode) 
					|| ByteCodeCommand.iload_3.equals(opCode)
					|| ByteCodeCommand.fload_3.equals(opCode) 
					|| ByteCodeCommand.voidreturn.equals(opCode) 
					|| ByteCodeCommand.astore_1.equals(opCode)){
	    		
	    		NoOperandCmd cmd = new NoOperandCmd(clzFile, opCode);
				cmds.add(cmd);
	    		
	    	}else {
				throw new RuntimeException("Sorry, the java instruction " + opCode + " has not been implemented");
			}
	    }
	    
	    calculateOffset(cmds);
	    
	    ByteCodeCommand[] result=new ByteCodeCommand[cmds.size()];	    
	    cmds.toArray(result);	    
	    return result;
	}

	private static void calculateOffset(List<ByteCodeCommand> cmds){
		int offset=0;
		for(ByteCodeCommand cmd:cmds){
			cmd.setOffset(offset);
			offset+=cmd.getLength();
		}
	}
	
	private static class CommandIterator{
		
		String codes=null;
		int pos;
		
		public CommandIterator(String codes){
			this.codes=codes;
		}

		public boolean hasNext() {
			
			return pos < codes.length();
		}
		
		public int next2CharAsInt(){
			String temp=next2CharAsString();
			return Integer.valueOf(temp, 16).intValue();
		}
		
		public String next2CharAsString(){
			String result=codes.substring(pos,pos+2);
			pos+=2;
			return result;
		}
	}
}
