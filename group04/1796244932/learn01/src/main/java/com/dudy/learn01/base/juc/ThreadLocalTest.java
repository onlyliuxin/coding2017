package com.dudy.learn01.base.juc;

/**
 * Created by dudy on 2017/3/9.
 *
 * 4. ThreadLocal  这个类实现原理和用途，在哪里用到了
 * 每个ThreadLocal可以放一个线程级别的变量,但是它本身可以被多个线程共享变量，而且又可以达到线程安全的目的，且绝对线程安全
 * spring的事物管理Session, 连接池管理 Connection
 * https://my.oschina.net/huangyong/blog/159725
 * 数据库事物的前提是: 必须是同一个连接
 */
public class ThreadLocalTest {

    static  class  Resource{
        public  static final ThreadLocal RESOURCE1 = new ThreadLocal();
        public  static final ThreadLocal RESOURCE2 = new ThreadLocal();
    }

    static class  A {
        public  void setOne(String str){
            Resource.RESOURCE1.set(str);
        }

        public  void setTwo(String str){
            Resource.RESOURCE2.set(str);
        }
    }

    static  class B {
        public void display(){
            System.out.println(Resource.RESOURCE1.get()
                +":" + Resource.RESOURCE2.get());
        }
    }

    public static void main(String[] args) {

        final  A a = new A();
        final  B b = new B();

        for (int i = 0; i< 5 ;i++){

            final String resource1 = "Thread_" + i;
            final String resource2 = "value " + i;

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        a.setOne(resource1);
                        a.setTwo(resource2);
                        b.display();
                    }finally {
                        Resource.RESOURCE2.remove();
                        Resource.RESOURCE1.remove();
                    }
                }
            }).start();
        }

    }


}
