package basicstruct;



public class ArrayList implements List{
	
	    private int size = 0;
		
		private Object[] elementData ;
		
		private static final int DEFAULT_CAPACITY = 10;
		
		public ArrayList() {
			elementData=new Object [DEFAULT_CAPACITY];
		}
		
		public ArrayList(int initialCapacity) {
			if(initialCapacity>=0){
				elementData=new Object[initialCapacity];
			}else {
				 throw new IllegalArgumentException("initialCapacity"+
                         initialCapacity+"不能为负数");
			}
		}
		
		public void add(Object o){
			ensureCapacity();
			elementData[size++] = o;
		}
		public void add(int index, Object o){
			if(index<0||index>size){
				throw new ArrayIndexOutOfBoundsException("index:"+index);
			}
			ensureCapacity();
			System.arraycopy(elementData, index, elementData, index + 1,size - index);
		    elementData[index] = o;
		    size++;
		}
		
		private void rangeCheck(int index) {
			if(index<0||index>=size){
				throw new ArrayIndexOutOfBoundsException("index:"+index);
			}
		}
		private void ensureCapacity() {
			if(size == elementData.length) {
				Object[] newArray = new Object[size * 2 + 1];
				System.arraycopy(elementData, 0, newArray, 0, elementData.length);
				elementData = newArray;
			}
	}
		public Object get(int index){
			rangeCheck(index);
			return elementData[index];
		}
		
		public Object remove(int index){
			rangeCheck(index);
			Object movedValue = elementData[index];
			//被删除元素后的元素数目
			int numMoved = size - index - 1;
			//后面有元素
		    if (numMoved > 0){
		   		System.arraycopy(elementData, index+1, elementData, index,numMoved);
		    }
		    //恰为最后一个元素
		    size--;
	        elementData[size] = null; //垃圾回收
			return movedValue;
		}
		
		public int size(){
			return size;
		}
		
		public Iterator iterator(){
			return null;
		}
		
}
