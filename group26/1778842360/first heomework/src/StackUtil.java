package first;

public class StackUtil {
		
		/**
		 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
		 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
		 */
		public static void reverse(Stack s) {
			if(s.isEmpty())
			{
				return;
			}
            Object o=getAndRemoveLastElement(s);
            reverse(s);
            s.push(o);
		}

		private static Object getAndRemoveLastElement(Stack s) {
			Object result=s.pop();
			if(s.isEmpty())
			{
				return result;
			}
			else{
				Object last=getAndRemoveLastElement(s);
				s.push(result);
				return last;
			}
		}

		/**
		 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
		 * 
		 * @param o
		 */
		public static void remove(Stack s,Object o) {
             Stack stack =new Stack();
             if(s.isEmpty())
             {
            	 return;
             }
             while(!s.isEmpty())
             {
            	 if(s.peek().equals(o))
            	 {
            		 s.pop();
            		 break;
            	 }
            	 else{
                   stack.push(s.pop());
            	 }
             }
             while(!stack.isEmpty())
             {
            	 s.push(stack.pop());
             }
		}

		/**
		 * 从栈顶取得len个元素, 原来的栈中元素保持不变
		 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
		 * @param len
		 * @return
		 */
		public static Object[] getTop(Stack s,int len) {
			if(s.isEmpty())
			{
				return null;
			}
			Stack stack=new Stack();
			if(s.size()<len)
			{
				len=s.size();
			}
			Object[] object=new Object[len];
			for(int i=0;i<object.length;i++)
			{
				object[i]=s.peek();
				stack.push(s.pop());
			}
			while(!stack.isEmpty())
			{
				s.push(stack.pop());
			}
			return object;
		}
		/**
		 * 字符串s 可能包含这些字符：  ( ) [ ] { }, a,b,c... x,yz
		 * 使用堆栈检查字符串s中的括号是不是成对出现的。
		 * 例如s = "([e{d}f])" , 则该字符串中的括号是成对出现， 该方法返回true
		 * 如果 s = "([b{x]y})", 则该字符串中的括号不是成对出现的， 该方法返回false;
		 * @param s
		 * @return
		 */
		public static boolean isValidPairs(String s){
			char[] chs=s.toCharArray();
			Stack stack=new Stack();
			for(int i=0;i<chs.length;i++)
			{
			    char ch=chs[i];
			    char c=0;
				if(ch=='('||ch=='{'||ch=='[')
				{
					stack.push(ch);
				}
				else if(ch==')'||ch==']'||ch=='}')
				{		
					if(stack.isEmpty())
					{
						return false;
					}
					switch(ch)
					{
						case ')':
						{
							c='(';
							ch=(char)stack.peek();
							if(ch==c)
							{
								stack.pop();
							}
							else{
								return false;
							}
							break;
						}
						case ']':
						{
							c='[';
							ch=(char)stack.peek();
							if(ch==c)
							{
								stack.pop();
							}
							else{
								return false;
							}
							break;
						}
						case '}':
						{
							c='{';
							ch=(char)stack.peek();
							if(ch==c)
							{
								stack.pop();
							}
							else{
								return false;
							}
							break;
						}
					}
				}
				else
				  {
					 continue;
				  }
			}
			if(!stack.isEmpty())
			{
				System.out.println("括号匹配失败");
				return false;
			}
			else{
				System.out.println("括号匹配成功");
			}
			return true;
		}
		public static String toString(Stack s){
				StringBuilder sb=new StringBuilder();
				sb.append("[");
				while(!s.isEmpty())
				{
					if(s.size()==1)
					{
						sb.append(s.pop());
						break;
					}
					else{
					  sb.append(s.pop()).append(",");
					}		
				}
				sb.append("]");
				return sb.toString();
			
		}
}
