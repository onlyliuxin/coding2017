package  com.dudy.learn01.base.aop.DynamicProxy;

/**
 * Created by dudy on 2017/3/22.
 */
public class Business implements  IBusiness,IBusiness2{
    @Override
    public void dosomething() {
        System.out.println("dosomething.....");
    }

    @Override
    public void dosomething2() {
        System.out.println("dosomething2.....");
    }
}
