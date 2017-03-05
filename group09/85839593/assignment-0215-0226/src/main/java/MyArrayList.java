import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: guohairui
 * Date: 17-2-22
 * Time: 上午12:06
 * To change this template use File | Settings | File Templates.
 */
public class MyArrayList  {
    public int size = 0;
    private Object [] elementData = new Object[5];
    public void add(int index,Object obj){
        if(index>size() ||index<0)
            throw new IndexOutOfBoundsException("哎呀我去,不够了.");
        elementData[index]=obj;
        size++;
    }
    public void insert(int index,Object obj){
        if(size>elementData.length-1){
            System.out.println("当前size：" + size + " 当前length:" + elementData.length+",再插不够了,需要扩容");
            Object [] tmpData =  elementData;
            elementData =new  Object[size+5] ;
            System.out.println("当前size：" + size + " 当前length扩了5后为:" + elementData.length);
            System.arraycopy(tmpData,0,elementData,0,index);
            elementData[index]=obj;
            System.arraycopy(tmpData,index,elementData,index+1,tmpData.length-index);
        }else {
            if(elementData[index]==null){
                elementData[index]=obj;
            }else {
                System.out.println("当前size：" + size + " 当前length:" + elementData.length);
                System.arraycopy(elementData,index,elementData,index+1,size-index);
                elementData[index]=obj;
            }
        }
        size++;
    }
    public void add(Object obj){
        if(size>=elementData.length){
            System.out.println("当前size：" + size + " 当前length:" + elementData.length);
            Object [] tmpData =  elementData;
           elementData =new  Object[size+5] ;
            System.out.println("当前size：" + size + " 当前length扩了5后为:" + elementData.length);
            System.arraycopy(tmpData,0,elementData,0,size);
            elementData[size]=obj;
        }else {
            System.out.println("当前size：" + size + " 当前length:" + elementData.length);
           elementData[size]=obj;
        }
        size++;
    }
    public Object get(int index) {
        if(index>=size)
            throw new IndexOutOfBoundsException("越了");
      return elementData[index];
    }
    public Object remove(int index){
        Object delValue = elementData[index];
        int movesize = size-index-1;
        System.out.print("size:"+size+" index:"+index+" ,size-index-1:"+movesize);
        System.arraycopy(elementData,index+1,elementData,index,movesize);
        System.out.print("删除后前移位，数组末位清空");
        elementData[--size]=null;

        return delValue;
    }
    public int size(){
        return size;
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i=0;i<size;i++) {
            Object o = elementData[i];
            sb.append(elementData[i]);
            if(i<size-1)
            sb.append(",");
        }
            return sb.append(']').toString();
    }
}
