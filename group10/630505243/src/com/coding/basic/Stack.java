package com.coding.basic;

public class Stack {
	private ArrayList elementData = new ArrayList();
	private int size = 0;
	
	/**
	 * ����ѹ���ջ����
	 * @param o
	 */
	public void push(Object o){		
		elementData.add(size,o);
		size++;
	}
	
	/**
	 * �Ƴ���ջ�����Ķ��󣬲���Ϊ�˺�����ֵ�����ظö���
	 * @return
	 */
	public Object pop(){
		if(this.elementData.size()>0){
			Object o = elementData.get(size-1);
			elementData.remove(size);
			size--;
			return o;
		}else{
			return null;
		}
	}
	
	/**
	 * �鿴��ջ�����Ķ��󣬵����Ӷ�ջ���Ƴ���
	 * @return
	 */
	public Object peek(){
		if(size>0){
			return elementData.get(size-1);
		}else{
			return null;
		}
	}
	
	/**
	 * ���Զ�ջ�Ƿ�Ϊ��
	 * @return
	 */
	public boolean isEmpty(){
		return size>0?false:true;
	}
	
	/**
	 * ��ȡ��ջ��С
	 * @return
	 */
	public int size(){
		return this.size;
	}
}
