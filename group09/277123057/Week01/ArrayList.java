package Week01;
/*
 * time:2017-2-20 21:51 created
 * 
 */
public class ArrayList implements List{
	
	private int size = 0;
	//开辟的空间只有100个
	private Object[] elementData = new Object[100];
	
	//向末位插入
	public void add(Object o){
		elementData[size++] = o;
	}
	
	//当前位置有元素，就向右移动当前位于该位置的元素及所有后续元素
	public void add(int index, Object o){
		System.arraycopy(elementData, index, elementData, index+1, size-index);
		elementData[index] = o;
		size++;
	}
	
	public Object get(int index){
		return elementData[index];
	}
	 //移除此链表上指定的元素,右边元素左移
	public Object remove(int index){
		int numMoved = size - index - 1; 
		if (numMoved > 0)
			System.arraycopy(elementData, index+1, elementData, index, numMoved);
		elementData[--size] = null;
		return elementData[index];
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return new ArrayListIterator();
	}
	
	private class ArrayListIterator implements Iterator{
		private int pos = 0;
		
		public boolean hashNext() {
			return pos < size();
		}
		
		public Object next() {
			return elementData[pos++];
		}
	}
}
