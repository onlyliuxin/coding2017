package com.coderising.jvm.cmd;

import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.clz.ClassFile;

public class CommandParser {

	public static ByteCodeCommand[] parse(ClassFile clzFile, String codes) {
		List<ByteCodeCommand> cmds = new ArrayList<ByteCodeCommand>();
		CommandIterator iter = new CommandIterator(codes.toUpperCase());
		while (iter.hasNext()) {
			String opCode = iter.next2CharAsString();
			if (ByteCodeCommand.new_object.equals(opCode)) {
				NewObjectCmd cmd = new NewObjectCmd(clzFile, opCode);

				cmd.setOprand1(iter.next2CharAsInt());
				cmd.setOprand2(iter.next2CharAsInt());

				cmds.add(cmd);
			} else if (ByteCodeCommand.invokespecial.equals(opCode)) {
				InvokeSpecialCmd cmd = new InvokeSpecialCmd(clzFile, opCode);
				cmd.setOprand1(iter.next2CharAsInt());
				cmd.setOprand2(iter.next2CharAsInt());
				// System.out.println( cmd.toString(clzFile.getConstPool()));
				cmds.add(cmd);
			} else if (ByteCodeCommand.invokevirtual.equals(opCode)) {
				InvokeVirtualCmd cmd = new InvokeVirtualCmd(clzFile, opCode);
				cmd.setOprand1(iter.next2CharAsInt());
				cmd.setOprand2(iter.next2CharAsInt());

				cmds.add(cmd);
			} else if (ByteCodeCommand.getfield.equals(opCode)) {
				GetFieldCmd cmd = new GetFieldCmd(clzFile, opCode);
				cmd.setOprand1(iter.next2CharAsInt());
				cmd.setOprand2(iter.next2CharAsInt());
				cmds.add(cmd);
			} else if (ByteCodeCommand.getstatic.equals(opCode)) {
				GetStaticFieldCmd cmd = new GetStaticFieldCmd(clzFile, opCode);
				cmd.setOprand1(iter.next2CharAsInt());
				cmd.setOprand2(iter.next2CharAsInt());
				cmds.add(cmd);
			} else if (ByteCodeCommand.putfield.equals(opCode)) {
				PutFieldCmd cmd = new PutFieldCmd(clzFile, opCode);
				cmd.setOprand1(iter.next2CharAsInt());
				cmd.setOprand2(iter.next2CharAsInt());
				cmds.add(cmd);
			} else if (ByteCodeCommand.ldc.equals(opCode)) {
				LdcCmd cmd = new LdcCmd(clzFile, opCode);
				cmd.setOperand(iter.next2CharAsInt());
				cmds.add(cmd);
			} else if (ByteCodeCommand.bipush.equals(opCode)) {
				BiPushCmd cmd = new BiPushCmd(clzFile, opCode);
				cmd.setOperand(iter.next2CharAsInt());
				cmds.add(cmd);
			} else if (ByteCodeCommand.dup.equals(opCode) || ByteCodeCommand.aload_0.equals(opCode) || ByteCodeCommand.aload_1.equals(opCode) || ByteCodeCommand.aload_2.equals(opCode)
					|| ByteCodeCommand.iload_1.equals(opCode) || ByteCodeCommand.iload_2.equals(opCode) || ByteCodeCommand.iload_3.equals(opCode)
					|| ByteCodeCommand.fload_3.equals(opCode) || ByteCodeCommand.voidreturn.equals(opCode) || ByteCodeCommand.astore_1.equals(opCode)) {

				NoOperandCmd cmd = new NoOperandCmd(clzFile, opCode);
				cmds.add(cmd);
			} else {
				throw new RuntimeException("Sorry, the java instruction " + opCode + " has not been implemented");
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
