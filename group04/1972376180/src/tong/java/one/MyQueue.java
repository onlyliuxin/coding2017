package tong.java.one;
/**
 * 自定义队列
 * @author tong
 *
 */
public class MyQueue {
	private MyLinkedList datas = new MyLinkedList();
	private int size;

	
	public void enqueue(Object o) {
		datas.add(o);
		size++;
	}

	public Object dequeue() {
		Object firstData = datas.removeFirst();
		size--;
		return firstData;
	}

	public boolean isEmpty() {
		if (size == 0) {
			return true;
		} else {
			return false;
		}
	}

	public int size() {
		return size;
	}
}
