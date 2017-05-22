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
		CommandIterator iter = new CommandIterator(codes);
		ArrayList<ByteCodeCommand> al = new ArrayList<ByteCodeCommand>();
		while(iter.hasNext()){
			String cmdCode = iter.next2CharAsString().toUpperCase();
			ByteCodeCommand cmd = null;
			
			if(ldc.equals(cmdCode)){
				cmd = new LdcCmd(clzFile,cmdCode);
				((LdcCmd)cmd).setOperand(iter.next2CharAsInt());
			}else if(bipush.equals(cmdCode)){
				cmd = new BiPushCmd(clzFile,cmdCode);
				((BiPushCmd)cmd).setOperand(iter.next2CharAsInt());
			}else if(getfield.equals(cmdCode)){
				cmd = new GetFieldCmd(clzFile,cmdCode);
				((GetFieldCmd)cmd).setOprand1(iter.next2CharAsInt());
				((GetFieldCmd)cmd).setOprand2(iter.next2CharAsInt());
			}else if(getstatic.equals(cmdCode)){
				cmd = new GetStaticFieldCmd(clzFile,cmdCode);
				((GetStaticFieldCmd)cmd).setOprand1(iter.next2CharAsInt());
				((GetStaticFieldCmd)cmd).setOprand2(iter.next2CharAsInt());
			}else if(invokespecial.equals(cmdCode)){
				cmd = new InvokeSpecialCmd(clzFile,cmdCode);
				((InvokeSpecialCmd)cmd).setOprand1(iter.next2CharAsInt());
				((InvokeSpecialCmd)cmd).setOprand2(iter.next2CharAsInt());
			}else if(invokevirtual.equals(cmdCode)){
				cmd = new InvokeVirtualCmd(clzFile,cmdCode);
				((InvokeVirtualCmd)cmd).setOprand1(iter.next2CharAsInt());
				((InvokeVirtualCmd)cmd).setOprand2(iter.next2CharAsInt());
			}else if(new_object.equals(cmdCode)){
				cmd = new NewObjectCmd(clzFile,cmdCode);
				((NewObjectCmd)cmd).setOprand1(iter.next2CharAsInt());
				((NewObjectCmd)cmd).setOprand2(iter.next2CharAsInt());
			}else if(putfield.equals(cmdCode)){
				cmd = new PutFieldCmd(clzFile,cmdCode);
				((PutFieldCmd)cmd).setOprand1(iter.next2CharAsInt());
				((PutFieldCmd)cmd).setOprand2(iter.next2CharAsInt());
			}else if(aconst_null.equals(cmdCode)||
					lstore.equals(cmdCode)||
					dup.equals(cmdCode)||
					aload_0.equals(cmdCode)||
					aload_1.equals(cmdCode)||
					aload_2.equals(cmdCode)||
					iload.equals(cmdCode)||
					iload_1.equals(cmdCode)||
					iload_2.equals(cmdCode)||
					iload_3.equals(cmdCode)||
					fload_3.equals(cmdCode)||
					voidreturn.equals(cmdCode)||
					ireturn.equals(cmdCode)||
					freturn.equals(cmdCode)||
					astore_1.equals(cmdCode)||
					if_icmp_ge.equals(cmdCode)||
					if_icmple.equals(cmdCode)||
					goto_no_condition.equals(cmdCode)||
					iconst_0.equals(cmdCode)||
					iconst_1.equals(cmdCode)||
					istore_1.equals(cmdCode)||
					istore_2.equals(cmdCode)||
					iadd.equals(cmdCode)||
					iinc.equals(cmdCode))
			{
				cmd = new NoOperandCmd(clzFile,cmdCode);
			}else{
				throw new RuntimeException("Not Supported Cmd Code:"+cmdCode);
			}
			al.add(cmd);
		}
		calcuateOffset(al);
		return al.toArray(new ByteCodeCommand[al.size()]);
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
