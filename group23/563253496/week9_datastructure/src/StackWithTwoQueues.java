/**
 * Created by bdl19 on 2017/5/6.
 */

import java.util.*;


public class StackWithTwoQueues {

    private Queue<Integer> queue1 = new LinkedList<Integer>();
    private Queue<Integer> queue2 = new LinkedList<Integer>();

    public void push(int data) {
        if(queue2.isEmpty()){
            queue1.add(data);
        }else{
            queue2.add(data);
        }
    }

    public int pop() {
        if (queue1.size() == 0 && queue2.size() == 0) {
            throw new IndexOutOfBoundsException();
        }



        if(queue2.isEmpty()){
            while(!(queue1.size()==1)){
                queue2.add(queue1.remove());
            }
            return queue1.remove();

        }else{
            while(!(queue2.size()==1)){
                queue1.add(queue2.remove());
            }
            return queue2.remove();
        }


    }

    public static void main(String[] args) {
        StackWithTwoQueues s = new StackWithTwoQueues();
        s.push(1);
        s.push(2);
        s.push(3);
        for (int i = 0; i < 3; i++) {
            System.out.println(s.pop());
        }
    }
}