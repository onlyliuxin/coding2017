public class LinkedList  {

private Node head;

private static  class Node{
	Object data;
	Node next;
}

public boolean hasNext(Node a){
	if(a.next!=null){
		return true;
	}
	return false;
}

public Node getIndex(int index){
	Node a=head.next;
	for(int i=0;i<index;i++){
		a=a.next;
	}
	return a;
}

public void add(Object o){
	Node a;
	if(!hasNext(head)){
		a=head.next;
		a.data=o;
		}else{
			a=head.next;
			while(a.next!=null){
				a=a.next;
			}
			a=a.next;
			a.data=o;
		}
}


public void add(int index , Object o){
	Node a=getIndex(index);
	Node b=null;
	b.data=o;
	b.next=a.next;
	a.next=b;	
}
public Object get(int index){
	Object o;
	Node a=getIndex(index);
	o=a.data;
	return o;
}
public Object remove(int index){
	Node a=getIndex(index-1);
	Node b=a.next;
	Object c=b.data;
	a.next=b.next;
	b.next=null;
	return c;
}

public int size(){
	Node a=head.next;
	int i=0;
	while(a.next==null){
		a=a.next;
		i++;
	}
	return i;
}

public void addFirst(Object o){
	Node a=null;
	a.data=o;
	Node b=head.next;
	a.next=b;
	head.next=a;
}
public void addLast(Object o){
	Node a;
	if(!hasNext(head)){
		a=head.next;
		a.data=o;
		}else{
			a=head.next;
			while(a.next!=null){
				a=a.next;
			}
			a=a.next;
			a.data=o;
		}
}
public Object removeFirst(){
	Node a=head.next;
	Node b=a.next;
	Object c=a.data;
	head.next=b;
	a.next=null;
	return c;
}
public Object removeLast(){
	Node a=head.next;
	while(a.next.next!=null){
		a=a.next;
	};
	Object b=a.next.data;
	a.next=null;
	return b;
	
}


//public Iterator iterator(){
//return null;
//}




}
