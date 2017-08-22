package com.coderising.myood.litejunit.v1;


public interface Test {
	int countTestCases();  // command模式，一个测试用例是一个command
	void run(TestResult tr);  // 分离测试用例和测试结果，收集参数模式
}
