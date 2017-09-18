package com.coderising.myknowledgepoint.closure;

/**
 * Created by thomas_young on 27/8/2017.
 */
public class Milk {

    public final static String name = "纯牛奶";//名称

    private static int num = 16;//数量

    public Milk()
    {
        System.out.println(name+"：16/每箱");
    }

    /**
     * 闭包
     * @return 返回一个喝牛奶的动作
     */
    public Active HaveMeals()
    {
        return () -> {
                if(num == 0)
                {
                    System.out.println("木有了，都被你丫喝完了.");
                    return;
                }
                num--;
                System.out.println("喝掉一瓶牛奶");
            };
    }

    /**
     * 获取剩余数量
     */
    public void currentNum()
    {
        System.out.println(name+"剩余："+num);
    }
}

/**
 * 通用接口
 */
interface Active
{
    void drink();
}
