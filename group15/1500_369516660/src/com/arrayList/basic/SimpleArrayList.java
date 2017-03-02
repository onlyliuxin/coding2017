package com.arrayList.basic;

import java.util.Arrays;

/**
 * 
 * @author Jodie
 *
 */
public class SimpleArrayList implements List {

	private final static int default_num = 10;//��������ʱ��û�ж����С��Ĭ��Ϊ10
	private Object[] elementData;
	private int size = 0;//��������Ĵ�С,��ʵ����Ĵ�С
	
	public SimpleArrayList(){
		this(default_num);
	}

	public SimpleArrayList(int size) {
		if(size<0){//�ж϶�������Ĵ�С�Ƿ�С��0
			throw new IllegalArgumentException();
		}else{
			 elementData = new Object[size];
		}
	}

/**
 * ��д���Ӻ����ķ���1
 */
	@Override
	public void add(Object o) {
		//�ж��Ƿ���Ҫ����
		ifSpaceEnougn(size+1);
		elementData[size++]=o;
	}
	
/**
 * ��д���Ӻ����ķ���2
 */
	@Override
	public void add(int index, Object o) {
		checkIfOut(index);//�Ƿ�Խ��
		ifSpaceEnougn(size+1);//�Ƿ�Ҫ����
		System.arraycopy(elementData, index, elementData, index + 1, size-index);//��index��Ԫ�ؼ��Ժ��Ԫ�������һλ
	}
/**
 * �õ�ָ���±������
 */
	@Override
	public Object get(int index) {
		checkIfOut(index);
		return elementData[index];
	}
/**
 * ɾ��ָ���±������
 */
	@Override
	public Object remove(int index) {
		Object value = get(index);
		int numRemove = size - index - 1;
		if(numRemove > 0){
			System.arraycopy(elementData, index+1, elementData, index, size - index);//������ǰ��һλ
		}
		elementData[--size] = null;
		return value;
	}
/**
 * ɾ��ָ��������	
 */
	@Override
	public String remove(Object o) {
		if(contains(o)){
			remove(indexOf(o));
			return "ɾ���ɹ�";
		}else{
			return "Ҫɾ�������ݲ��������У�ɾ��ʧ��";
		}
	}
/**
 * �ж��Ƿ���Ҫ����
 * @param size
 */
	private void ifSpaceEnougn(int size) {
		if(size>default_num){
			exlicitSpace(size);
		}
		if(size<0){//��size����Integer.MAX_VALUEʱ�����Ϊ����
			throw new OutOfMemoryError();
		}
	}
/**
 * �������ݷ���
 * @param 
 */
	private void exlicitSpace(int size) {
		final int max_arrayLength = Integer.MAX_VALUE-8;
		int newLength = elementData.length*2;//һ��������Ϊԭ��������������Ƶ��������
		if(newLength - size<0){
			newLength = size;
		}
		if(newLength > max_arrayLength){//�������ݺ�Ĵ�С�������ֵ
			newLength = (size > max_arrayLength ? Integer.MAX_VALUE : max_arrayLength);
		}
		elementData = Arrays.copyOf(elementData, newLength);
	}

/**
 * �ж��Ƿ�Խ��
 * @param index
 */
	private void checkIfOut(int index) {
		if(index<0 || index>size){
			throw new IndexOutOfBoundsException();
		}
	}

/**
 * �ҵ�ָ���������±�
 * @param o
 * @return
 */
private int indexOf(Object o) {
	if(o!=null){
		for(int i=0;i<size;i++){
			if(elementData[i].equals(o)){
				return i;
			}
		}
	}else{
		for(int i=0;i<size;i++){
			if(elementData[i] == null){
				return i;
			}
		}
	}
	return -1;
}

/**
 * �ж��������Ƿ�����������
 * @param o
 * @return
 */
	private boolean contains(Object o) {
		return indexOf(o) >= 0;
	}

	@Override
	public int size() {
		return size;
	}


}
