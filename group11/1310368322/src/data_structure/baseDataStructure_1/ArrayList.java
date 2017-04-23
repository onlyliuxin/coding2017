package Day_2017_2_26_FirstHomework;

public class ArrayList {
	
	private static final int DEFAULT_SIZE = 10;
	private static final int MAX_VALUE = 2147483647;
	private Object[] elementData = new Object[DEFAULT_SIZE];
	private Exception Exception;
	private int size = 0;

	public ArrayList(){
		this(DEFAULT_SIZE);
	}
	public ArrayList(int defaultSize) {
		rangCheckForConstructor(defaultSize);
		elementData = new Object[defaultSize];
	}
	

	private void rangCheckForConstructor(int defaultSize) {
		if(defaultSize<0 || defaultSize>MAX_VALUE){
			throw new IndexOutOfBoundsException("数值不合理");
		}
		
	}
	
	public void add(Object o){
		ensureCapacity();
		for(int i = 0; i < elementData.length; i++){
			if(null == elementData[i]){
				elementData[i] = o; 
				break;
			}
		}
		size++;
	}
	private void ensureCapacity() {
		if(size>elementData.length){
			elementData = ArrayList.grow(elementData, 10);
		}
	}
	public void add(int index, Object o){
		rangeCheckForAdd(index);
		ensureCapacity();
		int k = -1;
		for(int i = index; i < elementData.length; i++){
			if(null==elementData[i]){
				k = i-1;
				break;
			}
		}
		for(int i = k; i >= index;i--){
			elementData[i+1] = elementData[i];
		}
		elementData[index] = o;
		size++;
	}
	private void rangeCheckForAdd(int index) {
		if(index < 0 || index > this.size){// add 的元素只能在 [0,size](可以给size位置插元素，但不可以给size后插元素)
			throw new IndexOutOfBoundsException("下标越界");
		}
		
	}
	public Object get(int index){
		return elementData[index];
	}
	
	public Object remove(int index){
		while(true){
			elementData[index] = elementData[index+++1];
			if(elementData[index]==null){
				break;
			}
		}
		size--;
		return null;
	}
	public int size(){
		return -1;
	}
	public void getElementData(){
		for(int i = 0; i < elementData.length; i++){
			System.out.println(elementData[i]);
			
		}
	}
	public static Object[] grow(Object[] elementData2, int size){
		Object []target = new Object[elementData2.length+size];
		System.arraycopy(elementData2, 0, target, 0, elementData2.length);
		return target;
	}
	
	public static void main(String[] args) {
		ArrayList a = new ArrayList();
		a.getElementData();
		System.out.println(a.size);
	}
}
