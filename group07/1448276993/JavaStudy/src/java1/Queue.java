package java1;

public class Queue {
	private Object[] str=new Object[100];
	private int size=0;
	public void enQueue(Object o){	
		ensureCapacity(size+1);
		str[size++]=o;
	}
	
	public Object deQueue(){
		if(this.size==0){
			System.out.println("Is empty!");
			return null;
		}
		System.arraycopy(str, 1, str, 0, size-1);
		size--;
		return str;
	}
	
	public boolean isEmpty(){
		if(size==0){
			return true;
		}
		return false;
	}
	
	public int size(){
		return this.size;
	}
	public void ensureCapacity(int size){
		int oldCapacity=str.length;
		if(size>oldCapacity){
			Object[] newelementData=new Object[size+oldCapacity];
			str=newelementData;
		}
	}
}
