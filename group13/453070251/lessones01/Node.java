package lessones01;
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
	private static <E> void _connect(Node<E> arg_left,Node<E> arg_right){
		if(arg_left != null){arg_left.right = arg_right;}
		if(arg_right != null){arg_right.left = arg_left;}
	}
	private static <E> void _connect(Node<E> arg_left,Node<E> node,Node<E> arg_right){
		_connect(arg_left,node);
		_connect(node,arg_right);
	}
}