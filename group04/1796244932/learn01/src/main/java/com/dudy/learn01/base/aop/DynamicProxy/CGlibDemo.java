package  com.dudy.learn01.base.aop.DynamicProxy;

import net.sf.cglib.proxy.Enhancer;

/**
 * Created by dudy on 2017/3/22.
 */
public class CGlibDemo {

    public static void main(String[] args) {
        byteCodeGe();
    }

    public static  void byteCodeGe(){
        //创建一个织入器
        Enhancer enhancer = new Enhancer();
        // 设置父类
        enhancer.setSuperclass(Business.class);
        // 设置需要织入的逻辑
        enhancer.setCallback(new LogIntercept());
        //使用织入器创建子类
        IBusiness2  newBusiness = (IBusiness2) enhancer.create();
        newBusiness.dosomething2();

    }


}
