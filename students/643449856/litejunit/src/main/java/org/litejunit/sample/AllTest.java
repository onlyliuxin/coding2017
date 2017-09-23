package org.litejunit.sample;

import org.litejunit.Test;
import org.litejunit.TestTuite;
import org.litejunit.extension.RepeatedTest;
import org.litejunit.sample.caculator.CalculatorTuite;
import org.litejunit.sample.person.PersonTest;


public class AllTest {
public static Test tuite() {
	TestTuite tuite=new TestTuite("AllTest");
	tuite.addTest(CalculatorTuite.tuite());
	//tuite.addTestTuite(PersonTest.class);
	//return tuite;
	tuite.addTest(new RepeatedTest(new TestTuite(PersonTest.class), 1));
	return new org.litejunit.extension.TestSetup(tuite);
}
}
