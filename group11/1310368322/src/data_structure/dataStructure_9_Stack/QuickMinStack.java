package dataStructure_9_Stack;

import java.util.Stack;

/**
 * ���һ��ջ��֧��ջ��push��pop�������Լ������ֲ���findMin, �����ظ����ݽṹ�е���СԪ��
 * finMin�������������ʱ�临�Ӷ�Ӧ����O(1) �� ������������һ�ξͿ��Եõ���Сֵ
 * @author liuxin
 *
 */
public class QuickMinStack {
	
	Stack<Integer> stack = new Stack<Integer>();
	Stack<Integer> temp = new Stack<Integer>();
	public void push(int data){
		
		
		if(stack.isEmpty()){
			stack.push(data);
			temp.push(data);
		}else{
			
			if(data<temp.peek()){	
				stack.push(data);
				temp.push(data);
			}else{
				stack.push(data);
			}
		}
	}
	public int pop(){
		checkStackIsEmpty();
		int i = 0;
		if(stack.pop() == temp.peek()){
			temp.pop();
			i = stack.pop();
		}else{
			i = stack.pop();
		}
		return i;
	}
	
	private void checkStackIsEmpty() {
		if(stack.isEmpty()){
			throw new RuntimeException("ջΪ��");
		}
		
	}
	public int findMin(){
		return temp.peek();
	}
}
