public class Queue {

	LinkedList elementData = new LinkedList();

	public void enQueue(Object o) {
		if (o == null) {
			System.out.println("传入对象不能为空");
		} else {
			elementData.add(o);
		}
	}

	public Object deQueue() {
		if (isEmpty()==true){
			System.out.println("该队列为空队列！");
			return null;
		}else{
			Object o = elementData.removeFirst();
			return o;
		}
	}

	public boolean isEmpty() {
		if (elementData.size()==0){
			return true;
		}else {
			return false;
		}
	}

	public int size() {
		return elementData.size();
	}
}
