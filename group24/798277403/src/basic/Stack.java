package basic;

/**
 * 自己实现的Stack
 * Created by zhouliang on 2017-03-10.
 */
class Stack<E> {

    private ArrayList<E> elementData;

    public Stack(){
        this.elementData = new ArrayList<E>();
    }

    public void push(E e){
        elementData.add(e);
    }

    public E pop(){
        return elementData.remove(elementData.size()-1);
    }

    public E peek(){
        return elementData.get(elementData.size()-1);
    }

    public boolean isEmpty(){
        return elementData.size() > 0;
    }

    public int size(){
        return elementData.size();
    }
}
