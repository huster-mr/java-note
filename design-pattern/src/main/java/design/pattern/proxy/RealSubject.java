package design.pattern.proxy;

/**
 * @Author
 * @Description
 * @Date 2021/12/3
 */
public class RealSubject implements Subject{
    private String content;

    public RealSubject(String content) {
        this.content = content;
    }

    @Override
    public void request() {
        System.out.println("正式的请求");
        System.out.println(content);
    }
}
