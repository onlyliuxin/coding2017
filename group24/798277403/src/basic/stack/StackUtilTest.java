package basic.stack;

import org.junit.Test;

import java.util.Stack;

/**
 * Created by zhouliang on 2017-04-08.
 */
public class StackUtilTest {

    @Test
    public void testReverse(){
        Stack<Integer> s = new Stack<Integer>();
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        s.push(5);
        while(!s.isEmpty()){
            System.out.println(s.pop());
        }
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        s.push(5);
        StackUtil.reverse(s);
        while(!s.isEmpty()){
            System.out.println(s.pop());
        }
    }

    @Test
    public void remove(){
        Stack<Object> s = new Stack<Object>();
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        s.push(5);

        StackUtil.remove(s,3);
        while(!s.isEmpty()){
            System.out.println(s.pop());
        }
    }

    @Test
    public void getTop(){
        Stack<Object> s = new Stack<Object>();
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        s.push(5);

        Object[] result = StackUtil.getTop(s,2);
        while(!s.isEmpty()){
            System.out.println(s.pop());
        }

        for(Object o : result){
            System.out.println(o);
        }
    }

    @Test
    public void isValidPairs(){
        String s = "([e{d}f])";
        String s1 = "([b{x]y})";
        boolean result = StackUtil.isValidPairs(s);
        System.out.println(result);
        boolean result1 = StackUtil.isValidPairs(s1);
        System.out.println(result1);
    }
}
