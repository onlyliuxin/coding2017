package com.coderising.jvm.test;

import java.util.Arrays;

public class EmployeeV1 {


    private String name;
    private int age;

    public EmployeeV1(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void sayHello() {
        System.out.println("Hello , this is class Employee ");
    }

    public static void main(String[] args) {
        int a = 100;
        byte[] bytes = intToBytes2(a);
        System.out.println(Arrays.toString(bytes));

        int startIndex = bytesToInt2(bytes, 0);
        System.out.println(startIndex);
    }

    public static byte[] intToBytes2(int value)
    {
        byte[] src = new byte[4];
        src[0] = (byte) ((value>>24) & 0xFF);
        src[1] = (byte) ((value>>16)& 0xFF);
        src[2] = (byte) ((value>>8)&0xFF);
        src[3] = (byte) (value & 0xFF);
        return src;
    }

    public static int bytesToInt2(byte[] src, int offset) {
        int value;
        value = (int) (((src[offset] & 0xFF) << 24)
                | ((src[offset + 1] & 0xFF) << 16)
                | ((src[offset + 2] & 0xFF) << 8)
                | (src[offset + 3] & 0xFF));
        return value;
    }

}