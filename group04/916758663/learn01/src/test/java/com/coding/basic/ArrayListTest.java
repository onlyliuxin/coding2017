package com.coding.basic;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

/**
 * Created by qilei on 17/2/24.
 */
public class ArrayListTest {

  @Test
  public void add() throws Exception {
    ArrayList l = new ArrayList();
    l.add(1);
    l.add(2);
    l.add(3);
    l.add(4);
    assertThat(l.size()).isEqualTo(4);
  }

  @Test
  public void insert() throws Exception {
    ArrayList l = new ArrayList();
    l.add(1);
    l.add(2);
    l.add(3);
    l.add(1,4);
    assertThat(l.size()).isEqualTo(4);
    assertThat(l.get(1)).isEqualTo(4);
  }

  @Test
  public void remove() throws Exception {
    ArrayList l = new ArrayList();
    l.add(1);
    l.add(2);
    l.add(3);
    Object removed = l.remove(1);
    assertThat(l.size()).isEqualTo(2);
    assertThat(removed).isEqualTo(2);
  }

  @Test
  public void get() throws Exception {

  }

  @Test
  public void size() throws Exception {

  }

  @Test
  public void iterator() throws Exception {
    ArrayList l = new ArrayList();
    l.add(1);
    l.add(2);
    l.add(3);
    Iterator iterator = l.iterator();
    while (iterator.hasNext()) {
      Object next = iterator.next();
      System.out.println(next);
    }

  }

}