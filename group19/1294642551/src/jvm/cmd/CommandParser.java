package jvm.cmd;

import java.util.ArrayList;
import java.util.List;

import jvm.clz.ClassFile;

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
		if(codes == null || codes.length()== 0){
			throw new RuntimeException("code 代码不正确");
		}
		
		codes = codes.toUpperCase();
		CommandIterator iter = new CommandIterator(codes);
		ArrayList<ByteCodeCommand> cmds = new ArrayList<ByteCodeCommand>();

		while(iter.hasNext()){
			String opCode = iter.next2CharAsString();
			int cmdsLen = cmds.size();
			addNoOperandCmd(clzFile, cmds, opCode);
			addOneOperandCmd(clzFile, iter, cmds, opCode);
			addTwoOperandCmd(clzFile, iter, cmds, opCode);
			
			if(cmds.size() == cmdsLen){//说明opCode没有加入进cmds
				throw new RuntimeException("oprate code: "+opCode+"未实现");
			}

		}
		
		calcuateOffset(cmds);//计算偏移
		ByteCodeCommand[] result = new ByteCodeCommand[cmds.size()];
		cmds.toArray(result);
		
		
		return result;
	}

	public static void addNoOperandCmd(ClassFile clzFile,
			ArrayList<ByteCodeCommand> cmds, String opCode ) {
		NoOperandCmd noOperandCmd = null;
		if(opCode.equals(aload_0) || opCode.equals(aload_1) || opCode.equals(aload_2) 
				|| opCode.equals(iload_1) || opCode.equals(iload_2) ||opCode.equals(iload_3) 
				|| opCode.equals(fload_3) || opCode.equals(voidreturn) || opCode.equals(dup) 
				|| opCode.equals(astore_1)){
			noOperandCmd = new NoOperandCmd(clzFile, opCode);
			cmds.add(noOperandCmd);
		}
	}

	public static void addOneOperandCmd(ClassFile clzFile,
			CommandIterator iter, ArrayList<ByteCodeCommand> cmds, String opCode ) {
		OneOperandCmd oneOperandCmd = null;
		boolean flag = false;
		if(bipush.equals(opCode)){
			oneOperandCmd = new BiPushCmd(clzFile, opCode);
			flag = true;
		}else if(ldc.equals(opCode)){
			oneOperandCmd = new LdcCmd(clzFile, opCode);
			flag = true;
		}
		if(flag){
			oneOperandCmd.setOperand(iter.next2CharAsInt());
			cmds.add(oneOperandCmd);
		}
	}

	public static void addTwoOperandCmd(ClassFile clzFile,
			CommandIterator iter, ArrayList<ByteCodeCommand> cmds, String opCode) {
		TwoOperandCmd twoOperandCmd = null;
		boolean flag = false;
		if(new_object.equals(opCode)){
			twoOperandCmd = new NewObjectCmd(clzFile, opCode);
			flag = true;

		}else if(invokespecial.equals(opCode)){
			twoOperandCmd = new InvokeSpecialCmd(clzFile, opCode);
			flag = true;
			
		}else if(invokevirtual.equals(opCode)){
			twoOperandCmd = new InvokeVirtualCmd(clzFile, opCode);
			flag = true;
			
		}else if(getfield.equals(opCode)){
			twoOperandCmd = new GetFieldCmd(clzFile, opCode);
			flag = true;
			
		}else if(getstatic.equals(opCode)){
			twoOperandCmd = new GetStaticFieldCmd(clzFile, opCode);
			flag = true;
			
		}else if(putfield.equals(opCode)){
			twoOperandCmd = new PutFieldCmd(clzFile, opCode);
			flag = true;
			
		}
		
		if(flag){
			twoOperandCmd.setOprand1(iter.next2CharAsInt());
			twoOperandCmd.setOprand2(iter.next2CharAsInt());
			cmds.add(twoOperandCmd);
		}
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
