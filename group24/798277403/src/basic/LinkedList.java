package basic;

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

    /**
     * 把该链表逆置
     * 例如链表为 3->7->10 , 逆置后变为  10->7->3
     */
    public  void reverse(){

    }

    /**
     * 删除一个单链表的前半部分
     * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
     * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
     */
    public  void removeFirstHalf(){

    }

    /**
     * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
     * @param i
     * @param length
     */
    public  void remove(int i, int length){

    }
    /**
     * 假定当前链表和listB均包含已升序排列的整数
     * 从当前链表中取出那些listB所指定的元素
     * 例如当前链表 = 11->101->201->301->401->501->601->701
     * listB = 1->3->4->6
     * 返回的结果应该是[101,301,401,601]
     * @param list
     */
    public  int[] getElements(LinkedList list){
        return null;
    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 从当前链表中中删除在listB中出现的元素
     * @param list
     */
    public  void subtract(LinkedList list){

    }

    /**
     * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
     */
    public  void removeDuplicateValues(){

    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
     * @param min
     * @param max
     */
    public  void removeRange(int min, int max){

    }

    /**
     * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
     * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
     * @param list
     */
    public LinkedList intersection(LinkedList list){
        return null;
    }
}
