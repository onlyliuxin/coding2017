package org.v0_my.sample.caculator;

import org.v0_my.Test;
import org.v0_my.TestTuite;

public class CalculatorTuite {
public static Test tuite() {
	TestTuite tuite=new TestTuite("CalculatorTuite");
	tuite.addTestTuite(CaculatorTestCase.class);
	 return tuite;
}
}
