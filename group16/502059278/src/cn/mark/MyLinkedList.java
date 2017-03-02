package cn.mark;
/**
 * 自定义实现LinkedList数据结构
 * @author hilih
 *
 */
public class MyLinkedList implements MyList{
	
	private Node head;
	private int size;//集合的长度

	/**
	 * 添加元素
	 */
	@Override
	public boolean add(Object o) {
		//为空判断
		if ( o == null ){
			System.out.println("不允许null的元素插入!");
			return false;
		}
		if(head == null){
			head = new Node();
			head.data = o;
		}else{
			
		}
		
		return false;
	}

	@Override
	public boolean add(int index, Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private static class Node{
		Object data;
		Node next;
	}


	public static void main(String[] args) {
		

	}

}
