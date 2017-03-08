package xqfGit.dataStructure.conderising;

public class TestArrayUtil {
	
	public static void main(String[] args) {
		int[] num ={1,4,6,7,3,5};
		String temp=new ArrayUtil().join(num, "-");
		System.out.println(temp);
	}
}
