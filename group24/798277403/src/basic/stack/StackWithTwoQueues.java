package basic.stack;


import java.util.LinkedList;
import java.util.Queue;

public class StackWithTwoQueues {

    private Queue<Integer> queue1 = new LinkedList<Integer>();
    private Queue<Integer> queue2 = new LinkedList<Integer>();

    public void push(int data) {
        if(queue1.isEmpty()){
            queue1.add(data);
            while(!queue2.isEmpty()){
                queue1.add(queue2.poll());
            }
        }else{
            queue2.add(data);
            while(!queue1.isEmpty()){
                queue2.add(queue1.poll());
            }
        }
    }

    public int pop() {
        if(queue1.isEmpty() && queue2.isEmpty()){
            throw new RuntimeException("The stack is empty!");
        }
        int result = -1;
        if(queue1.isEmpty()){
            result = queue2.poll();
        }else{
            result = queue1.poll();
        }
        return result;
    }


    
}
