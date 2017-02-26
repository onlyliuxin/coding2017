/*
 * 采用双向链表实现LinkedList
 */

package com.firsthomework;





public class MyLinkedList {
	
	private Node first;//链表的第一个节点
	private Node last;//链表的最后一个节点
	private int size; //链表中节点的个数
	
	public void addLast(Object o){
		Node node=new Node(o); //添加的节点对象
		if(size==0){      //链表为空时：node即作为第一个也作为最后一个节点
			this.first=node;
			this.last=node;
		}else{
			
			this.last.next=node; //新增的节点作为之前最后一个节点的下一个节点
			node.pre=this.last;  //把之前最后一个节点作为新增节点的上一个节点
			this.last=node;//把新增节点作为最后一个节点
		}
		size++;
	}
	
	public void addFirst(Object o){
		Node node=new Node(o);
		if(size==0){
			this.first=node;
			this.last=node;
		}else{
			node.next=this.first;
			this.first.pre=node;
			this.first=node;
		}
		
		size++;
	}
	
	public void remove(Object o){
		//找到被删除的节点
		Node tarNode=this.first;
		for(int i=0;i<size;i++){
			
			if(!(tarNode.data.equals(o))){
				if(tarNode.next==null){
					System.out.println("没有此元素");
					return;
				}
				tarNode=tarNode.next;
			}
			
		}
		//删除节点
		if(tarNode==this.first){
			this.first=tarNode.next;
			this.first.pre=null;
		}else if(tarNode==this.last){
			this.last=tarNode.pre;
			this.last.next=null;
			
		}else{
			tarNode.pre.next=tarNode.next;
			tarNode.next.pre=tarNode.pre;
		}
		size--;
	}
	
	public Object get(int index){
		return null;
	}
	
	public int size(){
		return size;
	}
	//链表中的节点
	class Node{
		public Object data;  //当前节点存储的数据
		public Node next;    //下一个节点的对象
		public Node pre;    //上一个节点对象
		
		public Node(Object data){
			this.data=data;
		}
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyLinkedList ll= new MyLinkedList();
		ll.addFirst("hello");
		ll.addLast("world");
		ll.addLast("!");
		ll.addLast("haha");
		ll.addFirst("0");
		System.out.println(ll.size());
		System.out.println("*****");
		ll.remove("0");
		System.out.println(ll.size());
		
	}

}
