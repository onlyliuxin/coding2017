package firstweek;


import java.util.NoSuchElementException;

public class linkedlist<E> {
	private int size=0;
	
	private node<E> first;
	
	private node<E> next;
	
	private node<E> last;
	
	public linkedlist(){
		
	}
	
	private void linkFirst(E e){
		final node<E> f=first;
		final node<E> newnode=new node(null,e,f);
		first=newnode;
		if(f==null){
			last=f;
		}else{
			f.pre=newnode;
		}
		size++;
	}
	
	void linkLast(E e){
		final node<E> l=last;
		final node<E> newnode=new node(l,e,null);
		last=newnode;
		if(last==null){
			last=l;
		}else{
			l.next=newnode;
		}
	}
	
	void linkbefore(E e,node<E> nodes ){
		final node<E> pre=nodes.pre;
		final node<E> newnode=new node<>(pre,e,nodes);
		nodes.pre=newnode;
		if(pre==null){
			first=newnode;
		}else{
			pre.next=newnode;
		}
		size++;
	}
	
	 private E unlinkFirst(node<E> f){
		 //删除第一个非空节点 确定f为第一个节点并非空
		final E item=f.item;
		final node<E> next=f.next;
		f.item=null;
		f.next=null;//这样是为了gc来回收
		first=next;
		if(next==null){
			last=null;
		}else{
			next.pre=null;
		}
		size--;
		return item;
	}
	 private E unlinkLast(node<E> l){
		 //删除最后一个非空节点 确定l为最后节点且非空
		 final E item=l.item;
		 final node<E> pre=l.pre;
		 l.item=null;
		 l.pre=null;
		 last=pre;
		 if(pre==null){
			 first=null;
		 }else{
			 pre.next=null;
		 }
		 size--;
		 return item;
	 }
	 
	 E unlinekd(node<E> e){
		final node<E> pre=e.pre;
		final node<E> next=e.next;
		if(size==0){
			System.out.println("链表为空 无需删除");
			return  e.item;
		}
		if(e==last){
			if(size==1){
				last=null;
				first=null;
				size--;
				return e.item;
			}
			last=pre;
			pre.next=null;
			size--;
			return e.item;
		}
		if(e==first){
			if(size==1){
				first=null;
				last=null;
				size--;
				return e.item;
			}
			size--;
			first=next;
			next.pre=null;
			return e.item;
		}
		pre.next=next;
		next.pre=pre;
		size--;
		return e.item;
	 }
	 
	 public E getFirst(){
		 final node<E> f=first;
		 if(f==null){
			throw new NoSuchElementException();
		 }
		 return f.item;
	 }
	 
	 public E getLast(){
		 final node<E> l=last;
		 if(l==null){
			 throw new NoSuchElementException();
		 }
		 return l.item;
	 }
	 public E removeFirst(){
		 final node<E> f=first;
		 if(f==null){
			 throw new NoSuchElementException();
		 }
		 return unlinkFirst(f);
	 }
	 public E removeLast(){
		 final node<E> l=last;
		 if(l==null){
			 throw new NoSuchElementException();
		 }
		 return unlinkLast(l);
	 }
	 public void addLast(E e){
		 linkLast(e);
	 }
	 public void addFirst(E e){
		 linkFirst(e);
	 }
	 public int size(){
		 return size;
	 }
	 public boolean add(E e){
		 linkLast(e);
		 return true;
	 }
	 public int indexof(Object o){
		 int index=0;
		 if(o==null){
			 for(node<E> x=first;x!=null;x=x.next){
				 if(x.item==null){
					 return index;
				 }
				 index++;
			 }
		 }else{
			 for(node<E> x=first;x!=null;x=x.next){
				 if(o.equals(x.item)){
					 return index;
				 }
				 index++;
			 }
		 }
		 return -1;
	 }
}

class node<E>{
	E item;
	node<E> pre;
	node<E> next;
	node(node<E> pre,E item,node<E> next){
		this.pre=pre;
		this.next=next;
		this.item=item;
	}
}
