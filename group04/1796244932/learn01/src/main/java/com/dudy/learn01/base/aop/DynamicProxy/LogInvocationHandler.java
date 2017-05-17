package  com.dudy.learn01.base.aop.DynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by dudy on 2017/3/22.
 * 打印日志的切面
 */
public class LogInvocationHandler implements InvocationHandler{

    private  Object  targer; //目标对象

    public LogInvocationHandler(Object targer) {
        this.targer = targer;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 执行原有逻辑
        Object rev = method.invoke(targer, args);
        // 执行织入的日志，你可以控制哪些哪些方法执行切入逻辑
        if (method.getName().equals("dosomething2")){
            System.out.println("记录日志");
        }

        return rev;
    }
}
