package com.coderising.jvm.cmd;

import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.clz.ClassFile;

public class CommandParser {
		
	public static final String aconst_null = "01";// 将 null推送至栈顶
	public static final String new_object = "BB";// 创建一个对象，并将其引用值压入栈顶,后面的两个字节用于构建索引，该索引执行类或接口的符号引用
	public static final String lstore = "37";// 将栈顶 long 型数值存入 指定本地变量
	public static final String invokespecial = "B7";// 调用超类构造方法，实例初始化方法，私有方法,后面跟两个byte，用于构建当前类运行时的常量池的索引，指向了运行时常量池的一个方法的符号引用
	public static final String invokevirtual = "B6";// 调用实例方法，后面的两个字节用于构建索引，该索引指向一个方法的符号引用
	public static final String getfield = "B4";//获取指定类的实例域，并将其压入栈顶
	public static final String putfield = "B5";// 为指定的类的实例域赋值,后面的两个字节用于构建一个索引，这个索引指向了运行时字段的一个符号引用，该引用包含了字段的名称和描述符，以及该字段的类的符号引用
	public static final String getstatic = "B2";// 获取字段的静态字段值
	public static final String ldc = "12";// 将 int、 float 或String 型常量值从常量池中推送至栈顶，后面的一个字节是一个索引，该索引指向了int或者float类型的常量
	public static final String dup = "59";// 赋值操作数栈栈顶的值，并压入操作数栈
	public static final String bipush = "10";// 将 byte 带符号扩展为一个 int 类型的值 value，然后将value压入到操作数栈，后面的一个字节是value
	public static final String aload_0 = "2A";// 将第一个引用类型本地变量推送至栈顶
	public static final String aload_1 = "2B";// 将第二个本地变量表中引用数据类型的值压入操作数栈,后面无操作数
	public static final String aload_2 = "2C";
	public static final String iload = "15";
	public static final String iload_1 = "1B";// 将第二个int型本地变量推送至栈顶
	public static final String iload_2 = "1C";
	public static final String iload_3 = "1D";
	public static final String fload_3 = "25";// 将第四个float型本地变量表推送至栈顶

	public static final String voidreturn = "B1";// 无返回值的返回
	public static final String ireturn = "AC";// 从当前方法返回 int
	public static final String freturn = "AE";// 从当前方法返回 float

	public static final String astore_1 = "4C";//将栈顶的引用数据类型存入第二个局部变量，是一个NoOperand
	public static final String if_icmp_ge = "A2";
	public static final String if_icmple = "A4";
	public static final String goto_no_condition = "A7";
	public static final String iconst_0 = "03";// iconst_0 将int型 0 推送至栈顶
	public static final String iconst_1 = "04";
	public static final String istore_1 = "3C";
	public static final String istore_2 = "3D";
	public static final String iadd = "60";
	public static final String iinc = "84";
	
	
	public static ByteCodeCommand[] parse(ClassFile clzFile, String codes) {
		
		CommandIterator iter = new CommandIterator(codes);
		List<ByteCodeCommand> cmds = new ArrayList<ByteCodeCommand>();
		while(iter.hasNext()){
			String opCode = iter.next2CharAsString();
			System.out.println("字节码： opCode --- " + opCode);
			if(new_object.equalsIgnoreCase(opCode)){// new 属于有两个操作数的指令
				NewObjectCmd cmd = new NewObjectCmd(clzFile, opCode);
				System.out.println("cmd");
				cmd.setOprand1(iter.next2CharAsInt());
				cmd.setOprand2(iter.next2CharAsInt());
				
				cmds.add(cmd);
			}else if(invokespecial.equalsIgnoreCase(opCode)){// invokespecial 属于有两个操作数的指令
				InvokeSpecialCmd cmd = new InvokeSpecialCmd(clzFile, opCode);				
				cmd.setOprand1(iter.next2CharAsInt());
				cmd.setOprand2(iter.next2CharAsInt());
				cmds.add(cmd);
			}else if(getfield.equalsIgnoreCase(opCode)){// getfield 属于有两个操作数的指令
				GetFieldCmd cmd = new GetFieldCmd(clzFile, opCode);
				
				cmd.setOprand1(iter.next2CharAsInt());
				cmd.setOprand2(iter.next2CharAsInt());
				
				cmds.add(cmd);
			}else if(putfield.equalsIgnoreCase(opCode)){
				System.out.println("putfield");
				PutFieldCmd cmd = new PutFieldCmd(clzFile, opCode);
				cmd.setOprand1(iter.next2CharAsInt());
				cmd.setOprand2(iter.next2CharAsInt());
				cmds.add(cmd);
			}else if(getstatic.equalsIgnoreCase(opCode)){
				GetStaticFieldCmd cmd = new GetStaticFieldCmd(clzFile, opCode);
				cmd.setOprand1(iter.next2CharAsInt());
				cmd.setOprand2(iter.next2CharAsInt());
				cmds.add(cmd);
			}else if(invokevirtual.equalsIgnoreCase(opCode)){
				InvokeVirtualCmd cmd = new InvokeVirtualCmd(clzFile, opCode);
				cmd.setOprand1(iter.next2CharAsInt());
				cmd.setOprand2(iter.next2CharAsInt());
				cmds.add(cmd);
			}else if(bipush.equalsIgnoreCase(opCode)){
				BiPushCmd cmd = new BiPushCmd(clzFile, opCode);
				cmd.setOperand(iter.next2CharAsInt());
				cmds.add(cmd);
			}else if(ldc.equalsIgnoreCase(opCode)){
				LdcCmd cmd = new LdcCmd(clzFile, opCode);
				cmd.setOperand(iter.next2CharAsInt());
				cmds.add(cmd);
			}else if(dup.equalsIgnoreCase(opCode) || aload_0.equalsIgnoreCase(opCode) || iconst_0.equalsIgnoreCase(opCode) || iconst_1.equalsIgnoreCase(opCode) ||
					aload_0.equalsIgnoreCase(opCode) || aload_1.equalsIgnoreCase(opCode) || aload_2.equalsIgnoreCase(opCode) || iload_1.equalsIgnoreCase(opCode) ||
					iload_2.equalsIgnoreCase(opCode) || iload_3.equalsIgnoreCase(opCode) || voidreturn.equalsIgnoreCase(opCode) ||
					astore_1.equalsIgnoreCase(opCode)){
				NoOperandCmd cmd = new NoOperandCmd(clzFile, opCode);
				cmds.add(cmd);
			}else{
				System.out.println("the " + opCode+ "has not been implements");
			}
		}
		
		calcuateOffset(cmds);
		System.out.println("cmds.size ------ " + cmds.size());
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
