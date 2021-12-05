package design.pattern.proxy;

/**
 * @Author
 * @Description
 * @Date 2021/12/3
 */
public class ProxySubject implements Subject{
    private Subject realSubject;

    public ProxySubject(String content) {
        this.realSubject = new RealSubject(content);
    }
    @Override
    public void request() {
        System.out.println("代理请求");
        realSubject.request();
    }
}
