package BasicData;

//insert 方法有问题
public class MyTreeNode<T extends Comparable<T>> {

	private T data;
	private MyTreeNode<T> left = null;
	private MyTreeNode<T> right = null;
	private MyTreeNode<T> root = null;
	private MyTreeNode<T> cureeTreeNode = null;
	
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public MyTreeNode<T> getLeft() {
		return left;
	}
	public void setLeft(MyTreeNode<T> left) {
		this.left = left;
	}
	public MyTreeNode<T> getRight() {
		return right;
	}
	public void setRight(MyTreeNode<T> right) {
		this.right = right;
	}
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("[");
		if (cureeTreeNode == null) {
			string.append("]");
			return string.toString();
		} else {
			string.append(cureeTreeNode.toString()).append("]");
			return string.toString();
		}
	}
	
	public MyTreeNode<T> insert(T o){
		MyTreeNode<T> newNode = new MyTreeNode<T>();
		MyTreeNode<T> current = null;
		newNode.setData(o);
		if (root == null) {
			root = newNode;
			cureeTreeNode = newNode;//测试
			return newNode;
		}
		else {
			Digui(o, root);
			current = cureeTreeNode;
			if (current.getData().compareTo(o) == -1) {
				current.right = newNode;
			} else {
				current.left = newNode;
			}
			cureeTreeNode = newNode; //测试
			return newNode;
		}		
		
	}
	
	public void Digui(T o,MyTreeNode<T> parentnode){
		cureeTreeNode = parentnode;
		if (parentnode.left!= null) {
			if (parentnode.getData().compareTo(o) == -1) {
				parentnode = parentnode.right;
				Digui(o, parentnode);
			} 		
			else
			return;
		}
		if (parentnode.right != null) {
			if (parentnode.getData().compareTo(o) == 1) {
				parentnode = parentnode.left;
				Digui(o, parentnode);
			} 		
			else
			return;
		}
	}
	
	public void preOrder(MyTreeNode<T> root) {  
        visit(root);  
        if(root.getLeft() != null) {  
            preOrder(root.getLeft());  
        }  
        if(root.getRight() != null) {  
            preOrder(root.getRight());  
        } 
	}
	
	public void visit(MyTreeNode<T> btree) {  
        System.out.print(btree.getData() + "\t");  
    }  
}
