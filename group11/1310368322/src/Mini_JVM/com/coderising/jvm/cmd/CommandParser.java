package com.coderising.jvm.cmd;

import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.clz.ClassFile;

public class CommandParser {
		
	public static final String aconst_null = "01";// �� null������ջ��
	public static final String new_object = "BB";// ����һ�����󣬲���������ֵѹ��ջ��,����������ֽ����ڹ���������������ִ�����ӿڵķ�������
	public static final String lstore = "37";// ��ջ�� long ����ֵ���� ָ�����ر���
	public static final String invokespecial = "B7";// ���ó��๹�췽����ʵ����ʼ��������˽�з���,���������byte�����ڹ�����ǰ������ʱ�ĳ����ص�������ָ��������ʱ�����ص�һ�������ķ�������
	public static final String invokevirtual = "B6";// ����ʵ������������������ֽ����ڹ���������������ָ��һ�������ķ�������
	public static final String getfield = "B4";//��ȡָ�����ʵ���򣬲�����ѹ��ջ��
	public static final String putfield = "B5";// Ϊָ�������ʵ����ֵ,����������ֽ����ڹ���һ���������������ָ��������ʱ�ֶε�һ���������ã������ð������ֶε����ƺ����������Լ����ֶε���ķ�������
	public static final String getstatic = "B2";// ��ȡ�ֶεľ�̬�ֶ�ֵ
	public static final String ldc = "12";// �� int�� float ��String �ͳ���ֵ�ӳ�������������ջ���������һ���ֽ���һ��������������ָ����int����float���͵ĳ���
	public static final String dup = "59";// ��ֵ������ջջ����ֵ����ѹ�������ջ
	public static final String bipush = "10";// �� byte ��������չΪһ�� int ���͵�ֵ value��Ȼ��valueѹ�뵽������ջ�������һ���ֽ���value
	public static final String aload_0 = "2A";// ����һ���������ͱ��ر���������ջ��
	public static final String aload_1 = "2B";// ���ڶ������ر������������������͵�ֵѹ�������ջ,�����޲�����
	public static final String aload_2 = "2C";
	public static final String iload = "15";
	public static final String iload_1 = "1B";// ���ڶ���int�ͱ��ر���������ջ��
	public static final String iload_2 = "1C";
	public static final String iload_3 = "1D";
	public static final String fload_3 = "25";// �����ĸ�float�ͱ��ر�����������ջ��

	public static final String voidreturn = "B1";// �޷���ֵ�ķ���
	public static final String ireturn = "AC";// �ӵ�ǰ�������� int
	public static final String freturn = "AE";// �ӵ�ǰ�������� float

	public static final String astore_1 = "4C";//��ջ���������������ʹ���ڶ����ֲ���������һ��NoOperand
	public static final String if_icmp_ge = "A2";
	public static final String if_icmple = "A4";
	public static final String goto_no_condition = "A7";
	public static final String iconst_0 = "03";// iconst_0 ��int�� 0 ������ջ��
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
			System.out.println("�ֽ��룺 opCode --- " + opCode);
			if(new_object.equalsIgnoreCase(opCode)){// new ������������������ָ��
				NewObjectCmd cmd = new NewObjectCmd(clzFile, opCode);
				System.out.println("cmd");
				cmd.setOprand1(iter.next2CharAsInt());
				cmd.setOprand2(iter.next2CharAsInt());
				
				cmds.add(cmd);
			}else if(invokespecial.equalsIgnoreCase(opCode)){// invokespecial ������������������ָ��
				InvokeSpecialCmd cmd = new InvokeSpecialCmd(clzFile, opCode);				
				cmd.setOprand1(iter.next2CharAsInt());
				cmd.setOprand2(iter.next2CharAsInt());
				cmds.add(cmd);
			}else if(getfield.equalsIgnoreCase(opCode)){// getfield ������������������ָ��
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
