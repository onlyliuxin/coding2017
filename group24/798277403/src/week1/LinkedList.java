package week1;

/**
 * 自己实现的LinkedList
 * Created by zhouliang on 2017-03-10.
 */
class LinkedList<E> implements List<E> {

    private int size;
    private Node<E> first;
    private Node<E> last;

    public LinkedList(){
    }

    @Override
    public void add(E e) {
        Node<E> temp = new Node<E>(e);
        if(first != null){
            last.next = temp;
            last = temp;
        }else{
            first = temp;
            last = temp;
        }
        size++;
    }

    /**
     * 指定下标添加元素
     * @param index 可以在链表末尾加，就是可以的等于size，不能大于size
     * @param e 代表Element
     */
    @Override
    public void add(int index, E e) {
        checkPositionIndex(index);

        Node<E> temp = new Node<E>(e);
        if(index == size){
            last.next = temp;
            last = temp;
        }else{
            Node<E> begin = first;
            index--;
            while(index>0){
                begin = begin.next;
                index--;
            }
            Node<E> next = begin.next;
            begin.next = temp;
            temp.next = next;
        }
        size++;
    }

    @Override
    public E get(int index) {
        checkElementIndex(index);

        Node<E> temp = first;
        while(index>0){
            temp = temp.next;
            index--;
        }
        return temp.value;
    }

    @Override
    public E remove(int index) {
        checkElementIndex(index);

        Node<E> temp;
        if(index == 0){
            temp = first;
            first = first.next;
            size--;
            return temp.value;
        }else{
            temp = first;
            index--;
            //找到要删除节点的前一个节点
            while(index>0){
                temp = temp.next;
                index--;
            }
            Node<E> removeNode = temp.next;
            temp.next = removeNode.next;
            size--;
            return removeNode.value;

        }

    }

    public E removeLast(){
        return remove(size-1);
    }

    public void addFirst(E e){
        Node<E> temp = new Node<E>(e);
        if(first == null){
            first = temp;
            last = temp;
        }else{
            temp.next = first;
            first = temp;
        }
        size++;
    }

    public void addLast(E e){
        Node<E> temp = new Node<E>();
        if(first == null){
            first = temp;
            last = temp;
        }else{
            last.next = temp;
            last = temp;
        }
        size++;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: "
                    + size);
        }
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: "
                    + size);
        }
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }
    @Override
    public int size() {
        return size;
    }

    private static class Node<E>{
        E value;
        Node<E> next;

        Node(){

        }

        Node(E e){
            this.value = e;
        }
    }
}
