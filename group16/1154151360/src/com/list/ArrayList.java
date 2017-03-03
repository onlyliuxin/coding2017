package com.list;


//ArrayList
public class ArrayList {

	private int size;
	
	private Object [] elementData = new Object[10];
	
	public boolean add(Object data){
		
		getRow(size+1);
		elementData[size++] = data; 
		return true;
	}
	
	public boolean add(int index, Object data){
		if (index < 0 || index > elementData.length){
			throw new IndexOutOfBoundsException("�±�Խ��");
		}
		getRow(size + 1);
		Object object = elementData [index];
		System.arraycopy(elementData, index,elementData , index + 1,size - index );
		elementData[index] = data;
		size++;
		return true;
	}
	
	public Object get(int index){
		if (index < 0 || index > elementData.length){
			throw new IndexOutOfBoundsException("下标越界");
		}
		return elementData[index];
	}
	
	public Object remove(int index){
		if (index < 0 || index > elementData.length){
			throw new IndexOutOfBoundsException("下标越界");
		}
		Object object = elementData [index];
		System.arraycopy(elementData, index + 1 , elementData, index, size - 1 - index);
		elementData[size--]= null;
		return object;
	}
	
	
	private void getRow(int num){
		if (num > elementData.length ){
			int oldLength = elementData.length;
			int newLength = ((num + elementData.length) * 3) >> 2;
			Object [] oldelements = elementData;
			elementData = new Object[newLength];
			System.arraycopy(oldelements, 0, elementData, 0, size);
		}
	}
	
	public int size(){
		return size;
	}
	
	public int length(){
		return elementData.length;
	}
	public static void main(String[] args) {
			ArrayList list  = new ArrayList();
			
			list.add("A");
			list.add("B");
			list.add("C");
			list.add("D");
			list.add("E");
			list.add("F");
			list.add("G");
			list.add("H");
			list.add("I");
			list.add("J");
			list.add("K");
			list.add("L");
			list.add(2, 1);
			System.out.println("elementsData.Length: "+list.length());
			System.out.println("elementsData.size: "+list.size());
			for (int i = 0; i < list.size; i++){
				System.out.print(list.get(i)+ " ");
			}
			System.out.println(" ");
			list.remove(2);
			
			for (int i = 0; i < list.size; i++){
				System.out.print(list.get(i)+ " ");
			}
	}
}
