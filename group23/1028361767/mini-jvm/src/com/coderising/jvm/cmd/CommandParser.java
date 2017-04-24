package com.coderising.jvm.cmd;

import java.util.ArrayList;
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
		List<ByteCodeCommand> cmds = new ArrayList<>();
		CommandIterator iter = new CommandIterator(codes);
		while(iter.hasNext()){
			String opCode = iter.next2CharAsString().toUpperCase();
			if(new_object.equals(opCode)){
				NewObjectCmd newObjCmd = new NewObjectCmd(clzFile, opCode);
				newObjCmd.setOprand1(iter.next2CharAsInt());
				newObjCmd.setOprand2(iter.next2CharAsInt());
				cmds.add(newObjCmd);
			}else if(getfield.equals(opCode)){
				GetFieldCmd getFieldCmd = new GetFieldCmd(clzFile, opCode);
				getFieldCmd.setOprand1(iter.next2CharAsInt());
				getFieldCmd.setOprand2(iter.next2CharAsInt());
				cmds.add(getFieldCmd);
			}else if(getstatic.equals(opCode)){
				GetStaticFieldCmd getStaticFieldCmd = new GetStaticFieldCmd(clzFile, opCode);
				getStaticFieldCmd.setOprand1(iter.next2CharAsInt());
				getStaticFieldCmd.setOprand2(iter.next2CharAsInt());
				cmds.add(getStaticFieldCmd);
			}else if(putfield.equals(opCode)){
				PutFieldCmd putFieldCmd = new PutFieldCmd(clzFile, opCode);
				putFieldCmd.setOprand1(iter.next2CharAsInt());
				putFieldCmd.setOprand2(iter.next2CharAsInt());
				cmds.add(putFieldCmd);
			}else if(invokevirtual.equals(opCode)){
				InvokeVirtualCmd invokeVirtualCmd = new InvokeVirtualCmd(clzFile, opCode);
				invokeVirtualCmd.setOprand1(iter.next2CharAsInt());
				invokeVirtualCmd.setOprand2(iter.next2CharAsInt());
				cmds.add(invokeVirtualCmd);
			}else if(invokespecial.equals(opCode)){
				InvokeSpecialCmd invokeSpecialCmd = new InvokeSpecialCmd(clzFile, opCode);
				invokeSpecialCmd.setOprand1(iter.next2CharAsInt());
				invokeSpecialCmd.setOprand2(iter.next2CharAsInt());
				cmds.add(invokeSpecialCmd);
			}else if(ldc.equals(opCode)){
				LdcCmd ldcCmd = new LdcCmd(clzFile, opCode);
				ldcCmd.setOperand(iter.next2CharAsInt());
				cmds.add(ldcCmd);
			}else if(bipush.equals(opCode)){
				BiPushCmd biPushCmd = new BiPushCmd(clzFile, opCode);
				biPushCmd.setOperand(iter.next2CharAsInt());
				cmds.add(biPushCmd);
			}else if(dup.equals(opCode) || aload_0.equals(opCode)
				|| aload_1.equals(opCode) || aload_2.equals(opCode)
				|| astore_1.equals(opCode) || voidreturn.equals(opCode)
				|| iload.equals(opCode) || iload_1.equals(opCode)
				|| iload_2.equals(opCode) || iload_3.equals(opCode)){
				NoOperandCmd noOpCmd = new NoOperandCmd(clzFile, opCode);
				cmds.add(noOpCmd);
			}else{
				throw new RuntimeException("cmd " + opCode + " has not bean implemented yet.");
			}
		}
		calcuateOffset(cmds);
		ByteCodeCommand[] byteCodeCmds = new ByteCodeCommand[cmds.size()];
		return cmds.toArray(byteCodeCmds);
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
