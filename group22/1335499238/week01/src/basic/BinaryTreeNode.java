package basic;

public class BinaryTreeNode <T extends Comparable<T>>{
	
	 
	private T data; 
	private BinaryTreeNode<T> left; 
	private BinaryTreeNode<T> right; 
	
	public BinaryTreeNode(T o){
		this.data = o;
		this.left = null;
		this.right = null;
	}
	 
	public Object getData() { 
		return data; 
	}
	
	public void setData(T data) { 
		this.data = data; 
	}
	
	public BinaryTreeNode<T> getLeft() { 
		return left; 
	}
	
	public void setLeft(BinaryTreeNode<T> left) { 
		this.left = left; 
	}
	
	public BinaryTreeNode<?> getRight() { 
 		return right; 
 	}
	
 	public void setRight(BinaryTreeNode<T> right) { 
 		this.right = right; 
 	}
 	
 	public BinaryTreeNode<T> insert(T o){
 		BinaryTreeNode<T> current = this;
 		BinaryTreeNode<T> addTreeNode = new BinaryTreeNode<>(o);
 		while(true){
 			//如果传入的值比但前节点的值小
 			if(o.compareTo(current.data) < 0){
 				if(current.left != null){
 					current = current.left;
 				}else {
 					current.left = addTreeNode;
 					break;
 				}
 			}else{
 				if(current.right != null){
 					current = current.right;
 				}else{
 					current.right =addTreeNode;
 					break;
 				}
 			}
 		}
		return addTreeNode; 
	}
 	
 	public LinkedList prevOrder(BinaryTreeNode<T> binaryTreeNode){
 		LinkedList linkedList = new LinkedList();
 		preOrder(binaryTreeNode, linkedList);
 		return linkedList;
 	}
 	
 	private void preOrder(BinaryTreeNode<T> binaryTreeNode,LinkedList linkedList){
 		if(binaryTreeNode.left != null){
 			preOrder(binaryTreeNode.left, linkedList);
 			
 		}
 		linkedList.add(binaryTreeNode.data);
 		if(binaryTreeNode.right != null){
 			preOrder(binaryTreeNode.right, linkedList);
 		}
 	}
 	
}
