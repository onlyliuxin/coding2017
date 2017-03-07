package org.learning.container;


public class ArrayList {

	private Object [] objs = null;
	private int index = -1;
	public ArrayList(){
		objs = new Object[5];
	}
	public ArrayList(int size){
		objs = new Object[size];
	}
	/**
	 * 返回一个新的数组
	 * @param src
	 * @param src_index
	 * @param dest
	 * @param dest_index
	 * @param length
	 * @return
	 */
	private static Object[] copy(Object[] src,int src_index,Object[] dest,int dest_index,int length){
		System.arraycopy(src, src_index, dest, dest_index, length);
		return dest;
	}
	
	public void add(Object obj){
		if(this.index == objs.length-1) {
			Object[] dest = new Object[objs.length+5];
			objs = copy(objs,0,dest,0,objs.length);
		}
		this.index ++;
		objs[this.index] = obj;
	}
	
	public void add(int index,Object obj){
		if(index-1 > this.index || index < 0){
			throw new IndexOutOfBoundsException();
		}
		Object[] dest = new Object[objs.length+5];
		if(index == 0){
			dest[index] = obj;
			dest =copy(objs,index,dest,index+1,getSize());
			objs = dest;
		}else if(index == getSize()){
			objs[index] = obj;
		}else{
			dest = copy(objs,0,dest,0,index);//前部分
			dest[index] = obj;			//中间部分
			dest =copy(objs,index,dest,index+1,getSize()-index);//后部分
			objs = dest;
		}
		this.index++;
	}
	
	public Object get(int index){
		if(index > this.index || index <0){
			throw new IndexOutOfBoundsException();
		}
		return objs[index];
	}
	
	public boolean isEmpty(){
		if(objs == null || this.index == -1){
			return true;
		}
		return false;
	}
	
	public int getSize(){
		return this.index+1;
	}
	
	public boolean remove(int index){
		if (index <0 || index > objs.length){
			throw new IndexOutOfBoundsException();
		}
		Object[] dest = new Object[this.index];
		dest = copy(objs,0,dest,0,index);//前部分
		dest = copy(objs,index+1,dest,index,this.index-index);//后部分
		objs = dest;
		this.index --;
		return true;
	}
	public boolean remove(Object obj){
	 	for(int i=0;i<=this.index;i++){
	 		if(obj==null ? get(i)==null : obj.equals(get(i))) {
	 			remove(i);   //i 即 当前元素的下标识
	 			return true;
	 		}
	 	}
		return false;
	}
	public static void print(Object obj){
		System.out.println(obj);
	}
	
	public static void main(String [] args){
		ArrayList al = new ArrayList();
		/*print(al.isEmpty());
		al.add("a1");
		print(al.isEmpty());
		print(al.getSize());
		print(al.get(0));
		print(al.get(1));*/
		al.add("a0");
		al.add("a1");
		al.add("a2");
		al.add("a3");
		al.add("a4");
		al.add("a5");
		
		//al.remove(0);
		//al.remove(5);
		//al.remove(2);
		/*boolean flag = al.remove("a7");
		print(flag);
		for(int i=0;i<al.getSize();i++){
			print(al.get(i));
		}*/
		/*print(al.get(0));
		print(al.get(5));
		print(al.get(6));*/
		//print(al.getSize());
		//print(al.get(-1)); ok
		//print(al.get(0));
		//print(al.get(5));
		
		//print(al.get(6));
		
		/*for(int i=0;i<al.getSize();i++){
			print(al.get(i));
		}
		print("---------------------以上内容为add");*/
		
		al.add(0, "a00");
		al.add(5, "a6");
		for(int i=0;i<10;i++){
			print(al.get(i));
		}
		print("---------------------以上内容为add(index,obj)");
		
		/*al.add(6, "a6");
		al.add("a7");
		for(int i=0;i<al.getSize();i++){
			print(al.get(i));
		}
		print("---------------------以上内容为add(index,obj)");*/
		/*al.add("a0");
		al.add("a1");
		al.add("a2");
		al.add("a3");
		al.add("a4");
		al.add("a5");
		al.add("a6");
		print(al.isEmpty());
		print(al.getSize());
		print(al.get(0));
		print(al.get(5));
		//al.add(5,"5.5");
		
		for(int i=0;i<al.getSize();i++){
			print("i"+i+":"+(al.get(i)));
		}
		
		//print(al.remove("a4"));
		print(al.getSize());
		print(al.remove(2));
		print("删除后");
		print(al.getSize());
		for(int i=0;i<al.getSize();i++){
			print("i"+i+":"+(al.get(i)));
		}
		print("-----------add a7");
		al.add("a7");
		al.add(2,"a2");*/
		
		/*for(int i=0;i<al.getSize();i++){
			print("i"+i+":"+(al.get(i)));
		}
		print(al.get(6));*/
		
	}
	
}
