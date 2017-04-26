package miniJVM.cmd;


import miniJVM.clz.ClassFile;

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
		CommandIterator ci = new CommandIterator(codes);
		List<ByteCodeCommand> cmds = new ArrayList<ByteCodeCommand>();
		while (ci.hasNext()){
			String command = ci.next2CharAsString();
			if(command.equalsIgnoreCase(new_object)){
				NewObjectCmd cmd = new NewObjectCmd(clzFile, command);
				cmd.setOprand1(ci.next2CharAsInt());
				cmd.setOprand2(ci.next2CharAsInt());
				cmds.add(cmd);
			}else if(command.equalsIgnoreCase(invokespecial)){
				InvokeSpecialCmd cmd = new InvokeSpecialCmd(clzFile, command);
				cmd.setOprand1(ci.next2CharAsInt());
				cmd.setOprand2(ci.next2CharAsInt());
				cmds.add(cmd);
			}else if(command.equalsIgnoreCase(invokevirtual)){
				InvokeVirtualCmd cmd = new InvokeVirtualCmd(clzFile, command);
				cmd.setOprand1(ci.next2CharAsInt());
				cmd.setOprand2(ci.next2CharAsInt());
				cmds.add(cmd);
			}else if(command.equalsIgnoreCase(getfield)){
				GetFieldCmd cmd = new GetFieldCmd(clzFile, command);
				cmd.setOprand1(ci.next2CharAsInt());
				cmd.setOprand2(ci.next2CharAsInt());
				cmds.add(cmd);
			}else if(command.equalsIgnoreCase(getstatic)){
				GetStaticFieldCmd cmd = new GetStaticFieldCmd(clzFile, command);
				cmd.setOprand1(ci.next2CharAsInt());
				cmd.setOprand2(ci.next2CharAsInt());
				cmds.add(cmd);
			}else if(command.equalsIgnoreCase(putfield)){
				PutFieldCmd cmd = new PutFieldCmd(clzFile, command);
				cmd.setOprand1(ci.next2CharAsInt());
				cmd.setOprand2(ci.next2CharAsInt());
				cmds.add(cmd);
			}else if(command.equalsIgnoreCase(ldc)){
				LdcCmd cmd = new LdcCmd(clzFile, command);
				cmd.setOperand(ci.next2CharAsInt());
				cmds.add(cmd);
			}else if(command.equalsIgnoreCase(bipush)){
				BiPushCmd cmd = new BiPushCmd(clzFile, command);
				cmd.setOperand(ci.next2CharAsInt());
				cmds.add(cmd);
			}else if (command.equalsIgnoreCase(dup) ||
					command.equalsIgnoreCase(aload_0) ||
					command.equalsIgnoreCase(aload_1) ||
					command.equalsIgnoreCase(aload_2) ||
					command.equalsIgnoreCase(iload_1) ||
					command.equalsIgnoreCase(iload_2) ||
					command.equalsIgnoreCase(iload_3) ||
					command.equalsIgnoreCase(fload_3) ||
					command.equalsIgnoreCase(voidreturn) ||
					command.equalsIgnoreCase(astore_1)) {
				NoOperandCmd cmd = new NoOperandCmd(clzFile, command);
				cmds.add(cmd);
			}else{
				throw new RuntimeException("没有对指令=" + command + "进行处理");
			}
		}
		calculateOffset(cmds);

		ByteCodeCommand[] cmdArr = new ByteCodeCommand[cmds.size()];
		cmds.toArray(cmdArr);
		return cmdArr;
	}

	private static void calculateOffset(List<ByteCodeCommand> cmds) {

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
