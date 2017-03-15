package djj;


import java.util.Arrays;

/**
 * Created by jerry on 2017/2/26.
 */
public class ArrayList implements List {

    private int size = 0;

    private Object[] elementData = new Object[100];

    public void add(Object o){
        if(size>elementData.length*0.8){
            Arrays.copyOf(elementData,elementData.length*2);
        }
        elementData[size]=o;
        size++;
    }
    public void add(int index, Object o){
        if (size>=index){
            Object[] temp=new Object[elementData.length];
            System.arraycopy(elementData,0,temp,0,index);
            temp[index]=o;
            System.arraycopy(elementData,index,temp,index+1,size-index);
            elementData=temp;
        }else if(size<index){
            throw new RuntimeException("越界");
        }
        size++;
    }

    public Object get(int index){
        if(index>size){
            throw new RuntimeException("越界");
        }else{
            return elementData[index];
        }
    }

    public Object remove(int index){
        Object tempObj=null;
        if(index<=size){
            Object[] temp=new Object[elementData.length];
            System.arraycopy(elementData,0,temp,0,index);
            tempObj=elementData[index];
            System.arraycopy(elementData,index+1,temp,index,size-index-1);
            elementData=temp;
        }
        size--;
        return tempObj;
    }

    public int size(){
        return size;
    }

    public Iterator iterator(){
        return null;
    }

}
