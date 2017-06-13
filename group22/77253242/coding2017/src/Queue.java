import java.awt.image.AreaAveragingScaleFilter;

/**
 * Created by fantasy on 2017-03-13.
 */
public class Queue {
    ArrayList arrayList = new ArrayList();


    public void enQueue(Object o){
        arrayList.add(o);
    }

    public Object deQueue(){
        return arrayList.remove(0);
    }

    public boolean isEmpty(){
        return !(arrayList.size() >0);
    }

    public int size(){
        return arrayList.size();
    }
}