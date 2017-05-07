package  com.dudy.learn01.base.aop.DynamicProxy;
import java.lang.reflect.Proxy;

/**
 * Created by dudy on 2017/3/22.
 */
public class DynamicProxyDemo {


    public static void main(String[] args) {

        // 需要代理的接口，被代理类实现的多个接口都必须放在这里
        Class[] proxyInterface  = new Class[]{IBusiness.class,IBusiness2.class};
        // 构建AOP的Advice，这里需要传入业务类的实例
        LogInvocationHandler handler = new LogInvocationHandler(new Business());
        //生成代理类的字节码加载器
        ClassLoader loader  = DynamicProxyDemo.class.getClassLoader();
        //织入器，织入代码并生成代理类
        IBusiness2 proxyBusiness = (IBusiness2) Proxy.newProxyInstance(loader, proxyInterface, handler);

        proxyBusiness.dosomething2();
    }


}
