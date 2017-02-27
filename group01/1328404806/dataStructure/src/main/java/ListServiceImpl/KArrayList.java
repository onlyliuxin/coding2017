package ListServiceImpl;

import ListService.KIList;

public class KArrayList<T> implements KIList<T> {

	/** 初始化的容量的大小 */
	private final static int INIT_CAPACITY = 12;
	private Object[] mList = null;

	/** 当前的容量 */
	private int mCurrentCapacity = 0;
	/** 容器中元素的个数 */
	private int mSize = 0;

	public KArrayList() {
		mList = new Object[INIT_CAPACITY];
		mCurrentCapacity = INIT_CAPACITY;
	}

	/**
	 * 插入一个元素到链表尾部
	 * 
	 * @param item
	 */
	public void add(T item) {
		if (mSize == mCurrentCapacity) {
			expansion();
		}
		mList[mSize] = item;
		mSize++;

	}

	/**
	 * 插入一个元素到指定位置，从插入位置及其后面的元素往后移动一个位置
	 * 
	 * @param index
	 *            要插入的位置
	 * @param item
	 */
	public void add(int index, T item) {
		if (index < 0 || index >= mSize) { // 不允许index小于0，或者index >= 数组当前大小
			throw new IndexOutOfBoundsException();
		}
		if (mSize == mCurrentCapacity) {
			expansion();
		}
		Object[] newList = new Object[mCurrentCapacity];
		System.arraycopy(mList, 0, newList, 0, index);
		System.arraycopy(mList, index, newList, index + 1, mSize - index);
		newList[index] = item;
		mList = newList;
		mSize++;

	}

	/**
	 * 更新指定位置的元素
	 * 
	 * @param index
	 * @param item
	 */
	public void set(int index, T item) {
		if (index < 0 || index >= mSize) {
			throw new IndexOutOfBoundsException();
		}
		mList[index] = item;

	}

	/**
	 * 移除指定位置的元素，后面的元素向前移动一位
	 * 
	 * @param index
	 */
	public void remove(int index) {
		if (index < 0 || index >= mSize) {
			throw new IndexOutOfBoundsException();
		}
		Object[] newList = new Object[mCurrentCapacity];
		System.arraycopy(mList, 0, newList, 0, index);
		System.arraycopy(mList, index + 1, newList, index, mSize - index);
		mList = newList;
		mSize--;

	}

	/**
	 * 移除链表中特定的元素。（如果item在链表中有多个，只移除第一个）
	 * 
	 * @param item
	 */
	public void remove(T item) {
		for (int i = 0; i < mSize; i++) {
			if (mList[i].equals(item)) {
				remove(i);
				break;
			}
		}

	}

	/**
	 * 将链表清空，capacity不变
	 */
	public void clear() {
		mList = new Object[mCurrentCapacity];
		mSize = 0;

	}

	/**
	 * 判断是否包含某个元素
	 * 
	 * @param item
	 * @return true表示有这个元素，false表示没有这个元素
	 */
	public boolean contains(T item) {
		for (int i = 0; i < mSize; i++) {
			if (mList[i].equals(item)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断链表是否为空
	 * 
	 * @return boolean
	 */
	public boolean isEmpty() {
		return (mSize == 0) ? true : false;
	}

	/**
	 * 获取指定位置的元素
	 * 
	 * @param index
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T get(int index) {
		if (index < 0 || index >= mSize) {
			throw new IndexOutOfBoundsException();
		}
		return (T) mList[index];
	}

	/**
	 * 获取特定元素所在的位置。 如果该链表中存在多个相同的元素，只返回第一个的位置，如果找不到，则返回-1。
	 * 
	 * @param item
	 * @return int 如果没找到，返回-1
	 */
	public int indexOf(T item) {
		for (int i = 0; i < mSize; i++) {
			if (mList[i].equals(item)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 获取当前链表的长度
	 * 
	 * @return int
	 */
	public int size() {
		return mSize;
	}

	/**
	 * 扩容，当 mSize == mCurrentCapacity 时调用
	 */
	private void expansion() {
		Object[] oldList = mList;
		Object[] newList = new Object[getNewCapacity()];
		System.arraycopy(oldList, 0, newList, 0, oldList.length);
		mList = newList;
	}

	/**
	 * 获取新的容量大小 当满的时候每次增加当前容量的50%
	 */
	private int getNewCapacity() {
		return mCurrentCapacity = mCurrentCapacity + (mCurrentCapacity >> 1);
	}

}
