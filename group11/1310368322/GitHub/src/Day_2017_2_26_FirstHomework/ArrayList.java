package Day_2017_2_20_DateStructure;

public class ArrayList {
	
	
	private int size = 0;
	
	private Object[] elementData = new Object[10];

	private Exception Exception;
	
	public void add(Object o){
		if(size>elementData.length){
			elementData = ArrayList.grow(elementData, 10);
		}
		for(int i = 0; i < elementData.length; i++){
			if(null == elementData[i]){
				elementData[i] = o; 
				break;
			}
		}
		size++;
	}
	public void add(int index, Object o){
		if(size>elementData.length){
			elementData = ArrayList.grow(elementData, 10);
		}
		if(index<0){
			System.out.println("您插入的位置有误");	
		}
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
		a.add("a");
		a.getElementData();
		System.out.println(a.size);
	}
}
