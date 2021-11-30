package design.pattern.singleton;

/**
 * @Author
 * @Description 单例模式-饿汉式（内部类）
 * @Date 2021/11/30
 */
public class Singleton3 {
    private Singleton3() {}

    private static class SingletonInstance {
        private final static Singleton3 INSTANCE = new Singleton3();
    }

    private static Singleton3 getInstance() {
        return SingletonInstance.INSTANCE;
    }
}
