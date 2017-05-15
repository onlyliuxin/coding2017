package code;

import java.util.Arrays;

public class ArrayList {

	private Object[] obj = new Object[0];

	public void add(Object o) {
		Object[] tar = new Object[obj.length + 1];
		System.arraycopy(obj, 0, tar, 0, obj.length);
		tar[tar.length - 1] = o;
		obj = tar;
		System.out.println(Arrays.toString(obj));
	}

	public void add(int index, Object o) {
		Object[] tar = new Object[obj.length + 1];
		System.arraycopy(obj, 0, tar, 0, index);
		tar[index] = o;
		System.arraycopy(obj, index, tar, index + 1, obj.length - index);
		obj = tar;
	}

	public Object get(int index) {
		return obj[index];
	}
	
	public int size(){
		return obj.length;
	}

	public Object remove(int index){
		Object[] tar = new Object[obj.length-1];
		System.arraycopy(obj, 0, tar, 0, index);
		System.arraycopy(obj, index+1, tar, index, obj.length-index-1);
		Object o = obj[index];
		obj = tar;
		return o;//·µ»Ø±»É¾ÔªËØ
	}
	public static void main(String[] args) {
		ArrayList al = new ArrayList();
		al.add("hello");
		al.add("java");
		al.add(2, "addm");
		System.out.println(al.remove(1));
	}

}
