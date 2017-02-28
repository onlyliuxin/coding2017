package com.coding.basic;


public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[100];
	
	public void add(Object o){
		if (size+1>=elementData.length) {
			this.groupLength();
		}else if (size==0){
			elementData[0]=o;
		}else{
			elementData[size] = o;
		}
		size = size + 1;
	}
	public void add(int index, Object o){
		if (size+1>=elementData.length) {
			this.groupLength();
		}else if (index == size  ) {
			elementData[size] = o;
		}else if(index>=0 && index<=size ){
			Object[] elementDataNew = new Object[this.elementData.length];

			System.arraycopy(elementData, 0, elementDataNew, 0, index);		
			elementDataNew[index]=o;
			System.arraycopy(elementData, index, elementDataNew, index+1, size-index);	
			
			this.elementData = elementDataNew;
		}else{
			System.out.println("指针越界");
			return;
		}
		
		size = size + 1;
	}
	
	public Object get(int index){
		
		if (index>=0||index<=size) {
			return elementData[index];
		}else{
			System.out.println("指针越界");
			return null;
		} 
		
	}
	
	public Object remove(int index){
		
		Object obj =this.elementData[index];
		Object[] elementDataNew = new Object[this.elementData.length];
		if (index<0 || index > size-1) {
			System.out.println("指针越界");
			return null;
		}else if(index==0){
			System.arraycopy(elementData, 1, elementDataNew, 0, size-1);
		}else if(index==size){
			System.arraycopy(elementData, 0, elementDataNew, 0, size-1);
		}else if(index>0 && index <=size){
			System.arraycopy(elementData, 0, elementDataNew, 0, index);
			System.arraycopy(elementData, index+1, elementDataNew, index, size-index-1);
		}
		
		this.elementData = elementDataNew;
		size=size-1;
		return obj;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		
		return new IteratorArrayList(elementData,size);
	}
	
	private void groupLength(){
		this.elementData = new Object[this.elementData.length + 10];
		size=size+10;
	}
	
	public static void main(String[] args) {
		

		
		ArrayList list = new ArrayList();
		System.out.println(list.size());
		
		list.add("aaa");
//		System.out.println(list.remove(1));
		
		list.add("bbb");
		
		
		list.add(1, "ccc");
		list.add(2, "ddd");
		
//		System.out.println(list.size());
//		
//		System.out.println( list.remove(0) );
//		
//		list.add(0,"111");
//		list.add("xxxx");
	
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i)+",");
		}
		Iterator it = list.iterator();
		
		int idex=0;
		while (it.hasNext()) {
			String str = (String) it.next();
			idex++;
			System.out.println("str"+idex+"=="+str+";");
			
		}
	}
}
