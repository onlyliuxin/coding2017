

public class LRUPageFrame {
	
	private static class Node {
		
		Node prev;
		Node next;
		int pageNum;

		Node(int Num) {
			pageNum=Num;
		}
	}

	private int capacity;
	
	
	private Node first;// 链表头
	private Node last;// 链表尾

	private int nodeNum = 0;
	
	public LRUPageFrame(int capacity) {
		
		this.capacity = capacity;
		
	}

	/**
	 * 获取缓存中对象
	 * 
	 * @param key
	 * @return
	 */
	public void access(int pageNum) {
		int isPageNum = isContained(pageNum);
		if(isPageNum!=-1){
			int index = 0;
			Node node = first;
			while(index!=isPageNum){	// move to the Node
				node=node.next;
				index++;
			}
			if(index != 0){
				if(node.next == null){
					node.prev.next = null;
				}else{
					node.prev.next = node.next;
				}
				node.next = first;
				first = node;
			}
		}else{
			if(first != null){
				Node tmp = new Node(pageNum);
				tmp.next = first;
				first.prev = tmp;
				first = tmp;
				nodeNum++;
				if(nodeNum > capacity){
					Node node = first;
					for(int i=0;i<capacity-1;i++){
						node=node.next;
					}
					node.next.prev = null;
					node.next = null;
				}
			}else{
				first = new Node(pageNum);
				nodeNum++;
			}
		}
	
	}
	
	private int isContained(int pageNum){
		Node node = first;
		int result = 0;
		while(node != null){
			if(node.pageNum == pageNum){
				return result;
			}else{
				node = node.next;
				result++;
			}
		}
		result = -1;
		return result;
	}
	
	

	public String toString(){
		StringBuilder buffer = new StringBuilder();
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
