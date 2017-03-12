public class ArrayList<T> implements List<T> {
    //private fields
	private int size = 0;
    private Object[] elementData = new Object[100];
    //check if list is empty
    public boolean isEmpty() {
        return size == 0;
    }

	public void add(Object o){
		elementData[size++] = o;
	}
	public void add(int index, T o){
	    /* Not familiar with array copy, copied from GitMori  */
        System.arraycopy(elementData, index, elementData,
                index + 1, size-index);
        elementData[index] = o;
        size++;
	}
	
	public T get(int index){
		return (T) elementData[index];
	}
	
	public T remove(int index){
		T t = this.get(index);
		int position = size - index - 1; //why ?
        if (position > 0){
            System.arraycopy(elementData, index+1, elementData,
                    index, position);
        }
        elementData[--size] = null;
        return t;
	}
	
	public int size(){
		return size;
	}

	//??
	public Iterator iterator(){
		return null;
	}


}
