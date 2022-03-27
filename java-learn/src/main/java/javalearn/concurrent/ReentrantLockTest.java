package javalearn.concurrent;

import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantLockTest {

    // 互斥锁
    Lock lock = new ReentrantLock();
    public void function() {
        lock.lock();
        try {
            // 临界区处理逻辑
        } finally {
            // 解锁必须放在finally中，保证代码异常，可以释放锁
            lock.unlock();
        }
    }


    static class ReadWriteMap<K,V> {
        // 读写锁
        private  ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        private final Lock readLock = readWriteLock.readLock();
        private final Lock writeLock = readWriteLock.writeLock();

        private Map<K,V> map;

        public ReadWriteMap(Map<K,V> map) {
            this.map = map;
        }

        public V put(K key, V value) {
            writeLock.lock();
            try {
                return map.put(key, value);
            } finally {
                writeLock.unlock();
            }
        }

        public V get(K key) {
            readLock.lock();
            try {
                return map.get(key);
            } finally {
                readLock.unlock();
            }
        }
    }



}
