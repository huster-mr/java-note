package com.example.spring.learn.coreinterface;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Author
 * @Description
 * @Date 2021/12/19
 */
@Component
public class MySmartLifecycle implements SmartLifecycle {

    private AtomicBoolean started = new AtomicBoolean(false);

    private ThreadFactory asyncThreadPoolFactory = new ThreadFactoryBuilder().setNameFormat("async_thread_pool_%d").build();
    private ExecutorService asyncThreadPool = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), asyncThreadPoolFactory);

    void init() {
        // todo, 可以完成一些初始配置工作
        System.out.println("MySmartLifecycle 初始化工作");
    }

    // 返回true，才会自动回调
    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public boolean isRunning() {
        return started.get();
    }

    @Override
    public void start() {
        boolean start = started.compareAndSet(false, true);
        if (start) {
            init();
            // todo,开启异步任务
            asyncThreadPool.submit(() -> {
                System.out.println("MySmartLifecycle 开启异步任务！");
            });
        }
    }

    @Override
    public void stop() {
        boolean stop = started.compareAndSet(true, false);
        if (stop) {
            // todo, 可能需要处理一些断开连接的工作
            asyncThreadPool.shutdownNow();
        }
    }

    @Override
    public void stop(Runnable callback) {
        try {
            stop();
        } finally {
            callback.run();
        }
    }

    // 多个SmartLifecycle实现类时，可以根据返回设置回调顺序
    @Override
    public int getPhase() {
        return 0;
    }
}
