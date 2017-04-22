package com.coderising.jvm.cmd;

import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.constant.ConstantPool;

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

	public static ByteCodeCommand[] parse(ConstantPool constantPool, String code) {
		CommandIterator it = new CommandIterator(code);
		List<ByteCodeCommand> cmds = new ArrayList<>();
		while(it.hasNext()) {
			String opCode = it.next2CharAsString().toUpperCase();
			if(aload_0.equalsIgnoreCase(opCode) || aload_1.equalsIgnoreCase(opCode) || aload_2.equalsIgnoreCase(opCode) 
				|| voidreturn.equalsIgnoreCase(opCode) || dup.equalsIgnoreCase(opCode) || astore_1.equalsIgnoreCase(opCode)
				|| iload_1.equalsIgnoreCase(opCode) || iload_2.equalsIgnoreCase(opCode) || iload_3.equalsIgnoreCase(opCode)){
				NoOperandCmd cmd = new NoOperandCmd();
				cmd.setOpCode(opCode);
				cmds.add(cmd);
			} else if(invokespecial.equalsIgnoreCase(opCode)) {
				addTwoOperandCmd(it, cmds, opCode, new InvokeSpecialCmd());
			} else if(putfield.equalsIgnoreCase(opCode)) {
				addTwoOperandCmd(it, cmds, opCode, new PutFieldCmd());
			} else if(getstatic.equalsIgnoreCase(opCode)) {
				addTwoOperandCmd(it, cmds, opCode, new GetStaticCmd());
			} else if(invokevirtual.equalsIgnoreCase(opCode)) {
				addTwoOperandCmd(it, cmds, opCode, new InvokeVirtualCmd());
			} else if(new_object.equalsIgnoreCase(opCode)) {
				addTwoOperandCmd(it, cmds, opCode, new NewObjectCmd());
			} else if(ldc.equalsIgnoreCase(opCode)) {
				addOneOperandCmd(it, cmds, opCode, new LdcCmd());
			} else if(bipush.equalsIgnoreCase(opCode)) {
				addOneOperandCmd(it, cmds, opCode, new BiPushCmd());
			} else {
				throw new RuntimeException("no implements opCode : " + opCode);
			}
		}
		calcuateOffset(cmds);
		return cmds.toArray(new ByteCodeCommand[cmds.size()]);
	}

	private static void addOneOperandCmd(CommandIterator it, List<ByteCodeCommand> cmds, String opCode, OneOperandCmd cmd) {
		cmd.setOpCode(opCode);
		cmd.setOperand(it.next2CharAsInt());
		cmds.add(cmd);
	}

	private static void addTwoOperandCmd(CommandIterator it, List<ByteCodeCommand> cmds, String opCode,
			TwoOperandCmd cmd) {
		cmd.setIndex(it.next4CharAsInt());
		cmd.setOpCode(opCode);
		cmds.add(cmd);
	}
	
	private static void calcuateOffset(List<ByteCodeCommand> cmds) {
		int offset = 0;
		for (ByteCodeCommand cmd : cmds) {
			cmd.setOffset(offset);
			offset += cmd.getLength();
		}
	}
	
	private static class CommandIterator {
		private String code;
		private int index = 0;
		public CommandIterator(String code) {
			super();
			this.code = code;
		}
		public boolean hasNext() {
			return index < code.length();
		}
		public String next2CharAsString() {
			String result = code.substring(index, index + 2);
			index += 2;
			return result;
		}
		public int next2CharAsInt() {
			String str = this.next2CharAsString();
			return hexStrToInt(str);
		}
		public int next4CharAsInt() {
			String s1 = this.next2CharAsString();
			String s2 = this.next2CharAsString();
			return ((hexStrToInt(s1) << 8) | hexStrToInt(s2));
		}
		private int hexStrToInt(String s) {
			return Integer.valueOf(s, 16).intValue();
		}
	}

}
