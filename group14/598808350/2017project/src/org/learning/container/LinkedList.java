package org.learning.container;

public class LinkedList {
	
	private Node element = new Node(null,null,null);
	
	public LinkedList(){
		this.element.next = (this.element.prev = this.element);
	}
	
	
	
	public Node getElement() {
		return element;
	}

	public void setElement(Node element) {
		this.element = element;
	}


	private int index = 0;
	
	


	
	
	
	public void add(Object obj){
		add(obj,this.element);
	}
	public Node add(Object obj,Node node){
		// 将节点(节点数据是obj)添加到表头(element)之前
		// 即，将节点添加到双向链表的末端
		Node childNode = new Node(obj,node,node.prev);
		childNode.prev.next = childNode;
		childNode.next.prev = childNode;
		index += 1;
		return childNode;
	}
	
	/*public void add(int index,Object obj){
		for(int i=0;i<size();i++){
			if(i == index){
				add(obj);
			}
		}
	}*/
	
	public void addFirst(Object obj){
		//add(0,obj);
		add(obj,this.element.next);
	}
	
	public Object getFirst(){
		return element.next.obj;
	}
	
	public void addLast(Object obj){
		//add(size(),obj);
		add(obj,this.element);
	}
	
	public Object getLast(){
		return element.prev.obj;
	}
	public Node get(int index){
		Node obj = null;
		for(int i=0;i<size();i++){
			if(i == index) obj = element.next;
		}
		return obj;
	}
	public int size(){
		return this.index ;
	}
	public Object remove(Node node){
		Object e = node.obj;
		
		node.prev.next = node.next;
		node.next.prev = node.prev;
		
		node.prev = node.next = null;
		node.obj = null;
		index -=1;
		return e;
	}
	
	public void removeFirst(){
		remove(this.element.next);
	}
	public void removeLast(){
		remove(this.element.prev);
	}
	
	class Node{
		Object obj ;			//存放主体
		Node prev ;		//存放上一个对象
		Node next ;		//存放下一个对象。
		public Node(Object obj,Node next,Node prev){
			this.obj = obj;
			this.next = next;
			this.prev = prev;
		} 
	}
	
	
	public static void main(String [] args){
		/*Object [] objs = new Object[3];
		
		Object [] c0 = new Object[2];
		c0[0] = "c0";
		Object [] c1 = new Object[2];
		c1[0] = "c1";
		Object [] c2 = new Object[2];
		c2[0] = "c2";
		
		c0[1] = c1;
		c1[1] = c2;
		c2[1] = null;
		
		objs[2] = c2;
		objs[1] = c1;
		objs[0] = c0;
		
		for(int i=0;i<objs.length;i++){
			System.out.println("i="+i);
			System.out.println(((Object[])objs[i])[0]);
			if((Object[])((Object[])objs[i])[1] == null){
				continue;
			}
			Object[] obj = (Object[])((Object[])objs[i])[1];
			
			System.out.println(obj[0]);
			System.out.println(obj[1]);
		}*/
		LinkedList list = new LinkedList();
		list.add("test0");
		list.add("test1");
		list.add("test2");
		
		list.addFirst("first");
		list.addLast("last");
		
		System.out.println(list);
		System.out.println("list.getFirst()"+list.getFirst());
		System.out.println("list.getLast()"+list.getLast());
		
		
		for(int i=0;i<list.size();i++){
			Node tmp = list.get(i);
			print(tmp.obj);
		}
		
		System.out.println("----------------------------------------");
		java.util.LinkedList linkedList = new java.util.LinkedList();
		linkedList.add("object");
		linkedList.addFirst("first");
		//linkedList.addLast("last");
		
		
		for(Object obj : linkedList){
			print(obj);
		}
		
		print("--------------------添加后输出--------------------------------------");
		
		print("-----------------删除first，last---------------------------------------");
		
		linkedList.removeFirst();
		linkedList.removeLast();
		for(Object obj : linkedList){
			print(obj);
		}
		
		
	}
	
	public static void print(Object obj){
		System.out.println(""+obj);
	}
}
