
/**
 * Created by peter on 2017/2/23.
 */
public class Stack {
    private LinkedList linkedList = new LinkedList();
    //进栈
    public void push(Object o){
        linkedList.add(o);
    }
    //出栈
    public Object pop(){
        return linkedList.removeLast();
    }
    //取栈顶元素
    public Object peek(){
        return linkedList.get(linkedList.size()-1);
    }
    //判断是否为空
    public boolean isEmpty(){
        return linkedList.size()==0;
    }
    //求栈内元素
    public int size(){
        return linkedList.size();
    }
}
