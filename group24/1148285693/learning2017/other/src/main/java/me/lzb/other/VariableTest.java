package me.lzb.other;

/**
 * Created by LZB on 2017/4/25.
 */
public class VariableTest {
    //instance变量
    private int x;

    //局部变量
    public void localVar(int val) {
        int j = 0;
        for (int i = 0; i < val; i++) {
            j += 1;
        }
    }

    //实例变量
    public void instanceVar(int val) {
        for (int i = 0; i < val; i++) {
            x += 1;
        }
    }
}
