package src;

import java.util.EmptyStackException;

/**
 * Created by Yang on 2/25/2017.
 */
public class MyStack {

    private MyArrayList stack = new MyArrayList();

    public void push(Object obj){
        stack.add(obj);
    }

    public Object pop(){
        if (stack.size()==0){
            throw new EmptyStackException();
        }
        return stack.remove(stack.size()-1);
    }

    public boolean isEmpty(){
        return stack.isEmpty();
    }
}
