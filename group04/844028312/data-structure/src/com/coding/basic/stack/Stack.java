package com.coding.basic.stack;

import com.coding.basic.array.ArrayList;

public class Stack {
	private ArrayList elementData = new ArrayList();
	
	public void push(Object o){	
		elementData.add(o);								//��ջ��ѹ��Ԫ��
	}
	
	public Object pop(){
		Object o=elementData.get(elementData.size()-1);	//ջ��Ԫ�أ���Ϊջ�Ƚ���
		elementData.remove(elementData.size()-1);		//�Ƴ�ջ��Ԫ��
		return o;							
	}
	
	public Object peek(){
		return elementData.get(elementData.size()-1);   //��ȡջ��Ԫ��
	}
	public boolean isEmpty(){
		if(elementData.size()==0){			//���elementData.size�ж��Ƿ�Ϊ��
			return true;
		}
		return false;
	}
	public int size(){
		return elementData.size();			
	}
}
