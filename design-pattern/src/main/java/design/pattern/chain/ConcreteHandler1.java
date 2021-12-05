package design.pattern.chain;

/**
 * @Author
 * @Description
 * @Date 2021/12/5
 */
public class ConcreteHandler1 extends Handler{
    @Override
    public void handleRequest(String request) {
        if (request.equals("one")) {
            System.out.println("具体处理者1负责处理该请求！");
        } else {
            if (null != getNext()) {
                getNext().handleRequest(request);
            } else {
                System.out.println("无人处理！");
            }
        }
    }
}
