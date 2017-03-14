public class Stack<T>{
	Node<T> first;
	Node<T> lastest;
	private int size;
	public Stack(){
		first = lastest = new Node<T>(null);
		size = 0;
	}
	public int size(){
		return size;
	}
	public Stack<T> push(T arg_value){
		if(size == 0){
			lastest.value = arg_value;
		}else{
			lastest.setRight(new Node<T>(arg_value));
			lastest = lastest.right;
		}
		size++;
		return this;
	}
	public T pop(){
		if(size > 0){size--;};
		if(size == 0){
			T temp;
			temp = lastest.value;
			lastest.value = null;
			return temp;
		}else{
			lastest = lastest.left;
			return lastest.right.remove().value;
		}
	}
	public T peek(){
		return lastest.value;
	}
	public boolean empty(){
		return size == 0;
	}
}