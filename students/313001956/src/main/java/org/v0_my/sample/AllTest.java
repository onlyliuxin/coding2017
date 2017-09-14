package org.v0_my.sample;

import org.v0_my.Test;
import org.v0_my.TestTuite;
import org.v0_my.extension.RepeatedTest;
import org.v0_my.sample.caculator.CalculatorTuite;
import org.v0_my.sample.person.PersonTest;

import junit.extensions.TestSetup;

public class AllTest {
public static Test tuite() {
	TestTuite tuite=new TestTuite("AllTest");
	tuite.addTest(CalculatorTuite.tuite());
	//tuite.addTestTuite(PersonTest.class);
	//return tuite;
	tuite.addTest(new RepeatedTest(new TestTuite(PersonTest.class), 1));
	return new org.v0_my.extension.TestSetup(tuite);
}
}
