package design.pattern.singleton;

/**
 * @Author
 * @Description 单例模式-饿汉式
 * @Date 2021/11/30
 */
public class Singleton2 {
    private static Singleton2 instance = new Singleton2();
    private Singleton2(){}
    private static Singleton2 getInstance() {
        return instance;
    }
}
