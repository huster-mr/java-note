package design.pattern.proxy;



/**
 * @Author
 * @Description
 * @Date 2021/12/5
 */
public class ProxyTest {
    public static void main(String[] args) {
        Subject subject = new ProxySubject("hello world");
        subject.request();
    }
}
