package javalearn.collection;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class BlockingQueueTest {

    class Producer implements Runnable {
        private volatile boolean isRunning = true;
        private BlockingQueue queue;
        private AtomicInteger count;
        private static final int  DEFAULT_RANGE_FOR_SLEEP = 1000;

        public Producer(BlockingQueue queue, AtomicInteger count) {
            this.queue = queue;
            this.count = count;
        }

        @Override
        public void run() {
            String data = null;
            Random r = new Random();
            System.out.println("启动生产者线程！");
            try {
                while (isRunning) {
                    System.out.println("正在生产数据...");
                    Thread.sleep(r.nextInt(DEFAULT_RANGE_FOR_SLEEP));//取0~DEFAULT_RANGE_FOR_SLEEP值的一个随机数
                    data = "data:" + count.incrementAndGet();//以原子方式将count当前值加1
                    System.out.println("将数据：" + data + "放入队列...");
                    if (!queue.offer(data, 2, TimeUnit.SECONDS)) {//设定的等待时间为2s，如果超过2s还没加进去返回true
                        System.out.println("放入数据失败：" + data);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            } finally {
                System.out.println("退出生产者线程！");
            }

        }

        public void stop() {
            isRunning = false;
        }
    }

    class Consumer implements Runnable {
        private BlockingQueue<String> queue;
        private static final int DEFAULT_RANGE_FOR_SLEEP = 1000;

        public Consumer(BlockingQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            System.out.println("启动消费者线程！");
            Random r = new Random();
            boolean isRunning = true;
            try {
                while (isRunning) {
                    System.out.println("正从队列获取数据...");
                    String data = queue.poll(2, TimeUnit.SECONDS);//有数据时直接从队列的队首取走，无数据时阻塞，在2s内有数据，取走，超过2s还没数据，返回失败
                    if (null != data) {
                        System.out.println("拿到数据：" + data);
                        Thread.sleep(r.nextInt(DEFAULT_RANGE_FOR_SLEEP));
                    } else {
                        // 超过2s还没数据，认为所有生产线程都已经退出，自动退出消费线程。
                        isRunning = false;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            } finally {
                System.out.println("退出消费者线程！");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(10);
        AtomicInteger count = new AtomicInteger();

        Producer producer1 = new BlockingQueueTest().new Producer(blockingQueue, count);
        Producer producer2 = new BlockingQueueTest().new Producer(blockingQueue, count);
        Producer producer3 = new BlockingQueueTest().new Producer(blockingQueue, count);

        Consumer consumer = new BlockingQueueTest().new Consumer(blockingQueue);

        ExecutorService threadPoll = Executors.newCachedThreadPool();
        threadPoll.execute(producer1);
        threadPoll.execute(producer2);
        threadPoll.execute(producer3);

        new Thread(consumer).start();

        Thread.sleep(10000);
        producer1.stop();
        producer2.stop();
        producer3.stop();

        Thread.sleep(2000);
        threadPoll.shutdown();
    }
}
