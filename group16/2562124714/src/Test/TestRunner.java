package Test;

import org.junit.runner.JUnitCore;
import org.junit.runner.notification.Failure;

import javax.xml.transform.Result;

/**
 * Created by zhangwj on 2017/2/23. 调用测试类 可重复使用
 */
public class TestRunner {
    public static  void  main(String[] args) {
        org.junit.runner.Result result = JUnitCore.runClasses(BinaryTreeNodeTest.class);
        for (Failure failure:result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}
