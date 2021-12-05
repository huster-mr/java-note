package design.pattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author
 * @Description
 * @Date 2021/12/5
 */
public abstract class Subject {
    protected List<Observer> observers = new ArrayList<>();

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    public abstract void notifyObserver();
}
