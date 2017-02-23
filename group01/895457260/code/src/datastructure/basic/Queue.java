package datastructure.basic;

public class Queue {
	//数组实现自增长的循环队列
	private Object[] array = new Object[10];
	private int head = 0;
	private int rear = 0;

	public void enQueue(Object o){
		int target = mapIndex(rear);
		autoGrow();
		array[target] = o;
		rear++;
	}
	
	public Object deQueue() {
		Object obj = array[mapIndex(head)];
		head++;
		return obj;
	}
	
	public boolean isEmpty(){
		return head == rear;
	}
	
	public int size(){
		return rear - head;
	}

	private int capacity() {
		return array.length;
	}

	private void autoGrow() {
		if (size() >= capacity()) {
			Object[] newArray = new Object[nextCapacity()];
			System.arraycopy(array, 0, newArray, 0, capacity());

			int increase = nextCapacity() - capacity();
			int moveCount = size() - mapIndex(rear);
			System.arraycopy(newArray, mapIndex(head), newArray, mapIndex(head) + increase, moveCount);
			array = newArray;
			head += increase;
			rear += increase;
		}
	}

	private int nextCapacity() {
		return capacity() * 2;
	}

	private int mapIndex(int index) {
		return index >= capacity() ? index % capacity() : index;
	}

	public static void main(String[] args) {
		Queue queue = new Queue();
		for (int i = 0; i < 22; ++i) {
			queue.enQueue(i);
		}
		for (int i = 0; i < 6; ++i) {
			System.out.print(queue.deQueue() + " ");
		}
		for (int i = 22; i < 41; ++i) {
			queue.enQueue(i);
		}
		while (!queue.isEmpty()) {
			System.out.print(queue.deQueue() + " ");
		}
		System.out.println();
	}
}
