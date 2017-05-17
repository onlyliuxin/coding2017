package dataStructure_9_Stack;
import java.util.ArrayList;

import javax.management.RuntimeErrorException;
/**
 * ��һ������ʵ������ջ
 * ���������ʼλ�ÿ����ǵ�һ��ջ��ջ�ף��������β�������ڶ���ջ��ջ�ף�ѹջʱ��ջ��ָ��ֱ����м��ƶ���ֱ����ջ��ָ�������������ݡ�
 * @author liuxin
 *
 */
public class TwoStackInOneArray {
	
	Object[] data = new Object[10];
	int i = 0;// ��һ��ջ ��ջ��ָ��
	int j = data.length-1;// �ڶ���ջ��ջ��ָ��
	public int getSize(){
		return data.length;
	}
	/**
	 * ���һ��ջ��ѹ��Ԫ��
	 * @param o
	 */
	public void push1(Object o){
		ensureCapacity();
		System.out.println("i= " + i);
		data[i++] = o;
	}

	/**
	 * �ӵ�һ��ջ�е���Ԫ��
	 * @return
	 */
	public Object pop1(){
		if(i ==0 ){
			throw new RuntimeException("��һ��ջΪ��");
		}
		
		return data[i--];
	}
	
	/**
	 * ��ȡ��һ��ջ��ջ��Ԫ��
	 * @return
	 */
	
	public Object peek1(){
		return data[i];
	}
	/*
	 * ��ڶ���ջѹ��Ԫ��
	 */
	public void push2(Object o){
		ensureCapacity();
		System.out.println("j==" + j);
		data[j--] = o;
	}
	/**b
	 * �ӵڶ���ջ����Ԫ��
	 * @return
	 */
	public Object pop2(){
		return data[j++];
	}
	/**
	 * ��ȡ�ڶ���ջ��ջ��Ԫ��
	 * @return
	 */
	
	public Object peek2(){
		return data[j];
	}
	
	private void ensureCapacity() {
		System.out.println("ensureCapacity: " + i + "��" + j);
		if(i >= j){
			data = grow();
			j = data.length - j;
		}
		
	}

	private Object[] grow() {
		Object[] dataNew = new Object[data.length*2];
		System.arraycopy(data, 0, dataNew, 0, i);
		System.arraycopy(data, i, dataNew, dataNew.length-j, j);
		return dataNew;
		
	}
}
