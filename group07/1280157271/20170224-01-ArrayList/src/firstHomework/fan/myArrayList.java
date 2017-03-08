package firstHomework.fan;

public class myArrayList implements List {
	
	private int size = 0;
	private int initLength=10;
	private Object[] elementData = new Object[initLength];
	
	public void add(Object o){
		ensureCapacity(size+1);
		elementData[size++] = o;
	}
	
	public void add(int index, Object o){
		ensureCapacity(size+1);
		if(index<0||index>size){
			System.out.println("index应该在0-size之间！！！");
		}else{
			System.arraycopy(elementData, index, elementData, index+1, size-index);
			elementData[index] = o;
			size++;
		}
	}
	
	public Object get(int index){
		if(index<0||index>size){
			System.out.println("index应该在0-size之间！！！");
		}else{
			return elementData[index];
		}
		return null;
	}
	
	public Object remove(int index){
		if(index<0||index>size){
			System.out.println("index应该在0-size之间！！！");
			return null;
		}
		Object obj = get(index);
		System.arraycopy(elementData, index+1, elementData, index, size-index-1);
		size--;
		return obj;
	}
	
	public int size(){
		return this.size;
	}
	

	
	public void ensureCapacity(int x){
		int oldCapacity = elementData.length;
		if(x>oldCapacity){
			Object newEle[] = new Object[oldCapacity+initLength];
			System.arraycopy(elementData, 0, newEle, 0, size);
			elementData = newEle;
		}
	}
	
}
