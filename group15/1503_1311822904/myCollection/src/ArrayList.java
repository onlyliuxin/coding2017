import java.util.Arrays;

public class ArrayList implements List {
	
	private int size = 0;
	private int length=3;
	private Object[] elementData = new Object[length];
	
	public void add(Object o){
		if(size>=length){
			grow(100);
		}
		elementData[size]=o;
		size++;
	}
	public void add(int index, Object o){
		size++;
		if(size>=length){
			grow(100);
		}
		System.arraycopy(elementData,index,elementData,index+1,size-index-1);
		elementData[index]=o;
	}
	
	public Object get(int index){
		if(index<size)
			return elementData[index];
		throw new IndexOutOfBoundsException();
	}
	
	public Object remove(int index){
		//越界
		if(index>=size)
			throw new IndexOutOfBoundsException();
		size--;
		Object a=elementData[index];
		//刚好最后一个
		if (index+1==size){
			return a;
		}
		System.arraycopy(elementData,index+1,elementData,index,size);
		return a;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return new ArrayListIterator();
	}
	private class ArrayListIterator implements Iterator{
		private int index=0;
		@Override
		public boolean hasNext() {
			if(index+1<size){
				index++;
				return true;
			}
			return false;
		}

		@Override
		public Object next() {
			return elementData[index];
		}
	}
	private void grow(int increase){
		//return Arrays.copyOf(src,src.length+size);
		Object[] target=new Object[length+increase];
		System.arraycopy(elementData,0,target,0,length);
		elementData=  target;
		length=length+increase;
	}
	public String toString(){
		return Arrays.toString(Arrays.copyOf(elementData,size));
	}
	public static void main(String[] arg){
		ArrayList a=new ArrayList();
		a.add(0);
		a.add(1);
		a.add("2");
		a.add("3");
		a.add("4");
		a.add("5");
		a.add("six");
		a.add("七");
		a.add(8);
		Iterator iterator=a.iterator();
		while (iterator.hasNext())
			System.out.println(iterator.next());
		/*System.out.println(a.remove(3));
		System.out.println(a.size());
		System.out.println(a);*/

		//System.out.println(a.get(3));
		//System.out.println(a.get(99));
	}
}
