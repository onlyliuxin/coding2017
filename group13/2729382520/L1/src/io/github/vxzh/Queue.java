package io.github.vxzh;

/**
 * Created by xuxiaoqing on 26/02/2017.
 */
public interface Queue {

    void enQueue(Object o);

    Object deQueue();

    boolean isEmpty();

    int size();
}
