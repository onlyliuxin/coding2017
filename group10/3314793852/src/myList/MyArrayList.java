package myList;

/*
 *	ArrayList�ĵײ���һ�����飬ͨ�����´���������ķ�������̬���������������
 * 	��ArrayList��������ղ������ݡ�
 */

public class MyArrayList {
	private int theSize;							//��ǰ��С
	private static final int DEFAULT_CAPACITY=10;	//Ĭ������
	private Object[] theArr=new Object[10];			//�ײ�����
	
	//��ʼ��
	public MyArrayList(){
		clear();
	}
	
	//���
	public void clear(){
		theSize=0;
		capacityBigger(DEFAULT_CAPACITY);
	}
	
	//��ȡ��С
	public int size(){
		return theSize;
	}
	
	//��ȡ�ײ�����
	public Object[]	getArr(){
		return theArr;
		
	}
	//���룬ֱ�Ӳ��뵽����β����
	public void add(Object a){
		add(theSize, a);
	}
	
	//�����±��ȡ����
	public Object get(int i){
		if(i<0||i>=theSize){
			throw new ArrayIndexOutOfBoundsException();
		}
		return theArr[i];
	}
	
	//���룬����ָ���±���롣
	public void add(int i,Object a){	
		
		if(theSize==theArr.length){			//��ʼ����Ϊ10��ÿ���ɹ������һ������ʱ����size+1,��size������Ĵ�С��ͬʱ��������������󷽷�����̬��������Ĵ�С��
			capacityBigger(size());
		}
		for(int j=theSize-1;j>=i;j--){
			theArr[j+1]=theArr[j];
		}
		theArr[i]=a;
		
		theSize++;
	}
	
	//ɾ��,�����±�ɾ�����ݡ�
	public void remove(int i){
		
		for(int j=i;j<theSize;j++){
			theArr[j]=theArr[j+1];
		}
		
		theSize--;
	}
	
	//������������
	public void capacityBigger(int Size){
		Object[] newTheArr=new Object[Size*2];
		for(int i=0;i<theArr.length;i++){
			newTheArr[i]=theArr[i];
		}
		theArr=newTheArr;
	}
	
	//��ȡMyIterator�ӿڶ���
	public MyIterator myIterator(){
		 
		return myIterator(this);
	}
	
	public MyIterator myIterator(Object arr){
		MyIterator i=new MyIterator(arr);
		return i;
	}
	
	
	//��ӡ����
	public void print(){
		for(int i=0;i<theSize;i++){
			System.out.println(theArr[i]);
		}
	}
}





