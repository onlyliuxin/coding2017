package basic;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by zhouliang on 2017-03-10.
 */
public class StackTest {
    private Stack<Integer> stack = new Stack<>();

    @Before
    public void setUp(){
        for(int i=0; i<10; i++){
            stack.push(i);
        }
    }

    @Test
    public void pop() throws Exception {
        System.out.println("size "+stack.size());
        while(stack.size()>0){
            System.out.println(stack.pop());
        }
    }

    @Test
    public void peek() throws Exception {
        System.out.println(stack.size());
        int i = stack.peek();
        System.out.println(i+"   "+stack.size());
    }

    @Test
    public void isEmpty() throws Exception {

    }

}