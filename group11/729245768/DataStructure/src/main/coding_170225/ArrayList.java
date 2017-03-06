import java.util.Arrays;

/**
 * Created by peter on 2017/2/22.
 */
public class ArrayList {
   private Object[] elements = new Object[10];
    private int position = 0;
    //添加元素
    public void add(Object o){
        //首先判断当前数组是否已满
        if(position==elements.length){
            ensureCapacity();//如果到达则扩展数组长度
        }
        elements[position++] = o;
    }
    //在index位置添加元素
    public void add(int index,Object o){
        if(index<0||index>position){
            System.out.println("invalid index");
            throw new ArrayIndexOutOfBoundsException();
        }
        if(position==elements.length){
            //如果此时数组已满
            ensureCapacity();
        }
        for(int i=position-1;i>=index;i--){
            elements[i+1] = elements[i];//元素向后移动一位
        }
        elements[index] = o;
        position++;//数组元素个数加一
    }

    //获取index位置的元素
    public Object get(int index){
        if(index<0||index>position-1){
            System.out.println("不合法的index");
            throw new ArrayIndexOutOfBoundsException();
        }
        return elements[index];
    }

    //删除index位置的元素
    public Object remove(int index){
       if(index<0||index>position-1){
           System.out.println("不合法的index");
           throw new ArrayIndexOutOfBoundsException();
       }
       position--;
        return elements[position+1];
    }
    public int size(){
        return position;
    }
    //返回一个迭代器
    public Iterator getIterator(){
        return new ArrayListIterator(this);
    }
    //扩展数组大小，在原来基础上扩展一倍
    public void ensureCapacity(){
        elements = Arrays.copyOf(elements,elements.length);
    }
    private class ArrayListIterator implements Iterator{
      private ArrayList list;
      int position=-1;
       public ArrayListIterator(ArrayList list){
            this.list=list;
       }
        @Override
        public boolean hasNext() {
            if(++position<list.size()){
                return  true;
            }
            return false;
        }
        @Override
        public Object next() {
            return list.get(position);
        }

        @Override
        public Object remove() {
           return list.remove(position);
        }
    }
}
