package com.coderising.jvm.cmd;

import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.engine.ExecutionResult;
import com.coderising.jvm.engine.StackFrame;

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
		ArrayList<ByteCodeCommand> list=new ArrayList<>();
		CommandIterator iterator=new CommandIterator(codes);
		while (iterator.hasNext()) {
			String opCode=iterator.next2CharAsString();
			if (opCode.equalsIgnoreCase(aload_0)) {
				list.add(new LoadCmd(clzFile, opCode));				
			}
			else if (opCode.equalsIgnoreCase(invokespecial)) {
				InvokeSpecialCmd invokeSpecialCmd=new InvokeSpecialCmd(clzFile, opCode);
				invokeSpecialCmd.setOprand1(iterator.next2CharAsInt());
				invokeSpecialCmd.setOprand2(iterator.next2CharAsInt());
				list.add(invokeSpecialCmd);
				
			}
			else if (opCode.equalsIgnoreCase(aload_1)) {
				list.add(new LoadCmd(clzFile, opCode));
			}
			else if (opCode.equalsIgnoreCase(putfield)){
				PutFieldCmd putFieldCmd=new PutFieldCmd(clzFile, opCode);
				putFieldCmd.setOprand1(iterator.next2CharAsInt());
				putFieldCmd.setOprand2(iterator.next2CharAsInt());
				list.add(putFieldCmd);
				
			}
			else if (opCode.equalsIgnoreCase(iload_2)) {
				list.add(new LoadCmd(clzFile, opCode));
			}
			else if (opCode.equalsIgnoreCase(new_object)) {
				NewObjectCmd newObjectCmd=new NewObjectCmd(clzFile, opCode);
				newObjectCmd.setOprand1(iterator.next2CharAsInt());
				newObjectCmd.setOprand2(iterator.next2CharAsInt());
				list.add(newObjectCmd);
			}
			else if (opCode.equalsIgnoreCase(dup)) {
				
				list.add(new DupCmd(clzFile, opCode));
			}
			else if (opCode.equalsIgnoreCase(bipush)) {
				BiPushCmd biPushCmd=new BiPushCmd(clzFile, opCode);
				biPushCmd.setOperand(iterator.next2CharAsInt());		
				list.add(biPushCmd);
			}
			else if (opCode.equalsIgnoreCase(invokevirtual)) {
				InvokeVirtualCmd invokeVirtualCmd=new InvokeVirtualCmd(clzFile, opCode);
				invokeVirtualCmd.setOprand1(iterator.next2CharAsInt());
				invokeVirtualCmd.setOprand2(iterator.next2CharAsInt());
				list.add(invokeVirtualCmd);
			}
			else if (opCode.equalsIgnoreCase(voidreturn)) {
				list.add(new VoidReturnCmd(clzFile, opCode));
			}
			else if (opCode.equalsIgnoreCase(getstatic)) {
				GetStaticFieldCmd getStaticFieldCmd=new GetStaticFieldCmd(clzFile, opCode);
				getStaticFieldCmd.setOprand1(iterator.next2CharAsInt());
				getStaticFieldCmd.setOprand2(iterator.next2CharAsInt());
				list.add(getStaticFieldCmd);
			}
			else if (opCode.equalsIgnoreCase(getfield)) {
				GetFieldCmd getFieldCmd=new GetFieldCmd(clzFile, opCode);
				getFieldCmd.setOprand1(iterator.next2CharAsInt());
				getFieldCmd.setOprand2(iterator.next2CharAsInt());
				list.add(getFieldCmd);
			}
			else if (opCode.equalsIgnoreCase(ldc)) {
				LdcCmd ldcCmd=new LdcCmd(clzFile, opCode);
				ldcCmd.setOperand(iterator.next2CharAsInt());
				list.add(ldcCmd);
			}
			else if (opCode.equalsIgnoreCase(astore_1)) {
				list.add(new AstoreCmd(clzFile, opCode));
			}
		}
		calcuateOffset(list);
		ByteCodeCommand [] byteCodeCommands=new ByteCodeCommand [list.size()];
		for (int i = 0; i < byteCodeCommands.length; i++) {
			byteCodeCommands[i]=list.get(i);
		}
		return byteCodeCommands;
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
