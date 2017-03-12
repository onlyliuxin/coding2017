package week01;
import java.util.*;

public class MyArrayList<T> implements List {
	
	private int length = 0;//线性表中当前个数。
	
	private Object[] elementData = new Object[100];

	private Object entry;
	
	public void add(Object o){
		if (isFull())
		
			doubleArray();
			Object newEntry = null;
			elementData[length]= newEntry;
			length++;
	

		if (!isFull())
		{
			//新元素的位置将在线性列表中的最后一个元素之后，也就是说，处于位置length+1；
			//由于相应的数组索引比位置少1，所以索引是length
			elementData[length] = o;
			length++;
		}//end add
		        	
	}



private boolean isFull() {
	int in = ((List) entry).size();
	
	return length == in;
	}

	/*	* Task：为一个新元素在index位置处腾出空间。
		* Precondition:1<=index<=length+1;
		*			length是线性表在插入前的长度*/
	private void makeRoom(int newInex)
		{
			int index=0;
			//将每个元素移动到下一个编号更高的位置，从线性表的末尾开始并继续下去，
			//直到index处的元素被移动为止
			for (int newIndex=length;newIndex>=index ;newIndex-- )
			{
				elementData[newIndex]  = elementData[newIndex-1];
			}
		}
		
		public boolean isEmpty()
	{
		return length==0;
	}//end isEmpty
	public void add(int index, Object o){
		
		if (!isFull()&&(index>1)&&(index<=length+1))
		{
			makeRoom(index);
			length++;
			elementData[index-1] = o;}
			
		}//end add	
	public Object get(int index){
		
		Object result = null;
		if ((index >=1)&&(index <=length))
		{
			assert !isEmpty();
			result = elementData[index];

		}
		return result;

	}//end getEntry
	
	public Object remove(int index){
		return null;//返回值

	}//end remove
/* * Task:将被删除元素之后的元素平移到下一个更低的位置
   * Precondition：1《=givenPosition<=length;length是线性表在删除前的长度；*/

   private void removeGap(int index)

	{
		assert(index >=1)&&(index <length);
		
		int lastIndex = length -1;
	
		for (int removeIndex = index -1;index <lastIndex ;index++ )
		{
			elementData[index] = elementData[index-1];
		}
	}// end removeGap
	
	public int size(){
	
		return this.length;

	}
	
	public Iterator iterator(){
		
		return null;
	}

	

	
	
	private void doubleArray()
	{
		Object[] list = null;
		T[] oldList = (T[]) list;//保存指向线性表元素组的引用
		int oldSize = oldList.length;//保存旧数组的最大长度
		elementData = new Object[2 *oldSize];//将数组长度加倍

		for (int index = 0;index<oldSize ;index++ )
		{
			list[index] = oldList[index];
		}//end doubleArray

	
}

	

	
	
}

