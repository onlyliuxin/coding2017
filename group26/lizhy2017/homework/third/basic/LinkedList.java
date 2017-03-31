package third.basic;

/**
 * ${}
 * Created by spark_lizhy on 2017/3/31.
 */

public class LinkedList {

    private Node head;

    public void add(Object o){

    }
    public void add(int index , Object o){

    }
    public Object get(int index){
        return null;
    }
    public Object remove(int index){
        return null;
    }

    public int size(){
        return -1;
    }

    public void addFirst(Object o){

    }
    public void addLast(Object o){

    }
    public Object removeFirst(){
        return null;
    }
    public Object removeLast(){
        return null;
    }
//    public Iterator iterator(){
//        return null;
//    }


    private static  class Node{
        Object data;
        Node next;

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
     * 如果 list = 2->5->7->8->10 , 删除以后的值为 7,8,10
     */
    public  void removeFirstHalf(){

    }

    /**
     * 从第 i 个元素开始， 删除 length 个元素 ， 注意 i 从 0 开始
     * @param i
     * @param length
     */
    public  void remove(int i, int length){

    }
    /**
     * 假定当前链表和 listB 均包含已升序排列的整数
     * 从当前链表中取出那些 listB 所指定的元素
     * 例如当前链表 = 11->101->201->301->401->501->601->701
     * listB = 1->3->4->6
     * 返回的结果应该是 [101,301,401,601]
     * @param list
     */
    public  int[] getElements(LinkedList list){
        return null;
    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 从当前链表中中删除在 listB 中出现的元素
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
     * 试写一高效的算法，删除表中所有值大于 min 且小于 max 的元素（若表中存在这样的元素）
     * @param min
     * @param max
     */
    public  void removeRange(int min, int max){

    }

    /**
     * 假设当前链表和参数 list 指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
     * 现要求生成新链表 C，其元素为当前链表和 list 中元素的交集，且表 C 中的元素有依值递增有序排列
     * @param list
     */
    public  LinkedList intersection( LinkedList list){
        return null;
    }
}
