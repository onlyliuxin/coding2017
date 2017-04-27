package com.coderising.jvm.cmd;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.coderising.jvm.clz.ClassFile;

public class CommandParser {
	
	public static final String aconst_null = "01";
	public static final String new_object = "BB";
	public static final String lstore = "37";
	public static final String invokespecial = "B7";
	public static final String invokevirtual = "B6";
	public static final String getfield = "B4";
	public static final String putfield = "B5";
	public static final String getstatic = "B2";
	public static final String ldc = "12";
	public static final String dup = "59";
	public static final String bipush = "10";
	public static final String aload_0 = "2A";
	public static final String aload_1 = "2B";
	public static final String aload_2 = "2C";
	public static final String iload = "15";
	public static final String iload_1 = "1B";
	public static final String iload_2 = "1C";
	public static final String iload_3 = "1D";
	public static final String fload_3 = "25";

	public static final String voidreturn = "B1";
	public static final String ireturn = "AC";
	public static final String freturn = "AE";

	public static final String astore_1 = "4C";
	public static final String if_icmp_ge = "A2";
	public static final String if_icmple = "A4";
	public static final String goto_no_condition = "A7";
	public static final String iconst_0 = "03";
	public static final String iconst_1 = "04";
	public static final String istore_1 = "3C";
	public static final String istore_2 = "3D";
	public static final String iadd = "60";
	public static final String iinc = "84";

	public static ByteCodeCommand[] parse(ClassFile clzFile, String codes) {
		
		CommandIterator iter=new CommandIterator(codes);
		List<ByteCodeCommand> cmds=new LinkedList<>();
		while(iter.hasNext()){
			String oper=iter.next2CharAsString().toUpperCase();
			if(oper.equals(new_object)){
				NewObjectCmd cmd=new NewObjectCmd(clzFile, oper);
				cmd.setOprand1(iter.next2CharAsInt());
				cmd.setOprand2(iter.next2CharAsInt());
				cmds.add(cmd);
			}else if(oper.equals(invokespecial)){
				InvokeSpecialCmd cmd=new InvokeSpecialCmd(clzFile, oper);
				cmd.setOprand1(iter.next2CharAsInt());
				cmd.setOprand2(iter.next2CharAsInt());
				cmds.add(cmd);
			}else if(oper.equals(invokevirtual)){
				InvokeVirtualCmd cmd=new InvokeVirtualCmd(clzFile, oper);
				cmd.setOprand1(iter.next2CharAsInt());
				cmd.setOprand2(iter.next2CharAsInt());
				cmds.add(cmd);
			}else if(oper.equals(getfield)){
				GetFieldCmd cmd=new GetFieldCmd(clzFile, oper);
				cmd.setOprand1(iter.next2CharAsInt());
				cmd.setOprand2(iter.next2CharAsInt());
				cmds.add(cmd);
			}else if(oper.equals(putfield)){
				PutFieldCmd cmd=new PutFieldCmd(clzFile, oper);
				cmd.setOprand1(iter.next2CharAsInt());
				cmd.setOprand2(iter.next2CharAsInt());
				cmds.add(cmd);
			}else if(oper.equals(getstatic)){
				GetFieldCmd cmd=new GetFieldCmd(clzFile, oper);
				cmd.setOprand1(iter.next2CharAsInt());
				cmd.setOprand2(iter.next2CharAsInt());
				cmds.add(cmd);
			}else if(oper.equals(ldc)){
				LdcCmd cmd=new LdcCmd(clzFile, oper);
				cmd.setOperand(iter.next2CharAsInt());
				cmds.add(cmd);
			}else if(oper.equals(bipush)){
				BiPushCmd cmd=new BiPushCmd(clzFile, oper);
				cmd.setOperand(iter.next2CharAsInt());
				cmds.add(cmd);
			}else if(oper.equals(aconst_null)||oper.equals(dup)||oper.equals(aload_0)||oper.equals(aload_1)
					||oper.equals(aload_2)||oper.equals(iload_1)||oper.equals(iload_2)||oper.equals(iload_3)
					||oper.equals(fload_3)||oper.equals(voidreturn)||oper.equals(ireturn)||oper.equals(freturn)
					||oper.equals(astore_1)||oper.equals(iconst_0)||oper.equals(iconst_1)||oper.equals(istore_1)
					||oper.equals(istore_2)||oper.equals(iadd)||oper.equals(iinc)){
				NoOperandCmd cmd=new NoOperandCmd(clzFile, oper);
				cmds.add(cmd);
			}else {
				throw new RuntimeException("the Instruction "+oper+" has not implemented yet");
			}
			
		}
		calcuateOffset(cmds);
		ByteCodeCommand[] answer=new ByteCodeCommand[cmds.size()];
		int index=0;
		Iterator<ByteCodeCommand> iterator=cmds.iterator();
		while(iterator.hasNext()){
			answer[index++]=iterator.next();
		}
		return answer;
	}

	private static void calcuateOffset(List<ByteCodeCommand> cmds) {

		int offset = 0;
		for (ByteCodeCommand cmd : cmds) {
			cmd.setOffset(offset);
			offset += cmd.getLength();
		}

	}

	private static class CommandIterator {
		String codes = null;
		int pos = 0;

		CommandIterator(String codes) {
			this.codes = codes;
		}

		public boolean hasNext() {
			return pos < this.codes.length();
		}

		public String next2CharAsString() {
			String result = codes.substring(pos, pos + 2);
			pos += 2;
			return result;
		}

		public int next2CharAsInt() {
			String s = this.next2CharAsString();
			return Integer.valueOf(s, 16).intValue();
		}

	}
}
