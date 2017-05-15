//package com.coding.basic.stack;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.Stack;
//
///**
// * Created by 14258 on 2017/4/9.
// */
//public class StackUtilTest {
//
//    java.util.Stack<Integer> s = new java.util.Stack<Integer>();
//
//
//    @Before
//    public void setUp() throws Exception {
//
//        for (int i = 0; i < 10; i++) {
//            s.push(i);
//        }
//        //集合遍历方式
//        for (Integer x : s) {
//            System.out.println(x);
//        }
////        while (!s.empty()){
////            System.out.println(s.pop());
////        }
////        System.out.println("-----------1-----------");
//
//
//    }
//
//    @After
//    public void tearDown() throws Exception {
//
//    }
//
//    @Test
//    public void testReverse() throws Exception {
//        Stack ss = StackUtil.reverse(s);
//
//        for (Object xx : ss) {
//            System.out.println(xx);
//        }
//
//
//    }
//
//    @Test
//    public void testRemove() throws Exception {
//        Stack ss = StackUtil.remove(s, 1);
//        for (Object xx : ss) {
//            System.out.println(xx);
//        }
//
//
//    }
//
//    @Test
//    public void testGetTop() throws Exception {
//        Object[] ss = StackUtil.getTop(s, 11);
//
//        for (Object o : ss) {
//            System.out.println(o);
//
//        }
//    }
//
//    @Test
//    public void testIsValidPairs() throws Exception {
//
//        String str = "({[ddf]})";
//        System.out.println(  StackUtil.isValidPairs(str)
//        );
//    }
//
//    @Test
//    public void testMain() throws Exception {
//
//    }
//}