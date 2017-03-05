package task01;

/**第一周作业
 * 自己实现一个 栈
 * Created by eurry on 2017/2/26.
 */
public class Stack {

    private ArrayList elementData= new ArrayList();

    public void push(Object o){
        elementData.add(o);
    }

    public Object pop(){
        return elementData.remove(elementData.size()-1);
    }

    /**
     *查看栈顶对象而不移除它
     */
    public Object peek(){
        return elementData.get(elementData.size()-1);
    }

    public boolean isEmpty(){
        return elementData.size()==0;
    }
}

