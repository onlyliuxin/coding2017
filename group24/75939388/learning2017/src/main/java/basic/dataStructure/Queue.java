package basic.dataStructure;

/**
 * Created by macvi on 2017/4/4.
 */
public class Queue {
    private Object[] data;

    private int size = 10;

    private int extendedSize = 10;

    public Queue(){
        this.data = new Object[size];
    }

    public Queue(Object o){
        this.data = new Object[size];
        data[0] = o;
    }

    public void enQueue(Object o){
        //被添加的位置
        int index = this.size();
        if(this.size() == this.size){
            this.size += extendedSize;
            Object[] newData = new Object[this.size];
            System.arraycopy(this.data, 0, newData, 0, index);
            newData[index] = o;
            this.data = newData;
        }else{
            this.data[index] = o;
        }
    }

    public Object deQueue(){
        Object[] newData = new Object[this.size];
        Object d = this.data[0];
        System.arraycopy(this.data, 1, newData, 0, this.size - 1);
        this.data = newData;

        return d;
    }

    public Object peek(){
        return this.data[0];
    }

    public boolean isEmpty(){
        return peek() == null;
    }

    public int size(){
        int size = 0;
        for(Object obj : this.data){
            size += obj == null ? 0 : 1;
        }

        return size;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for(Object obj : this.data){
            if(obj != null){
                sb.append(obj.toString()).append(",");
            }else break;
        }
        return sb.toString();
    }
}
