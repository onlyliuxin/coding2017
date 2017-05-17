/**
 * Created by lxx on 2017/5/6.
 */
public class CircleQueue{

    private int size;
    private int first;
    private int last;
    Object[] queue;

    public CircleQueue(int size){
        queue = new Object[size];
        first = 0;
        last = 0;
    }

    public boolean isEmpty(){
        return first == last;
    }

    public void enCircleQueue(Object o){
        if( (last+1) % size == first){
               System.out.println("Circle Queue is full");
        }else {
            queue[last] = o;
            last = (last+1) % queue.length;
        }
    }

    public int getSize(){
        return (last - first + queue.length) % queue.length;
    }

    public Object deCircleQueue(){
        if(isEmpty()){
            return null;
        }else{
            Object item = queue[first];
            first = (first+1)%queue.length;
            return item;
        }

    }
}
