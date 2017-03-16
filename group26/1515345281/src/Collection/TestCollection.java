package Collection;

import org.junit.Test;


public class TestCollection {

	@Test
	public void arrayListTest(){
		ArrayList array=new ArrayList();
		array.add(1);
		array.add(2);
		System.out.println(array.size());
		Iterator it=array.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
	@Test
	public void stackTest(){
		Stack stack=new Stack();
		stack.push(1);
		stack.push(2);
		System.out.println(stack.peek());
		System.out.println(stack.pop());
	}
	
	@Test
	public void linkedListTest(){
		LinkedList list=new LinkedList();
		list.add(1);
		list.add(2);
		list.addLast(3);
		Iterator it=list.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
	
	@Test
	public void queueTest(){
		Queue queue=new Queue();
		System.out.println(queue.isEmpty());
		queue.enQueue(1);
		queue.enQueue(2);
		queue.enQueue(2);
		System.out.println(queue.isEmpty());
		System.out.println(queue.size());
		
	}
	@Test
	public void binaryTreeNodeTest(){
		BinaryTreeNode root=new BinaryTreeNode(0);
		Queue queue=new Queue();
		queue.enQueue(root);
		
		for(int i=1;i<10;){

			BinaryTreeNode node=(BinaryTreeNode) queue.deQueue();
			//System.out.print(node.getData()+" ");
			if(node.getLeft()==null){
				BinaryTreeNode t=node.insert(i);
				node.setLeft(t);
				queue.enQueue(t);
				i++;
			}
			if(node.getRight()==null){
				BinaryTreeNode t=node.insert(i);
				node.setRight(t);
				queue.enQueue(t);
				i++;
			}
		}
		System.out.println("中序遍历结果为:");
		print(root);
	}
	private void print(BinaryTreeNode root) {
		
		
		if(root.getLeft()!=null){
			print(root.getLeft());
		}
		
		System.out.print(root.getData()+" ");
		
		if(root.getRight()!=null){
			print(root.getRight());
		}
	}
}
