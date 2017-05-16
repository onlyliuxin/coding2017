package dataStructure_7_PreAndPost;

public class TestIntegerValueOf {
	public static void main(String[] args) {
		String s = "123";
		System.out.println(Integer.valueOf(s).intValue());// 先把 String 装换成 Integer 然后在转化int
	}
}
