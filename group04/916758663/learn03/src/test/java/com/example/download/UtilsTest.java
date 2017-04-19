package com.example.download;

import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

/**
 * Created by qilei on 17/3/26.
 */
public class UtilsTest {

  @Test
  public void testSplit(){
    int len = 10;

    int[][] result = Utils.split(10,3);

    assertThat(result[0][0]).isEqualTo(0);
    assertThat(result[0][1]).isEqualTo(3);
    assertThat(result[2][0]).isEqualTo(8);
    assertThat(result[2][1]).isEqualTo(9);
  }

  @Test
  public void testMath(){
    double a = Math.ceil((double)10 / 3);
    double b = Math.floor((double)10 / 3);
    System.out.println("");

  }

}
