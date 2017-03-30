package study;

import com.alibaba.fastjson.JSON;

/**
 * @Author shane
 * @Time 2017/2/25 13:06
 * @Email stevenchenguang@gmail.com
 * @Desc 测试基类
 */
public class AbstractTest {

    protected void printStar() {
        System.out.println("********************************************");
    }

    protected void printHyphen() {
        System.out.println("--------------------------------------------");
    }

    protected void printJson(Object obj) {
        System.out.println(JSON.toJSONString(obj));
    }
}
