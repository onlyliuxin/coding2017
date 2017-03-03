package firstweek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class arraylist implements List {
	ArrayList<Integer> q=new ArrayList<Integer>();
	
	private static final int InitialValue=10;
	
	private static final Object[] Empty_Elementdata={};	
	
	private Object[] elementData = new Object[100];
	
	private int size = 0;
	
	public boolean add(Object o){
		if(elementData==Empty_Elementdata){
			//设置为初始化容量为10的数组
			elementData[0]=o;
		}
		if(size==elementData.length){
			//copy数组并增大50%
			int oldLength=elementData.length;
			int newLength=oldLength+(oldLength>>1);
			if(newLength-oldLength>0){
				elementData=Arrays.copyOf(elementData, newLength);
			}
		}
		elementData[size++]=o;
		return true;
		
	}
	public void add(int index, Object o){
		if(elementData==Empty_Elementdata){
			elementData[0]=o;
			System.out.print("创建了一个新的只有该元素的数组");
			return ;
		}
		if(size==elementData.length){
			int oldLength=elementData.length;
			int newLength=oldLength+(oldLength>>1);
			if(newLength-oldLength>0){
				System.arraycopy(elementData, index, elementData,index+1 ,size-index);
			}
		}
		elementData[index]=o;
		size++;
	}
	
	public Object get(int index){
		rangecheck(index);		
		return elementData[index];
	}
	
	public Object remove(int index){
		rangecheck(index);
		Object oldValue=elementData(index);
		int numMove=size-index-1;
		if(numMove>0){
			System.arraycopy(elementData, index+1, elementData, index, numMove);
		}
		elementData[--size]=null;
		return true;
	}
	public boolean removeObject(Object o){
		if(o==null){
			for(int index=0;index<size;index++){
				if(elementData[index]==null){
					fastremove(index);
					return true;
				}
			}
		}else{
			for(int index=0;index<size;index++){
				if(o.equals(elementData[index])){
					fastremove(index);
					return true;
				}
			}
		}
		return false;
	}
	
	private ArrayList<Integer> elementData(int index) {
		// TODO Auto-generated method stub
		return null;
	}
	public int size(){
		return size;
	}
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size==0;
	}
	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return 0>=indexOf(o);
	}
	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		//不会
		return null;
	}
	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return Arrays.copyOf(elementData,size);
	}
	@Override
	public Object[] toArray(Object[] a) {
		
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		if(o==null){
			for(int i=0;i<elementData.length;i++){
				if(elementData[i]==null){
					fastremove(i);
					return true;
				}
			}
		}else{
			for(int i=0;i<elementData.length;i++){
				if(o.equals(elementData[i])){
					fastremove(i);
					return true;
				}
			}
		}
		
		return false;
	}
	@Override
	public boolean containsAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean addAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean addAll(int index, Collection c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean removeAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean retainAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		for(int i=0;i<elementData.length;i++){
			elementData[i]=null;
		}
		size=0;
		
	}
	@Override
	public Object set(int index, Object element) {
		// TODO Auto-generated method stub
		if(index>size||index<0){
			System.out.println("查询超出范围。");
			return null;
		}
		elementData[index]=element;
		return null;
	}
	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		if(o==null){
			for(int i=0;i<elementData.length;i++){
				if(elementData[i]==null){
					return i;
				}
			}
		}else{
			for(int i=0;i<elementData.length;i++){
				if(o.equals(elementData[i])){
					return i;
				}
			}
		}
	
		return 0;
	}
	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		int Storage=0;
		boolean temp=false;
		for(int i=0;i<size;i++){
			if(o.equals(elementData[i])){
				Storage=i;
				temp=true;
			}
		}
		if(temp){
			return Storage;
		}
		return -1;
	}
	@Override
	public ListIterator listIterator() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ListIterator listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}
	private void rangecheck(int index){
		if (index < 0 || index >= this.size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
	}
	private String outOfBoundsMsg(int index) {
            return "Index: "+index+", Size: "+this.size;
	}
	private void fastremove(int index){
		int numMoved=size-index-1;
		if(numMoved>0){
			System.arraycopy(elementData, index+1, elementData, index, numMoved);
		}
		elementData[--size]=null;
	}
	
}
