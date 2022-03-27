package javalearn.concurrent;

import com.sun.corba.se.impl.orbutil.concurrent.Sync;
import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

public class SynchronizedTest {

//    private synchronized void func1() {
//        System.out.println("线程：" + Thread.currentThread().getName());
//    }
//
//    public void func2() {
//        synchronized (this) {
//            System.out.println("线程：" + Thread.currentThread().getName());
//        }
//    }
//
//    public void func3() {
//        Object obj = new Object();
//        synchronized (obj) {
//            // todo
//        }
//    }
//
//    public synchronized static void func4() {
//        System.out.println("线程：" + Thread.currentThread().getName());
//    }
//
//    public static void func5() {
//        synchronized (SynchronizedTest.class) {
//            System.out.println("线程：" + Thread.currentThread().getName());
//        }
//    }


//案例一
//    public synchronized void minus() {
//        for (int i = 0; i < 5; i++) {
//            System.out.println("线程：" + Thread.currentThread().getName() + "-" + i);
//            try {
//                TimeUnit.MILLISECONDS.sleep(500);
//            } catch (InterruptedException e) {
//            }
//
//        }
//    }
//
//    public static void main(String[] args) {
//        final SynchronizedTest synchronizedTest = new SynchronizedTest();
//        Thread thread1 = new Thread(() -> {
//            synchronizedTest.minus();
//        });
//        Thread thread2 = new Thread(() -> {
//            synchronizedTest.minus();
//        });
//        thread1.start();
//        thread2.start();
//    }

//案例二
//    public synchronized void minus() {
//        for (int i = 0; i < 5; i++) {
//            System.out.println("线程：" + Thread.currentThread().getName() + "-" + i);
//            try {
//                TimeUnit.MILLISECONDS.sleep(500);
//            } catch (InterruptedException e) {
//            }
//
//        }
//    }
//
//    public static void main(String[] args) {
//        final SynchronizedTest synchronizedTest1 = new SynchronizedTest();
//        final SynchronizedTest synchronizedTest2 = new SynchronizedTest();
//        Thread thread1 = new Thread(() -> {
//            synchronizedTest1.minus();
//        });
//        Thread thread2 = new Thread(() -> {
//            synchronizedTest2.minus();
//        });
//        thread1.start();
//        thread2.start();
//    }


//案例三
//    public synchronized void minus() {
//        for (int i = 0; i < 5; i++) {
//            System.out.println("线程：" + Thread.currentThread().getName() + "-" + i);
//            try {
//                TimeUnit.MILLISECONDS.sleep(500);
//            } catch (InterruptedException e) {
//            }
//
//        }
//    }
//
//    public synchronized void minus2() {
//        for (int i = 0; i < 5; i++) {
//            System.out.println("线程：" + Thread.currentThread().getName() + "-" + i);
//            try {
//                TimeUnit.MILLISECONDS.sleep(500);
//            } catch (InterruptedException e) {
//            }
//
//        }
//    }
//
//    public static void main(String[] args) {
//        final SynchronizedTest synchronizedTest = new SynchronizedTest();
//        Thread thread1 = new Thread(() -> {
//            synchronizedTest.minus();
//        });
//        Thread thread2 = new Thread(() -> {
//            synchronizedTest.minus2();
//        });
//        thread1.start();
//        thread2.start();
//    }

//案例四
//    public synchronized void minus() {
//        for (int i = 0; i < 5; i++) {
//            System.out.println("线程：" + Thread.currentThread().getName() + "-" + i);
//            try {
//                TimeUnit.MILLISECONDS.sleep(500);
//            } catch (InterruptedException e) {
//            }
//
//        }
//    }
//
//    public void minus2() {
//        for (int i = 0; i < 5; i++) {
//            System.out.println("线程：" + Thread.currentThread().getName() + "-" + i);
//            try {
//                TimeUnit.MILLISECONDS.sleep(500);
//            } catch (InterruptedException e) {
//            }
//
//        }
//    }
//
//    public static void main(String[] args) {
//        final SynchronizedTest synchronizedTest = new SynchronizedTest();
//        Thread thread1 = new Thread(() -> {
//            synchronizedTest.minus();
//        });
//        Thread thread2 = new Thread(() -> {
//            synchronizedTest.minus2();
//        });
//        thread1.start();
//        thread2.start();
//    }

//案例五
//    public static synchronized void minus() {
//        for (int i = 0; i < 5; i++) {
//            System.out.println("线程：" + Thread.currentThread().getName() + "-" + i);
//            try {
//                TimeUnit.MILLISECONDS.sleep(500);
//            } catch (InterruptedException e) {
//            }
//
//        }
//    }
//
//    public static void main(String[] args) {
//        Thread thread1 = new Thread(() -> {
//            SynchronizedTest.minus();
//        });
//        Thread thread2 = new Thread(() -> {
//            SynchronizedTest.minus();
//        });
//        thread1.start();
//        thread2.start();
//    }

//案例六
//    public static synchronized void minus() {
//        for (int i = 0; i < 5; i++) {
//            System.out.println("线程：" + Thread.currentThread().getName() + "-" + i);
//            try {
//                TimeUnit.MILLISECONDS.sleep(500);
//            } catch (InterruptedException e) {
//            }
//
//        }
//    }
//
//    public synchronized void minus2() {
//        for (int i = 0; i < 5; i++) {
//            System.out.println("线程：" + Thread.currentThread().getName() + "-" + i);
//            try {
//                TimeUnit.MILLISECONDS.sleep(500);
//            } catch (InterruptedException e) {
//            }
//
//        }
//    }
//
//    public static void main(String[] args) {
//        final SynchronizedTest synchronizedTest = new SynchronizedTest();
//        Thread thread1 = new Thread(() -> {
//            SynchronizedTest.minus();
//        });
//        Thread thread2 = new Thread(() -> {
//            synchronizedTest.minus2();
//        });
//        thread1.start();
//        thread2.start();
//    }


// 案例7
//    public class Task implements Runnable {
//        private String str;
//        Task(String str) {
//            this.str = str;
//        }
//
//        public void minus() {
//            for (int i = 0; i < 5; i++) {
//                System.out.println("线程：" + Thread.currentThread().getName() + "-" + i);
//                try {
//                    TimeUnit.MILLISECONDS.sleep(500);
//                } catch (InterruptedException e) {
//                }
//
//            }
//        }
//
//        @Override
//        public void run() {
//            synchronized (str) {
//                System.out.println(str);
//                minus();
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        SynchronizedTest synchronizedTest = new SynchronizedTest();
//        Thread thread1 = new Thread(synchronizedTest.new Task("1"));
//        Thread thread2 = new Thread(synchronizedTest.new Task("1"));
//        thread1.start();
//        thread2.start();
//    }

// 可重入性
//    public class Task implements Runnable {
//        private Integer i;
//
//        public Task(Integer i) {
//            this.i = i;
//        }
//
//        @Override
//        public void run() {
//            for(int j=0; j<100000; j++) {
//                synchronized (this) {
//                    // 可重入性
//                    increase();
//                }
//            }
//        }
//
//        public synchronized void increase() {
//            i++;
//            System.out.println(i);
//        }
//    }
//
//    public static void main(String[] args) throws Exception{
//        SynchronizedTest synchronizedTest = new SynchronizedTest();
//        Integer num = 0;
//        Thread thread1 = new Thread(synchronizedTest.new Task(num));
//        Thread thread2 = new Thread(synchronizedTest.new Task(num));
//        thread1.start();
//        thread2.start();
//        thread1.join();
//        thread2.join();
//    }


    public static void main(String[] args) {
        Object obj = new Object();
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        System.out.println("-----------加锁之后的变化-----------");
        synchronized (obj) {
            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        }
    }

}
