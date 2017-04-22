package basic.dataStructure.stack;

import basic.dataStructure.array.ArrayList;

/**
 * Created by macvi on 2017/4/4.
 */
public class Stack {

    private ArrayList elementData = new ArrayList();

    public Stack(){}

    public Stack(Object...objs){
        for(int i = 0; i < objs.length; i++){
            this.push(objs[i]);
        }
    }

    public void push(Object o){
        this.elementData.add(o);
    }

    public Object pop(){
        int index = elementData.size() - 1;
        Object obj = elementData.remove(index);

        return obj;
    }

    public Object peek(){
        int index = elementData.size() - 1;
        return index == -1 ? null : elementData.get(index);
    }
    public boolean isEmpty(){
        return peek() == null;
    }
    public int size(){
        return elementData.size();
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for(int i = this.size() - 1; i >= 0; i--){
            if(i != 0){
                sb.append(elementData.get(i).toString()).append(",");
            }else{
                sb.append(elementData.get(i).toString());
            }
        }

        return sb.toString();
    }
}
