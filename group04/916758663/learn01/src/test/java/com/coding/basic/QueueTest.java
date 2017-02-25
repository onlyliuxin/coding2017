package com.coding.basic;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

/**
 * Created by qilei on 17/2/25.
 */
public class QueueTest {

  @Test
  public void enQueue() throws Exception {
    Queue q = new Queue();
    q.enQueue("a");
    assertThat(q.size()).isEqualTo(1);
  }

  @Test
  public void deQueue() throws Exception {
    Queue q = new Queue();
    q.enQueue("a");
    q.enQueue("b");
    Object o = q.deQueue();
    assertThat(q.size()).isEqualTo(1);
    assertThat(o).isEqualTo("a");
  }

}