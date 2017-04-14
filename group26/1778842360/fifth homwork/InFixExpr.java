package first;

import java.util.Arrays;

/*
 * 中序表达式求值
 * 
 */
public class InFixExpr {
    String expr = null;
	
	public InFixExpr(String expr) {
		this.expr = expr;
	}
	        
	public float evaluate() {
		
		//运算符栈
		Stack ops=new Stack();
		//操作数栈
		Stack vals=new Stack();
		//将字符串进行分割
		String data[]=spilt(expr);
		for(int i=0;i<data.length;i++)
		{
			String s =data[i];
			float result=0.0f;
			if(s.equals("*")||s.equals("/"))
			{
				if(vals.isEmpty())
				{
					throw new RuntimeException("该计算表达式有问题");
				}
				float d=Float.valueOf((String)vals.pop());
				float d2=Float.valueOf(data[++i]);
				switch(s)
				{
				case "*":
					result=d*d2;
					break;
				case "/": 
						result=d/d2;
					break;
				}
				vals.push(String.valueOf(result));
				
			}
			else if(s.equals("+")||s.equals("-"))
			{
				ops.push(data[i]);
			}
			else{
				vals.push(data[i]);
			}
			
		}
		
		while(!ops.isEmpty())
		{
			float d1=Float.valueOf((String)vals.pop());
			float d2=Float.valueOf((String)vals.pop());
			float result=0.0f;
			String s=(String) ops.pop();
			switch(s)
			{
			case "+":
				result=d1+d2;
				break;
			case "-":
				result=d2-d1;
				break;
			}
			vals.push(String.valueOf(result));
		}
		return Float.valueOf((String)vals.pop());
	}
	
	public String[] spilt(String s)
	{
		int count=0;
    	String temp="";
    	String data[]=new String[s.length()];
    	for(int i=0;i<s.length();i++)
    	{
    		char ch=s.charAt(i);
    		if(ch>='0'&&ch<='9')
    		{
    			//如果是数字，则进行拼接
    			temp+=String.valueOf(ch);
    		}
    		else{
    			data[count++]=temp;
    			temp=String.valueOf(ch);
    			data[count++]=temp;
    			temp="";
    		}
    	}
    	//这是位于字符串末尾的字符
    	data[count++]=temp;
    	data=Arrays.copyOf(data, count);
		return data;
	}
}
