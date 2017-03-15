/*
 * �ó���Ϊ�Լ�ʵ��ArrayList
 * ArrayList�ײ�Ϊ����ʵ�֣��Ƕ�̬����
 */
package com.firsthomework;


public class MyArrayList {
	private int size=0;
	private int initialcapcity=10; 
	private Object[] elements= null;
	public MyArrayList(){
	  this.elements=new Object[initialcapcity];
	}
	
	public MyArrayList(int capcity){

		this.elements = new Object[capcity];
		
	}
	public void enlarge(){
		initialcapcity*=2;
		Object[] tmpelements= new Object[initialcapcity];
		System.arraycopy(elements, 0, tmpelements, 0,size);
		elements=tmpelements;
	}
	public void add(Object o){
		if(o==null){
			throw new RuntimeException("����Ϊ�գ�");
		}
		if(size>initialcapcity){
			enlarge();
		}
		elements[size]=o;
		size++;
		
	}
	public Object get(int index){
		if(index<0||index>size-1){
			throw new IndexOutOfBoundsException("����Խ�磺");
		}
		return elements[index];
		
	}
//ʵ��remove()������ɾ��λ�ã������Ԫ��Ҫǰ�ƣ�����ɾ��λ�õ�Ԫ��
	public Object remove(int index){
		Object oldValue=elements[index];
		int eleMoved=size-index-1; //�ƶ���Ԫ�صĸ���
		if(eleMoved>0){
			System.arraycopy(elements, //ԭ����
					index+1, //ԭ�������ʼλ�ã�ɾ��Ԫ�صĺ���һ��λ�ã�
					elements, //Ŀ������
					index, //Ŀ���������ʼλ��
					eleMoved);//�����ĳ���
		}
		elements[size-1]=null;
		size--;
		
		return oldValue;
	}
	
	public boolean set(int index,Object o){
		if(index<0||index>size-1){
			throw new IndexOutOfBoundsException("����Խ��");
		}
		elements[index]=o;
		return true;
	}
	
	//��дtoString()����
	public String toString(){
		StringBuffer sb=new StringBuffer();
		sb.append("[");
		for(int i=0;i<size;i++){
			Object o=elements[i];
			sb.append(o);
			if(i<size-1){
				
				sb.append(",");
			}
			
		}
		sb.append("]");
		return sb.toString();
		
	}
	
	public static void main(String[] args) {
		MyArrayList my=new MyArrayList();
		my.add("hello");
		my.add("world");
		my.add("!");
		my.add(1);
		my.add(2);
		my.add(3);
		System.out.println(my.get(1));
		my.set(2, "oo");
		System.out.println(my.get(2));
		System.out.println("***");
		System.out.println(my.remove(1));
		System.out.println(my.remove(2));
		System.out.println(my);
	}
	
	
	
}
