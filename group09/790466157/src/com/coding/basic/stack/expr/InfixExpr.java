package com.coding.basic.stack.expr;


import java.io.IOException;


import com.coding.basic.stack.Stack;

public class InfixExpr {
	String expr = null;
	
	public InfixExpr(String expr) {
		this.expr = expr;
	}

	public void evaluate() throws IOException {		
		Stack operandstack = new Stack();//操作数栈
	    Stack operatorstack = new Stack();//运算符栈
	    
	    String str = InfixExpr.this.expr;
	    int len = str.length();
	    int i = 0;
	    while(i<len) {
	      if (operator(str.charAt(i))){//读取的是运算符
	        if(operatorstack.isEmpty()) {//运算符栈空
	          System.out.println("运算符栈空中："+str.charAt(i));
	          operatorstack.push(str.charAt(i));
	        }
	        else {//运算符栈非空
	          if(priority(str.charAt(i),(char) operatorstack.peek())) {//准备入栈的运算符优先级大于栈顶运算符
	            System.out.println("运算符栈非空中："+str.charAt(i));
	            operatorstack.push(str.charAt(i));//入栈
	          }
	          else {
	            char oper = (char) operatorstack.pop();//取运算符栈顶元素
	            int num1 = (int) operandstack.pop();//取操作数栈顶中两个操作数
	            int num2 = (int) operandstack.pop();
	            System.out.println("oper="+oper+",num1="+num1+",num2="+num2+",str.charAt(i)="+str.charAt(i));
	            System.out.println("准备入栈的运算符优先级小于栈顶运算符"+result(num2,num1,oper));
	            operandstack.push(result(num2,num1,oper));//计算结果存入操作数栈
	            operatorstack.push(str.charAt(i));//将准备进入的运算符入栈
	          }
	        }
	      }
	      else {//读取的是操作数
	        int num = Integer.parseInt(Character.toString(str.charAt(i)));
	        System.out.println("num="+num);
	        operandstack.push(num);
	      }
	      i ++;
	    }
	    while(!operatorstack.isEmpty()) {//运算符栈非空，这里要用到循环while，而不是条件if。
	      char oper = (char) operatorstack.pop();//取运算符栈顶元素
	      int num1 = (int) operandstack.pop();//取操作数栈顶中两个操作数
	      int num2 = (int) operandstack.pop();
	      System.out.println("运算符栈非空''"+result(num2,num1,oper));
	      operandstack.push(result(num2,num1,oper));//计算结果存入操作数栈
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
	  
	  private static boolean priority(char c1, char c2) {//运算符的比较:c1的优先级大于c2。
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
	  	