package cn.xl.basic.stack;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * Stack��һ������ȳ���last in first out��LIFO���Ķ�ջ��
 * ��Vector��Ļ�������չ5����������
 * @author XIAOLONG
 *
 */
public class MyStack {

	private int elementCount;

	private Object[] elementData = new Object[5];

	/**
	 * �޲ι��췽��������һ����ջ
	 * 
	 */
	public MyStack(){

	}


	/**
	 * Ԫ����ջ
	 * @param item
	 * @return ��ջԪ��
	 */
	public synchronized Object push(Object item){

		ensureCapacity(elementCount+1);
		elementData[elementCount] = item;
		elementCount ++;
		return item;
	}

	/**
	 * ��ջ��Ԫ���Ƴ��������ظ�Ԫ��
	 * @return  ջ��Ԫ��
	 */
	public synchronized Object pop(){
		Object    obj;

		obj = peek();
		elementCount --;
		elementData[elementCount] = null;

		return obj;
	}

	/**
	 * �鿴ջ��Ԫ��
	 * 
	 * @return ջ��Ԫ��
	 * @throws ���ջΪ�� �� �׳� EmptyStackException�쳣  .
	 */
	public synchronized Object peek(){
		int len = elementCount;

		if(len == 0)
			throw new EmptyStackException();

		return  elementData[len - 1];

	}

	/**
	 * ���ջ�Ƿ�Ϊ��
	 * 
	 * @return True or false
	 */
	public boolean isEmpty(){

		return elementCount == 0;
	}

	/**
	 * ��ѯռջ�Ƿ����ĳԪ��
	 * @param  ��ѯԪ��
	 * @return ���Ԫ�ش��ڷ���Ԫ������λ�ã�ջ��Ԫ��λ��Ϊ1����
	 *         �����Ԫ����ջ�����ظ����򷵻ؾ���ջ�������Ԫ��λ�ã�
	 *         �����Ԫ����ջ�в����ڣ��򷵻� -1 ��
	 */
	public synchronized int search(Object o){

		if(o == null){
			for(int i = elementCount -1;i >= 0; i--){
				if(elementData[i] == null){
					return elementCount - i;
				}
			}
		}else{
			for(int i = elementCount -1;i >= 0; i-- ){
				if(o.equals(elementData[i])){
					return elementCount - i;
				}
			}
		}

		return -1;
	}

	/**
	 * ��չ��������������������һ��
	 * @param ��ǰջ������С����size
	 */
	private void ensureCapacity(int minCapacity){
		int oldCapacity = elementData.length;
		if(minCapacity > oldCapacity){
			int newCapacity = oldCapacity << 1;
			elementData = Arrays.copyOf(elementData, newCapacity);
		}
	}

	public static void main(String[] args){
		
		
		
	}

}
