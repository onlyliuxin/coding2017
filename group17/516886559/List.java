package com.rd.p2p.common.util.liuxin;

public interface List<E> {
	public void add(E o);
	public void add(int index, E o);
	public Object get(int index);
	public Object remove(int index);
	public int size();
}
