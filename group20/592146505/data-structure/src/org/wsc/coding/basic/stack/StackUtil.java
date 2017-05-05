package org.wsc.coding.basic.stack;

/**
 * @author Administrator
 * @date 2017年4月10日下午4:55:34
 * @version v1.0
 *
 */
public class StackUtil {

    /**
     * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
     * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     */
    public static void reverse(Stack s) {
    	if(!s.isEmpty()){
    		Object top = s.pop();
    		reverse(s);
    		toTop(s,top);
    	}

    }
    private static void toTop(Stack s,Object t){
    	if(s.isEmpty()){
    		s.push(t);
    		return;
    	}
    	Object top = s.pop();
    	toTop(s,t);
    	s.push(top);
    }

    /**
     * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     *
     * @param o
     */
    public static void remove(Stack s, Object o) {
    	//递归
    	if (!s.isEmpty()) {
    		Object ob = s.pop();
    		remove(s,o);
			if(ob !=o)
				s.push(ob);
		}
    }

    /**
     * 从栈顶取得len个元素, 原来的栈中元素保持不变
     * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     *
     * @param len
     * @return
     */
    public static Object[] getTop(Stack s, int len) {
    	Object[] tops = new Object[len];
    	getTop(s,len,tops);
        return tops;
    }
    
    private static void getTop(Stack s, int len,Object[] tops) {
    	//递归
    	if (!s.isEmpty()&&len>0) {
    		Object ob = s.pop();
    		getTop(s,--len,tops);
    		tops[tops.length-1-len] = ob;
			s.push(ob);
		}
    }

    /**
     * 字符串s 可能包含这些字符：  ( ) [ ] { }, a,b,c... x,yz
     * 使用堆栈检查字符串s中的括号是不是成对出现的。
     * 例如s = "([e{d}f])" , 则该字符串中的括号是成对出现， 该方法返回true
     * 如果 s = "([b{x]y})", 则该字符串中的括号不是成对出现的， 该方法返回false;
     *
     * @param s
     * @return
     */
    public static boolean isValidPairs(String s) {//未完成实现
    	char[] chars = s.toCharArray();
    	Stack<Character> stack = new Stack<Character>();
    	for (int i = 0; i > chars.length; i++) {
    		stack.push(chars[i]);
		}
    	
        return false;
    }

}
