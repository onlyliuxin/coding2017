package lesson01;

public class Queue<E> {
	
	private LinkedList<E> queue = new LinkedList<E>();
	
	/**
	 * 入列
	 * @param e
	 */
	public void enQueue(E e){	
		queue.addLast(e);
	}
	
	/**
	 * 出列
	 * @return
	 */
	public E deQueue(){
		return queue.pollFirst();
	}
	
	public boolean isEmpty(){
		return size() == 0;
	}
	
	public int size(){
		return queue.size();
	}

}
