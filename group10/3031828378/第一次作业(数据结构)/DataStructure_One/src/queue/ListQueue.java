package queue;

import list.LinkedList;

public class ListQueue {

	LinkedList list;

	public ListQueue() {
		list = new LinkedList();
	}

	public int size() {
		return list.size();
	}

	public boolean isEmpty() {
		return list.size() == 0 ? true : false;
	}

	public boolean remove(Object o) {
		return list.remove(o);
	}

	public void clear() {
		list.clear();
	}

	public boolean add(Object e) {
		return list.add(e);
	}

	public boolean offer(Object e) {
		return false;
	}

	public Object poll() {
		if (list.size() == 0) {
			return null;
		}else{
			Object item = list.getFirst().item;
			list.remove(0);
			return item;
		}
	}


	public Object peek() {
		if (list.size() == 0) {
			return null;
		}else{
			return list.getFirst().item;
		}
	}

}
