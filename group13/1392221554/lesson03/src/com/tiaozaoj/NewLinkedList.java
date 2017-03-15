package com.tiaozaoj;

import java.util.Iterator;

import sun.net.www.content.text.plain;

public class NewLinkedList {
	
	private Node head;
	private int size = 0;
	
	//�½�����
	public NewLinkedList(){
		head = new Node("0x666");
		head.next = null;
	}
	
	//����½ڵ�
	public void add(Object o){
		Node newNode = new Node(o);
		newNode.next = null;
		Node p = head.next;
		while(p.next != null){
			p = p.next;
		}
		p.next = newNode;
		this.size++;
	}
	
	//�ж�index
	private void verifyIndex(int index){
		try{
			if(index<0 || index>size)
				throw new Exception("Խ���쳣");
		}catch(Exception e){
			e.printStackTrace();
			return;
		}
	}
	
	public void add(int index,Object o){
		this.verifyIndex(index);
		int j = -1;
		Node newNode = new Node(o);
		//�������
		for(Node p = head.next;p.next != null;p = p.next){
			if((index) == j+1){
				Node q = p.next;
				p.next = newNode;
				newNode.next = q;
				break;
			}
			j++;
		}
		this.size++;
	}
	
	//��ȡ�ڵ�
	public Object get(int index){
		this.verifyIndex(index);
		int j = 0;
		//�������
		Node p = head.next;
		for(;p.next != null;p = p.next){
			if((index) == j){
				break;
			}
			j++;
		}
		return p;
	}
	
	//ɾ���ڵ�
	public Object remove(int index){
		this.verifyIndex(index);
		int j = -1;
		//�������
		Node p = head.next;
		for(;p.next != null;p = p.next){
			if((index) == j+1){
				break;
			}
			j++;
		}
		Node toRemoveNode = p.next;
		p.next = toRemoveNode.next;
		return toRemoveNode;
	}
	
	//��ȡ��С
	public int size(){
		return size;
	}
	
	public void addFirst(Object o){
		//���ֻ��ͷ�ڵ㣬��ֱ���½�
		if(head.next == null)
			add(o);
		//������ͷ���͵�һ���ڵ�֮�����
		Node p = head.next;
		Node newNode = new Node(o);
		head.next = newNode;
		newNode.next = p;
	}
	public void addLast(Object o){
		Node newNode = new Node(o);
		newNode.next = null;
		Node p = head.next;
		while(p.next != null){
			p = p.next;
		}
		p.next = newNode;
		this.size++;
	}
	public Object removeFirst(){
		if(head.next == null)
			return null;
		if(head.next != null && head.next.next == null){
			head.next = null;
			return null;
		}
		Node p = head.next;
		head.next = p.next;
		p = null;
		return head.next;
	}
	
	public Object removeLast(){
		Node p = head.next;
		Node q = null;
		while(p.next != null){
			q = p;//��������ڵ㣬�ٺ���
			p = p.next;
		}
		q.next = null;
		p = null;
		return q;
	}
	
	private static class Node{
		public Object data;
		public Node next;
		
		public Node(Object o){
			this.data = o;
		}
	}
	
	/**
	 * �Ѹ���������
	 * ��������Ϊ 3->7->10 , ���ú��Ϊ  10->7->3
	 */
	public  void reverse(){		
        //������Ϊ�ջ�ֻ��ͷ����ֻ��һ��Ԫ�أ����ý������ò���  
        if(this == null|| this.head.next == null|| this.head.next.next == null)  
            return;  
        Node p = head.next.next;//��pָ�����Ա��е�2��Ԫ��a2   
        head.next.next = null;//�����Ա��е�1��Ԫ��a1��nextΪ��  
        while(p != null){  
            Node q = p.next;  
            //��p����ͷ���֮��  
            p.next = head.next;  
            head.next = p;  
            p = q;//����������һ��Ԫ��  
        } 
	}
	
	/**
	 * ɾ��һ���������ǰ�벿��
	 * ���磺list = 2->5->7->8 , ɾ���Ժ��ֵΪ 7->8
	 * ���list = 2->5->7->8->10 ,ɾ���Ժ��ֵΪ7,8,10
	 */
	public  void removeFirstHalf(){
		
	}
	
	/**
	 * �ӵ�i��Ԫ�ؿ�ʼ�� ɾ��length ��Ԫ�� �� ע��i��0��ʼ
	 * @param i
	 * @param length
	 */
	public  void remove(int i, int length){
		
	}
	/**
	 * �ٶ���ǰ�����listB���������������е�����
	 * �ӵ�ǰ������ȡ����ЩlistB��ָ����Ԫ��
	 * ���統ǰ���� = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * ���صĽ��Ӧ����[101,301,401,601]  
	 * @param list
	 */
	public  int[] getElements(NewLinkedList list){
		return null;
	}
	
	/**
	 * ��֪�����е�Ԫ����ֵ�����������У����Ե��������洢�ṹ��
	 * �ӵ�ǰ��������ɾ����listB�г��ֵ�Ԫ�� 

	 * @param list
	 */
	
	public  void subtract(NewLinkedList list){
		
	}
	
	/**
	 * ��֪��ǰ�����е�Ԫ����ֵ�����������У����Ե��������洢�ṹ��
	 * ɾ����������ֵ��ͬ�Ķ���Ԫ�أ�ʹ�ò���������Ա�������Ԫ�ص�ֵ������ͬ��
	 */
	public  void removeDuplicateValues(){
		
	}
	
	/**
	 * ��֪�����е�Ԫ����ֵ�����������У����Ե��������洢�ṹ��
	 * ��дһ��Ч���㷨��ɾ����������ֵ����min��С��max��Ԫ�أ������д���������Ԫ�أ�
	 * @param min
	 * @param max
	 */
	public  void removeRange(int min, int max){
		
	}
	
	/**
	 * ���赱ǰ����Ͳ���listָ�����������Ԫ����ֵ�����������У�ͬһ���е�Ԫ��ֵ������ͬ��
	 * ��Ҫ������������C����Ԫ��Ϊ��ǰ�����list��Ԫ�صĽ������ұ�C�е�Ԫ������ֵ������������
	 * @param list
	 */
	public  NewLinkedList intersection( NewLinkedList list){
		return null;
	}
}
