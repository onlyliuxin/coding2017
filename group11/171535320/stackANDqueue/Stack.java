package stackANDqueue;

import java.util.ArrayList;

/**
 * Created by dengdechao on 2017/2/22.
 */
public class Stack {
    private ArrayList elementData = new ArrayList();

    public void push(Object o){
        elementData.add(o);
    }

    public Object pop(){
        if(elementData.size() == 0) {
            return null;
        }
        Object obj = null;
        for(int i = 0; i < elementData.size(); ++i) {
            obj = elementData.get(i);
        }
        int j = 0;
        while(j < elementData.size()) {
            ++j;
        }
        elementData.set(j - 1, null);
        return obj;
    }

    public Object peek(){
        if(elementData.size() == 0) {
            return null;
        }
        Object obj = null;
        for(int i = 0; i < elementData.size(); ++i) {
            obj = elementData.get(i);
        }

        return obj;
    }
    public boolean isEmpty(){
        if(elementData.size() == 0) {
            return true;
        }

        return false;
    }
    public int size(){
        return elementData.size();
    }
}
