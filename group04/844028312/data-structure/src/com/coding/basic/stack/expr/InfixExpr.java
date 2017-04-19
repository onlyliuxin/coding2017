package com.coding.basic.stack.expr;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.coding.basic.Stack;

public class InfixExpr {
	String expr = null;
	
	public InfixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {		
		if(expr==null|| expr.equals("")){
			return 0.0f;
		}
		Stack numStack=new Stack();
		Stack opoStack=new Stack();
		int num = 0;
		StringTokenizer st = new StringTokenizer(expr,"+,-,*,/",true);
		
		while(st.hasMoreElements()){
			String s=(String) st.nextElement();
			if("+".equals(s) || "*".equals(s) || "-".equals(s)|| "/".equals(s)){
				if(opoStack.isEmpty()){
					opoStack.push(s);
				}
				else{
					num=opoPush(opoStack,numStack,s);
				}
			}
			else {
//				if(num!=0){
//					Stack temp=new Stack();
//					while(!numStack.isEmpty()){
//						temp.push(numStack.pop());
//					}
//					String top = null;
//					String a = null;
//					int j=0;
//					while(!temp.isEmpty()){
//						top=(String) temp.pop();
//						if(j==num){
//							a=top;
//							numStack.push(s);
//						}
//						else{
//							numStack.push(top);
//						}
//						j++;
//					}
//					numStack.push(a);
//				}
//				else{
//					numStack.push(s);
//				}
				numStack.push(s);
			}
		}
		float result = 0;
		if(!opoStack.isEmpty()){
			String opoOne=(String) opoStack.pop();
			String opoTwo=(String) opoStack.pop();
			opoStack.push(opoTwo);
			opoStack.push(opoOne);
			if(("*".equals(opoOne)||"/".equals(opoOne))&&("*".equals(opoTwo)||"/".equals(opoTwo))){
				
			}
			else if("*".equals(opoOne)||"/".equals(opoOne)){
				float one= Float.parseFloat((String) numStack.pop()) ;
				float two= Float.parseFloat((String) numStack.pop());
				String opo=(String) opoStack.pop();
				if("-".equals(opo)){
					result=two-one;
				}
				else if("+".equals(opo)){
					result=one+two;
				}
				else if("/".equals(opo)){
					result=two/one;
				}
				else {
					result=two*one;
				}
				numStack.push(result+"");
			}
		}
		Stack revOpo=new Stack();
		Stack revNum=new Stack();
		
		while(!opoStack.isEmpty()){
			revOpo.push(opoStack.pop());
		}
		while(!numStack.isEmpty()){
			revNum.push(numStack.pop());
		}
		while(!revOpo.isEmpty()){
			float one= Float.parseFloat((String) revNum.pop()) ;
			float two= Float.parseFloat((String) revNum.pop());
			String opo=(String) revOpo.pop();
			if("-".equals(opo)){
				result=one-two;
			}
			else if("+".equals(opo)){
				result=one+two;
			}
			else if("/".equals(opo)){
				result=one/two;
			}
			else {
				result=two*one;
			}
			revNum.push(result+"");
		}
		return Float.parseFloat((String) revNum.pop());
	}
	//2+3*4+5 5-2-2+3-->  5+3-2  75/3/5*5 15*5/3
	private int opoPush(Stack opoStack,Stack numStack,Object opo){
		int num=0;//记录减号的最深位置
		if("+".equals(opo)){
			if(!opoStack.isEmpty()){
				if("*".equals(opoStack.peek())||"/".equals(opoStack.peek())){
					float one= Float.parseFloat((String) numStack.pop()) ;
					float two= Float.parseFloat((String) numStack.pop());
					float result;
					if("*".equals(opoStack.peek())){
						opoStack.pop();
						result=two*one;
					}
					else{
						result=two/one;
					}
					numStack.push(result+"");
				}
			}
//			Stack temp=new Stack();
//			boolean flag=false; //记录是否转移位置
//			while(!opoStack.isEmpty()){
//				temp.push(opoStack.pop());
//			}
//			int i=0;
//			while(!temp.isEmpty()){
//				String top= (String) temp.pop();
//				if("-".equals(top)&&!flag){
//					num=i+1;
//					opoStack.push("+");
//					flag=true;
//				}
//				opoStack.push(top);
//				i++;
//			}
//			if(!flag){
//				opoStack.push("+");
//			}
			opoStack.push("+");
		}
		else if("-".equals(opo)){
			if(!opoStack.isEmpty()){
				if("*".equals(opoStack.peek())||"/".equals(opoStack.peek())){
					float one= Float.parseFloat((String) numStack.pop()) ;
					float two= Float.parseFloat((String) numStack.pop());
					float result;
					if("*".equals(opoStack.peek())){
						opoStack.pop();
						result=one*two;
					}
					else{
						result=one/two;
					}
					numStack.push(result+"");
				}
			}
			opoStack.push("-");
		}
		else if("*".equals(opo)){
//			Stack temp=new Stack();
//			boolean flag=false; //记录是否转移位置
//			while(!opoStack.isEmpty()){
//				temp.push(opoStack.pop());
//			}
//			int i=0;
//			while(!temp.isEmpty()){
//				String top=(String) temp.pop();
//				if("/".equals(top)&&!flag){
//					num=i+1;
//					opoStack.push("*");
//					flag=true;
//				}
//				opoStack.push(top);
//				i++;
//			}
//			if(!flag){
//				opoStack.push("*");
//			}	
			opoStack.push("*");
		}
		else if("/".equals(opo)){
			opoStack.push("/");
		}
		return num;
	}
	
	
}
