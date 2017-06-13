/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclass;

/**
 *
 * @author CJ
 */
public interface List {
	public void add(Object o);
	public void add(int index, Object o);
	public Object get(int index);
	public Object remove(int index);
	public int size();
}
