package basic.dataStructure.stack;

/**
 * 设计一个栈，支持栈的push和pop操作，以及第三种操作findMin, 它返回改数据结构中的最小元素
 * finMin操作最坏的情形下时间复杂度应该是O(1) ， 简单来讲，操作一次就可以得到最小值
 * @author liuxin
 *
 */
public class QuickMinStack {

	private int defaultSize = 10;
	private int extendSize = 10;
	Object[] elements = new Object[defaultSize];

	private int min = -1;

	private int size = 0;
	public QuickMinStack(){}

	public boolean isEmpty(){
		boolean flag = false;
		for(int i = 0; i < elements.length; i++){
			if(elements[i] != null){
				return false;
			}

			flag = elements == null;
		}
		return flag;
	}

	public void push(int data){
		if(isEmpty()){
			this.min = data;
		}else{
			this.min = data < min ? data : min;
		}
		size ++;
		elements[size - 1] = data;
		if(size >= elements.length){
			extend();
		}
	}
	public int pop(){
		Integer d = (Integer)elements[size - 1];
		elements[size - 1] = null;
		size--;
		return d;
	}

	public int findMin(){
		return this.min;
	}

	public int size(){
		return this.size;
	}

	private void extend(){
		int curSize = elements.length;
		Object[] arr = new Object[curSize + extendSize];

		System.arraycopy(elements, 0, arr, 0, curSize);

		this.elements = arr;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		for(int i = size - 1; i >= 0; i--){
			sb.append(elements[i]);
			if(i > 0){
				sb.append(",");
			}else{
				sb.append("]");
			}
		}

		return sb.toString();
	}
}
