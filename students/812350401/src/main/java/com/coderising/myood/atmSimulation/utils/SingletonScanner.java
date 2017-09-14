package com.coderising.myood.atmSimulation.utils;

import java.util.Scanner;

/**
 * Created by thomas_young on 30/7/2017.
 * todo 不得已这么做，因为没法测试连续的控制台输入,强烈建议删除
 */
public class SingletonScanner {
    private static Scanner ourInstance = new Scanner(System.in);

    public static Scanner getInstance() {
        return ourInstance;
    }

    private SingletonScanner() {
    }
}
