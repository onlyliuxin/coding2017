package com.coderising.jvm.cmd;

import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.clz.ClassFile;
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

	public static ByteCodeCommand[] parse(ClassFile clzFile, String codes) {
		if (codes == null || codes.length() <= 0) {
			throw new RuntimeException("字节码指令解析错误!");
		}
		List<ByteCodeCommand> cmds = new ArrayList<ByteCodeCommand>();
		CommandIterator iter = new CommandIterator(codes);
		while (iter.hasNext()) {
			String opcmd = iter.next2CharAsString();
			System.out.println("opcmd:" + opcmd);
			if (bipush.equalsIgnoreCase(opcmd)) {
				BiPushCmd cmd = new BiPushCmd(clzFile, opcmd);
				cmd.setOperand(iter.next2CharAsInt());
				cmds.add(cmd);
			} else if (getfield.equalsIgnoreCase(opcmd)) {
				GetFieldCmd cmd = new GetFieldCmd(clzFile, opcmd);
				cmd.setOprand1(iter.next2CharAsInt());
				cmd.setOprand2(iter.next2CharAsInt());
				cmds.add(cmd);
			} else if (getstatic.equalsIgnoreCase(opcmd)) {
				GetStaticFieldCmd cmd = new GetStaticFieldCmd(clzFile, opcmd);
				cmd.setOprand1(iter.next2CharAsInt());
				cmd.setOprand2(iter.next2CharAsInt());
				cmds.add(cmd);
			} else if (invokespecial.equalsIgnoreCase(opcmd)) {
				InvokeSpecialCmd cmd = new InvokeSpecialCmd(clzFile, opcmd);
				cmd.setOprand1(iter.next2CharAsInt());
				cmd.setOprand2(iter.next2CharAsInt());
				cmds.add(cmd);
			} else if (invokevirtual.equalsIgnoreCase(opcmd)) {
				InvokeVirtualCmd cmd = new InvokeVirtualCmd(clzFile, opcmd);
				cmd.setOprand1(iter.next2CharAsInt());
				cmd.setOprand2(iter.next2CharAsInt());
				cmds.add(cmd);
			} else if (ldc.equalsIgnoreCase(opcmd)) {
				LdcCmd cmd = new LdcCmd(clzFile, opcmd);
				cmd.setOperand(iter.next2CharAsInt());
				cmds.add(cmd);
			} else if (new_object.equalsIgnoreCase(opcmd)) {
				NewObjectCmd cmd = new NewObjectCmd(clzFile, opcmd);
				cmd.setOprand1(iter.next2CharAsInt());
				cmd.setOprand2(iter.next2CharAsInt());
				cmds.add(cmd);
			} else if (putfield.equalsIgnoreCase(opcmd)) {
				PutFieldCmd cmd = new PutFieldCmd(clzFile, opcmd);
				cmd.setOprand1(iter.next2CharAsInt());
				cmd.setOprand2(iter.next2CharAsInt());
				cmds.add(cmd);
			} else if (dup.equalsIgnoreCase(opcmd) || aload_0.equalsIgnoreCase(opcmd) || aload_1.equalsIgnoreCase(opcmd)
					|| aload_2.equalsIgnoreCase(opcmd) || iload_1.equalsIgnoreCase(opcmd)
					|| iload_2.equalsIgnoreCase(opcmd) || iload_3.equalsIgnoreCase(opcmd)
					|| fload_3.equalsIgnoreCase(opcmd) || voidreturn.equalsIgnoreCase(opcmd)
					|| astore_1.equalsIgnoreCase(opcmd)) {
				NoOperandCmd cmd = new NoOperandCmd(clzFile, opcmd);
				cmds.add(cmd);
			} else {
				throw new RuntimeException("暂不支持的指令类型!");
			}
		}
		calcuateOffset(cmds);
		ByteCodeCommand[] result = new ByteCodeCommand[cmds.size()];
		cmds.toArray(result);
		return result;
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
