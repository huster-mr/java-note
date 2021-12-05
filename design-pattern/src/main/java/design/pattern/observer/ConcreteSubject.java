package design.pattern.observer;

/**
 * @Author
 * @Description
 * @Date 2021/12/5
 */
public class ConcreteSubject extends Subject{
    @Override
    public void notifyObserver() {
        for (Observer obs: observers) {
            obs.update();
        }
    }
}
