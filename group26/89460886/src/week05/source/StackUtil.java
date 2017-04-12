package list;

import coding.coderising.litestruts.StringUtils;

import java.util.List;
import java.util.ArrayList;

/**
 * @author jiaxun
 */
public class StackUtil {

    public static void reverse(Stack s) {
        if (s.isEmpty() || s.size() == 1) return;
        int size = s.size();
        int i = 0;
        Stack tempStack = new Stack();
        while (i != size - 1) {
            Object top = s.pop();
            int j = size - i - 1;
            while (j != 0) {
                tempStack.push(s.pop());
                j--;
            }
            s.push(top);
            while (!tempStack.isEmpty()) {
                s.push(tempStack.pop());
            }
            i++;
        }
    }

    public static void remove(Stack s, Object o) {
        Stack tempStack = new Stack();
        while (!s.isEmpty()) {
            Object sObj = s.pop();
            if (sObj.equals(o)) {
                break;
            } else {
                tempStack.push(sObj);
            }
        }
        while (!tempStack.isEmpty()) {
            s.push(tempStack.pop());
        }
    }

    public static Object[] getTop(Stack s, int len) {
        if (len <= 0) return null;
        Object[] resultList;
        if (s.size() < len) {
            resultList = new Object[s.size()];
        } else {
            resultList = new Object[len];
        }
        int count = 0;
        Stack tempStack = new Stack();
        while (!s.isEmpty() && count <= len - 1) {
            Object sObj = s.pop();
            tempStack.push(sObj);
            resultList[count++] = sObj;
        }
        while (!tempStack.isEmpty()) {
            s.push(tempStack.pop());
        }
        return resultList;
    }

    public static boolean isValidPairs(String s) {
        if (StringUtils.isEmpty(s)) return false;
        Stack stack = new Stack();
        for (int i = 0, len = s.length(); i < len; i++) {
            char character = s.charAt(i);
            if (openCharList.contains(character)) {
                stack.push(character);
            } else if (closeCharList.contains(character)) {
                Object openChar = stack.pop();
                return validPairs((Character) openChar, character);
            } else {
                // nothing
            }
        }
        return false;
    }

    private static boolean validPairs(Character open, Character close) {
        return (open != null && close != null) && (open.equals(openBrace) && close.equals(closeBrace) ||
                        open.equals(openSquareBrackets) && close.equals(closeSquareBrackets) ||
                        open.equals(openParentheses) && close.equals(closeParentheses));
    }

    private static List<Character> openCharList = new ArrayList<>();
    private static List<Character> closeCharList = new ArrayList<>();

    private static Character openBrace = '{';
    private static Character closeBrace = '}';
    private static Character openSquareBrackets = '[';
    private static Character closeSquareBrackets = ']';
    private static Character openParentheses = '(';
    private static Character closeParentheses = ')';

    static {
        openCharList.add(openBrace);
        openCharList.add(openSquareBrackets);
        openCharList.add(openParentheses);
        closeCharList.add(closeBrace);
        closeCharList.add(closeSquareBrackets);
        closeCharList.add(closeParentheses);
    }

}
