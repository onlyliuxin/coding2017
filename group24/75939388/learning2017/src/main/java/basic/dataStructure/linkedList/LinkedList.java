package basic.dataStructure.linkedList;

import basic.dataStructure.ArrayUtil;
import basic.dataStructure.List;
import basic.dataStructure.array.ArrayList;

/**
 * Created by macvi on 2017/4/3.
 */
public class LinkedList implements List {
    private Node head;

    public LinkedList() {
        this.head = new Node();
    }

    public void add(Object o) {
        if (this.head.data == null) {
            this.head = new Node(o, null);
        } else {
            Node temp = this.head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = new Node(o, null);
        }
    }

    public void add(int index, Object o) {
        if (index > this.size() || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        if(index == 0){
            Node newNode = new Node(o, this.head);
            this.head = newNode;
            return;
        }

        if(index == this.size()){
            this.add(o);
            return;
        }

        Node before = getNode(index - 1);
        Node next = getNode(index);
        Node newNode = new Node(o, next);
        before.next = newNode;

    }

    private Node getNode(int index) {
        int i = 0;
        Node temp = this.head;
        while (temp.data != null) {
            if (index == i) {
                return temp;
            }

            if (temp.next != null) {
                temp = temp.next;
            } else break;

            i++;
        }

        return null;
    }

    public Object get(int index) {
        if (index > this.size() || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        return this.getNode(index).data;
    }

    public Object remove(int index) {
        if(index > this.size() || index < 0){
            throw new IndexOutOfBoundsException();
        }

        Object removed = get(index);

        Node before = getNode(index - 1);
        Node next = getNode(index + 1);
        before.next = next;

        return removed;
    }

    public int size() {
        int size = 0;
        Node temp = this.head;
        while (temp.data != null) {
            size++;
            if (temp.next != null) {
                temp = temp.next;
            } else break;
        }

        return size;
    }

    public void asList(Object[] array){
        LinkedList list = new LinkedList();
        for(int i = 0; i < array.length; i++){
            list.add(array[i]);
        }

        this.head = list.head;
    }

    public Object[] toArray(LinkedList list){
        int size = list.size();
        Object[] arr = new Object[size];
        for(int i = 0; i < size; i++){
            arr[i] = list.get(i);
        }

        return arr;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        Node temp = this.head;
        while (temp.data != null) {
            sb.append(temp.data.toString()).append(",");
            if (temp.next != null) {
                temp = temp.next;
            } else break;
        }

        return sb.toString();
    }

    private static class Node {
        Object data;
        Node next;

        public Node() {}

        public Node(Object obj, Node next) {
            this.data = obj;
            this.next = next;
        }

    }


    /**
     * 把该链表逆置
     * 例如链表为 3->7->10 , 逆置后变为  10->7->3
     */
    public void reverse(){
        int size = this.size();

        if(size == 1){
            return;
        }

        Object[] data = new Object[size];
        for(int i = 0; i < size; i++){
            data[i] = this.get(i);
        }

        this.head = new Node();

        for(int i = size - 1; i >= 0; i--){
            this.add(data[i]);
        }
    }

    /**
     * 删除一个单链表的前半部分
     * 例如：array = 2->5->7->8 , 删除以后的值为 7->8
     * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

     */
    public  void removeFirstHalf(){
        int size = this.size();
        int index = this.size()/2;
        ArrayList al = new ArrayList();
        for(int i = index; i < size; i++){
            al.add(this.get(i));
        }

        this.head = new Node();

        for(int i = 0; i < al.size(); i++){
            this.add(al.get(i));
        }
    }

    /**
     * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
     * @param i
     * @param length
     */
    public  void remove(int i, int length){
        for(int j = i; j < i + length; j++){
            this.remove(i);
        }
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
        int size = list.size();
        int[] arr = new int[size];
        for(int i = 0; i < size; i++){
            int index = (Integer) list.get(i);
            arr[i] = (Integer) this.get(index);
        }

        return arr;
    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 从当前链表中中删除在listB中出现的元素

     * @param list
     */

    public void subtract(LinkedList list){
        Object[] arr1 = toArray(this);
        Object[] arr2 = toArray(list);
        for(int i = 0; i < arr2.length; i++){
            arr1 = ArrayUtil.remove(arr1, arr2[i]);
        }

        asList(arr1);
    }

    /**
     * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
     */
    public void removeDuplicateValues(){
        int size = this.size();
        ArrayList indexList = new ArrayList();
        ArrayList valueList = new ArrayList();
        for(int i = 0; i < size; i ++){
            int valueI = (Integer)this.get(i);
            int index = 0;
            for(int j = i + 1; j < size; j++){
                if(valueList.contains(valueI)){
                    continue;
                }
                int valueJ = (Integer) this.get(j);
                if(valueJ == valueI){
                    index++;
                }

                if(index > 0){
                    indexList.add(j);
                    valueList.add(valueJ);
                }
            }
        }

        Object[] arr = new Object[size];
        for(int i = 0; i < size; i++){
            arr[i] = indexList.contains(i) ? false : this.get(i);
        }

        ArrayUtil au = new ArrayUtil();
        arr = au.remove(arr, false);

        asList(arr);
    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
     * @param min
     * @param max
     */
    public void removeRange(int min, int max){
        int size = this.size();
        int[] range = new int[max - min];
        int index = 0;
        for(int i = 0; i < size; i++){
            int value = (Integer) this.get(i);
            if(value > min && value < max){
                range[index] = value;
                index++;
            }
        }

        Object[] arr = new Object[size];
        for(int i = 0; i < size; i++){
            arr[i] = this.get(i);
        }

        for(int i = 0; i < range.length; i++){
            arr = ArrayUtil.remove(arr, range[i]);
        }

        asList(arr);
    }

    /**
     * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
     * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
     * @param list
     */
    public LinkedList intersection( LinkedList list){
        //组合成新的链表
        int listSize = list.size();
        for(int i = 0 ; i < listSize; i ++){
            this.add(list.get(i));
        }

        //转化成数组
        int size = this.size();
        int[] arr = new int[size];
        for(int i = 0; i < size; i++){
            arr[i] = (Integer)this.get(i);
        }
        //排序
        for(int i = 0; i < size - 1; i ++){
            for(int j = 0; j < size - i - 1; j ++){
                if(arr[j] >= arr[j + 1]){
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        //组装
        LinkedList li = new LinkedList();
        for(int i = 0; i < size; i ++){
            li.add(arr[i]);
        }
        return li;
    }
}
