public class LinkedList<T>{
	public static void main(String[] args){
		LinkedList<String> arr = new LinkedList<String>();
		arr.add("0");
		System.out.println(arr.get(0));
		
		System.out.println(arr.get(1));
		arr.add("1");
		System.out.println(arr.get(1));
		
		
		System.out.println(arr.get(3));
		arr.add("3",3);
		System.out.println(arr.get(3));
		arr.add("before 2",2);
		System.out.println(arr.get(2));
		System.out.println(arr.get(4));
		arr.set(4,"4");
		System.out.println(arr.get(4));
		arr.set(8,"8");
		System.out.println(arr.get(8));
		
	}
	protected Node<T> first;
	protected Node<T> lastest;
	private int size;
	public LinkedList(){
		lastest = first = new Node<T>(null);
		size = 0;
	}
	public T get(int arg_num){
		//throw Error if arg_num<0,unfinished
		return _get(arg_num);
	}
	public void set(int arg_num,T arg_value){
		//throw Error if arg_num<0,unfinished
		_set(arg_num,arg_value);
	}
	public T remove(int arg_num){
		//throw Error if arg_num<0,unfinished
		return _remove(arg_num).value;
	}
	public void add(T arg_value,int arg_num){
		//throw Error if arg_num<0,unfinished
		_add(arg_value,arg_num);
	}
	public void add(T arg_value){
		_add(arg_value,size);
	}
	public int size(){
		return size;
	}
	protected Node<T> _remove(int arg_num){
		if(arg_num<size){
			Node<T> node = _getNode(arg_num);
			node.remove();
			size--;
			if(arg_num == size){
				lastest = node.left;
			}else if(arg_num == 0 && size != 0){
				first = node.right;
			}
			return node;
		}else{
			return null;
		}
	}
	protected Node<T> _getNode(int arg_num){
		Node<T> node = first;
		for(int num = 0;num<arg_num;num++){
			node = node.right;
		}
		return node;
	}
	protected T _get(int arg_num){
		if(arg_num>=size){return null;}
		return _getNode(arg_num).value;
	}
	protected void _set(int arg_num,T arg_value){
		if(arg_num>=size){
			_add(arg_value,arg_num);
		}else{
			_getNode(arg_num).value = arg_value;
		}
	}
	protected void _add(T arg_value,int arg_num){
		if(arg_num > size){
			Node<T> n = new Node<T>(null);
			lastest.setRight(n);
			for(int num=size+1;num<arg_num;num++){
				n.setRight(new Node<T>(null));
				n = n.right;
			}
			n.setRight(new Node<T>(arg_value));
			lastest = n.right;
			size = arg_num+1;
		}else if(arg_num == size){
			if(size == 0){
				lastest.value = arg_value;
			}else{
				lastest.setRight(new Node<T>(arg_value));
				lastest = lastest.right;
			}
			size++;
		}else if(arg_num == 0){
			first.setLeft(new Node<T>(arg_value));
			first = first.left;
			size++;
		}else{
			Node<T> node = _getNode(arg_num);
			node.addLeft(new Node<T>(arg_value));
			size++;
		}
		
	}
}