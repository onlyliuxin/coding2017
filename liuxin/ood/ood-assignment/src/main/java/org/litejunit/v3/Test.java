package org.litejunit.v3;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The <code>Test</code> annotation tells JUnit that the <code>public void</code> method
 * to which it is attached can be run as a test case. To run the method,
 * JUnit first constructs a fresh instance of the class then invokes the
 * annotated method. Any exceptions thrown by the test will be reported
 * by JUnit as a failure. If no exceptions are thrown, the test is assumed
 * to have succeeded.
 * <p>
 * A simple test looks like this:<br>
 * <code>
 * public class Example {<br>
 * &nbsp;&nbsp;@Test public void method() {<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;System.out.println("Hello");<br>
 * &nbsp;&nbsp;}<br>
 * }
 * </code>
 * <p>
 * The <code>Test</code> annotation supports two optional parameters.
 * The first, <code>expected</code>, declares that a test method should throw
 * an exception. If it doesn't throw an exception or if it throws a different exception
 * than the one declared, the test fails. For example, the following test succeeds: <br>
 * <code>
 * &nbsp;&nbsp;@Test(<b>expected=IndexOutOfBoundsException.class</b>) public void outOfBounds() {<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;new ArrayList&lt;Object&gt;().get(1);<br>
 * &nbsp;&nbsp;}<br>
 * </code>
 * <p>
 * The second optional parameter, <code>timeout</code>, causes a test to fail if it takes longer than a specified
 * amount of clock time (measured in milliseconds). The following test fails:<br>
  * <code>
 * &nbsp;&nbsp;@Test(<b>timeout=100</b>) public void infinity() {<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;for(;;);<br>
 * &nbsp;&nbsp;}<br>
 * </code>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test {
	static class None extends Throwable {
		private static final long serialVersionUID= 1L;		
		private None() {
		}
	}
	
	/**
	 * Optionally specify <code>expected</code>, a Throwable, to cause a test method to succeed iff 
	 * an exception of the specified class is thrown by the method.
	 */
	Class<? extends Throwable> expected() default None.class;
	
	/** 
	 * Optionally specify <code>timeout</code> in milliseconds to cause a test method to fail if it
	 * takes longer than that number of milliseconds.*/
	long timeout() default 0L; 
}
