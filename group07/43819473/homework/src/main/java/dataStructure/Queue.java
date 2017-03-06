package dataStructure;

/**
 * Created by LvZhenxing on 2017/2/22.
 */
public class Queue {

	private LinkedList list=new LinkedList();

	public void enQueue(Object o) {
		list.addFirst(o);
	}

	public Object deQueue() {
		return list.removeLast();
	}

	public boolean isEmpty() {
		return list.size()==0?true:false;
	}

	public int size() {
		return list.size();
	}
}
