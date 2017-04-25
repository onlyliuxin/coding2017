package me.practise;

import java.awt.event.ActionListener;
import java.util.function.BinaryOperator;

public class chapter2 {
	
	public static void main(String[] args) {
		Runnable noArg = () -> System.out.println("runnable with no argument.");
		Thread thread = new Thread(noArg);
		thread.start();
		
		ActionListener oneArg = event -> System.out.println("actionListener with one argument.");
		
		Runnable multiStatement = () -> {
			System.out.println("Runnable with multi statement.");
			System.out.println("Hello Java8!");
		};
		BinaryOperator<Long> add = (x, y) -> x + y;
		BinaryOperator<Long> addExplicit = (Long x, Long y) -> x + y;
		
		String name = "abc";
		name = "def";
		//ActionListener oneArg1 = event -> System.out.println("actionListener with one argument." + name);
		// 编译无法通过：方法块中使用的变量必须使用final声明或既成事实的final变量，即变成赋值成功后不能再赋值
	}
}
