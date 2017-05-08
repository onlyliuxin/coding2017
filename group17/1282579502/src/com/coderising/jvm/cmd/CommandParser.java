package com.coderising.jvm.cmd;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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
	
	public static List<LocalByteCmd> templateCmd= null;
	static{
		templateCmd = constructCmdTemplates();
	}

	public static ByteCodeCommand[] parse(ClassFile clzFile, String codes) {
		CommandIterator iter = new CommandIterator(codes);
		//System.out.println("codes: " + codes);
		List<ByteCodeCommand> cmdList = new LinkedList<>();
		while(iter.hasNext()){
			String opCode = iter.next2CharAsString();
			//System.out.println("current opCode: " + opCode);
			Optional<ByteCodeCommand> bcc = templateCmd.stream().map(cmd ->{
				return cmd.generateCmd(opCode, iter, clzFile);
			}).filter(item -> item != null).findFirst();
			if(bcc.isPresent()){
				cmdList.add(bcc.get());
			}
			else{
				System.err.println("Unrecognized operation Code: " + opCode);
			}
		}
		
		calcuateOffset(cmdList);
		
//		cmdList.forEach(cmd->{
//			System.out.println(cmd.toString(clzFile.getConstantPool()));
//		});
		ByteCodeCommand[] bccArray = new ByteCodeCommand[cmdList.size()];
		return cmdList.toArray(bccArray);
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
			//System.out.println("next2CharAsInt: " + s);
			return Integer.valueOf(s, 16).intValue();
		}

	}
	
	private interface LocalByteCmd{
		public ByteCodeCommand generateCmd(String operationCode, CommandIterator iter, ClassFile clz);
	}
	
	public static List<LocalByteCmd> constructCmdTemplates(){
		List<LocalByteCmd> cmds = new LinkedList<>();
		LocalByteCmd aconst_null_cmd = (oc, iter, clz) ->{
			if(oc.equalsIgnoreCase(aconst_null)){
				NoOperandCmd bcc = new NoOperandCmd(clz, oc);
				return bcc;
			}
			return null;
		};
		cmds.add(aconst_null_cmd);
		
		LocalByteCmd new_object_cmd = (oc, iter, clz) ->{
			if(oc.equalsIgnoreCase(new_object)){
				NewObjectCmd bcc = new NewObjectCmd(clz, oc);
				bcc.setOprand1(iter.next2CharAsInt());
				bcc.setOprand2(iter.next2CharAsInt());
				return bcc;
			}
			return null;
		};
		cmds.add(new_object_cmd);
		
		LocalByteCmd invokespecial_cmd = (oc, iter, clz) ->{
			if(oc.equalsIgnoreCase(invokespecial)){
				InvokeSpecialCmd bcc = new InvokeSpecialCmd(clz, oc);
				bcc.setOprand1(iter.next2CharAsInt());
				bcc.setOprand2(iter.next2CharAsInt());
				return bcc;
			}
			return null;
		};
		cmds.add(invokespecial_cmd);
		
		LocalByteCmd aload_0_cmd = (oc, iter, clz) ->{
			if(oc.equalsIgnoreCase(aload_0)){
				NoOperandCmd bcc = new NoOperandCmd(clz, oc);
				return bcc;
			}
			return null;
		};
		cmds.add(aload_0_cmd);
		
		LocalByteCmd aload_1_cmd = (oc, iter, clz) ->{
			if(oc.equalsIgnoreCase(aload_1)){
				NoOperandCmd bcc = new NoOperandCmd(clz, oc);
				return bcc;
			}
			return null;
		};
		cmds.add(aload_1_cmd);
		
		LocalByteCmd putField_cmd = (oc, iter, clz) ->{
			if(oc.equalsIgnoreCase(putfield)){
				PutFieldCmd bcc = new PutFieldCmd(clz, oc);
				bcc.setOprand1(iter.next2CharAsInt());
				bcc.setOprand2(iter.next2CharAsInt());
				return bcc;
			}
			return null;
		};
		cmds.add(putField_cmd);
		
		LocalByteCmd iload_1_cmd = (oc, iter, clz) ->{
			if(oc.equalsIgnoreCase(iload_1)){
				NoOperandCmd bcc = new NoOperandCmd(clz, oc);
				return bcc;
			}
			return null;
		};
		cmds.add(iload_1_cmd);
		
		LocalByteCmd iload_2_cmd = (oc, iter, clz) ->{
			if(oc.equalsIgnoreCase(iload_2)){
				NoOperandCmd bcc = new NoOperandCmd(clz, oc);
				return bcc;
			}
			return null;
		};
		cmds.add(iload_2_cmd);
		
		LocalByteCmd voidreturn_cmd = (oc, iter, clz) ->{
			if(oc.equalsIgnoreCase(voidreturn)){
				NoOperandCmd bcc = new NoOperandCmd(clz, oc);
				return bcc;
			}
			return null;
		};
		cmds.add(voidreturn_cmd);

		LocalByteCmd ldc_cmd = (oc, iter, clz) ->{
			if(oc.equalsIgnoreCase(ldc)){
				LdcCmd bcc = new LdcCmd(clz, oc);
				bcc.setOperand(iter.next2CharAsInt());
				return bcc;
			}
			return null;
		};
		cmds.add(ldc_cmd);
		
		LocalByteCmd getstatic_cmd = (oc, iter, clz) ->{
			if(oc.equalsIgnoreCase(getstatic)){
				GetStaticFieldCmd bcc = new GetStaticFieldCmd(clz, oc);
				bcc.setOprand1(iter.next2CharAsInt());
				bcc.setOprand2(iter.next2CharAsInt());
				return bcc;
			}
			return null;
		};
		cmds.add(getstatic_cmd);
		
		LocalByteCmd invokevirtual_cmd = (oc, iter, clz) ->{
			if(oc.equalsIgnoreCase(invokevirtual)){
				InvokeVirtualCmd bcc = new InvokeVirtualCmd(clz, oc);
				bcc.setOprand1(iter.next2CharAsInt());
				bcc.setOprand2(iter.next2CharAsInt());
				return bcc;
			}
			return null;
		};
		cmds.add(invokevirtual_cmd);
		
//    	assertOpCodeEquals("0: new #1", cmds[0]);
//    	assertOpCodeEquals("3: dup", cmds[1]);
//    	assertOpCodeEquals("4: ldc #43", cmds[2]);
//    	assertOpCodeEquals("6: bipush 29", cmds[3]);
//    	assertOpCodeEquals("8: invokespecial #45", cmds[4]);
//    	assertOpCodeEquals("11: astore_1", cmds[5]);
//    	assertOpCodeEquals("12: aload_1", cmds[6]);
//    	assertOpCodeEquals("13: invokevirtual #47", cmds[7]);
//    	assertOpCodeEquals("16: return", cmds[8]);
		LocalByteCmd astore_1_cmd = (oc, iter, clz) ->{
			if(oc.equalsIgnoreCase(astore_1)){
				NoOperandCmd bcc = new NoOperandCmd(clz, oc);
				return bcc;
			}
			return null;
		};
		cmds.add(astore_1_cmd);
		
		LocalByteCmd dump_cmd = (oc, iter, clz) ->{
			if(oc.equalsIgnoreCase(dup)){
				NoOperandCmd bcc = new NoOperandCmd(clz, oc);
				return bcc;
			}
			return null;
		};
		cmds.add(dump_cmd);
		
		LocalByteCmd bipush_cmd = (oc, iter, clz) ->{
			if(oc.equalsIgnoreCase(bipush)){
				BiPushCmd bcc = new BiPushCmd(clz, oc);
				bcc.setOperand(iter.next2CharAsInt());
				
				return bcc;
			}
			return null;
		};
		cmds.add(bipush_cmd);
		
		return cmds;
	}
}
