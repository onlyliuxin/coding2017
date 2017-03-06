package myList;

/*
 *	ArrayList的底层是一个数组，通过重新创建新数组的方法，动态扩大数组的容量。
 * 	该ArrayList不允许隔空插入数据。
 */

public class MyArrayList {
	private int theSize;							//当前大小
	private static final int DEFAULT_CAPACITY=10;	//默认容量
	private Object[] theArr=new Object[10];			//底层数组
	
	//初始化
	public MyArrayList(){
		clear();
	}
	
	//清空
	public void clear(){
		theSize=0;
		capacityBigger(DEFAULT_CAPACITY);
	}
	
	//获取大小
	public int size(){
		return theSize;
	}
	
	//获取底层数组
	public Object[]	getArr(){
		return theArr;
		
	}
	//插入，直接插入到数组尾部。
	public void add(Object a){
		add(theSize, a);
	}
	
	//根据下标获取数据
	public Object get(int i){
		if(i<0||i>=theSize){
			throw new ArrayIndexOutOfBoundsException();
		}
		return theArr[i];
	}
	
	//插入，根据指定下标插入。
	public void add(int i,Object a){	
		
		if(theSize==theArr.length){			//开始容量为10，每当成功添加了一个数据时，则size+1,当size和数组的大小相同时，则调用数组扩大方法，动态扩大数组的大小。
			capacityBigger(size());
		}
		for(int j=theSize-1;j>=i;j--){
			theArr[j+1]=theArr[j];
		}
		theArr[i]=a;
		
		theSize++;
	}
	
	//删除,根据下标删除数据。
	public void remove(int i){
		
		for(int j=i;j<theSize;j++){
			theArr[j]=theArr[j+1];
		}
		
		theSize--;
	}
	
	//数组扩大容量
	public void capacityBigger(int Size){
		Object[] newTheArr=new Object[Size*2];
		for(int i=0;i<theArr.length;i++){
			newTheArr[i]=theArr[i];
		}
		theArr=newTheArr;
	}
	
	//获取MyIterator接口对象
	public MyIterator myIterator(){
		 
		return myIterator(this);
	}
	
	public MyIterator myIterator(Object arr){
		MyIterator i=new MyIterator(arr);
		return i;
	}
	
	
	//打印数组
	public void print(){
		for(int i=0;i<theSize;i++){
			System.out.println(theArr[i]);
		}
	}
}





