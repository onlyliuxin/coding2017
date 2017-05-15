package com.coding.basic.stack.expr;


import java.io.IOException;


import com.coding.basic.stack.Stack;

public class InfixExpr {
	String expr = null;
	
	public InfixExpr(String expr) {
		this.expr = expr;
	}

	public void evaluate() throws IOException {		
		Stack operandstack = new Stack();//������ջ
	    Stack operatorstack = new Stack();//�����ջ
	    
	    String str = InfixExpr.this.expr;
	    int len = str.length();
	    int i = 0;
	    while(i<len) {
	      if (operator(str.charAt(i))){//��ȡ���������
	        if(operatorstack.isEmpty()) {//�����ջ��
	          System.out.println("�����ջ���У�"+str.charAt(i));
	          operatorstack.push(str.charAt(i));
	        }
	        else {//�����ջ�ǿ�
	          if(priority(str.charAt(i),(char) operatorstack.peek())) {//׼����ջ����������ȼ�����ջ�������
	            System.out.println("�����ջ�ǿ��У�"+str.charAt(i));
	            operatorstack.push(str.charAt(i));//��ջ
	          }
	          else {
	            char oper = (char) operatorstack.pop();//ȡ�����ջ��Ԫ��
	            int num1 = (int) operandstack.pop();//ȡ������ջ��������������
	            int num2 = (int) operandstack.pop();
	            System.out.println("oper="+oper+",num1="+num1+",num2="+num2+",str.charAt(i)="+str.charAt(i));
	            System.out.println("׼����ջ����������ȼ�С��ջ�������"+result(num2,num1,oper));
	            operandstack.push(result(num2,num1,oper));//���������������ջ
	            operatorstack.push(str.charAt(i));//��׼��������������ջ
	          }
	        }
	      }
	      else {//��ȡ���ǲ�����
	        int num = Integer.parseInt(Character.toString(str.charAt(i)));
	        System.out.println("num="+num);
	        operandstack.push(num);
	      }
	      i ++;
	    }
	    while(!operatorstack.isEmpty()) {//�����ջ�ǿգ�����Ҫ�õ�ѭ��while������������if��
	      char oper = (char) operatorstack.pop();//ȡ�����ջ��Ԫ��
	      int num1 = (int) operandstack.pop();//ȡ������ջ��������������
	      int num2 = (int) operandstack.pop();
	      System.out.println("�����ջ�ǿ�''"+result(num2,num1,oper));
	      operandstack.push(result(num2,num1,oper));//���������������ջ
	    }
	    while(!operandstack.isEmpty()) {
	      System.out.print(operandstack.pop());
	    }
	  }
	  
	  private static boolean operator(char c) {
	    if(c=='+' || c=='-' || c=='*' || c=='/') {
	      return true;
	    }
	    else return false;
	  }
	  
	  private static boolean priority(char c1, char c2) {//������ıȽ�:c1�����ȼ�����c2��
	    if(c1=='*'||c1=='/' && c2=='+'||c2=='-')
	      return true;
	    else return false;
	  }
	  
	  private static int result(int operand1, int operand2, char operator) {
	    System.out.println("-----");
	    System.out.println("operand1="+operand1);
	    System.out.println("operand2="+operand2);
	    System.out.println("~~~~~~~~~~~~~~~~");
	    switch(operator) {
	     case '+': return (operand1 + operand2); 
	     case '-': return (operand1 - operand2); 
	     case '*': return (operand1 * operand2); 
	     case '/': return (operand1 / operand2); 
	    }
	    return 0;
	  }
}
	  	