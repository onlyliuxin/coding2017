package TestCollection;

public class ArrayList implements List {
	
	
	
	private Object[] elementData = new Object[100];
	private int size  ;
	public void add(Object o){
		if(size==elementData.length){
			Object[]newData=new Object[size*2+1];//½«Êý×éÀ©ÈÝ
			System.arraycopy(elementData, 0, newData, 0, elementData.length);
		elementData=newData;
		}
		elementData[size++]=o;
		 
	}
	public void add(int index, Object o){
		System.arraycopy(elementData, index, elementData,index+1 , size-index);
		elementData[index]=o;
		size++;
	}
	
	public Object get(int index){
		return elementData[index];
	}
	
	public Object remove(int index){
		System.arraycopy(elementData, index+1, elementData, index, size-index-1);
		elementData[--size]=null;
		return null;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return null;
	}
	
}
