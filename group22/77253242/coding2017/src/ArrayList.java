import javax.swing.plaf.basic.BasicTreeUI;
import java.security.SignatureException;
import java.util.Arrays;

/**
 * Created by fantasy on 2017-03-13.
 */

public class ArrayList implements List {

    private int size = 0;

    private Object[] elementData = new Object[10];

    public void add(Object o) {
        if(size >= elementData.length)
        {
            elementData= Arrays.copyOf(elementData, size+10);
        }
        elementData [++size] = o;
    }

    public void add(int index, Object o) {
        if(size >= elementData.length)
        {
            elementData = new Object[size + 10];
        }
        if(size > index)
        {
            elementData[++size] = o;
        }
        else
        {
            for(int i = size;i>index;i--)
            {
                elementData [i + 1] = elementData [i];
            }
        }
        elementData [index] = o;
        size++;
    }

    public Object get(int index) {
        if(index < 0 || index > size)
        {
            throw new IndexOutOfBoundsException("size:" + size + ",index:"+index);
        }
        return elementData[size];
    }

    public Object remove(int index) {
        if(size > index)
        {
            Object OldValue = elementData[index];
            for(int i = index;i<= size;)
            {
                elementData[i] = elementData[++i];
            }
            return OldValue;
        }
        throw new IndexOutOfBoundsException("size:"+ size + ",index:" + index);
    }


    public int size() {
        return size;
    }

    public Iterator iterator() {
        return null;
    }


}