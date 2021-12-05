package design.pattern.chain;

/**
 * @Author
 * @Description
 * @Date 2021/12/5
 */
public class ChainTest {
    public static void main(String[] args) {
        Handler handler1 = new ConcreteHandler1();
        Handler handler2 = new ConcreteHandler2();
        handler1.setNext(handler2);
        handler1.handleRequest("two");
    }
}
