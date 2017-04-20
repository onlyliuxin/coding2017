package com.github.ipk2015.coding2017.minijvm.cmd;

import java.util.ArrayList;
import java.util.List;

import com.github.ipk2015.coding2017.minijvm.clz.ClassFile;

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
		if(null == codes || codes.length() == 0){
			throw new RuntimeException("字节码不存在");
		}
		codes = codes.toUpperCase();
		CommandIterator iterator = new CommandIterator(codes);
		List<ByteCodeCommand> commands = new ArrayList<ByteCodeCommand>();
		while(iterator.hasNext()){
			String operatorCode = iterator.next2CharAsString(); 
			if(ldc.equals(operatorCode)){
				LdcCmd cmd = new LdcCmd(clzFile,operatorCode);
				cmd.setOperand(iterator.next2CharAsInt());
				commands.add(cmd);
			}else if(bipush.equals(operatorCode)){
				BiPushCmd cmd = new BiPushCmd(clzFile,operatorCode);
				cmd.setOperand(iterator.next2CharAsInt());
				commands.add(cmd);
			}else if(new_object.equals(operatorCode)){
				NewObjectCmd cmd = new NewObjectCmd(clzFile,operatorCode);
				cmd.setOprand1(iterator.next2CharAsInt());
				cmd.setOprand2(iterator.next2CharAsInt());
				commands.add(cmd);
			}else if(invokespecial.equals(operatorCode)){
				InvokeSpecialCmd cmd = new InvokeSpecialCmd(clzFile,operatorCode);
				cmd.setOprand1(iterator.next2CharAsInt());
				cmd.setOprand2(iterator.next2CharAsInt());
				commands.add(cmd);
			}else if(invokevirtual.equals(operatorCode)){
				InvokeVirtualCmd cmd = new InvokeVirtualCmd(clzFile,operatorCode);
				cmd.setOprand1(iterator.next2CharAsInt());
				cmd.setOprand2(iterator.next2CharAsInt());
				commands.add(cmd);
			}else if(putfield.equals(operatorCode)){
				PutFieldCmd cmd = new PutFieldCmd(clzFile,operatorCode);
				cmd.setOprand1(iterator.next2CharAsInt());
				cmd.setOprand2(iterator.next2CharAsInt());
				commands.add(cmd);
			}else if(getfield.equals(operatorCode)){
				GetFieldCmd cmd = new GetFieldCmd(clzFile,operatorCode);
				cmd.setOprand1(iterator.next2CharAsInt());
				cmd.setOprand2(iterator.next2CharAsInt());
				commands.add(cmd);
			}else if(getstatic.equals(operatorCode)){
				GetStaticFieldCmd cmd = new GetStaticFieldCmd(clzFile,operatorCode);
				cmd.setOprand1(iterator.next2CharAsInt());
				cmd.setOprand2(iterator.next2CharAsInt());
				commands.add(cmd);
			}else if(dup.equals(operatorCode) || aload_0.equals(operatorCode) || aload_1.equals(operatorCode) || 
					aload_2.equals(operatorCode) || astore_1.equals(operatorCode) || voidreturn.equals(operatorCode)
					|| iload.equals(operatorCode) || iload_1.equals(operatorCode) || iload_2.equals(operatorCode)
					|| iload_3.equals(operatorCode) || fload_3.equals(operatorCode) || iconst_0.equals(operatorCode)
					|| iconst_1.equals(operatorCode) || istore_1.equals(operatorCode) || istore_2.equals(operatorCode)
					|| iadd.equals(operatorCode)|| iinc.equals(operatorCode)){
				NoOperandCmd command = new NoOperandCmd(clzFile,operatorCode);
				commands.add(command);
			}else{
				throw new RuntimeException("this operator code not includes yet:"+operatorCode);
			}
		}
		calcuateOffset(commands);
		ByteCodeCommand[] result = new ByteCodeCommand[commands.size()];
		return commands.toArray(result);
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
