package com.bruce.homework0226;

import com.bruce.utils.MyException;

import java.util.Arrays;

/**
 * 实现Queue的基本功能：peek,poll,add,length,isEmpty
 * Created by Bruce.Jiao on 2017/2/25.
 */
public class QueueV00  {
    /**
     * 队列元素的数组
     */
    private Object[] elementData;

    /**
     * 队列容量
     */
    private int max_size;

    /**
     * 队列头，允许删除
     */
    private int head;

    /**
     * 队列尾，允许插入
     */
    private int tail;

    /**
     * 无参构造，默认的初始长度10
     */
    public QueueV00() throws MyException{
        this(10);
    }

    /**
     * 有参构造
     * @param initCapacity 用户自定的初始长度
     */
    public QueueV00(int initCapacity) throws MyException{
        if(initCapacity < 0){
            throw new MyException("队列长度不能为负数");
        }
        this.max_size = initCapacity;
        elementData = new Object[initCapacity];
        head = tail = 0;
    }

    /**
     * 向队列里添加元素
     * @param value 添加的元素
     * @return true：添加成功；false：添加失败
     * @throws MyException 加入添加完成后元素个数超过队列最大尺寸，抛出异常
     */
    public boolean add(Object value) throws MyException{
        if(tail == max_size){
            throw new MyException("队列已满，不能继续插入");
        }
        elementData[tail++] = value;
        return true;
    }

    /**
     * 返回队列的第一个元素，但不从队列中删除该元素
     * @return 队列的第一个元素，以插入顺序为先后标准
     */
    public Object peek() throws MyException{
        if(isEmpty()){
            throw new MyException("队列为空队列");
        }
        return elementData[head];
    }

    /**
     * 返回队列的第一个元素，并且从队列中将该元素删除
     * @return 队列的第一个元素
     * @throws MyException 队列为空，抛出异常
     */
    public Object poll() throws MyException{
        if(isEmpty()){
            throw new MyException("队列为空队列");
        }
        //将队列的第一个元素暂存
        Object result = elementData[head];
        //将队列的第一个元素设置为null，并且将head加1
        elementData[head++] = null;
        return result;
    }

    /**
     * 队列长度
     * @return 队列中元素个数
     */
    public int length(){
        return tail-head;
    }

    /**
     * 判断队列是否为空
     * @return true：队列为空；false：队列不为空
     */
    public boolean isEmpty(){
        return tail == head;
    }

    /**
     * 临时方法，仅作为测试阶段打印队列元素使用
     * @return
     */
    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(elementData,length()));
    }
}
