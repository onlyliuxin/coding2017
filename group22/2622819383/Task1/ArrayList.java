//代码参考自《数据结构与算法分析》
public class ArrayList implements List {
	
    private int size;
    
    private int capacity;
    
    private static final int DEFAULT_CAPACITY = 10;
	
    private Object[] elementData;
    
    //add()时用于在必要时刻扩充底层数组容量
    private void expand() {
        if (size < capacity) return;//尚未满员，不必扩容        
        if (capacity < DEFAULT_CAPACITY) capacity = DEFAULT_CAPACITY;//不低于最小容量
        
        Object[] oldElem = elementData;
        elementData = new Object[capacity <<= 1];
        for (int i = 0; i < size; i++)
            elementData[i] = oldElem[i];
    }
    
    //remove()时用于在必要时刻缩小底层数组容量
    private void shrink() {
        if (capacity < DEFAULT_CAPACITY << 1) return;//不致收缩至DEFAULT_CAPACITY以下
        if (capacity >> 2 < size) return; //以25%为界
        
        Object[] oldElem = elementData; elementData = new Object[capacity >>= 1];
        for (int i = 0; i < size; i++)
            elementData[i] = oldElem[i];            
    }
    
    public ArrayList() {
        clear();
    }
    
    
    public void clear() {
        size = 0;
        elementData = new Object[capacity = DEFAULT_CAPACITY];
    }
	
    public int size() { 
        return size; 
    }
    
    public int capacity() { //用于测试shrink()&expand()
        return capacity; 
    }
    
    public boolean isEmpty() { 
        return size == 0; 
    }
    
    public void add(Object o){
        add(size(), o);
    }

    public void add(int index, Object o){
        if (index < 0 || size < index) 
            throw new IndexOutOfBoundsException();
        
        expand();
        for (int i = size; i > index; i--)
            elementData[i] = elementData[i - 1];
        elementData[index] = o;
        size++;
    }
        
    public Object get(int index){
        if (index < 0 || size <= index) 
            throw new IndexOutOfBoundsException();
        
        return elementData[index];
    }

    public Object remove(int index){
        if (index < 0 || size <= index) 
            throw new IndexOutOfBoundsException();
        
        Object removed = elementData[index];
        for (int i = index; i < size - 1; i++)
            elementData[i] = elementData[i + 1];
        size--;
        shrink();
        return removed;
    }



    public Iterator iterator(){
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator {
        private int current;
        
        public boolean hasNext() { 
            return current != size; 
        }
        
        public Object next() {
            if (!hasNext()) 
                throw new java.util.NoSuchElementException();
            
            return elementData[current++];
        }
    }


    //以下方法便于测试

    public ArrayList(Object ...args) {
        this();
        for (Object o : args)
            add(o);
    }

    public void add(Object ...args) {
        for (Object o : args) 
            add(o);
    }

    public void removeElems(int ...args) {
        for (int i : args)
            remove(i);
    }
    public static void showElements(ArrayList list) {
        System.out.print("当前list中元素：");
        Iterator iter = list.iterator();
        while (iter.hasNext())
            System.out.print(iter.next() + " ");
        System.out.println();
    }

    public static void test(ArrayList list) {
        System.out.println("--------基本方法测试---------");
        System.out.println("当前list.isEmpty(): " + list.isEmpty());
        System.out.println("当前list.size(): " + list.size());
        System.out.println("当前list.capacity(): " + list.capacity());
        showElements(list);        
    }

    public static void main(String[] args) {
        ArrayList list = new ArrayList(1, 2, 3, 4, 5);
        test(list);
        list.add(6, 7, 8, 9, 10);
        test(list);
        list.add(3, 11);
        list.get(3);
        test(list);
        list.remove(3);
        test(list);
        list.add(11,12,13,14,15,16,17,18,19,20,21,22,23,24);
        test(list);
        
        list.removeElems(1, 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1);
        test(list);
        
        
    }
}
