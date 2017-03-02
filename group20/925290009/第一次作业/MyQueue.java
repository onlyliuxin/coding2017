package BasicData;

/**
 * 实现基本数据结构Queue(环形)
 * 
 * @author Administrator
 * 
 */
public class MyQueue<T> {

	private int head;
	private int tail;
	private T[] elements;
	private static final int DEFAUL_SIZE = 10;
	private int numOfelements;

	public MyQueue() {
		head = 0;
		tail = 0;
		numOfelements = 0;
		setCapacity(DEFAUL_SIZE);
	}

	public MyQueue(int capacity) {
		head = 0;
		tail = 0;
		numOfelements = 0;
		setCapacity(capacity);

	}

	@SuppressWarnings("unchecked")
	private void setCapacity(int capacity) {
		elements = (T[]) new Object[capacity];
	}

	public boolean enQueue(T t) {

		if (numOfelements == elements.length) {
			return false;
		} else {
			elements[tail] = t;
			numOfelements++;
			if (tail == elements.length)
				tail = 0;
			else
				tail++;
			return true;
		}

	}

	public T deQueue() {
		if (head == tail) {
			return null;
		} else {
			T t = elements[head];
			numOfelements--;
			elements[head] = null;
			if (head == elements.length)
				head = 0;
			else {
				head++;
			}
			return t;
		}

	}

	public boolean isEmpty() {
		return numOfelements == 0;
	}

	public int size() {
		int msize = head - tail;
		return msize > 0 ? msize : -msize;
	}

}
