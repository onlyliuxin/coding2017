package third;

	/**
	 * 用双向链表实现LRU算法
	 * 
	 *
	 */
public class LRUPageFrame {
		
		private int capacity;		
	    private Node first;// 链表首
		private Node last;// 链表尾    
		private final static int maxCapacity=3;
		
		public LRUPageFrame() {
			this.capacity =0;			
		}

		/**
		 * 获取缓存中对象
		 * 
		 * @param key
		 * @return
		 */
		public void access(int pageNum) {
			//首先判断是否包含该页面
			Node node=find(pageNum);
			if(node!=null)
			{
				//将该元素放到链表头
				moveExistingNodeToFirst(node);
			}
			else{
				node=new Node();
				node.pageNum=pageNum;
				if(capacity>=maxCapacity)
				{
					//将新添加的页号放在最前
					removeLast();
				}
				addNewNodeToHead(node);
			}
		}
        //将新节点添加到链表首
		private void addNewNodeToHead(Node node) {
			
			if(isEmpty())
			{
				node.prev=null;
				node.next=null;
				first=node;
				last=node;
				
			}
			else{
				node.prev=null;
				node.next=first;
				first.prev=node;
				first=node;
			}
			this.capacity++;
		}

		private boolean isEmpty() {
			
			return (first==null)&&(last==null);
		}

		private void moveExistingNodeToFirst(Node node) {
			if(first==node)
			{
				return;
			}
			//当前链表是链表尾，放到链表头
			else if(node==last)
			{
				Node preNode=node.prev;
				preNode.next=null;
				node.prev=null;
				last=preNode;
			}
			//node节点在中间，把node节点的前后连接起来
			else{
				//Node preNode=node.prev;
				node.prev.next=node.next;
				//Node nextNode=node.next;
				node.next.prev=node.prev;
			}
			//将node节点放在链表首
			node.prev=null;
			node.next=first;
			first.prev=node;
			first=node;
		}

		private Node find(int pageNum) {
			
			Node node=first;
			while(node!=null)
			{
				if(node.pageNum==pageNum)
				{
					return node;
				}
				node=node.next;
			}
			return null;
		}
        /**
         * 删除链表尾部节点  表示删除最少使用的缓存对象
         */
		public void removeLast() {
			Node preNode=last.prev;
			preNode.next=null;
			last.prev=null;
			last=preNode;
			capacity--;
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
		private static class Node {
					
					Node prev;
					Node next;
					int pageNum;
		
					public Node() {}
				}
}
	

