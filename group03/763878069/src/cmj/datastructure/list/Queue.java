package cmj.datastructure.list;

import java.util.Arrays;

public class Queue {

	private transient Object[] elementData;
	private int size;

	/** 数组的头部,即 下次删除数据的 index */
	private int head;
	/** 数组的尾部,即 下次插入数据的 index */
	private int tail;

	/**
	 * Queue 初始化无参数构造函数
	 */
	public Queue() {
		this(10);
	}

	/**
	 * Queue带容量的构造函数
	 * 
	 * @param initialCapacity初始化容量
	 */
	public Queue(int initialCapacity) {
		super();
		if (initialCapacity < 0)
			throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
		// 新建一个数组
		this.elementData = new Object[initialCapacity];
		this.head = 0;
		this.tail = 0;
		this.size = 0;

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
	 * 添加数据到尾部
	 * 
	 * @param o——要添加的数据
	 */
	public void enQueue(Object o) {
		// 确定ArrayList的容量大小
		ensureCapacity(size + 1); // Increments modCount!!
		// 添加o到ArrayList中
		elementData[tail] = o;
		size++;
		tail++;
	}

	/**
	 * 删除数据 从头部
	 * 
	 * @return——被删除的数据
	 */
	public Object deQueue() {
		if (isEmpty()) {
			throw new RuntimeException("队列为空");
		}
		Object deleted = (Object) elementData[head];
		elementData[head] = null;
		size--;
		head++;
		return deleted;
	}

	public boolean isEmpty() {
		return size <= 0 ? true : false;
	}

	public int size() {
		return size;
	}

	@Override
	public String toString() {
		if (isEmpty()) {
			return "[空队列]";
		}
		String arraylist = "[";
		for (int i = head; i < tail; i++) {
			if (i == tail - 1) {
				arraylist += elementData[i].toString() + "]";
			} else {
				arraylist += elementData[i].toString() + "--->";
			}
		}
		return arraylist;
	}

	public static void main(String[] args) {
		Queue queue = new Queue(4);
		queue.enQueue("one");
		queue.enQueue("two");
		queue.enQueue("three");
		queue.enQueue("four");
		queue.enQueue("five");
		System.out.println(queue);
		queue.deQueue();
		System.out.println(queue);
		queue.deQueue();
		System.out.println(queue);
		queue.deQueue();
		System.out.println(queue);
		queue.deQueue();
		System.out.println(queue);
		queue.deQueue();
		System.out.println(queue);

	}

}