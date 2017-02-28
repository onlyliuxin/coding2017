package com.coding.basic;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

/**
 * Created by qilei on 17/2/25.
 */
public class JavaArrayListTest {

  @Test
  public void add() throws Exception {
    java.util.ArrayList l = new java.util.ArrayList();
    l.add(1);
    l.add(2);
    l.add(3,3);
    assertThat(l.size()).isEqualTo(3);
  }

}
