package design.pattern.observer;

/**
 * @Author
 * @Description
 * @Date 2021/12/5
 */
public class ConcreteObserver2 extends Observer{
    @Override
    public void update() {
        System.out.println("ConcreteObserver2 更新状态！");
    }
}
