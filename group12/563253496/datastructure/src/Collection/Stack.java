package Collection;


public class Stack {
	private ArrayList elementData;
	private int size;

	public Stack(Object o) {
		elementData = new ArrayList(o);
		size = 1;
	}

	public Stack() {
		elementData = new ArrayList();
		size = 0;
	}

	public void push(Object o) {
		elementData.add(o);
		size++;
	}

	private void checkCapacity(){
		if(size<=0||size>elementData.size()){
			throw new IndexOutOfBoundsException();
		}
	}
	public Object pop(){
		checkCapacity();
		Object o = elementData.remove(size-1);
		size--;
		return o;
	}

	public Object peek() {
		checkCapacity();
		Object o = elementData.get(size-1);
		return o;
	}

	public boolean isEmpty() {
		if(size!=0){
			return true;
		}
		return false;
	}

	public String toString(){
		return super.toString();
	}

	public int size() {
		return size;
	}
}
