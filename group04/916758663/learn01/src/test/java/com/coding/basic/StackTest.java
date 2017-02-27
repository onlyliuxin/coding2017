package com.coding.basic;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

/**
 * Created by qilei on 17/2/25.
 */
public class StackTest {

  @Test
  public void push() throws Exception {
    Stack s = new Stack();
    s.push("a");
    assertThat(s.size()).isEqualTo(1);
    assertThat(s.peek()).isEqualTo("a");
  }

  @Test
  public void pop() throws Exception {
    Stack s = new Stack();
    s.push("a");
    s.push("b");
    Object pop = s.pop();
    assertThat(pop).isEqualTo("b");
  }

}