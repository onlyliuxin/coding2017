package com.coding.basic.stack;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class StackUtil {

    private static HashMap<Character, Character> cache = Maps.newHashMap();

    static {
        cache.put('(', ')');
        cache.put('[', ']');
        cache.put('{', '}');
    }

    /**
     * <pre>
     * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 
     * 元素次序变为: 1,2,3,4,5 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty，
     * 可以使用另外一个栈来辅助
     * </pre>
     */
    public static void reverse(Stack stack) {
        List temp = Lists.newArrayList();
        while (stack.isEmpty()) {
            temp.add(0, stack.pop());
        }
        for (Object ele : temp) {
            stack.push(ele);
        }
    }

    /**
     * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
     */
    public static void remove(Stack stack, Object data) {
        List cache = Lists.newArrayList();
        while (stack.isEmpty()) {
            Object pop = stack.pop();
            if (!Objects.equals(pop, data)) {
                cache.add(pop);
            }
            cache.add(pop);
        }
        for (Object ele : cache) {
            stack.push(ele);
        }
    }

    /**
     * <pre>
     * 从栈顶取得len个元素, 原来的栈中元素保持不变 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 
     * 可以使用另外一个栈来辅助
     * </pre>
     * 
     * @param len
     * @return
     */
    public static List getTop(Stack stack, int len) {
        Preconditions.checkArgument(len > 0, "获取的数据长度必须大于0");
        Preconditions.checkArgument(stack != null && stack.size() > 0, "数据集为空");
        List result = Lists.newArrayList();
        for (int i = 0; i < len && i < stack.size(); i++) {
            result.add(stack.pop());
        }
        for (Object ele : result) {
            stack.push(ele);
        }
        return result;
    }

    /**
     * <pre>
     * 字符串s 可能包含这些字符： ( ) [ ] { }, a,b,c... x,yz 使用堆栈检查字符串s中的括号是不是成对出现的。
     * 例如s = "([e{d}f])" , 则该字符串中的括号是成对出现， 该方法返回true
     * 如果 s = "([b{x]y})", 则该字符串中的括号不是成对出现的， 该方法返回false;
     * </pre>
     * 
     * @param str
     * @return
     */
    public static boolean isValidPairs(String str) {
        char[] chars = str.toCharArray();
        Stack stack = new Stack();
        for (char ch : chars) {
            if (stack.isEmpty()) {
                stack.push(ch);
            }
            if (cache.containsKey(ch)) {
                Object peek = stack.peek();
                if (Objects.equals(peek, ch)) {
                    stack.pop();
                }
            }
        }
        return stack.isEmpty();
    }

}
