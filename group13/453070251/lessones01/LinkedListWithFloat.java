public class LinkedListWithFloat<T> extends LinkedList<T>{
	public static void main(String[] args){
		LinkedListWithFloat<String> arr = new LinkedListWithFloat<String>();
		arr.add("0");
		System.out.println(arr.get(0));
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
		System.out.println(arr.remove(8));
		System.out.println(arr.get(8));
		System.out.println(arr.remove(2));
		System.out.println(arr.get(2));
		System.out.println(arr.remove(0));
		System.out.println(arr.get(0));
		System.out.println(arr.size());
	}
	
	private Node<T> floatNode;
	private int floatNum;
	public LinkedListWithFloat(){
		super();
		floatNode = first;
		floatNum = 0;
	}
	protected Node<T> _getNode(int arg_num){
		int position = arg_num - floatNum;
		if(position == 0){return floatNode;}
		//arg_num = (int leftPosition = arg_num);
		int rightPosition = size() - 1 - arg_num;
		boolean floatNear = position * position <= arg_num * rightPosition;
		if(floatNear){
			if(position>0){
				return findFloatRight(arg_num);
			}else/*if(position<0)*/{
				return findFloatLeft(arg_num);
			}
		}else if(position<0){
			floatNode = first;
			floatNum = 0;
			return findFloatRight(arg_num);
		}else/*if(position>0)*/{
			floatNode = lastest;
			floatNum = rightPosition + arg_num;//size()-1
			return findFloatLeft(arg_num);
		}
	}
	protected void _add(T arg_value,int arg_num){
		super._add(arg_value,arg_num);
		if(arg_num <= floatNum){floatNum++;}
	}
	protected Node<T> _remove(int arg_num){
		Node<T> temp = super._remove(arg_num);
		if(temp == null){return temp;}
		if(temp.right != null){
			floatNode = temp.right;
		}else{
			floatNode = lastest;
			floatNum = size()-1;
		}
		return temp;
	}
	private Node<T> findFloatLeft(int arg_num){
		for(;floatNum>arg_num;){
			floatNode = floatNode.left;
			floatNum--;
		}
		return floatNode;
	}
	private Node<T> findFloatRight(int arg_num){
		for(;floatNum<arg_num;){
			floatNode = floatNode.right;
			floatNum++;
		}
		return floatNode;
	}
}