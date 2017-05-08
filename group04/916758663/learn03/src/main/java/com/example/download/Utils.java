package com.example.download;

/**
 * Created by qilei on 17/3/26.
 */
public class Utils {

  public static int[][] split(int len, int count) {
    int[][] result = new int[count][2];
    int baseLen = (int)Math.ceil(((double)len / count));
    for (int i = 0; i < count; i++) {
      int startPos = baseLen * i ;
      int endPos = baseLen * (i + 1) -1;
      if (i == count - 1) {
        if (endPos > len - 1) {
          endPos = len - 1;
        }
      }
      result[i][0] = startPos;
      result[i][1] = endPos;
    }
    return result;
  }
}
