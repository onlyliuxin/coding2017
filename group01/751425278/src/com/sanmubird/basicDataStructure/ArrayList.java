package com.sanmubird.basicDataStructure;

import java.util.Arrays;

public class ArrayList implements List {
	
	/**		ArrayList 是一个类，一个类就会有对象，属性，构造方法，方法
	 * 		ArrayList 是基于数组来实现List接口； 那么它的元素就会有 存储在数组中的元素， 和ArrayList的长度
	 * 		这个地方需要区分size= ArrayList.size() 和  length = Array.length ；
	 * 		size 是 已经占用的长度；
	 * 		length 是数组的长度； length 》= size 当，size > length 时，数组要动态扩容；
	 * */
	
//	数组默认的长度
	private static final int DEFAULT_SIZE  = 10;
	
//	ArrayList的大小
	private int size  ; 
	
//	数组中存储的元素
	private Object[] elementData = null;
	
	private int count ;
	
//	ArrayList 的构造方法	通过构造方法 可以得到这个类的对象
//	有参构造方法
	public ArrayList(int i){
		if(i <= 0 ){
			throw new RuntimeException("数组的长度不能小于等于0");
		}else{
			this.elementData = new Object[i];
			this.size = 0 ;		// 集合ArrayList的大小；
		}
	}
	//	无参构造方法 
	public ArrayList(){
		this(DEFAULT_SIZE); // this 会调用本类中 相同参数（相同的参数个数和参数类型）的构造方法；
	}
	
	/**	ArrayList 其他方法分析
	 * 	目标方法：		
	 * 				size();			Array的length就是ArrayList的大小
	 * 				get(int index);		Array的【index-1】就是 ArrayList的第index位元素
	 * 				add(Object o) ; 	这个方法是在数组的末尾顺序添加一个元素； 找到数组的长度size，将array【size-1】= Object 
	 * 				add(int index , Object o);	这个方法是在数组的指定位置添加一个元素；找到index位，将index位起往后挪一位，并将array【index】=Object
	 * 				remove(int index); 这个方法是 删除指定位上的元素，直接将这个位至最后的元素往前挪移一位。
	 * 
	 * 工具方法：
	 * 			argumentCheck(int index); 判断输入的参数是否合法;比如传入的参数不能比数组长度大，或者不能为负数等
	 * 			ensureCapacity();	判断当前数组的长度是否足够大，能再容纳下一个元素。
	 * 
	 * */
	
	
//	对传入的参数进行验证是否合法   如果输入的参数不合法 就抛出异常
	public void argumentCheck(int index){
		if(index >= size || index < 0 ){	// 此处我觉得需要 ‘=’ 因为 index 我觉得是下标
			throw new IndexOutOfBoundsException("插入的下标是:"+index +"，但ArrayLsit的长度是："+size);
		}
	}
	
	//	判断是否数组是否溢出的方法	 如果数组现在的长度小于所需的最小长度，就需要对数组进行扩容
	public void ensureCapacity(int minCapacity){
		int length = elementData.length;  // 得出当前数组的长度
		if(minCapacity > length){
		int newCapacity = length * 3 / 2 + 1 ; //你是否对此有疑问？得出的结果会不会是小数？ 答案是不会的，java中算术运算符“/”；两个int类型相除，结果一定是int类型
			if(minCapacity > newCapacity ){
				newCapacity = minCapacity ;
			}
			count++;
			System.out.println("扩容"+count+"次");
		elementData = Arrays.copyOf(elementData, newCapacity);//此处为什么用Arrays.copyOf()而不用System.arraycopy()?
			// Arrays.copyOf(): 不仅仅copy数组中的元素，还会创建一个新的数组来存放copy的对象
			// System.arraycopy():仅仅是copy数组中的元素，不会新建一个数组对象,也不会改变原有的数组长度。
			// 在原有数组长度不够的情况下，只能选择新建一个数组，并将原有的数组复制到新数组的办法来解决。
		}
	} 
	
	//  得到ArrayList 大小的方法 ； 此处的size 不是Array的length,而是ArrayList中元素的个数
	public int size(){
		return size;
	}
	
//	传入下标得到元素
	public Object get(int index){
		argumentCheck(index);	//需要判断传入的参数是否合法；
		return elementData[index];
	}
	
//	按顺序在数字尾部添加元素
	public void add(Object o){
		ensureCapacity(size+1);		// 判断是否会溢出
		elementData[size++] = o ;	//此处使用 size++ 的好处：elementData[size+1];size++;
	}
	
	public void add(int index, Object o){	//这个地方需要搞清楚index的含义：index在此处是下标的意思
		argumentCheck(index);	//判断输入的下标是否合法  --->
		//	刚开始的时候 ； 我觉得这个地方不需要加这个判断，因为ArrayList是动态增长的；
		//	我还需要想明白这个问题；	
		ensureCapacity(size+1);		// 判断是否会溢出
		int moveLength = size - (index + 1) + 1; // 此处index是下标；下标是从0开始计算的；所以第n位的下标就是（n-1）；所以，n = index + 1 
		//  此处的 +1 刚开始没想明白，后来组长给举了个例子，1-3 有三个数，但不是通过3-1=2 算出来的
		System.arraycopy(elementData, index, elementData, index+1, moveLength ); 
		elementData[index] = o ;
		size++;
	}
	
	public Object remove(int index){
		argumentCheck(index);	//判断输入的下标是否合法
		Object o = elementData[index];
		System.arraycopy(elementData, index, elementData, index-1, size-index);
		elementData[size] = null ;
		size--;
		return o;
	}
	
	public Iterator iterator(){
		return new Iterator(){
			private int index = 0 ;
			
			@Override
			public Object next(){
				return elementData[index++];
			}
			@Override
			public boolean hasNext() {
				return index < size ;
			}
		};
	}
	
	public static  void main(String [] args){
		ArrayList al = new ArrayList();
		al.add(1);
		al.add(2);
		al.add(4);
		al.add(5);
		al.add(6);
		al.add(7);
		al.add(2,3);
		al.add(8);
		al.add(9);
		al.add(10);
		al.add(11);
		al.add(13);
		al.add(9,12);
		al.add(14);
		al.add(15);
		al.remove(9);
		for(int i = 0 ; i < al.size() ; i++ ){
			System.out.println(al.get(i));
		}
		System.out.println("al的size是"+al.size());
		System.out.println(al.get(15));
	}
}