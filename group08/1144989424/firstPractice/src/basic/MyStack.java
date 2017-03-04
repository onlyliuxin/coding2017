package basic;

/**
 * 栈实现
 * @author Wayss
 * 2017-02-25
 */

public class MyStack {
    private MyArrayList arrList = new MyArrayList();
    
    public void push(Object o){
        arrList.add(o);
    }
    
    public Object pop(){
        //elementData.size()-1是当前数组的最后一个元素的下标
        return arrList.remove(arrList.size() - 1);
    }
    
    public Object peek(){
        return arrList.get(arrList.size() - 1);
    }
    public boolean isEmpty(){
        if(arrList.size() == 0){
            return true;
        }
        return false;
    }
    public int size(){
        return arrList.size();
    }
}
