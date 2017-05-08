package structure.week6;
import java.util.Stack;
public class StackUtil {
	
	public static void bad_reverse(Stack<Integer> s) {
		if(s == null || s.isEmpty()){
			return;
		}
		Stack<Integer> tmpStack = new Stack();
		while(!s.isEmpty()){
			tmpStack.push(s.pop());
		}
		s = tmpStack;
	}
	
    /**
    * 閸嬪洩顔曢弽鍫滆厬閻ㄥ嫬鍘撶槐鐘虫ЦInteger, 娴犲孩鐖ゆい璺哄煂閺嶅牆绨抽弰锟� 5,4,3,2,1 鐠嬪啰鏁ょ拠銉︽煙濞夋洖鎮楅敍锟介崗鍐濞嗏�绨崣妯硅礋: 1,2,3,4,5
    * 濞夈劍鍓伴敍姘涧閼虫垝濞囬悽鈯縯ack閻ㄥ嫬鐔�張顒佹惙娴ｆ粣绱濋崡纭僽sh,pop,peek,isEmpty閿涳拷閸欘垯浜掓担璺ㄦ暏閸欙箑顦绘稉锟介嚋閺嶅牊娼垫潏鍛И
    */
    public static void reverse(Stack<Integer> s){
        if(s == null || s.isEmpty()) return;
        int size = 0;
        Stack<Integer> s1 = new Stack<Integer>();
        while(!s.isEmpty()){
            s1.push(s.pop());
            size += 1;
        }
        while(!s1.isEmpty())
            s.push(s1.pop());
        for(int i=0;i<size;i++){
            Integer integer = s.pop();
            while(s.size()>i)
                s1.push(s.pop());
            s.push(integer);
            while(s1.size()>0)
                s.push(s1.pop());
        }
    }
    // 閫氳繃閫掑綊鍙互涓嶄娇鐢ㄥ爢鏍堝畬鎴愯繖涓�姛鑳�
	public static void reverse2(Stack<Integer> s) {
		if(s == null || s.isEmpty()){
			return;
		}
		Integer top = s.pop();
		reverse(s);
		addToBottom(s,top);
	}
	public static void addToBottom(Stack<Integer> s,  Integer value){
		if(s.isEmpty()){
			s.push(value);
		} else{
			Integer top = s.pop();
			addToBottom(s,value);
			s.push(top);
		}
	}
	/**
	 * 閸掔娀娅庨弽鍫滆厬閻ㄥ嫭鐓囨稉顏勫帗缁憋拷濞夈劍鍓伴敍姘涧閼虫垝濞囬悽鈯縯ack閻ㄥ嫬鐔�張顒佹惙娴ｆ粣绱濋崡纭僽sh,pop,peek,isEmpty閿涳拷閸欘垯浜掓担璺ㄦ暏閸欙箑顦绘稉锟介嚋閺嶅牊娼垫潏鍛И
	 * 
	 * @param o
	 */
	public static void remove(Stack s,Object o) {
		if(s == null || s.isEmpty()){
			return;
		}
		Stack tmpStack = new Stack();
		while(!s.isEmpty()){
			Object value = s.pop();
			if(!value.equals(o)){
				tmpStack.push(value);
			} 			
		}
		while(!tmpStack.isEmpty()){
			s.push(tmpStack.pop());
		}
	}

	/**
	 * 娴犲孩鐖ゆい璺哄絿瀵版en娑擃亜鍘撶槐锟�閸樼喐娼甸惃鍕垽娑擃厼鍘撶槐鐘辩箽閹镐椒绗夐崣锟�	 * 濞夈劍鍓伴敍姘涧閼虫垝濞囬悽鈯縯ack閻ㄥ嫬鐔�張顒佹惙娴ｆ粣绱濋崡纭僽sh,pop,peek,isEmpty閿涳拷閸欘垯浜掓担璺ㄦ暏閸欙箑顦绘稉锟介嚋閺嶅牊娼垫潏鍛И
	 * @param len
	 * @return
	 */
	public static Object[] getTop(Stack s,int len) {
		if(s == null || s.isEmpty() || s.size()<len || len <=0 ){
			return null;
		}
		Stack tmpStack = new Stack();
		int i = 0;
		Object[] result = new Object[len];
		while(!s.isEmpty()){
			Object value = s.pop();			
			tmpStack.push(value);
			result[i++] = value;
			if(i == len){
				break;
			}
		}
		return result;
	}
	/**
	 * 鐎涙顑佹稉鐬�閸欘垵鍏橀崠鍛儓鏉╂瑤绨虹�妤冾儊閿涳拷 ( ) [ ] { }, a,b,c... x,yz
	 * 娴ｈ法鏁ら崼鍡樼垽濡拷鐓＄�妤冾儊娑撶灚娑擃厾娈戦幏顒�娇閺勵垯绗夐弰顖涘灇鐎电懓鍤悳鎵畱閵嗭拷
	 * 娓氬顩 = "([e{d}f])" , 閸掓瑨顕氱�妤冾儊娑撹弓鑵戦惃鍕閸欓攱妲搁幋鎰嚠閸戣櫣骞囬敍锟界拠銉︽煙濞夋洝绻戦崶鐎焤ue
	 * 婵″倹鐏�s = "([b{x]y})", 閸掓瑨顕氱�妤冾儊娑撹弓鑵戦惃鍕閸欒渹绗夐弰顖涘灇鐎电懓鍤悳鎵畱閿涳拷鐠囥儲鏌熷▔鏇＄箲閸ョ�alse;
	 * @param s
	 * @return
	 */
	public static boolean isValidPairs(String s){
		Stack<Character> stack = new Stack();
		for(int i=0;i<s.length();i++){
			char c = s.charAt(i);
			if(c == '(' || c =='[' || c == '{'){
				stack.push(c);
			} else if( c == ')'){
				char topChar = stack.pop();
				if(topChar != '('){
					return false;
				}
			} else if( c == ']'){
				char topChar = stack.pop();
				if(topChar != '['){
					return false;
				}
			} else if( c == '}'){
				char topChar = stack.pop();
				if(topChar != '{'){
					return false;
				}
			}
		}
		return stack.size() == 0;
	}	
}
