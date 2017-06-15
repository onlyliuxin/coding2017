package code08;

/**
 * 用数组实现循环队列 - 杜绝“假上溢”
 * 在入队和出队的操作中，头尾指针只增加不减小，致使被删除元素的空间永远无法重新利用。
 *
 * 因此，尽管队列中实际的元素个数远远小于向量空间的规模，但也可能由于尾指针巳超出向量空间的上界而不能做入队操作。
 * 为充分利用向量空间。克服上述假上溢现象的方法是将向量空间想象为一个首尾相接的圆环，并称这种向量为循环队列。
 * 在循环队列中进行出队、入队操作时，头尾指针仍要加1，朝前移动。
 * 只不过当头尾指针指向向量上界（QueueSize-1）时，其加1操作的结果是指向向量的下界0。
 * @param <E>
 */
public class CircleQueue <E> {
	
	private int maxSize;
    //用数组来保存循环队列的元素
    private Object[] elementData;
    //队头
    private int front;
    //队尾
    private int rear;
    //元素个数
    private int count;

    public CircleQueue(int maxSize) {
        this.maxSize = maxSize+1; //队列需要一个空的位置用于区分队列满和队列空
        this.elementData  = new Object[maxSize+1];
        this.front = 0;
        this.rear = 0;
    }

    public int size() {
        return count;
    }

    public void enQueue(E data) {
        if(this.isFull()){
            System.out.println("queue is full, enQueue failed");
            return;
        }
        elementData[rear] = data;
        rear = (rear + 1) % this.maxSize;
        count ++;
    }

    public E deQueue() {
        if(this.isEmpty()){
            System.out.println("queue is empty, deQueue failed");
            return null;
        }
        E e = (E) elementData[front];
        front = (front + 1) % this.maxSize;
        count --;
        return e;
    }

    public boolean isFull(){
	    return ((rear + 1) % this.maxSize == front);
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public static void main(String[] args) {
        CircleQueue<String> q = new CircleQueue<String>(11);
        q.enQueue("a1");
        q.enQueue("a2");
        q.enQueue("a3");
        q.enQueue("a4");
        q.enQueue("a5");
        q.enQueue("a6");
        q.enQueue("a7");
        q.enQueue("a8");
        q.enQueue("a9");
        q.enQueue("a10");
        System.out.println("current size is : " + q.size());

        System.out.println("dequeue : " + q.deQueue());
        System.out.println("after deQueue , current size is : " + q.size());

        q.enQueue("new1");
        System.out.println("after enQueue , current size is : " + q.size());

        /*while(!q.isEmpty()){
            System.out.println(q.deQueue());
            System.out.println("current size is : " + q.size());
        }*/



    }
}
