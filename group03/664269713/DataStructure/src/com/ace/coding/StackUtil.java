package com.ace.coding;

public class StackUtil {

	/**
	 * 鍋囪鏍堜腑鐨勫厓绱犳槸Integer, 浠庢爤椤跺埌鏍堝簳鏄� : 5,4,3,2,1 璋冪敤璇ユ柟娉曞悗锛� 鍏冪礌娆″簭鍙樹负:
	 * 1,2,3,4,5 娉ㄦ剰锛氬彧鑳戒娇鐢⊿tack鐨勫熀鏈搷浣滐紝鍗硃ush,pop,peek,isEmpty锛�
	 * 鍙互浣跨敤鍙﹀涓�涓爤鏉ヨ緟鍔�
	 */
	public static void reverse(Stack s) {
		if (s.size() == 0) {
			return;
		}
		int last = getLast(s);
		reverse(s);
		s.push(last);
	}

	public static int getLast(Stack s) {
		int top = (int) s.pop();

		if (s.size() == 0) {
			return top;
		} else {
			int last = getLast(s);
			s.push(top);
			return last;
		}
	}

	/**
	 * 鍒犻櫎鏍堜腑鐨勬煇涓厓绱� 娉ㄦ剰锛氬彧鑳戒娇鐢⊿tack鐨勫熀鏈搷浣滐紝鍗硃ush,pop,peek,isEmpty锛�
	 * 鍙互浣跨敤鍙﹀涓�涓爤鏉ヨ緟鍔�
	 * 
	 * @param o
	 */
	public static void remove(Stack s, Object o) {
		Stack newStack = new Stack();
		for (int i = 0; i < s.size(); i++) {
			Object obj = s.pop();
			if (obj == o) {
				break;
			}
			newStack.push(obj);
		}

		for (int j = 0; j < newStack.size(); j++) {
			Object obj = newStack.pop();
			s.push(obj);
		}
	}

	/**
	 * 浠庢爤椤跺彇寰條en涓厓绱�, 鍘熸潵鐨勬爤涓厓绱犱繚鎸佷笉鍙�
	 * 娉ㄦ剰锛氬彧鑳戒娇鐢⊿tack鐨勫熀鏈搷浣滐紝鍗硃ush,pop,peek,isEmpty锛� 鍙互浣跨敤鍙﹀涓�涓爤鏉ヨ緟鍔�
	 * 
	 * @param len
	 * @return
	 */
	public static Object[] getTop(Stack s, int len) {
		Object[] objArr = new Object[len];
		Stack newStack = new Stack();

		for (int i = 0; i < len; i++) {
			Object obj = s.pop();
			objArr[i] = obj;
			newStack.push(obj);
		}

		for (int j = 0; j < len; j++) {
			Object obj = newStack.pop();
			s.push(obj);
		}

		return objArr;
	}

	/**
	 * 瀛楃涓瞫 鍙兘鍖呭惈杩欎簺瀛楃锛� ( ) [ ] { }, a,b,c... x,yz
	 * 浣跨敤鍫嗘爤妫�鏌ュ瓧绗︿覆s涓殑鎷彿鏄笉鏄垚瀵瑰嚭鐜扮殑銆� 渚嬪s = "([e{d}f])" ,
	 * 鍒欒瀛楃涓蹭腑鐨勬嫭鍙锋槸鎴愬鍑虹幇锛� 璇ユ柟娉曡繑鍥瀟rue 濡傛灉 s = "([b{x]y})",
	 * 鍒欒瀛楃涓蹭腑鐨勬嫭鍙蜂笉鏄垚瀵瑰嚭鐜扮殑锛� 璇ユ柟娉曡繑鍥瀎alse;
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isValidPairs(String s) {
		Stack stack = new Stack();
		int len = s.length();
		for (int i = 0; i < len; i++) {
			stack.push(s.charAt(i));
		}
		StringBuilder stringBuilder = new StringBuilder();

		for (int j = 0; j < stack.size(); j++) {
			stringBuilder.append(stack.pop());
		}

		return stringBuilder.toString().equals(s);
	}

}
