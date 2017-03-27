package wiki.liven.code.dataStructures;

/**
 * Created by leven on 2017/2/21.
 * 队列
 * 只允许一端进行出队操作,另一端进行入队操作。
 * 特性是:先进先出
 * 本质上是一种操作受限制的线性表
 * 本实现线性表采用自己实现的ArrayList,
 * ArrayList理论上会受到JVM分配的内存大小,从而其空间会有上限。
 * 但是,该错误,将有JVM抛出。
 * 所以,本实现,队列忽略执行判断满队操作。
 */
public class Queue {

    private ArrayList list;
    private int head;//队头指针
    private int foot;//队尾指针


    public Queue(){
        head = foot = 0;
    }

    /**
     * 判空
     * @return
     */
    public boolean queueEmpty(){
        if (head==0&&foot==0){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 入队列
     * 1.先把元素放置到队列中
     * 2.将队尾指针加1
     * @param o
     */
    public void enQueue(Object o){
        list.add(foot,o);
        foot++;
    }

    /**
     * 删除队头元素
     * 0.先判断队列是否为空
     * 1.先取出队头的元素
     * 2.然后将队头的指针加1
     * @return
     */
    public int deQueue(){
        if (queueEmpty()==true)
            throw new IndexOutOfBoundsException("队列为空,无法执行该操作。");
        list.remove(head);
        return head++;
    }



}
