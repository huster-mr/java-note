package design.pattern.observer;

/**
 * @Author
 * @Description
 * @Date 2021/12/5
 */
public class ConcreteObserver1 extends Observer{
    @Override
    public void update() {
        System.out.println("ConcreteObserver1 更新状态！");
    }
}
