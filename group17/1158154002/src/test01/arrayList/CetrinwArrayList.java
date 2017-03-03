package test01.arrayList;

public class CetrinwArrayList<E> implements CetrinwList<E>  {

	/**
	 * 数组默认长度
	 */
	private static final int DEFAULT_SIZE=10;
	
	/**
	 * 存储队列中的元素
	 */
	private Object[] elements=null;
	
	/**
	 * 数组大小指针
	 */
	private int capacity;
	
	/**
	 * 当前游标
	 */
	private int current;
	
	public CetrinwArrayList() {
		this(DEFAULT_SIZE);
	}
	
	public CetrinwArrayList(int size) {
		if (size<0) {
			throw new RuntimeException("数组大小不能小于0");
		} else {
			this.elements=new Object[size];
			this.current=0;
			this.capacity=size;
		}
	}
	
	@Override
	public E get(int index) {
		confirmIndex(index);
		return (E)elements[index];
	}

	@Override
	public void add(E e) {
		confirmSize();
		this.elements[current]=e;
		this.current++;
	}

	@Override
	public void remove(int index) {
		confirmIndex(index);
		for (int i = index; i < elements.length; i++) {
			if (i+1<elements.length) {
				elements[i]=elements[i+1];
			}
		}
		this.current--;
	}

	@Override
	public void insert(int index, E e) {
		confirmIndex(index);
		for (int i = current; i >=index; i--) {
			elements[i+1]=elements[i];
		}
		elements[index]=e;
		current++;
	}

	@Override
	public boolean contains(Object o) {
		for (Object object : elements) {
			if (object.equals(o)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int size() {
		return this.current;
	}

	@Override
	public boolean isEmpty() {
		if (current>0) {
			return false;
		}
		return true;
	}

	@Override
	public void clearList() {
		for (int i = 0; i < current; i++) {
			elements[i]=null;
		}
	}

	/**
	 * 确认当前数组的容量，如果满足，则不操作，如果不满足，则增加空间
	 */
	private void confirmSize(){
		if (this.current==this.capacity) {
			this.capacity=this.capacity*3/2;
			Object[] newElements=new Object[this.capacity];
			System.arraycopy(elements, 0, newElements, 0, elements.length);
			this.elements=newElements;
		}
	}
	
	
	/**
	 * 判断下标是否越界
	 */
	private void confirmIndex(int index){
		if (index>capacity||index<0) {
			throw new RuntimeException("下标越界");
		}
	}
}
