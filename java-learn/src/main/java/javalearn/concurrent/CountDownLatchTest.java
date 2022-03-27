package javalearn.concurrent;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    public long timeTasks(int nThread, final Runnable task) throws InterruptedException {
        // 启动门
        final CountDownLatch startGate = new CountDownLatch(1);
        // 结束门
        final CountDownLatch endGate = new CountDownLatch(nThread);
        for (int i = 0; i < nThread; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        startGate.await();
                        try {
                            task.run();
                        } finally {
                            endGate.countDown();
                        }
                    } catch (InterruptedException ingored) {

                    }
                }
            }).start();
        }
        long startTime = System.currentTimeMillis();
        startGate.countDown();
        endGate.await();
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
}
