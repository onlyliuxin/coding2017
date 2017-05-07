import java.util.LinkedList;


/**
 * Created by lxx on 2017/5/6.
 */
public class StackWithTwoQueues {

    LinkedList<Integer> queue1=new LinkedList<Integer>();
    LinkedList<Integer> queue2=new LinkedList<Integer>();
    public void push(int data){
        queue1.addLast(data);
    }
    public int pop() {
        if (sSize() > 0) {
            if (!queue1.isEmpty()) {
                while (queue1.size() > 1) {
                    queue2.addLast(queue1.removeFirst());
                }
                return queue1.removeFirst();
            } else {
                while (queue2.size() > 1) {
                    queue1.addLast(queue2.removeFirst());
                }
                return queue2.removeFirst();
            }
        } else {
            System.out.println("栈空,不能出栈");
            return Integer.MAX_VALUE;
        }
    }
    public int sSize(){
        return queue1.size() + queue2.size();
    }
    public static void main(String[] args)
    {
        StackWithTwoQueues stack=new StackWithTwoQueues();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack.push(5);
        stack.push(6);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
