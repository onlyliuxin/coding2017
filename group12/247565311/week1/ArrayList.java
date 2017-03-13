package week1;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class ArrayList<E> implements List<E> {
	private int size=0,offset=10;
	private Object[] data = null;
    public ArrayList(){
    	data = new Object[offset];
    }
    public ArrayList(int arg0){
    	if(arg0<0) arg0=0;
    	size = arg0;
    	data = new Object[size];
    }
	@Override
	public boolean add(E arg0) {
		if(arg0 == null) return false;
		size += 1;
		int leng = data.length;
		if(size>leng){
			Object[] newdata = new Object[size + offset];
			for(int i=0;i<leng;i++){
				newdata[i] = data[i];
			}
			data = newdata;
		}
		data[leng] = arg0;
		return true;
	}

	@Override
	public void add(int arg0, E arg1) {
		if( arg0>size || 0<arg0) return ;
		size += 1;
		int leng = data.length;
		if(size>leng){
			Object[] newdata = new Object[size + offset];
			for(int i=0;i<leng;i++){
				newdata[i] = data[i];
			}
			data = newdata;
		}
		for (int i=1;i<size-arg0;i++){
			data[size-i] = data[size-i-1];
		}
		data[arg0] = arg1;
		return ;
	}

	@Override
	public boolean addAll(Collection<? extends E> arg0) {
		if (arg0 == null) return false;
		int leng = data.length,newobjnum = arg0.size(),lastsize=size;
		size += newobjnum;
		if(size>leng){
			Object[] newdata = new Object[size + offset];
			for(int i=0;i<leng;i++){
				newdata[i] = data[i];
			}
			data = newdata;
		}
		int i=lastsize;
		for(E e : arg0){
			data[i] = e;
		}
		return true;
	}

	@Override
	public boolean addAll(int arg0, Collection<? extends E> arg1) {
		int newobjnum = arg1.size(),lastsize = size;
		if(arg1 == null || arg0>size+1 || 0>arg0 || newobjnum==0) return false;
		size += newobjnum;
		int leng = data.length;
		if(size>leng){
			Object[] newdata = new Object[size + offset];
			for(int i=0;i<leng;i++){
				newdata[i] = data[i];
			}
			data = newdata;
		}
		for (int i=1;i<size-arg0;i++){
			data[size-i] = data[size-i-newobjnum];
		}
		int i = arg0;
		for (E e:arg1){
			data[i] = e;
			i += 1;
		}
		return true;
	}

	@Override
	public void clear() {
		size=0;
		data = new Object[offset];
	}

	@Override
	public boolean contains(Object arg0) {
		for(Object e:data){
			if(e.equals(arg0)) return true;
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		for(Object o:arg0){
			if(!this.contains(o)) return false;
		}
		return true;
	}

	@Override
	public E get(int arg0) {
        if(arg0 >-1 && arg0<this.size) return (E)data[arg0];
		return null;
	}

	@Override
	public int indexOf(Object arg0) {
		for(int i=0;i<this.size;i++){
			if(this.data[i].equals(arg0)) return i;
		}
		return -1;
	}

	@Override
	public boolean isEmpty() {
		return this.size==0;
	}
	@Override
	public int lastIndexOf(Object arg0) {
		for(int i=this.size-1;i>-1;i--){
			if(this.data[i].equals(arg0)) return i;
		}
		return -1;
	}
	
	@Override
	public Iterator<E> iterator() {

		return null;
	}
	
	@Override
	public ListIterator<E> listIterator() {

		return null;
	}

	@Override
	public ListIterator<E> listIterator(int arg0) {

		return null;
	}

	@Override
	public boolean remove(Object arg0) {
		for(int i=0;i<this.size;i++){
			if(this.data[i].equals(arg0)){
				this.remove(i);
				return true;
			}
		}
		return false;
	}

	@Override
	public E remove(int arg0) {
		if(arg0<0 ||arg0>this.size-1) return null;
		E res = (E)data[arg0];
		for(int i=arg0;i<this.size-1;i++){
			this.data[i] = this.data[i+1];
		}
		this.size -= 1;
		return res;
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		int toberemovednums = arg0.size();
		if(!this.containsAll(arg0)) return false;
		int index=0;
		for(int i=0;i<this.size;i++){
			if(arg0.contains(data[i])){
				this.remove(i);
			}
		}
		return true;
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
        // what does this mean?
		return false;
	}

	@Override
	public E set(int arg0, E arg1) {
		if(arg0<0||arg0>this.size-1) return null;
		this.data[arg0] = arg1;
		return arg1;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public List<E> subList(int arg0, int arg1) {
		if(arg0>=arg1 || arg0<0 || arg1>this.size-1) return null;
		List<E> res = new ArrayList<E>();
		for(int i=arg0;i<arg1;i++){
			// 如何获取拷贝？
            
            
		}
		
		return null;
	}
//////////////////////////////////////////////
	@Override
	public Object[] toArray() {
		if(this.size == 0) return null;
		Object[] res = new Object[this.size];
		for(int i=0;i<this.size;i++){
			res[i] = this.data[i].getClass();
		}
		return null;
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		T[] res = (T[])(new Object[this.size]);
		for(int i=0;i<this.size;i++){
			
		}
		return null;
	}
}
