/*范例名称：
 * 原文件名称：
 * 要点：
 * 1. 实现基本的数据结构类：ArrayList

 */
public class ArrayList_self<T> implements KIList<T> {
	/***初始化容量大小***/
	private final static int INIT_CAPACITY=12;
	private Object[] mList=null;
	
	/***当前容量***/
	private int mCurrentCapacity=0;
	/***容器中元素个数***/
	private int mSize=0;
	
	public ArrayList_self(){
		mList=new Object[INIT_CAPACITY];
		mCurrentCapacity=INIT_CAPACITY;
	}

	/**
     * 插入一个元素到ArrayList尾部
     * @param item
     * */
	@Override
	public void add(T item) {
		// TODO Auto-generated method stub
		if(mSize==mCurrentCapacity){
			expansion();//扩容
		}
		mList[mSize]=item;
		mSize++;
	}

	/**
     * 插入一个元素到指定位置，从插入位置及其后面的元素往后移动一个位置
     * @param index 要插入的位置
     * @param item
     * */
	@Override
	public void add(int index, T item) {
		// TODO Auto-generated method stub
		if (index<0 || index>=mSize) {//不允许index小于0，或者index >= 数组当前大小
			throw new IndexOutOfBoundsException();//抛出越界异常
		}
		if(mSize==mCurrentCapacity){
			expansion();//扩容
		}
		Object[] newList=new Object[mCurrentCapacity];
		System.arraycopy(mList, 0, newList, 0, index);
		System.arraycopy(mList, index, newList, index+1, mSize-index);
		newList[index]=item;
		mList=newList;
		mSize++;
	}
	/**
     * 移除指定位置的元素，后面的元素向前移动一位
     * @param index
     * */
	@Override
	public T remove(int index) {
		// TODO Auto-generated method stub
		if(index<0 || index>=mSize){
			throw new IndexOutOfBoundsException();
		}
		Object[] newList=new Object[mCurrentCapacity];
		System.arraycopy(mList, 0, newList, 0, index);
		System.arraycopy(mList, index+1, newList, index, mSize - index);
		
		T tempT=(T) mList[index];
		mList=newList;
		mSize--;
		
		return tempT;
	}
	/**
     * 更新指定位置的元素
     * @param index
     * @param item
     * */
	@Override
	public void set(int index, T item) {
		// TODO Auto-generated method stub
		if(index<0 || index>=mSize){
			throw new IndexOutOfBoundsException();
		}
		mList[index]=item;
	}
	/**
     * 获取指定位置的元素
     * @param index
     * @return
     * */
	@Override
	public T get(int index) {
		// TODO Auto-generated method stub
		if(index<0 || index>=mSize){
			throw new IndexOutOfBoundsException();
		}
		
		return (T)mList[index];
	}
	/**
     * 获取当前链表的长度
     * @return int
     * */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return mSize;
	}
	
	/**
     * 扩容，当 mSize == mCurrentCapacity 时调用;当满的时候每次增加当前容量的50%
     * */
	private void expansion() {
		// TODO Auto-generated method stub
		Object[] oldList=mList;
		Object[] newList=new Object[mCurrentCapacity + (mCurrentCapacity >> 1)];
		System.arraycopy(oldList, 0, newList, 0, oldList.length);
		mList=newList;
		mCurrentCapacity=mCurrentCapacity + (mCurrentCapacity >> 1);
	}

}
