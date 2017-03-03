package basic;
/**
 * arraylist，Linkedlist,栈，队列，二叉树，迭代器实现，一篇文章关于计算机组成原理 描述CPU，内存， 硬盘，指令之间的关系
 * 
 * @author lvxg
 *
 */
public class MyArrayList {
	private Object[] element;
	private int size;
	private static final Object[] EMPTY = new Object[10];

	public MyArrayList() {
		this.element = EMPTY;
	}

	public boolean add(Object o) {
		// 如果数组没满
		if (size < element.length) {
			element[size] = o;
			size++;
		} else {
			// 数组扩容
			grow();
			element[size] = o;
			size++;
		}
		return true;
	}

	// 根据索引添加数据
	public boolean add(int index, Object o) {
		rangeCheckForAdd(index);
		if (size < element.length + 1) {
			Object[] e = new Object[element.length+1];
			System.arraycopy(element, 0, e, 0, index);
			e[index] = o;
			System.arraycopy(element, index, e, index + 1, element.length-index);
			size++;
		}
		return true;
	}

	public Object get(int index) {
		rangeCheck(index);
		return element[index];
	}

	public Object remove(int index) {
		rangeCheck(index);
		Object oldValue = element[index];
		int numMoved = size - index-1;
		if(numMoved>0){
			System.arraycopy(element, index+1, element, index, numMoved); 
		}
		element[--size] =null;
		return oldValue;
	}
	public int size() {
		return size;
	}
	 private void rangeCheck(int index) {
	        if (index >= size)
	            throw new IndexOutOfBoundsException();
	    }
	private void rangeCheckForAdd(int index) {
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
	}

	// 数组扩容方法
	private void grow() {
		Object[] e = new Object[size * 2];
		// 数组拷贝
		System.arraycopy(element, 0, e, 0, element.length);
		element = e;

	}

	public static void main(String[] args) {
		MyArrayList m = new MyArrayList();
		m.add("1");
		for (int i = 0; i < 10; i++) {
			m.add(i);
		}
		m.add(2, "123");
		m.remove(1);
	}

}
