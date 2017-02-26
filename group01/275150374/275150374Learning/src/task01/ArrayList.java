package task01;

import java.util.Arrays;

/**第一周作业
 * 自己实现一个 ArrayList
 * Created by eurry on 2017/2/26.
 */
public class ArrayList {
    /**
     * ArrayList的长度
     */
    private int size = 0;

    private Object[] elementData = {};

    public void add(Object o){
        elementData = Arrays.copyOf(elementData, size+1);
        elementData[size] = o;
        size++;
    }

    public void add(int index, Object o){
        if(index < size){
            elementData = Arrays.copyOf(elementData, size+1);
            System.arraycopy(elementData, index, elementData, index+1, size-index);
            elementData[index] = o;
            size++;
        }else{
            elementData = Arrays.copyOf(elementData, index+1);
            elementData[index] = o;
            size = index+1;
        }
    }

    public Object get(int index){
        if(index < size){
            return elementData[index];
        }else{
            throw new IndexOutOfBoundsException("导致对数组范围以外的数据的访问");
        }
    }

    public Object remove(int index){
        if(index < size){
            Object re = elementData[index];
            System.arraycopy(elementData, index+1, elementData, index, size-index-1);
            elementData = Arrays.copyOf(elementData, size-1);
            size--;
            return re;
        }else{
            throw new IndexOutOfBoundsException("导致对数组范围以外的数据的访问");
        }
    }

    public int size(){
        return size;
    }

    public String toString(){
        String str = null;
        if(elementData.length > 0){
            str = "";
            for (Object anElementData : elementData) {
                str += anElementData.toString() + ",";
            }
            str = str.substring(0, str.length()-1);
        }
        return str;
    }

    /**
     * 测试
     */
    public static void main(String[] str){
        ArrayList list = new ArrayList();
        list.add("A");
        list.add("B");
        list.add(1, "C");
        list.add("D");
        Object d = list.get(3);
        Object dd = list.remove(3);
        System.out.println(list.size());
        System.out.println(list.toString());
    }
}
