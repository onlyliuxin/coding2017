package basic;

public class MyArrayList implements MyList {
    
    private int size = 0;
    
    private Object[] elementData = new Object[10];
    
    /**
     * 往集合中添加一个元素
     */
    public void add(Object o){
        int length = elementData.length;
        //1.比较元素个数和数组大小，判断是否需要扩大数组
        if(size == length){
            expandArray();
        }
        //2.直接赋值给size，即最后一个元素
        elementData[size] = o;
        size++;
    }
    
    /**
     * 往集合指定位置添加一个元素，该位置和其之后的元素向后移动一位。
     * 位置不合法时，抛出异常。
     */
    public void add(int index, Object o){
        int length = elementData.length;
        //0.先对index的值进行判断，不能小于0，且不能大于size
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("插入的下标越界了:"+"插入的下标为："+index+"集合大小为："+size);
        }
        //1.比较元素个数和数组大小，判断是否需要扩大数组
        if(size == length){
            expandArray();
        }
        //2.移动index之后的数组元素
        for(int i = size; i > index; i--){
            elementData[i] = elementData[i-1];
        }
        elementData[index] = o;
        size++;
    }
    
    public Object get(int index){
        return null;
    }
    
    public Object remove(int index){
        return null;
    }
    
    public int size(){
        return -1;
    }
    
    public MyIterator iterator(){
        return null;
    }
    
    private void expandArray(){
        int length = elementData.length;
        Object [] newArr = new Object[length * 2];
        for(int i = 0; i < length; i++){
            newArr[i] = elementData[i];
        }
        elementData = newArr;
    }
}
