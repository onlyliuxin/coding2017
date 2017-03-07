interface MyIterator{
	public boolean hasNext();
	public Object next();

}


public class ArrayList {
/*	public static void main(String[] d){
		ArrayList p = new ArrayList();
		p.add("asd");p.add("123");p.add("123");p.add("234");p.add("456");
		p.remove(1);
		p.add(1, 345);
		MyIterator it = p.iterator();
		while(it.hasNext()){
			System.out.println(it.next().toString());
		}
	}
*/
	
	private int size = 0;
	
	private Object[] elementData = new Object[100];

	public void add(Object o){
		if(size >= elementData.length){//扩充数组长度
			Object[] tem = new Object[elementData.length*2];
			for(int i=0;i<size;i++){
				tem[i] = elementData[i];
			}
			elementData = tem;
		}
		elementData[size] = o;
		size++;	
	}
	public void add(int index, Object o){
		if(size >= elementData.length){
			Object[] tem = new Object[elementData.length*2];
			for(int i=0;i<size;i++){
				tem[i] = elementData[i];
			}
			elementData = tem;
		}
		for(int i = index; i < size; i++){
			elementData[i+1] = elementData[i];
		}
		elementData[index] = o;
		size++;
	}
	
	public Object get(int index){
		return elementData[index];
	}
	
	public Object remove(int index){
		if(index<0  || index >=size){
			throw new IndexOutOfBoundsException();
		}
		for(int i = index; i < size; i++){
			elementData[i-1] = elementData[i];
		}
		size--;
		return elementData[index];
	}
	
	public int size(){
		return size;
	
	}
	
	
	class MMIt implements MyIterator{
		int loc = 0;
		
		public boolean hasNext() {
			if(loc>=size)
				return false;
			else 
				return true;
		}

		
		public Object next() {
			++loc;
			return elementData[loc-1];
		}
		
	}
	
	
	public MyIterator iterator(){
		return new MMIt();
	}
	
}