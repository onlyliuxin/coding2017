package cn.cs.week1.basic;


import com.sun.corba.se.impl.oa.poa.POAImpl;

public class LinkedList implements List {

  private Node head;
  private int size;

  public void add(Object o) {
    Node pointer = head;
    if(pointer == null){
      head = new Node(o,null);
      size ++;
      return;
    }
    while (pointer.next != null) {
      pointer = pointer.next;
    }
    pointer.next = new Node(o, null);
    size++;
  }

  public void add(int index, Object o) {
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException("插入索引越界");
    }
    Node newNode = new Node(o, null);
    //插入位置是头结点
    if(index == 0){
      newNode.next = head;
      head = newNode;
      size ++;
      return;
    }
    Node pointer = head;
    for (int i = 0; i < index - 1; i++) {
      pointer = pointer.next;
    }
    newNode.next = pointer.next;
    pointer.next = newNode;
    size++;
  }

  public Object get(int index) {
    if (index < 0 || index > size - 1) {
      throw new IndexOutOfBoundsException("索引越界");
    }
    Node pointer = head;
    for (int i = 0; i < index; i++) {
      pointer = pointer.next;
    }
    return pointer.data;
  }

  public Object remove(int index) {
    if (index < 0 || index > size - 1) {
      throw new IndexOutOfBoundsException("索引越界");
    }
    Node pointer = head;
    //删除的是头结点
    if(index == 0){
      Object o = head.data;
      head = head.next;
      pointer.next = null;
      size --;
      return o;
    }
    for (int i = 0; i < index - 1; i++) {
      pointer = pointer.next;
    }
    Object o = pointer.next.data;
    pointer.next = pointer.next.next;
    size--;
    return o;
  }

  public int size() {
    return size;
  }

  public void addFirst(Object o) {
    Node newNode = new Node(o, head);
    head = newNode;
    size++;
  }

  public void addLast(Object o) {
    Node newNode = new Node(o, null);
    Node pointer = head;
    while (pointer.next != null) {
      pointer = pointer.next;
    }
    pointer.next = newNode;
    size++;
  }

  public Object removeFirst() {
    Node first = head;
    Object o = first.data;
    head = first.next;
    first.next = null;
    size --;
    return o;
  }

  public Object removeLast() {
    Node pointer = head;
    if(pointer == null){
      return null;
    }else if(pointer.next == null){
      Object o = pointer.data;
      head = null;
      size --;
      return o;
    }
    while(pointer.next.next != null){
      pointer = pointer.next;
    }
    Object o = pointer.next.data;
    pointer.next = null;
    size --;
    return o;
  }

  public Iterator iterator() {
    return new Iterator() {
      Node pointer = new Node(0,head);
      @Override
      public boolean hasNext() {
        return pointer.next != null;
      }

      @Override
      public Object next() {
        pointer = pointer.next;
        return pointer.data;
      }
    };
  }


  public static class Node {
    Object data;
    Node next;

    Node(Object data, Node next) {
      this.data = data;
      this.next = next;
    }
  }

  /**
   * 把该链表逆置
   * 例如链表为 3->7->10 , 逆置后变为  10->7->3
   */
  public void reverse() {

  }

  /**
   * 删除一个单链表的前半部分
   * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
   * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10
   */
  public void removeFirstHalf() {

  }

  /**
   * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
   *
   * @param i
   * @param length
   */
  public void remove(int i, int length) {

  }

  /**
   * 假定当前链表和list均包含已升序排列的整数
   * 从当前链表中取出那些list所指定的元素
   * 例如当前链表 = 11->101->201->301->401->501->601->701
   * listB = 1->3->4->6
   * 返回的结果应该是[101,301,401,601]
   *
   * @param list
   */
  public static int[] getElements(LinkedList list) {
    return null;
  }

  /**
   * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
   * 从当前链表中中删除在list中出现的元素
   *
   * @param list
   */

  public void subtract(LinkedList list) {

  }

  /**
   * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
   * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
   */
  public void removeDuplicateValues() {

  }

  /**
   * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
   * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
   *
   * @param min
   * @param max
   */
  public void removeRange(int min, int max) {

  }

  /**
   * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
   * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
   *
   * @param list
   */
  public LinkedList intersection(LinkedList list) {
    return null;
  }
}
