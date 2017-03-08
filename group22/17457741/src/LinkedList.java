import java.util.Iterator;





public class LinkedList<T> implements Iterable<T>{
	private Node<T>begin;
	private Node<T>end;
	private int size;
	private int modCount=0;

	public LinkedList(){
		doClear();
	}
	public void clear(){
		doClear();
	}
	private void doClear(){
		begin=new Node<T>(null,null,null);
		end=new Node<T>(null,begin,null);
		begin.next=end;
			}

	public int size(){
		return size;
	}
	public boolean isEmpty(){
		return size==0;
	}
	
	public boolean add( T x){
		add(size(),x);
		return true;
	}
	public void add(int a,T x){
		addBefore(getNode(a,0,size()),x);
	}
	
	public T get(int a){
		return getNode(a).data;
	}
	public T set(int a,T newVal){
		Node<T>p=getNode(a);
		T old=p.data;
		p.data=newVal;
		return old;
	}
	
	public T remove(int a){
		return remove(getNode(a));
	}
	
	private void addBefore(Node<T>p,T x){
		Node <T>newNode=new Node<>(x,p.prev,p);
		newNode.prev.next=newNode;
		p.prev=newNode;
		size++;
		modCount--;
	}
	
	private T remove(Node<T>p){
		p.next.prev=p.prev;
		p.prev.next=p.next;
		size--;
		modCount++;
		
		return p.data;
	}
	
	private Node<T>getNode(int a){
		return getNode(a,0,size()-1);
	}
	
	private Node<T>getNode(int a,int lower,int upper){
		Node<T>p;
		
		if(a<lower||a>upper)
			throw new IndexOutOfBoundsException();
		if(a<size()/2){
			p=begin.next;
			for(int i=0;i<a;i++)
				p=p.next;
		}
		else
		{
			p=end;
			for(int i=size();i>a;i--)
				p=p.prev;
		}
		return p;
	}
	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return new LinkedListIterator();
	}
	private class LinkedListIterator implements Iterator<T>{
	    private Node<T> current=begin.next;
	   private int expectedModCount=modCount;
	   private boolean toRemove=false;

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return current!=end;
	}
	@Override
	public T next() {
		// TODO Auto-generated method stub
		if(modCount!=expectedModCount)
			throw new java.util.ConcurrentModificationException();
		if(!toRemove)
			throw new IllegalStateException();
		
		T nextItem=current.data;
		current=current.next;
		toRemove=true;
		return nextItem;
	}  
	
	public void remove(){
		if(modCount!=expectedModCount)
			throw new java.util.ConcurrentModificationException();
		if(!toRemove)
			throw new IllegalStateException();
		
	    LinkedList.this.remove(current.prev);
		expectedModCount++;
		toRemove=false;
	}
	}

	private static class Node<T>{

		public T data;
		public Node<T> prev;
		public Node<T> next;

		public Node(T d, Node<T> p,Node<T> n) {
			// TODO Auto-generated constructor stub
			data=d;prev=p;next=n;
		}
		
	}

}

