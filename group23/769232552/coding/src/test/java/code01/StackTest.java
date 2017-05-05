package code01;

import org.junit.Assert;
import org.junit.Test;


/**
 * Created by yaoyuan on 2017/3/8.
 */
public class StackTest {

    @Test
    public void testStack() throws Exception {
        Stack stack = new Stack();
        String[] array = new String[]{"a","b","c"};
        for (String str : array){
            stack.push(str);
        }
        int i = 2;
        while (!stack.isEmpty()){
            Assert.assertEquals(array[i],stack.peek());
            Assert.assertEquals(array[i],stack.pop());
            i --;
        }
    }

}