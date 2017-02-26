package java1;

public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[100];
	
	public void add(Object o){
		ensureCapacity(size+1);
		elementData[size++]=o;
		
	}
	public void add(int index, Object o){
		ensureCapacity(size+1);
		if(index<0&&index>size){
			System.out.println("Wrong Input£¡");
		}else{
			System.arraycopy(elementData, index, elementData, index+1, size-index);
			elementData[index]=o;
			size++;
		}	
		
	}
	
	public Object get(int index){
		if(index<0&&index>size){
			System.out.println("Wrong Input£¡");
			return null;
		}
		return elementData[index];
	}
	
	public Object remove(int index){
		if(index<0&&index>size){
			System.out.println("Wrong Input£¡");
			return null;
		}
		Object a=elementData[index];
		System.arraycopy(elementData, index+1, elementData, index, size-index-1);
		return a;
	}
	
	public int size(){
		return this.size;
	}
	
	public void ensureCapacity(int size){
		int oldCapacity=elementData.length;
		if(size>oldCapacity){
			Object[] newelementData=new Object[size+oldCapacity];
			elementData=newelementData;
		}
	}
	
}

