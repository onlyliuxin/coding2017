package cn.task1;

import java.util.LinkedList;

public class ArrayList implements List {

	int size;
	Object[] obj;
	
	public ArrayList(){
		size = 0;
		obj = new Object[size];
	}
	
	@Override
	public void add(Object object) {
		if(size==0){
			obj = new Object[1];
			obj[0] = object;
			size = 1;
		}else{
			Object[] temp = new Object[size+1];
//			for(int k =0;k<size;k++){
//				temp[k] = obj[k];
//			}
			System.arraycopy(obj, 0, temp, 0, size);
			temp[size] = object;
			size = size +1;
			obj = temp;
		}
	}

	@Override
	public Object get(int index) {
		// TODO Auto-generated method stub
		return obj[index];
	}

	@Override
	public void remove(int index) {
		// TODO Auto-generated method stub
		Object[] temp = new Object[size-1];
		for(int k=0;k<size-1;k++){
			if(k<index){
				temp[k] = obj[k];
			}if(k>index){
				temp[k-1] = obj[k];
			}
		}
		obj = temp;
		size--;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		if(size>0){
			return true;
		}
		return false;
	}
	
	public void set(int index,Object object){
		Object[] temp = new Object[size+1];
		for(int k=0;k<size+1;k++){
			if(k<index){
				temp[k] = obj[k];
			}if(k==index){
				temp[k] = object;
			}if(k>index){
				temp[k] = obj[k-1];
			}
		}
		obj = temp;
		size++;
	}
	
	
	public static void main(String[] args) {
		ArrayList list = new ArrayList();
		
		list.add("d");
		list.add("dd");
		list.add("cc");
		list.remove(2);
		list.set(1, "dwe");
		
		System.out.println(list.get(0));
		System.out.println(list.get(1));
		System.out.println(list.get(2));
		System.out.println(list.size());
		System.out.println(list.isEmpty());
		
	}

}
