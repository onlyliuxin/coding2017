package com.coderising.jvm.loader;

public class TestByteToInt {
	public static void main(String[] args) {
		byte a = -1;
		int b = a;// �������a��ֱֵ�Ӹ��� b�����ᷢ���ı䣬���ڴ��У� �Ὣ ǰ�����ȫ����� 1�� -128�� byte�е��ڴ��ʾΪ�� 1000 0000��Ȼ����int���ǣ� 1111 1111 1111 1111 1111 1111 1000 0000��-128��int�Ͳ��룩
		int c = a & 0xFFFF;// ��λ�������ڴ��н��еģ�Ҳ����˵���Ƕ�a�Ĳ�����в����ģ� a & 0xFF �е� a ������������λ��0xFF(Ĭ����int�͵�) ���������
		System.out.println(b);
		System.out.println(c);
		System.out.println(a>>>24);
	}
}
