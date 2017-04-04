package TestCollection;

 ;

public class Queue {
	private  LinkedList list=new LinkedList();
	
	public void enQueue(Object o){
		list.addLast (  o) ;//问题用add(index, o)出错
	}
	
	public Object deQueue(){
		Object o=list.get(1);
		list.remove(1);
		return o;
	}
	
	public boolean isEmpty(){
		if(list.size()!=0){
		return false;}else
			return true;
	}
	
	public int size(){
		return list.size();
	}
	public static void main(String[] args) {
		Queue q=new Queue();
		q.enQueue("1");
	 	q.enQueue("2");
		 q.enQueue("3");
		System.out.println(q.size());
		  Object o=q.deQueue();
		  System.out.println(o);
		System.out.println(q.size());
		
	}
}
