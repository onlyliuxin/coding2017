package minijvm.cmd;

import java.util.ArrayList;
import java.util.List;

import minijvm.clz.ClassFile;

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
	    List<ByteCodeCommand> commandList = new ArrayList<>();
	    
	    CommandIterator iter = new CommandIterator(codes);
	    while (iter.hasNext()) {
	        String operCode = iter.next2CharAsString();
	        if (aload_0.equalsIgnoreCase(operCode)) {
	            NoOperandCmd cmd = new NoOperandCmd(clzFile, operCode);
	            commandList.add(cmd);
	        } else if (invokespecial.equalsIgnoreCase(operCode)) {
	            InvokeSpecialCmd cmd = new InvokeSpecialCmd(clzFile, operCode);
	            cmd.setOprand1(iter.next2CharAsInt());
	            cmd.setOprand2(iter.next2CharAsInt());
	            commandList.add(cmd);
	        } else if (aload_1.equalsIgnoreCase(operCode)) {
	            NoOperandCmd cmd = new NoOperandCmd(clzFile, operCode);
	            commandList.add(cmd);
	        } else if (putfield.equalsIgnoreCase(operCode)) {
	            PutFieldCmd cmd = new PutFieldCmd(clzFile, operCode);
	            cmd.setOprand1(iter.next2CharAsInt());
	            cmd.setOprand2(iter.next2CharAsInt());
	            commandList.add(cmd);
	        } else if (aload_2.equalsIgnoreCase(operCode)) {
	            NoOperandCmd cmd = new NoOperandCmd(clzFile, operCode);
	            commandList.add(cmd);
	        } else if (ireturn.equalsIgnoreCase(operCode)) {
	            NoOperandCmd cmd = new NoOperandCmd(clzFile, operCode);
	            commandList.add(cmd);
	        } else if (voidreturn.equalsIgnoreCase(operCode)) {
	            NoOperandCmd cmd = new NoOperandCmd(clzFile, operCode);
	            commandList.add(cmd);
	        } else if (getstatic.equalsIgnoreCase(operCode)) {
	            GetStaticFieldCmd cmd = new GetStaticFieldCmd(clzFile, operCode);
	            cmd.setOprand1(iter.next2CharAsInt());
	            cmd.setOprand2(iter.next2CharAsInt());
	            commandList.add(cmd);
	        } else if (ldc.equalsIgnoreCase(operCode)) {
	            LdcCmd cmd = new LdcCmd(clzFile, operCode);
	            cmd.setOperand(iter.next2CharAsInt());
	            commandList.add(cmd);
	        } else if (invokevirtual.equalsIgnoreCase(operCode)) {
	            InvokeVirtualCmd cmd = new InvokeVirtualCmd(clzFile, operCode);
	            cmd.setOprand1(iter.next2CharAsInt());
	            cmd.setOprand2(iter.next2CharAsInt());
	            commandList.add(cmd);
	        } else if (new_object.equalsIgnoreCase(operCode)) {
	            NewObjectCmd cmd = new NewObjectCmd(clzFile, operCode);
	            cmd.setOprand1(iter.next2CharAsInt());
	            cmd.setOprand2(iter.next2CharAsInt());
	            commandList.add(cmd);
	        } else if (dup.equalsIgnoreCase(operCode)) {
	            NoOperandCmd cmd = new NoOperandCmd(clzFile, operCode);
	            commandList.add(cmd);
	        } else if (bipush.equalsIgnoreCase(operCode)) {
	            BiPushCmd cmd = new BiPushCmd(clzFile, operCode);
	            cmd.setOperand(iter.next2CharAsInt());
	            commandList.add(cmd);
	        } else if (astore_1.equalsIgnoreCase(operCode)) {
	            NoOperandCmd cmd = new NoOperandCmd(clzFile, operCode);
	            commandList.add(cmd);
	        } else if (iload_1.equalsIgnoreCase(operCode)) {
	            NoOperandCmd cmd = new NoOperandCmd(clzFile, operCode);
	            commandList.add(cmd);
	        } else if (iload_2.equalsIgnoreCase(operCode)) {
	            NoOperandCmd cmd = new NoOperandCmd(clzFile, operCode);
                commandList.add(cmd);
	        } else {
	            throw new RuntimeException("未实现的操作码：" + operCode);
	        }
	    }
	    calcuateOffset(commandList);
	    ByteCodeCommand[] commands = new ByteCodeCommand[commandList.size()];
		return commandList.toArray(commands);
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
		
		public void nextLengthByte(int length) {
		    pos += (length * 2);
		}

	}
}
