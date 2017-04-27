package stack;

import java.util.ArrayList;
import java.util.EmptyStackException;

/**
 * Created by Lxx on 2017/4/23.
 */
public class Stack {

    private ArrayList array;
    private int count;


    public Stack() {
        array = new ArrayList();
    }

    public void push(Object o) {
        count++;
        array.add(o);

    }

    public Object pop() {
        count--;
        Object o = array.get(count);
       array.remove(count);

        return o;
    }

    public Object peek() {

        if(count == 0){
            throw new EmptyStackException();
        }
        return array.get(array.size()-1);
    }

    public boolean isEmpty() {
        return array.size() == 0;
    }

    public int size() {

        return count;
    }

    public String toString(){

        StringBuilder string = new StringBuilder();
        Stack s = new Stack();
        int size = this.size();
        for(int i = 0; i < size; i++){
            string.append(this.peek().toString());
            if(i < size-1 )
                string.append(",");
            s.push(this.pop());
        }

        while(!s.isEmpty()){
            this.push(s.pop());
        }

        return string.toString();
    }


}
