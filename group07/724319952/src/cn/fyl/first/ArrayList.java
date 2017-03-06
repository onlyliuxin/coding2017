package cn.fyl.first;

/*
 * 动态数组
 */
public class ArrayList implements List {

	// 记录数组大小
	private int size = 0;

	// 设数组容量为3
	private Object[] elementData = new Object[3];

	// 扩容
	public void dilatation(int newCapacity) {

		if (newCapacity < size) {
			return;
		}

		Object[] old = elementData;
		elementData = new Object[newCapacity];
		for (int i = 0; i < size; i++) {
			elementData[i] = old[i];
		}

	}

	// 插入值
	public void add(Object o) {
		add(size(), o);
	}

	// 按索引插入值
	public void add(int index, Object o) {
		if (elementData.length == size()) {
			dilatation(size() * 2 + 1);
		}
		// 插入的位置小于数组大小，将index位置的值依次向后退一位
		for (int i = size; i > index; i--) {
			elementData[i] = elementData[i - 1];
		}
		elementData[index] = o;
		size++;

	}

	// 取值
	public Object get(int index) {

		if (index > size) {
			return null;
		} else {
			return elementData[index];
		}

	}

	// 按索引删除该值
	public Object remove(int index) {
		Object o = elementData[index];
		// 删除的位置小于数组大小，将index位置后面的值依次向前挪一位
		for (int i = index; i < elementData.length - 1; i++) {
			elementData[i] = elementData[i + 1];
		}
		size--;
		return o;
	}

	// 返回数组大小
	public int size() {
		return size;
	}

	//迭代器
	 class MyIterator implements Iterator {
		int current = 0;

		public boolean hasNext() {
			return current < size;
		}

		public Object next() {
			if (hasNext())
				return elementData[current++];
			else
				throw new java.util.NoSuchElementException();
		}

	}

	public static void main(String[] args) {
		ArrayList a = new ArrayList();
		a.add(0, 1);
		a.add(2);
		a.add(3);
		a.add(4);
		a.add(1, 0);
		a.remove(3);
		ArrayList.MyIterator m = a.new MyIterator();
		System.err.println(a.elementData.length);
		while(m.hasNext()){
			System.out.print(m.next()+"  ");
		}
	}
	
}
