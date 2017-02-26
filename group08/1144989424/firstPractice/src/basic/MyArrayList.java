package basic;

/**
 * 我的ArrayList实现
 * @author Wayss
 * 2017-02-22
 */

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
        //0.先对index的值进行判断，小于0，或者，大于size，越界
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
        int length = elementData.length;
        //0.先对index的值进行判断，小于0，或者，大于等于size，便越界
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("插入的下标越界了:"+"插入的下标为："+index+"集合大小为："+size);
        }
        //2.拿出index位置的值，
        Object o = elementData[index];
        
        return o;
    }
    
    public Object remove(int index){
        //remove 前两步的逻辑和get方法相同
        int length = elementData.length;
        //0.先对index的值进行判断，小于0，或者，大于等于size，便越界
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("插入的下标越界了:"+"插入的下标为："+index+"集合大小为："+size);
        }
        //2.拿出index位置的值，
        Object o = elementData[index];
        
        //3.删除index位置的值，之后的值，向前移动。
        for(int i = index; i < size-1; i++){
            elementData[i] = elementData[i+1];
        }
        size--;
        return null;
    }
    
    public int size(){
        return size;
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
