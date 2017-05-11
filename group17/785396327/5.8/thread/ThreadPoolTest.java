package thread;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by gongxun on 2017/5/11.
 */
public class ThreadPoolTest {
    @Before
    public void startUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void fun1() {
        final DefaultThreadPool threadPool = new DefaultThreadPool();
//        final AtomicInteger atomicInteger = new AtomicInteger(0);
//        threadPool.execute(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    int i = atomicInteger.incrementAndGet();
//                    System.out.println(Thread.currentThread().getName() + "   " + threadPool.getWorkerSize() + "  " + i);
////                    wait();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
        for (int i = 0; i < 2; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
//                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName() + "  " + threadPool.getWorkerSize());
//                        Thread.sleep(2000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Test
    public void fun2() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 6; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            });
        }
    }

    @Test
    public void fun3() {
        final ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "   ");
            }
        });
    }

    @Test
    public void fun4() {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>();
        for (int i = 0; i < 5; i++) {
            queue.offer(i + "");
        }
        while (!queue.isEmpty()) {
            try {
                String value = queue.take();
                System.out.println(value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            String str = queue.take();
            System.out.println(str);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
