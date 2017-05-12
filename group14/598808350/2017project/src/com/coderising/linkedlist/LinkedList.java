package com.coderising.linkedlist;

import java.util.Stack;

public class LinkedList {

	private Node head;
	private int size;

	public LinkedList(){
		this.head = new Node(null,null);
	}
	public void add(Object o){
		Node lastNode = head;
		for(int i=0;i<size;i++){
			lastNode = lastNode.next;
		}
		lastNode.next = new Node(o,null);
		size+=1;
	}
	public void add(int index , Object o){
		Node lastNode = head;
		for(int i=0;i<index;i++){
			lastNode = lastNode.next;
		}
		Node oldNode = lastNode.next ;
		lastNode.next  = new Node(o,oldNode);
		size+=1;
	}
	public Object get(int index){
		Node node = head;
		for(int i =0;i<size;i++){
			node = node.next;
			if(index == i){
				return node.data;
			}
		}
		return null;
	}
	public Object remove(int index){
		Node prev = head;
		for(int i=0;i<size;i++){
			prev = prev.next;
			if(i == index){
				Node nnNode = prev.next.next;
				prev.next = nnNode;
			}			
		}
		size --;
		return prev.next;
	}
	public Object remove(Object obj){
		Node prev = head;
		Node cur = prev.next;
		for(int i=0;i<size;i++){
			Object tmp = prev.data;
			
			
			if(obj.equals(tmp) ){
				prev.next = cur.next;
			}	
			prev = cur;
			cur = cur.next;
		}
		size --;
		return prev.next;
	}

	public int size(){
		return size;
	}

	public void addFirst(Object o){
		add(0,o);
	}
	public void addLast(Object o){
		add(size,o);
	}
	public Object removeFirst(){
		Node first = head.next;
		Node second = first.next;
		head.next = second;
		size --;
		return first.data;
	}
	public Object removeLast(){
		Node tempNode = head;
		for(int i=0;i<size-1;i++){
			tempNode = tempNode.next;
		}
		Object obj =tempNode.next;
		tempNode.next = null;
		size --;
		return obj;
	}
	
	
	
	//public Iterator iterator(){
			//return null;
	//}
	
	public static void main(String[] args){
		LinkedList list = new LinkedList();
		list.add("2");
		list.add("3");
		list.add("4");
		list.addFirst("1");
		list.addFirst("0");
		
		list.addLast("5");
		list.addLast("6");
		
		list.removeFirst();
		list.removeLast();
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
		}
		
		
	}


	private static  class Node{
		Object data;
		Node next;

		public Node(Object o,Node next){
			this.data = o;
			this.next = next;
		}
	}
	
	private static class Iterator{
		boolean hasNext(){
			return false;
		}
		Object next(){
			return null;
		}
	}
	
	/**
	 * �Ѹ���������
	 * ��������Ϊ 3->7->10 , ���ú��Ϊ  10->7->3
	 */
	public  void reverse(){
		Stack<Node> nodes = new Stack<Node>();
		
		Node currentNode = head;
		while(currentNode != null){
			nodes.push(currentNode);
			Node nextNode = currentNode.next;
			currentNode.next = null;
			currentNode = nextNode;
		}
		head = nodes.pop();
		currentNode = head;
		
		while(!nodes.isEmpty()){
			Node nextNode = nodes.pop();
			currentNode.next = nextNode;
			currentNode = nextNode;
			
		}
	}
	
	/**
	 * ɾ��һ���������ǰ�벿��
	 * ���磺list = 2->5->7->8 , ɾ���Ժ��ֵΪ 7->8
	 * ���list = 2->5->7->8->10 ,ɾ���Ժ��ֵΪ7,8,10

	 */
	public  void removeFirstHalf(){
		int l = size/2;
		for(int i=0;i<l;i++){
			removeFirst();
		}
	}
	
	/**
	 * �ӵ�i��Ԫ�ؿ�ʼ�� ɾ��length ��Ԫ�� �� ע��i��0��ʼ
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		Node node = head;
		for(int s=i;s<i+length;s++){
			node = node.next;
		}
		head.next = node;
		size = size - length;
	}
	
	/**
	 * ��ȡ node ��  ��index��Ԫ��
	 * @param list
	 * @param index
	 * @return
	 */
	public static Node getElement(Node node,int index){
		int s = 0;
		
		Node stNode = node;
		while(index > s){
			stNode = node.next;
			s++;
		}
		return stNode;
	}
	
	/**
	 * �ٶ���ǰ�����list������������е�����
	 * �ӵ�ǰ������ȡ����Щlist��ָ����Ԫ��
	 * ���統ǰ���� = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * ���صĽ��Ӧ����[101,301,401,601]  
	 * @param list
	 */
	public static int[] getElements(LinkedList list,Integer[] listB){
		int [] result = new int[list.size()];
		int res_index = 0;
		for(int i=0;i<listB.length;i++){
			int index = listB[i];
			Node node = getElement(list.head,index);
			result[res_index] = Integer.parseInt((String)node.data);
			
		}
		return null;
	}
	
	/**
	 * ��֪�����е�Ԫ����ֵ�����������У����Ե��������洢�ṹ��
	 * �ӵ�ǰ��������ɾ����list�г��ֵ�Ԫ�� 

	 * @param list
	 */
	
	public  void subtract(LinkedList list){
		for(int i=0;i<list.size();i++){
			this.remove(list.get(i));
		}
	}
	
	/**
	 * ��֪��ǰ�����е�Ԫ����ֵ�����������У����Ե��������洢�ṹ��
	 * ɾ���������ֵ��ͬ�Ķ���Ԫ�أ�ʹ�ò���������Ա�������Ԫ�ص�ֵ����ͬ��
	 */
	public  void removeDuplicateValues(){
		Node pre = head;
		Node cur = head;
		
		while(cur.next!= null){
			cur = cur.next;
			Object preData = pre.data;
			while(cur.data.equals(preData)){
				if(cur.next == null){
					pre.next = null;
					break;
				}
				
				pre.next = cur.next;
				size --;
				cur = cur.next;
				if(cur == null){
					break;
				}
			}
			pre = pre.next;
		}
		
		
	}
	
	/**
	 * ��֪�����е�Ԫ����ֵ�����������У����Ե��������洢�ṹ��
	 * ��дһ��Ч���㷨��ɾ���������ֵ����min��С��max��Ԫ�أ������д��������Ԫ�أ�
	 * @param min
	 * @param max
	 */
	public  void removeRange(int min, int max){
		Node node = head;
		int start = -1;
		int end = -1;
		
		int index =0;
		while(node!= null){
			if((int)node.data > min){
				start = index;
			}
			if((int)node.data < max){
				end = index;
				break;
			}
			node = node.next;
			index++;
		}
		
		for(int i=start;i<end;i++){
			remove(i);
		}
		
		
	}
	
	/**
	 * ���赱ǰ����Ͳ���listָ�����������Ԫ����ֵ�����������У�ͬһ���е�Ԫ��ֵ������ͬ��
	 * ��Ҫ�����������C����Ԫ��Ϊ��ǰ�����list��Ԫ�صĽ������ұ�C�е�Ԫ������ֵ������������
	 * @param list
	 */
	public  LinkedList intersection( LinkedList list){
		int i1 =0;
		int i2= 0;
		LinkedList result = new LinkedList();
		
		while(i1<this.size() && i2<list.size()){
			int value1 = (int)this.get(i1);
			int value2 = (int)list.get(i2);
			if(value1 == value2){
				result.add(value1);
				i1++;i2++;
			}else if(value1 < value2){
				i1++;
			}else if(value2 < value1){
				i2++;
			}
		}
		return result;
	}
}
