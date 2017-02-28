import java.io.Serializable;
import java.util.Collection;

/**
 * Created by wangtiegang on 2017/2/18.
 */
public class MyArrayList implements List,Serializable{
    private int size;
    private Object[] elementData;

    //空构造方法，初始容量10
    public MyArrayList(){
        this(10);
    }

    //构造指定容量的空列表
    public MyArrayList(int size){
        this.elementData = new Object[size];
    }

    //构造一个包含指定 collection 的元素的列表，这些元素是按照该 collection 的迭代器返回它们的顺序排列的。
    public MyArrayList(Collection<?> collection){
        this.size = collection.size();
        this.elementData = new Object[collection.size()];
        //复制元素到数组中
    }

    //添加值到列表尾
    public void add(Object obj){
        //先判断长度够不够
        this.ensureCapacity(size+1);
        //添加到数组
        elementData[size++] = obj;
    }

    //添加值到指定位置
    public void add(int index,Object obj){
        this.ensureCapacity(size+1);
        //添加元素到指定位置,将所有指定位置开始的值都往后移一位
        for(int i = index;i<elementData.length;i++){
            Object tempObj = elementData[i];
            elementData[i] = obj;
            obj = tempObj;
        }
        //size加一
        size++;
    }

    //移除所有元素
    public void clear(){
        elementData = new Object[10];
    }

    //如果包含指定元素，则返回true
    public boolean contains(Object obj){
        if(obj == null){
            return false;
        }
        for(int i = 0;i<size;i++){
            if(obj.equals(elementData[i])){
                return true;
            }
        }
        return false;
    }

    //返回此列表中首次出现的指定元素的索引
    public int indexOf(Object obj){
        if(obj == null){
            return -1;
        }
        for(int i = 0;i<size;i++){
            if(obj.equals(elementData[i])){
                return i;
            }
        }
        return -1;
    }

    //返回此列表中最后一次出现的指定元素的索引
    public int lastIndexOf(Object obj){
        if(obj == null){
            return -1;
        }
        for(int i = size;i>=0;i--){
            if(obj.equals(elementData[i])){
                return i;
            }
        }
        return -1;
    }

    //没有元素则返回true
    public boolean isEmpty(){
        return size == 0 ? true : false;
    }

    //remove指定位置元素
    public Object remove(int index){
        if(index > size){
            throw new IndexOutOfBoundsException();
        }else {
            Object removeObj = elementData[index];
            for(int i=index;i<size;i++){
                elementData[i] = elementData[i+1];
            }
            --size;
            return removeObj;
        }
    }

    //remove指定匹配的第一个元素
    public boolean remove(Object obj){
        if(this.contains(obj)){
            this.remove(indexOf(obj));
            return true;
        }
        return false;
    }

    //移除指定范围内元素
    public void removeRange(int fromIndex,int toIndex){
        if((fromIndex < 0 || fromIndex > size) || (toIndex < 0 || toIndex > size) ){
            throw new IndexOutOfBoundsException();
        }else{
            if(fromIndex < toIndex){
                for(int i = fromIndex ; i < size ; i++){
                    if(toIndex + 1 <= size){
                        elementData[i] = elementData[toIndex+1];
                    }else{
                        elementData[i] = null;
                    }
                }
                size = size - (toIndex - fromIndex) - 1;
            }else if(fromIndex > toIndex){
                for(int i = toIndex ; i < size ; i++){
                    if(fromIndex + 1 <= size){
                        elementData[i] = elementData[fromIndex+1];
                    }else{
                        elementData[i] = null;
                    }
                }
                size = size - (fromIndex - toIndex) - 1;
            }
        }
    }

    //替换指定位置元素
    public Object set(int index,Object obj){
        if(index > size || index < 0){
            throw new IndexOutOfBoundsException();
        }else {
            Object oldObj = elementData[index];
            elementData[index] = obj;
            return oldObj;
        }
    }

    public Object[] toArray(){
        Object[] arr = new Object[elementData.length];
        for(int i = 0 ; i<size ;i++){
            arr[i] = elementData[i];
        }
        return arr;
    }

    public void ensureCapacity(int minCapacity){
        if(minCapacity > elementData.length){
            Object[] oldData = elementData;
            int newCapacity = (elementData.length*3)/2 + 1;
            if(newCapacity < minCapacity){
                newCapacity = minCapacity;
            }
            Object[] newData = new Object[newCapacity];
            //复制元素到新的数组中
            elementData = this.copyArray(elementData,newCapacity);
        }
    }

    public Object[] copyArray(Object[] arr,int newCapacity){
        Object[] newArray = new Object[newCapacity];

        for(int i = 0 ; i < arr.length ; i++){
            newArray[i] = arr[i];
        }

        return newArray;
    }

    public int size(){
        return this.size;
    }

    public Object get(int index){
        return elementData[index];
    }

}
