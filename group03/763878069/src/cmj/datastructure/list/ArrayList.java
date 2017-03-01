package cmj.datastructure.list;

import java.util.Arrays;
import java.util.Collection;

public class ArrayList implements List {
	private transient Object[] elementData;
	private int size;

	/**
	 * ArrayList初始化无参数构造函数
	 */
	public ArrayList() {
		this(10);
	}

	/**
	 * ArrayList带容量的构造函数
	 * 
	 * @param initialCapacity初始化容量
	 */
	public ArrayList(int initialCapacity) {
		super();
		if (initialCapacity < 0)
			throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
		// 新建一个数组
		this.elementData = new Object[initialCapacity];
	}

	/**
	 * 检查数组的容量
	 * 
	 * @param neededMinCapacity所需最小的容量
	 */
	public void ensureCapacity(int neededMinCapacity) {
		int currCapacity = elementData.length;// 获取当前数据的全部容量
		// 需要扩容的情况
		if (neededMinCapacity > currCapacity) {
			int newCapacity = (currCapacity * 3) / 2 + 1;// 计算新的容量
			elementData = Arrays.copyOf(elementData, newCapacity);
		}
	}

	/**
	 * 添加数据
	 * 
	 * @param o要添加的元素
	 * @return 是否添加成功
	 */
	public void add(Object o) {
		// 确定ArrayList的容量大小
		ensureCapacity(size + 1); // Increments modCount!!
		// 添加o到ArrayList中
		elementData[size++] = o;
	}

	/**
	 * 就是检查一下是不是超出数组界限了，超出了就抛出IndexOutBoundsException异常。
	 * 
	 * @param index要用于检查的索引
	 */
	private void RangeCheck(int index) {
		if (index > size || index < 0)
			throw new IndexOutOfBoundsException("Index: " + index + " 超出访问范围");
	}

	/**
	 * 向指定的位置添加元素
	 * 
	 * @param index
	 * @param o
	 */
	public void add(int index, Object o) {
		RangeCheck(index);
		ensureCapacity(size + 1);// 检查容量

		/* 将原数组从第index个位置复制到原数组第index+1个位置上，一共移动size-index（也就是后面剩下的）个元素 */
		System.arraycopy(elementData, index, elementData, index + 1, size - index);
		elementData[index] = o;
		size++;
	}

	public boolean addAll(Collection<? extends Object> c) {
		Object[] a = c.toArray();
		int growthNum = a.length;
		ensureCapacity(size + growthNum); // Increments modCount
		System.arraycopy(a, 0, elementData, size, growthNum);
		size += growthNum;
		return growthNum != 0;
	}

	public Object get(int index) {
		RangeCheck(index);
		return elementData[index];

	}

	public Object remove(int index) {
		RangeCheck(index);
		int numMoved = size - index - 1;// 删除后需要移动的对象
		Object RemovedValue = elementData[index];
		if (numMoved > 0)
			System.arraycopy(elementData, index + 1, elementData, index, numMoved);
		elementData[--size] = null;
		return RemovedValue;
	}

	public int size() {
		return size;
	}

	@Override
	public String toString() {
		String arraylist = "[";
		for (int i = 0; i < size; i++) {
			if (i == size - 1) {
				arraylist += elementData[i].toString() + "]";
			} else {
				arraylist += elementData[i].toString() + " ,";
			}
		}
		return arraylist;
	}

	public static void main(String[] args) {
		ArrayList arrayList = new ArrayList(5);
		arrayList.add(1);
		arrayList.add(2);
		arrayList.add(3);
		arrayList.add(4);
		arrayList.add(5);
		arrayList.add(6);
		System.out.println(arrayList);
		arrayList.add(1, 1234);
		System.out.println(arrayList);
		arrayList.remove(1);
		System.out.println(arrayList);
		System.out.println(arrayList.get(5));

		ArrayList stringArraylist = new ArrayList(3);
		stringArraylist.add("Hello ");
		stringArraylist.add("string ");
		stringArraylist.add("arraylist");
		System.out.println(stringArraylist);

		ArrayList mixArraylist = new ArrayList(5);
		mixArraylist.add("String");
		mixArraylist.add(1);
		mixArraylist.add('f');
		mixArraylist.add(3.1f);
		mixArraylist.add(4L);
		System.out.println(mixArraylist);
	}
}
