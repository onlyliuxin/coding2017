package com.coding.mini_jvm.src.com.coderising.jvm.cmd;

import com.coding.mini_jvm.src.com.coderising.jvm.clz.ClassFile;

import java.util.ArrayList;
import java.util.List;
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
		List<ByteCodeCommand> cmds = new ArrayList<>();
		//2ab7000c2a2bb5000f2a1cb50011b1
		while (cmdIter.hasNext()) {
			String operCode = cmdIter.next2CharAsString().toUpperCase();
			switch (operCode) {
				case bipush:
					BiPushCmd cmd = new BiPushCmd(clzFile, operCode);
					cmd.setOperand(cmdIter.next2CharAsInt());
					cmds.add(cmd);
					break;
				case getfield:
					GetFieldCmd getFieldCmd = new GetFieldCmd(clzFile, operCode);
					getFieldCmd.setOprand1(cmdIter.next2CharAsInt());
					getFieldCmd.setOprand2(cmdIter.next2CharAsInt());
					cmds.add(getFieldCmd);
					break;
				case getstatic:
					GetStaticFieldCmd getStaticFieldCmd = new GetStaticFieldCmd(clzFile, operCode);
					getStaticFieldCmd.setOprand1(cmdIter.next2CharAsInt());
					getStaticFieldCmd.setOprand2(cmdIter.next2CharAsInt());
					cmds.add(getStaticFieldCmd);
					break;
				case invokespecial:
					InvokeSpecialCmd invokeSpecialCmd = new InvokeSpecialCmd(clzFile, operCode);
					invokeSpecialCmd.setOprand1(cmdIter.next2CharAsInt());
					invokeSpecialCmd.setOprand2(cmdIter.next2CharAsInt());
					cmds.add(invokeSpecialCmd);
					break;
				case invokevirtual:
					InvokeVirtualCmd invokeVirtualCmd = new InvokeVirtualCmd(clzFile, operCode);
					invokeVirtualCmd.setOprand1(cmdIter.next2CharAsInt());
					invokeVirtualCmd.setOprand2(cmdIter.next2CharAsInt());
					cmds.add(invokeVirtualCmd);
					break;
				case ldc:
					LdcCmd ldcCmd = new LdcCmd(clzFile, operCode);
					ldcCmd.setOperand(cmdIter.next2CharAsInt());
					cmds.add(ldcCmd);
					break;
				case new_object:
					NewObjectCmd newObjectCmd = new NewObjectCmd(clzFile, operCode);
					newObjectCmd.setOprand1(cmdIter.next2CharAsInt());
					newObjectCmd.setOprand2(cmdIter.next2CharAsInt());
					cmds.add(newObjectCmd);
					break;
				case putfield:
					PutFieldCmd putFieldCmd = new PutFieldCmd(clzFile, operCode);
					putFieldCmd.setOprand1(cmdIter.next2CharAsInt());
					putFieldCmd.setOprand2(cmdIter.next2CharAsInt());
					cmds.add(putFieldCmd);
					break;
				case astore_1:
				case aload_0:
				case aload_1:
				case iload_1:
				case iload_2:
				case istore_1:
				case voidreturn:
				case dup:
					NoOperandCmd noOperandCmd = new NoOperandCmd(clzFile, operCode);
					cmds.add(noOperandCmd);
					break;
				default:
					throw new RuntimeException("this oper [ " +operCode+ " ]not impl yet");
			}
		}
		calcuateOffset(cmds);
		return cmds.toArray(new ByteCodeCommand[cmds.size()]);
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
