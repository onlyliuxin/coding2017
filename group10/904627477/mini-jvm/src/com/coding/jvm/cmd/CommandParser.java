package com.coding.jvm.cmd;

import java.util.ArrayList;
import java.util.List;

import com.coding.jvm.clz.ClassFile;

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
		CommandIterator cmdIter = new CommandIterator(codes);
		List<ByteCodeCommand> cmds = new ArrayList<ByteCodeCommand>();
		while(cmdIter.hasNext()){
			ByteCodeCommand cmd = getByteCodeCommand(clzFile,cmdIter);
			if(cmd!=null){
				cmds.add(cmd);
			}
		}
		calcuateOffset(cmds);
		return cmds.toArray(new ByteCodeCommand[0]);
	}


	private static ByteCodeCommand getByteCodeCommand(ClassFile clzFile,CommandIterator cmdIter) {
		String opCode = cmdIter.next2CharAsString().toUpperCase();
		switch (opCode) {
		case new_object:
			NewObjectCmd cmd = new NewObjectCmd(clzFile, opCode);
			cmd.setOprand1(cmdIter.next2CharAsInt());
			cmd.setOprand2(cmdIter.next2CharAsInt());
			return cmd;
		case aconst_null:
		case aload_0:
		case aload_1:
		case aload_2:
		case astore_1:
		case dup:
		case fload_3:
		case freturn:
		case iadd:
		case iconst_0:
		case iconst_1:
		case iload_1:
		case iload_2:
		case iload_3:
		case ireturn:
		case istore_1:
		case istore_2:
		case voidreturn:
			NoOperandCmd cmd0 = new NoOperandCmd(clzFile, opCode);
			return cmd0;
		case lstore:
		case iload:
			OneOperandCmd cmd1 = OneOperandCmd.getOneOperandCmd(clzFile, opCode);
			cmd1.setOperand(cmdIter.next2CharAsInt());
			return cmd1;
		case if_icmp_ge:
		case if_icmple:
		case goto_no_condition:
		case iinc:
			TwoOperandCmd cmd2 = TwoOperandCmd.getTwoOperandCmd(clzFile, opCode);
			cmd2.setOprand1(cmdIter.next2CharAsInt());
			cmd2.setOprand2(cmdIter.next2CharAsInt());
			return cmd2;
		case invokespecial:
			InvokeSpecialCmd cmdis = new InvokeSpecialCmd(clzFile, opCode);
			cmdis.setOprand1(cmdIter.next2CharAsInt());
			cmdis.setOprand2(cmdIter.next2CharAsInt());
			return cmdis;
		case invokevirtual:
			InvokeVirtualCmd cmdiv = new InvokeVirtualCmd(clzFile, opCode);
			cmdiv.setOprand1(cmdIter.next2CharAsInt());
			cmdiv.setOprand2(cmdIter.next2CharAsInt());
			return cmdiv;
		case getfield:
			GetFieldCmd cmdgf = new GetFieldCmd(clzFile, opCode);
			cmdgf.setOprand1(cmdIter.next2CharAsInt());
			cmdgf.setOprand2(cmdIter.next2CharAsInt());
			return cmdgf;
		case putfield:
			PutFieldCmd cmdpf = new PutFieldCmd(clzFile, opCode);
			cmdpf.setOprand1(cmdIter.next2CharAsInt());
			cmdpf.setOprand2(cmdIter.next2CharAsInt());
			return cmdpf;
		case getstatic:
			GetStaticFieldCmd cmdgsf = new GetStaticFieldCmd(clzFile, opCode);
			cmdgsf.setOprand1(cmdIter.next2CharAsInt());
			cmdgsf.setOprand2(cmdIter.next2CharAsInt());
			return cmdgsf;
		case ldc:
			LdcCmd cmdl = new LdcCmd(clzFile, opCode);
			cmdl.setOperand(cmdIter.next2CharAsInt());
			return cmdl;
		case bipush:
			BiPushCmd cmdb = new BiPushCmd(clzFile, opCode);
			cmdb.setOperand(cmdIter.next2CharAsInt());
			return cmdb;
		default:
			return null;
		}
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
