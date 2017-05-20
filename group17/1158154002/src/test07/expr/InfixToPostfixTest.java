package test07.expr;

public class InfixToPostfixTest {
	
	public static void main(String[] args) {
		//2+3*4-8/2
		//2+3*4*5*5-8/2
		InfixToPostfix.convert("2+3*4*5*5-8/2/2");
		InfixToPostfix.convert("2+3");
	}

}
