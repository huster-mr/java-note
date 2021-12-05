package design.pattern.adapter;

/**
 * @Author
 * @Description
 * @Date 2021/12/3
 */
public class Adapter extends Target{
    private Adaptee adaptee = new Adaptee();
    @Override
    public void request() {
        adaptee.specificRequest();
    }
}
