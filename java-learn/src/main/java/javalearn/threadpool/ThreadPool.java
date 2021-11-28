package javalearn.threadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author
 * @Description 并发编程-线程池学习
 * @Date 2021/11/28
 */
public class ThreadPool {
    // 单线程池
    private static final ExecutorService SINGLE_THREAD_POOL = new ThreadPoolExecutor(1, 1,
            0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

    // 固定线程池
    private static final ExecutorService FIXED_THREAD_POOL = new ThreadPoolExecutor(5, 5,
            0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(),
            new ThreadFactory() {
                private AtomicInteger index = new AtomicInteger(0);
                @Override
                public Thread newThread(Runnable r) {
                    return new Thread(r, "FIXED_THREAD_POOL_" + index.incrementAndGet());
                }
            });

    // 缓存线程池
    private static final ThreadFactory CACHE_THREAD_FACTORY = new ThreadFactoryBuilder().setNameFormat("CACHE_THREAD_POOL_%d").build();
    private static final ExecutorService CACHE_THREAD_POOL = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
            6, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), CACHE_THREAD_FACTORY);

    // 延时、循环任务线程池
    private static final ThreadFactory SCHEDULED_THREAD_FACTOR = new ThreadFactoryBuilder().setNameFormat("SCHEDULED_THREAD_POOL_%d").build();
    private static final ScheduledExecutorService SCHEDULED_EXECUTOR_POOL = new ScheduledThreadPoolExecutor(1, SCHEDULED_THREAD_FACTOR);

    private static Runnable runTask = new Runnable() {
        @Override
        public void run() {
            try {
                printThreadInfo("开始执行");
                Thread.sleep(1000);
                printThreadInfo("执行完毕");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private static void printThreadInfo(String action) {
        System.out.println("【" + action + "】" + " 【线程名字】：" + Thread.currentThread().getName() + " 【线程存活数】：" + Thread.activeCount() + " 【当前时间】：" + System.currentTimeMillis());
    }

    private static void threadPoolTest(ExecutorService threadPool) {
        for (int i = 0; i < 10; i++) {
            System.out.println("开启第" + i +"个任务！");
            threadPool.execute(runTask);
        }
        if (CACHE_THREAD_POOL.equals(threadPool)) {
            try {
                System.out.println("当前存活线程数：" + Thread.activeCount());
                Thread.sleep(6100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("当前存活线程数：" + Thread.activeCount());
            threadPool.execute(runTask);
        }
    }

    private static void scheduledTest() {
        System.out.println("开始时间：" + System.currentTimeMillis());
        SCHEDULED_EXECUTOR_POOL.schedule(runTask, 2, TimeUnit.SECONDS);
    }

    private static void scheduledAtFixedRateTest() {
        System.out.println("开始时间：" + System.currentTimeMillis());
        SCHEDULED_EXECUTOR_POOL.scheduleAtFixedRate(runTask, 2, 10, TimeUnit.SECONDS);
    }

    private static void scheduledWithFixedDelayTest() {
        System.out.println("开始时间：" + System.currentTimeMillis());
        SCHEDULED_EXECUTOR_POOL.scheduleWithFixedDelay(runTask, 2, 10, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
//        System.out.println("============单线程池测试开始============");
//        // 1.单线程测试
//        threadPoolTest(SINGLE_THREAD_POOL);
//
//        System.out.println("============固定线程池测试开始============");
//        // 2.固定线程池测试
//        threadPoolTest(FIXED_THREAD_POOL);
//
//        System.out.println("============缓存线程池测试开始============");
//        // 3.缓存线程池测试
//        threadPoolTest(CACHE_THREAD_POOL);

        System.out.println("============延时循环线程池测试开始============");
        // 4.延时、循环任务线程池测试
        scheduledTest();
        scheduledAtFixedRateTest();
        scheduledWithFixedDelayTest();
    }

}
