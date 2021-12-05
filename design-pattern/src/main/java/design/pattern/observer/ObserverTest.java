package design.pattern.observer;

/**
 * @Author
 * @Description
 * @Date 2021/12/5
 */
public class ObserverTest {
    public static void main(String[] args) {
        Subject subject = new ConcreteSubject();
        Observer observer1 = new ConcreteObserver1();
        Observer observer2 = new ConcreteObserver2();
        subject.attach(observer1);
        subject.attach(observer2);
        subject.notifyObserver();
    }
}
