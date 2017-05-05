package com.github.mrwengq.tid.list;


public interface List
{

    public abstract void add(Object obj);

    public abstract void add(int i, Object obj);

    public abstract Object get(int i);

    public abstract Object remove(int i);

    public abstract int size();
}
