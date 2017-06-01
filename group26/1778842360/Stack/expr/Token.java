package FixEXpr;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Token {
	
   public static final List<String> OPERATORS=Arrays.asList("+","-","*","/");
   //设置优先级
   public static final Map<String,Integer> priorities=new HashMap<>();
   //静态代码块，只执行一次
   static{
	   priorities.put("+", 1);
	   priorities.put("-", 1);
	   priorities.put("*", 2);
	   priorities.put("/", 2);
   }
   static final int OPERATOR=1;
   static final int NUMBER=2;
   String value;
   int type;  //标记是运算符还是操作数
   
   public Token(int type,String value)
   {
	   this.value=value;
	   this.type=type;
   }
   public boolean isNumber()
   {
	   return type==NUMBER;
   }
   public boolean isOperator()
   {
	   return type==OPERATOR;
   }
   public int getIntValue()
   {
	   return Integer.valueOf(value).intValue();
   }
   public String toString()
   {
	   return value;
   }
   public boolean hasHigherPriority(Token t)
   {
	   if(!this.isOperator()&&!t.isOperator())
	   {
		   throw new RuntimeException("numbers can't compare priority");
	   }
	   return priorities.get(this.value)-priorities.get(t.value)>0;
   }
   
}
