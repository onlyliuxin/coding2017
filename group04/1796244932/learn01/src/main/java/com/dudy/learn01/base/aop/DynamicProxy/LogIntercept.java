package  com.dudy.learn01.base.aop.DynamicProxy;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by dudy on 2017/3/22.
 */
public class LogIntercept implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy proxy) throws Throwable {
        //执行原有的逻辑，注意这里是invokeSuper
        Object rev = proxy.invokeSuper(o, objects);
        //执行织入的日志
        if (method.getName().equals("dosomething2")){
            System.out.println("CGlib dosometing2.....");
        }


        return rev;
    }
}
