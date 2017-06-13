package lessones01;
import lessones01.Node;
import others.Range;
import java.util.Iterator;
public class LinkedList<T> implements Iterable<T>{
	private static class Iter<E> implements Iterator<E>{
		private Node<E> node;
		private LinkedList<E> list;
		public Iter(Node<E> arg_nullNode,LinkedList<E> arg_list){
			node = arg_nullNode;
			list = arg_list;
		}
		public boolean hasNext(){
			return node.right!=null;
		}
		public E next(){
			node = node.right;
			return node.value;
		}
	}
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
		arr.set("4",4);
		System.out.println(arr.get(4));
		arr.set("8",8);
		System.out.println(arr.get(8));
		arr.remove(5);
		arr.remove(6);
		for(String x:arr){
			System.out.print(x);
			System.out.print(",");
		}
		
	}
	protected Node<T> nullNode;
	protected Node<T> point;
	protected int pointNum;
	protected int size;
	public LinkedList(){
		nullNode = point = new Node<T>(null);
		pointNum = -1;
		size = 0;
	}
	private void setPoint(int n){
		while(size<=n){add(null);}
		if(n<0){
			point = nullNode;
			pointNum = -1;
		}else{
			if((n<<1) < pointNum){
				point = nullNode;
				pointNum = -1;
			}
			while(pointNum<n){
				point = point.right;
				pointNum++;
			}
			while(pointNum>n){
				point = point.left;
				pointNum--;
			}
		}
	}
	public int size(){
		return size;
	}
	public void add(T arg_value){
		setPoint(size-1);
		point.setRight(new Node<T>(arg_value));
		size++;
	}
	public void add(T arg_value,int arg_num){
		if(arg_num<0){return;}
		setPoint(arg_num-1);
		Node<T> temp = point.right;
		point.setRight(new Node<T>(arg_value));
		point.right.setRight(temp);
		size++;
	}
	public void set(T arg_value,int arg_num){
		if(arg_num<0){return;}
		setPoint(arg_num);
		point.value = arg_value;
	}
	public T get(int arg_num){
		if(size>arg_num&&arg_num>=0){
			setPoint(arg_num);
			return point.value;
		}else{
			return null;
		}
	}
	public T remove(int arg_num){
		if(arg_num<0||arg_num>=size){return null;}
		setPoint(arg_num-1);
		T temp = point.right.value;
		point.setRight(point.right.right);
		size--;
		return temp;
	}
	public void remove(int arg_num,int arg_length){
		Range<Integer> r = new Range<Integer>(0,size-1);
		r.and(new Range<Integer>(arg_num,arg_num+arg_length));
		setPoint(r.max());
		Node<T> temp = point;
		setPoint(r.min()-1);
		point.setRight(temp);
		size-=r.max()- r.min();
	}
	public Integer index(T arg_value){
		setPoint(-1);
		if(arg_value == null){
			while(point.right != null){
				point = point.right;
				pointNum++;
				if(point.value == null){return pointNum;}
			}
		}else{
			while(point.right != null){
				point = point.right;
				pointNum++;
				if(arg_value.equals(point.value)){return pointNum;}
			}
		}
		return null;
	}
	public void reverse(){
		if(size<2){return;}
		setPoint(-1);
		_reverse();
		pointNum = size - pointNum;
	}
	public void removeFirstHalf(){
		setPoint(size>>1);
		nullNode.setRight(point);
		size -= pointNum;
		pointNum = 0;
	}
	public Iterator<T> iterator(){
		return new Iter<T>(nullNode,this);
	}
	public LinkedList<T> getElements(LinkedList<Integer> arg_list){
		LinkedList<T> ret = new LinkedList<T>();
		for(Integer x:arg_list){ret.add(get(x));}
		return ret;
	}
	public void subtract(LinkedList<T> arg_list){
		for(T x:arg_list){
			Integer i;
			while((i = index(x))!=null){this.remove(i);}
		}
	}
	public void removeDuplicateValues(){
		setPoint(0);
		while(point.right != null){
			if(point.value.equals(point.right.value)){
				remove(pointNum+1);
			}else{
				setPoint(pointNum+1);
			}
		}
	}
	public <E extends Comparable<T>> void removeRange(E min,E max){
		int length = 0;
		int i = 0;
		while(i<size){
			boolean bigThenMin = min.compareTo(get(i))<=0;
			boolean lessThenMax = max.compareTo(get(i))>=0;
			if(bigThenMin&&lessThenMax){length++;}
			if(bigThenMin&&!lessThenMax){break;}
			i++;
		}
		remove(i,length);
	}
	public <E extends Comparable<T>> LinkedList<T> intersection(LinkedList<E> arg_list){
		LinkedList<T> ret = new LinkedList<T>();
		int x=0;int y = 0;
		while(x<size&&y<arg_list.size){
			int result = arg_list.get(y).compareTo(get(x));
			if(result == 0){
				ret.add(get(x));
				x++;
			}else if(result>0){
				x++;
			}else{
				y++;
			}
		}
		ret.removeDuplicateValues();
		return ret;
	}
	private void _reverse(){
		Node<T> temp = point;
		Node<T> temp2 = (point = point.right);
		if(temp == null){return;}
		_reverse();
		temp2.setRight(temp);
	}
}