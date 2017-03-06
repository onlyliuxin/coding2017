package com.coding.basic;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

/**
 * Created by qilei on 17/2/25.
 */
public class LinkedListTest {

  @Test
  public void add() throws Exception {
    LinkedList l = new LinkedList();
    l.add(1);
    l.add(2);
    l.add(3);
    assertThat(l.size()).isEqualTo(3);
    assertThat(l.get(1)).isEqualTo(2);
  }

  @Test
  public void insert() throws Exception {
    LinkedList l = new LinkedList();
    l.add(1);
    l.add(2);
    l.add(3);
    l.add(1,4);
    assertThat(l.size()).isEqualTo(4);
    assertThat(l.get(1)).isEqualTo(4);
  }

  @Test
  public void remove() throws Exception {
    LinkedList l = new LinkedList();
    l.add(1);
    l.add(2);
    l.add(3);
    Object removed = l.remove(1);
    assertThat(l.size()).isEqualTo(2);
    assertThat(removed).isEqualTo(2);
  }

  @Test
  public void addFirst() throws Exception {

  }

  @Test
  public void addLast() throws Exception {

  }

  @Test
  public void removeFirst() throws Exception {

  }

  @Test
  public void removeLast() throws Exception {

  }

}