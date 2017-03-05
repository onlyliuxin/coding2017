/*�������ƣ�
 * ԭ�ļ����ƣ�
 * Ҫ�㣺
 * 1. ʵ�ֻ��������ݽṹ�ࣺArrayList

 */
public class ArrayList_self<T> implements KIList<T> {
	/***��ʼ��������С***/
	private final static int INIT_CAPACITY=12;
	private Object[] mList=null;
	
	/***��ǰ����***/
	private int mCurrentCapacity=0;
	/***������Ԫ�ظ���***/
	private int mSize=0;
	
	public ArrayList_self(){
		mList=new Object[INIT_CAPACITY];
		mCurrentCapacity=INIT_CAPACITY;
	}

	/**
     * ����һ��Ԫ�ص�ArrayListβ��
     * @param item
     * */
	@Override
	public void add(T item) {
		// TODO Auto-generated method stub
		if(mSize==mCurrentCapacity){
			expansion();//����
		}
		mList[mSize]=item;
		mSize++;
	}

	/**
     * ����һ��Ԫ�ص�ָ��λ�ã��Ӳ���λ�ü�������Ԫ�������ƶ�һ��λ��
     * @param index Ҫ�����λ��
     * @param item
     * */
	@Override
	public void add(int index, T item) {
		// TODO Auto-generated method stub
		if (index<0 || index>=mSize) {//������indexС��0������index >= ���鵱ǰ��С
			throw new IndexOutOfBoundsException();//�׳�Խ���쳣
		}
		if(mSize==mCurrentCapacity){
			expansion();//����
		}
		Object[] newList=new Object[mCurrentCapacity];
		System.arraycopy(mList, 0, newList, 0, index);
		System.arraycopy(mList, index, newList, index+1, mSize-index);
		newList[index]=item;
		mList=newList;
		mSize++;
	}
	/**
     * �Ƴ�ָ��λ�õ�Ԫ�أ������Ԫ����ǰ�ƶ�һλ
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
     * ����ָ��λ�õ�Ԫ��
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
     * ��ȡָ��λ�õ�Ԫ��
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
     * ��ȡ��ǰ����ĳ���
     * @return int
     * */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return mSize;
	}
	
	/**
     * ���ݣ��� mSize == mCurrentCapacity ʱ����;������ʱ��ÿ�����ӵ�ǰ������50%
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
