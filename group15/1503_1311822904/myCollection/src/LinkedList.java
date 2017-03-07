public class LinkedList implements List {
	private int size = 0;
	private Node head=new Node(null);
	
	public void add(Object o){
		if(head.data==null){
			head.data=o;
			return;
		}
		Node n=head;
		while (n.next!=null){
			n=n.next;
		}
		n.next=new Node(o);
		size++;
	}
	public void add(int index , Object o){
		Node n=getNode(index);
		Node newN=new Node(o);
		newN.next=n.next;
		n.next=newN;
		size++;
	}
	public Object get(int index){
		return getNode(index).data;
	}
	private Node getNode(int index){
		Node n=head;
		for (int i=0;i<index;i++){
			if(n==null)
				throw new IndexOutOfBoundsException();
			n=	n.next;
		}
		return n;
	}
	public Object remove(int index){
		Object o;
		if (index==0){
			o=head.data;
			head.data=null;
			size--;
			return o;
		}
		Node n=getNode(index-1);
		o=n.next.data;
		n.next=n.next.next;
		size--;
		return o;
	}
	
	public int size(){

		return size;
	}
	
	public void addFirst(Object o){
		size++;
		if(head.data==null){
			head.data=o;
			return;
		}
		Node n=new Node(o);
		n.next=head;
		head=n;

	}
	public void addLast(Object o){
		size++;
		//要是还没有值 就是头
		if (head.data==null){
			head.data=o;
		}
		Node n=head;
		//找到最后一个
		while (n.next!=null){
			n=n.next;
		}
		//接上去
		n.next=new Node(o);

	}
	public Object removeFirst(){
		//没有头
		if(head.data==null)
			throw new IndexOutOfBoundsException();
		Object o=head.data;
		head=head.next;
		size--;
		return o;
	}
	public Object removeLast(){
		//还没有值 异常
		if (head.data==null){
			throw new IndexOutOfBoundsException();
		}
		size--;
		Object o;
		//只有头有值
		if(head.next==null){
			o=head.data;
			head.data=o;
			return o;
		}
		Node n=head;
		//找到最后第二个
		while (n.next.next!=null)
			n=n.next;
		//拿到最后一个值
		o=n.next.data;
		//去掉最后一个节点
		n.next=null;
		return o;
	}
	public Iterator iterator(){
		return new  LinkedIterator();
	}
	private class LinkedIterator implements Iterator{
		private Node n=head;
		@Override
		public boolean hasNext() {
			if(n!=null&&n.next!=null){
				n=n.next;
				return true;
			}
			return false;
		}

		@Override
		public Object next() {
			return n.data;
		}
	}
	
	private static  class Node{
		Object data;
		Node next;
		Node(Object o){
			data=o;
		}

		
	}

	public String toString(){
		String s="[";
		for (int i=0;i<size;i++)
			s+=get(i)+", ";
		s+="]";
		return s;
	}

	public static void main(String[] arg){
		LinkedList a=new LinkedList();
	//	a.removeFirst();
		a.addFirst("first");
		a.addLast("ll");
		a.add(0);
		a.add(1);
		a.add("2");
		a.add("3");
		a.add("4");
		a.add("5");
		a.add("six");
		a.add("七");
		a.add(8);
		System.out.println(a);
		Iterator iterator=a.iterator();
		while (iterator.hasNext())
			System.out.println(iterator.next());
		/*System.out.println(a.size);
		System.out.println(a);
		System.out.println(a.remove(3));
		System.out.println(a.remove(3));
		a.removeFirst();
		a.removeLast();
		System.out.println(a);*/


		//System.out.println(a.get(3));
		//System.out.println(a.get(99));
	}
}
