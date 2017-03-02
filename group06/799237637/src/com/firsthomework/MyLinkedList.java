/*
 * ����˫������ʵ��LinkedList
 */

package com.firsthomework;





public class MyLinkedList {
	
	private Node first;//����ĵ�һ���ڵ�
	private Node last;//��������һ���ڵ�
	private int size; //�����нڵ�ĸ���
	
	public void addLast(Object o){
		Node node=new Node(o); //��ӵĽڵ����
		if(size==0){      //����Ϊ��ʱ��node����Ϊ��һ��Ҳ��Ϊ���һ���ڵ�
			this.first=node;
			this.last=node;
		}else{
			
			this.last.next=node; //�����Ľڵ���Ϊ֮ǰ���һ���ڵ����һ���ڵ�
			node.pre=this.last;  //��֮ǰ���һ���ڵ���Ϊ�����ڵ����һ���ڵ�
			this.last=node;//�������ڵ���Ϊ���һ���ڵ�
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
		//�ҵ���ɾ���Ľڵ�
		Node tarNode=this.first;
		for(int i=0;i<size;i++){
			
			if(!(tarNode.data.equals(o))){
				if(tarNode.next==null){
					System.out.println("û�д�Ԫ��");
					return;
				}
				tarNode=tarNode.next;
			}
			
		}
		//ɾ���ڵ�
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
	//�����еĽڵ�
	class Node{
		public Object data;  //��ǰ�ڵ�洢������
		public Node next;    //��һ���ڵ�Ķ���
		public Node pre;    //��һ���ڵ����
		
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
