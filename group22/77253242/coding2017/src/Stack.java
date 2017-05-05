import java.util.EmptyStackException;

/**
 * Created by fantasy on 2017-03-13.
 */
public class Stack {
    private ArrayList elementData = new ArrayList();

    public void push(Object o){
        elementData.add(o);
    }

    public Object pop(){
        if(elementData.size() == 0)
        {
            return elementData.remove(elementData.size() - 1 );
        }
        return null;
    }

    public Object peek(){
        if(elementData.size() == 0 )
        {
            return elementData.get(elementData.size() - 1);
        }
        throw new EmptyStackException();
    }
    public boolean isEmpty(){
        return !(elementData.size()>0);
    }
    public int size(){
        return elementData.size();
    }
}
