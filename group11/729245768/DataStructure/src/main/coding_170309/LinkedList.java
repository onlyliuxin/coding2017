package main.coding_170309;

/**
 * Created by peter on 2017/3/10.
 */
public class LinkedList implements List {
   private Node head;
   public LinkedList(){
       head = new Node();
       head.data = null;
       head.next = null;
   }
    @Override
    public void add(Object o) {
        Node p = head;
        while (p.next!=null){
            p=p.next;
        }
        Node node = new Node();
        node.data = o;
        node.next = null;
        p.next = node;

    }

    @Override
    public void add(Object o, int index) {
        if(index<0||index>getSize()){
            throw new ArrayIndexOutOfBoundsException("插入位置不合法");
        }
        int i =0;
        Node p =head;
        while (i<index){
            p = p.next;
            i++;
        }
        Node q = p.next;
        Node node = new Node();
        node.data = o;
        p.next = node;
        node.next = q;
    }

    @Override
    public Object get(int index) {
       if(getSize()==0){
           return null;
       }
       if(index<0||index>=getSize()){
           throw new ArrayIndexOutOfBoundsException("数组越界");
       }
       int i =0 ;
       Node p = head.next;
       while (i<index){
        p =p.next;
        i++;
       }
       return p.data;
    }

    @Override
    public Object remove(int index) {
       if(getSize()==0){
           return  null;
       }
       if(index<0||index>=getSize()){
           throw new ArrayIndexOutOfBoundsException("不合法的index");
       }
       int i = 0;
       Node p =head;
       while (i<index){
           p = p.next;
           i++;
       }
       Node q = p.next;
       p.next = q.next;
       return q.data;
    }

    @Override
    public int getSize() {
       int i =0;
       Node p = head.next;
       while (p!=null){
           p = p.next;
           i++;
       }
       return i;
    }
    private static class Node{
        Object data;
        Node next;
    }

    @Override
    public String toString() {
       StringBuilder sb = new StringBuilder("[");
       for(int i=0;i<getSize();i++){
           sb.append(get(i));
           if(i<getSize()-1){
               sb.append(",");
           }
       }
       return sb.append("]").toString();
    }

    /**
     * 把该链表逆置
     * 例如链表为 3->7->10 , 逆置后变为  10->7->3
     */
    public  void reverse(){
        Node p = head.next;
        head.next = null;
        while (p!=null){
            add(p.data,0);
            p = p.next;
        }
    }

    /**
     * 删除一个单链表的前半部分
     * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
     * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

     */
    public  void removeFirstHalf(){
        int removeSize = getSize()/2;
        for(int i=0;i<removeSize;i++){
            remove(i);
        }
    }

    /**
     * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
     * @param i
     * @param length
     */
    public  void remove(int i, int length){
        if(i<0||i>=getSize()){
            throw  new ArrayIndexOutOfBoundsException("下标越界");
        }
        if(length<0){
            throw new IllegalArgumentException("长度不能为负");
        }
        for(int index = i;index<i+length;index++){
         remove(index);
        }
    }
    /**
     * 假定当前链表和list均包含已升序排列的整数
     * 从当前链表中取出那些list所指定的元素
     * 例如当前链表 = 11->101->201->301->401->501->601->701
     * listB = 1->3->4->6
     * 返回的结果应该是[101,301,401,601]
     * @param list
     */
    public  int[] getElements(LinkedList list){
        int length = list.getSize();
        int[] data= new int[length];
        for(int i=0;i<length;i++){
            data[i] = (int)this.get((int)list.get(i));
        }
        return data;
    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 从当前链表中中删除在list中出现的元素

     * @param list
     */

    public  void subtract(LinkedList list){
        int index1 =0;
        int index2 = 0;
        while (index1<this.getSize()&&index2<list.getSize()){
            if((int)this.get(index1)<(int)list.get(index2)){
                index1++;
            }else if((int)this.get(index1)==(int)list.get(index2)){
                remove(index1);
                index1++;
            }else{
                index2++;
            }
        }
    }

    /**
     * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
     */
    public  void removeDuplicateValues(){
        Node p = head.next;
        if(p!=null){
            Node q = p.next;
            while (q!=null){
                if((int)p.data==(int)q.data){
                    Node temp = q;
                    p.next = q.next;
                    q = temp.next;
                }else{
                    p = p.next;
                    q = p.next;
                }
            }
        }
    }

    /**
     * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
     * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
     * @param min
     * @param max
     */
    public  void removeRange(int min, int max){
        if(min>=max){
            throw new IllegalArgumentException("max必须不能比min小");
        }
        int length = 0;//满足条件的个数
        Node p =head.next;
        Node q = head;
       Node startPos=null,endPos=null;
       boolean isStarted=false;
       while (p!=null){
           if((int)p.data>min&&!isStarted){
               startPos = q;
               isStarted = true;
           }
           if((int)p.data>=max){
               endPos = p;
               break;
           }
           q = p;
           p = p.next;
       }
       //存在三种情况:startPos和endPos均为null,startPos不为null,endPos为null,startPos不为null和endPos不为null
        startPos.next = endPos;

    }

    /**
     * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
     * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
     * @param list
     */
    public  LinkedList intersection( LinkedList list){
        Node p =this.head.next;
        Node q = list.head.next;
        LinkedList newList = new LinkedList();
        while (p!=null&&q!=null){
            if((int)p.data>(int)q.data){
                q = q.next;
            }else if((int)p.data==(int)q.data){
                newList.add(p.data);
                p = p.next;
                q = q.next;
            }else {
                p = p.next;
            }
        }
        while (p!=null){
            newList.add(p.data);
            p = p.next;
        }
        while (q!=null){
            newList.add(q.data);
            q = q.next;
        }
        return newList;
    }
}
