public class Queue<T>{
	Node<T> first;
	Node<T> lastest;
	private int size;
	public Queue(){
		first = lastest = new Node<T>(null);
		size = 0;
	}
	public int size(){
		return size;
	}
	public Queue<T> push_back(T arg_value){
		if(size == 0){
			first.value = arg_value;
		}else{
			lastest.setRight(new Node<T>(arg_value));
			lastest = lastest.right;
		}
		size++;
		return this;
	}
	public T pop_front(){
		if(size > 0){size--;};
		if(size == 0){
			T temp;
			temp = first.value;
			first.value = null;
			return temp;
		}else{
			first = first.right;
			return first.left.remove().value;
		}
	}
	public T front(){
		return first.value;
	}
	public T back(){
		return lastest.value;
	}
}