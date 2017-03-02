public class Node<T>{
	public Node<T> left;
	public Node<T> right;
	public T value;
	public Node(T arg_value){
		value = arg_value;
	}
	public void setLeft(Node<T> arg_another){
		_connect(arg_another,this);
	}
	public void setRight(Node<T> arg_another){
		_connect(this,arg_another);
	}
	public void addLeft(Node<T> arg_another){
		_connect(left,arg_another,this);
	}
	public void addRight(Node<T> arg_another){
		_connect(this,arg_another,right);
	}
	public Node<T> remove(){
		_connect(left,right);
		return this;
	}
	private void _connect(Node<T> arg_left,Node<T> arg_right){
		if(arg_left != null){arg_left.right = arg_right;}
		if(arg_right != null){arg_right.left = arg_left;}
	}
	private void _connect(Node<T> arg_left,Node<T> node,Node<T> arg_right){
		_connect(arg_left,node);
		_connect(node,arg_right);
	}
}