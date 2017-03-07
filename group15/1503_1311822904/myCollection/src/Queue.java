public class Queue {
	private LinkedList elementData = new LinkedList();
	public void enQueue(Object o){
		elementData.add(o);
	}
	
	public Object deQueue(){
		return elementData.remove(0);
	}
	
	public boolean isEmpty(){
		return elementData.size()==0;
	}
	
	public int size(){
		return elementData.size();
	}

	public String toString(){
		return elementData.toString();
	}
	public static void main(String[] arg){
		Queue a=new Queue();
		a.enQueue(0);
		a.enQueue(1);
		a.enQueue("2");
		a.enQueue("3");
		a.enQueue("4");
		System.out.println(a);
		System.out.println(a.deQueue());
		System.out.println(a.deQueue());
		System.out.println(a);
		System.out.println(a.deQueue());
		System.out.println(a.deQueue());
		System.out.println(a.deQueue());
		System.out.println(a);
		System.out.println(a.size());
		System.out.println(a.isEmpty());


		//System.out.println(a.get(3));
		//System.out.println(a.get(99));
	}
}
