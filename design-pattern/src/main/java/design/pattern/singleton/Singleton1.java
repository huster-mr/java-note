package design.pattern.singleton;

/**
 * @Author
 * @Description 单例模式-懒汉式
 * @Date 2021/11/30
 */
public class Singleton1 {
    private volatile static Singleton1 instance;
    private Singleton1(){}
    private static Singleton1 getInstance() {
        if (instance == null) {
            synchronized (Singleton1.class) {
                if (instance == null) {
                    instance = new Singleton1();
                }
            }
        }
        return instance;
    }
}
