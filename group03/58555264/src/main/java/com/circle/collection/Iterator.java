package com.circle.collection;

/**
 * Created by keweiyang on 2017/2/25.
 * Iterator对外暴露2个接口，隐藏了具体实现（数组or链表）
 */
public interface Iterator {
    boolean hasNext();

    Object next();
}
