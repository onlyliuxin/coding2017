package com.pan.week01;

/**
 * Created by QiPan on 2017/2/23.
 */
public interface Iterator {
    boolean hasNext();

    Object next();

    void remove();
}
