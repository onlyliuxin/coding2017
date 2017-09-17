请运行com.coderising.myood.litejunit.v2.TestRunner.main函数来查看结果


### 使用模式
1. 命令模式: 每个TestCase都有个run方法，run内部包含了业务代码，但是对TestCase来讲不用管, 只要运行run即可
2. 观察者模式: TestResult是主题, Listener是观察者, 解耦了测试结果和结果显示逻辑
3. 参数收集: TestResult收集了测试结果, 和测试用例的运行进行了解耦
4. 组合模式: TestSuite组合了TestCase, 因此可以对类、包、模块进行测试
5. 装饰器模式: 在Test外面包裹了装饰器, 从而实现重复运行测试用例或者对包整体进行setUp, tearDown
6. 模板方法: setUp, runTest(), tearDown()
7. 协议: 规定了public static Test suite(), 测试用例以test开头, 从而方便反射的运用

---------------------------------------

### 改进点
1. TestRunner没有使用反射来寻找suite方法
2. junit-v4版本后续有空可以实现
3. 两个装饰器的异常判断不足, 可能意外跳出