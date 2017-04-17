package DataStructure_4_LRU;

import org.junit.runners.Parameterized.Parameters;

/*
 * ��˫������ʵ��LRU�㷨
 */
public class LRUPageFrame {
	private static class Node{
		Node prev;
		Node next;
		int pageNum = -1;// ����ҳ
		
		Node(){
			
		}
	}
	
	private int capacity;
	
	private Node first;// ����ͷ
	private Node last;// ����β
	boolean tag = false;

	public LRUPageFrame(int capacity){
		this.capacity = capacity;
		
		for(int i = 0; i < capacity; i++){
			Node curNode = new Node();
			if(null == first){
				last = first = curNode;
			}else{
				last.next = curNode;
				curNode.prev = last;
				last = last.next;
			}
			last.next = null;
		}
	}
	public void printList(){
		Node curNode = first;
		while(curNode != null){
			curNode = curNode.next;
		}
	}
	/*
	 *  ��ȡ�����ж���
	 *  @param key
	 *  @return
	 */
	public void access(int pageNum){
		printList();
		Node index = findLogicPage(pageNum);
		modifyPhysicalPage(index,pageNum);	
	}
	
	/*
	 * @param pageNum ��ʾҪ��ѯ���߼�ҳ��
	 * @return ��������ҳ���ҵ�Ҫ��ѯ���߼�ҳ�棬�򷵻ظ�����ҳ�ڵ�����ã����򷵻�null
	 */
	public Node findLogicPage(int pageNum){
		
		Node index = null;
		Node curNode = first;
		while(curNode != null){
			if(curNode.pageNum == pageNum){
				index = curNode;
				tag = true;
			}
			curNode = curNode.next;
		}
		return index;
	}
	/*
	 * @prama index ������ ���߼�ҳ������ҳ�Ľڵ������
	 */
	public void modifyPhysicalPage(Node index,int pageNum){
		push(pageNum,index);
	}
	/*
	 *  @param pageNum Ҫ push���߼�ҳ�棬  Ĭ��ջ���� first, bottom ջ��  ָ����ջ�Ĵ�С
	 */
	public void push(int pageNum,Node bottom){
		Node index = checkWhichListNodeNotUsed();
		if(index != null){
			index.pageNum = pageNum;
			return;
		}

		Node lastNode;
		if(null == bottom){
			lastNode = last;
		}else{
			lastNode = bottom;
		}
		Node curNode = lastNode.prev;
		while(curNode != null){
			lastNode.pageNum = curNode.pageNum;
			lastNode = curNode;
			curNode = curNode.prev;
		}
		lastNode.pageNum = pageNum;
		return;
	}
	
	/* 
	 * @return ��������ҳ�� pageNum û�б�ʹ�õĽڵ������(����ջ���������)�����ȫ������ʹ�ã��򷵻� null
	 */
	public Node checkWhichListNodeNotUsed(){
		Node node = first;
		Node index = null;
		while(node != null){
			if(node.pageNum == -1){
				index = node;
			}
			node = node.next;
		}
		return index;
	}
	
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		Node node = first;
		while(node != null){
			buffer.append(node.pageNum);
			
			node = node.next;
			if(node != null){
				buffer.append(",");
			}
		}
		return buffer.toString();
	}
	
	
	
	
	
	
	
	
	
}
