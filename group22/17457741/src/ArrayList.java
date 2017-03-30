import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<T> implements Iterable<T>{
    private static final int CAPACITY = 5;
    
    private int size;
    
    private T [] items;
    
    public ArrayList(){doClear();}
    
    public void clear(){
    	doClear();
    }
    
    private void doClear(){
    	this.size=0;ensureCapacity(CAPACITY);
    }
    
    public int size(){
    	return size;
    }
    
    public boolean isEmpty(){
    	return size==0;
    }
    
    public void trimToSize(){
    	ensureCapacity(size());
    }
    
    public T get( int a){
    	return items[a];
    }
    
    public T set(int a,T b){
    	T old = items[a];
    	items[a]=b;
    	return old;
    }
    
    public void ensureCapacity(int newCapacity){
    	if(newCapacity<size){
    		return ;
    	}
    	T []old =items;
    	items=(T[]) new Object[newCapacity];
    	for(int i=0;i<size();i++){
    		items[i]=old[i];
    	}
    }
    
    public boolean add(T a){
    	add(size(),a);
    	return true;
    }
    
    public void add(int a,T b){
    	if(items.length==size())
    		ensureCapacity(size()*2-1);
    	for(int i=size;i>a;i--){
    		items[i]=items[i-1];
    	}
    		items[a]=b;
    		size++;
    	}
    
    public T remove(int a){
    	T removedItem=items[a];
    	for(int i=a;i<size()-1;i++){
    		items[i]=items[i+1];
    	}
    	size--;
    	return removedItem;
    }
    
   
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new ArrayListIterator();
	}
	
	private class ArrayListIterator implements Iterator<T>{
        
		private int current =0;
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return current<size();
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			if(!hasNext())
				throw new NoSuchElementException();
			return items[current++];
				
		}
		public void remove(){
			ArrayList.this.remove(--current);
		}
	}
	
	
}

