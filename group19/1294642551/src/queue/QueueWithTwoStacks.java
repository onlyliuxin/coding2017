package queue;

import java.util.Stack;

/**
 * 用两个栈来实现一个队列
 * @author liuxin
 *
 * @param <E>
 */
public class QueueWithTwoStacks<E> {
	private Stack<E> stack1;    // 临时容器，当新的元素需要入队时，保存原来在stack2中的元素
    private Stack<E> stack2;    // 用于顺序存储队列的元素，栈顶为最先入队的元素

//    int size = 0;
    
    public QueueWithTwoStacks() {
        stack1 = new Stack<E>();
        stack2 = new Stack<E>();
    }


    public boolean isEmpty() {
        return stack2.size() == 0;
    }
    
    public int size() {
        return stack2.size();   
    }



    public void enQueue(E item) {
    	if(stack2.isEmpty()){
    		stack2.push(item);
    		return ;
    	}
    	
		while(!stack2.isEmpty()){
			stack1.push(stack2.pop());
    	}
		stack2.push(item);
		while(!stack1.isEmpty()){
			stack2.push(stack1.pop());
		}
    }

    public E deQueue() {
    	if(stack2.isEmpty()){
    		throw new IndexOutOfBoundsException("队列为空");
    	}
        return stack2.pop();
    }



 }

