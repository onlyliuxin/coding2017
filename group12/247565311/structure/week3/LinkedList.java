package structure.week3;


import java.util.NoSuchElementException;

import structure.week1.Iterator;
import structure.week1.List;

public class LinkedList implements List {
	private Node head = new Node();
	private int size = 0;
	private void checkAddIndex(int index){
        if(index > size || index<0) throw new IndexOutOfBoundsException("Index:"+index+", Size:"+size);
	}
	private void checkGetIndex(int index){
        if(index >= size || index<0) throw new IndexOutOfBoundsException("Index:"+index+", Size:"+size);
	}
	public void add(Object o){    
        Node newNode = new Node(o),p = head;
        while(p.next!=null)
        	p = p.next;
        p.next = newNode;
        size += 1;
	}
	/**
	 * 
	 * */
	public void add(int index , Object o){
		checkAddIndex(index);
        Node p = head;
	    for(int i=0;i<index;i++)
	    	p = p.next;
	    Node newNode = new Node(o);
	    newNode.next = p.next;
	    p.next = newNode;
	    size += 1;
	}
	/**
	 * 
	 * */
	public Object get(int index){
		checkGetIndex(index);
		Node p = head;
		for(int i=0;i<index;i++){
			p = p.next;
		}
		return p.next.data;
	}
	public Object remove(int index){
		checkGetIndex(index);
		Node p = head;
		for(int i=0;i<index;i++)
			p = p.next;
		Node tar = p.next;
		Object res = tar.data;
		p.next = p.next.next;
		size -= 1;
		tar.next = null;
		tar.data = null;
		return res;
	}

	public int size(){
		return size;
	}

	public void addFirst(Object o){
        add(o);
	}
	public void addLast(Object o){
        add(size, o);
	}
	public Object removeFirst(){
		return remove(0);
	}
	public Object removeLast(){
		return remove(size-1);
	}
	public Iterator iterator(){
		return null;
	}

	/**
	 * �Ѹ���������
	 * ��������Ϊ 3->7->10 , ���ú��Ϊ  10->7->3
	 */
	public  void reverse(){		
		Node rhead = new Node(),p=head;
		for(int i=0;i<size;i++){
			p = p.next;
			Node newNode = new Node(p.data);
			newNode.next = rhead.next;
			rhead.next = newNode;
		}
		p = head;
		for(int i=0;i<=size;i++){ // head ҲҪ��ɾ��
			Node delNode = p;
			p = p.next;
			delNode.next = null;
			delNode.data = null;
		}
		head = rhead;
	}
	
	/**
	 * ɾ��һ���������ǰ�벿��
	 * ���磺list = 2->5->7->8 , ɾ���Ժ��ֵΪ 7->8
	 * ���list = 2->5->7->8->10 ,ɾ���Ժ��ֵΪ7,8,10

	 */
	public  void removeFirstHalf(){
		int numToRemove = size/2;
		for(int i=0;i<numToRemove;i++)
			remove(0);
	}
	
	/**
	 * �ӵ�i��Ԫ�ؿ�ʼ�� ɾ��length ��Ԫ�� �� ע��i��0��ʼ
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		for(int j=0;j<length;j++)
			remove(i);
	}
	/**
	 * �ٶ���ǰ�����list���������������е�����
	 * �ӵ�ǰ������ȡ����Щlist��ָ����Ԫ��
	 * ���統ǰ���� = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * ���صĽ��Ӧ����[101,301,401,601]  
	 * @param list
	 */
	public int[] getElements(LinkedList list){
		int []res = new int[list.size()];
		Node p = head.next,q = list.head.next;
		int lenl = list.size(),index=0;
		for(int i=0;i<lenl && p!=null;i++){
			int tar =( (Integer)q.data).intValue();
			checkGetIndex(tar);
			q = q.next;
			while(index<tar && p!=null){
				p = p.next;
				index += 1;
			}
			if(p!=null) res[i] = ((Integer)(p.data)).intValue(); // �о���ǰ����������������������
		}
		return res;
	}
	/**
	 * ��֪�����е�Ԫ����ֵ�����������У����Ե��������洢�ṹ��
	 * �ӵ�ǰ��������ɾ����list�г��ֵ�Ԫ�� 
	 * @param list
	 */
	public  void subtract(LinkedList list){
		Node p = head,q = list.head.next;
		int lenl = list.size();
		for(int i=0;i<lenl && p.next!=null;i++){
			int tar = ((Integer) q.data).intValue();
			q = q.next;
			int cur = ((Integer) p.next.data).intValue(); //  ���д������ѭ�������ٲ��������Ƿ���ѭ�������ʹ������
			while(p.next!=null && cur != tar){
				cur = ((Integer) p.next.data).intValue();
				p = p.next;
			}
			while(p.next!=null && cur == tar){
				Node delNode = p.next;
				p.next = delNode.next;
				size -= 1;
				delNode.data=null;
				delNode.next = null;
				if(p.next!=null) cur = ((Integer) p.next.data).intValue();
			}
		}
	}
	
	/**
	 * ��֪��ǰ�����е�Ԫ����ֵ�����������У����Ե��������洢�ṹ��
	 * ɾ����������ֵ��ͬ�Ķ���Ԫ�أ�ʹ�ò���������Ա�������Ԫ�ص�ֵ������ͬ��
	 */
	public  void removeDuplicateValues(){
		Node p = head;
		while(p.next !=null){
			int cur = ((Integer) p.next.data).intValue();
			p = p.next;
			while(p.next !=null && ((Integer)p.next.data).intValue() == cur){
				Node delNode = p.next;
				p.next=delNode.next;
				size -= 1;
				delNode.data=null;
				delNode.next = null;
			}
		}
	}
	
	/**
	 * ��֪�����е�Ԫ����ֵ�����������У����Ե��������洢�ṹ��
	 * ��дһ��Ч���㷨��ɾ����������ֵ����min��С��max��Ԫ�أ������д���������Ԫ�أ�
	 * @param min
	 * @param max
	 */
	public  void removeRange(int min, int max){
		if(min+2>max) return; // Ŀ������ (min,max)
		Node p = head;
		while(p.next != null && ((Integer)p.next.data).intValue()<=min) // ��������ɵ�Ԫ��
			p = p.next;
		while(p.next != null && ((Integer)p.next.data).intValue()<max){ // ɾ������������
			Node delNode = p.next;
			p.next = delNode.next;
			size -= 1;
			delNode.next = null;
			delNode.data = null;
		}
	}
	/**
	 * ���赱ǰ����Ͳ���listָ�����������Ԫ����ֵ�����������У�ͬһ���е�Ԫ��ֵ������ͬ��
	 * ��Ҫ������������C����Ԫ��Ϊ��ǰ�����list��Ԫ�صĽ������ұ�C�е�Ԫ������ֵ������������
	 * @param list
	 */
	public  LinkedList intersection( LinkedList list){
		LinkedList res = new LinkedList();
		Node p = head.next,q = list.head.next;
		while(q != null && p != null){
			int tar = ((Integer)q.data).intValue();
			q = q.next;
		    int cur = ((Integer)p.data).intValue();
		    while(p != null && cur < tar){
		    	p = p.next;
		    	cur =  ((Integer)p.data).intValue();
		    }
		    if (cur == tar){
		    	res.add(cur);
		    	p = p.next;
		    }
		}
		return res;
	}
	@Override
	public boolean hasNext() {
		return false;
	}
	@Override
	public Object next() {
		return null;
	}
	private static  class Node{
		Object data;
		Node next;
		public Node(Object o){data = o;next=null;}
		public Node(){data=null;next=null;}
	}
}
