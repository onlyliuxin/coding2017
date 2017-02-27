import java.util.Objects;

/**
 * Created by Yusen Meng on 25/02/2017.
 */
public class myIterator implements Iterator {
    int index = 0;
    ArrayList _array = null;
    LinkedList _list = null;
    myIterator (LinkedList theList) {
        _list = theList;
    }
    myIterator (ArrayList theArray) {
        _array = theArray;
    }
    public boolean hasNext() {
        boolean  result;
        if (_array != null) {
            result = index < _array.size();
        } else {
            result = index < _list.size();
        }
        return  result;
    }

    public Object next() {
        Object returnValue;
        if (_array != null) {
            returnValue =  _array.get(index);
            index++;
        } else  {
            returnValue = _list.get(index);
            index++;
        }
        return returnValue;
    }
}
