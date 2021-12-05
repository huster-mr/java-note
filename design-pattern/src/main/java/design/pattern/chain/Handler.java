package design.pattern.chain;

/**
 * @Author
 * @Description
 * @Date 2021/12/5
 */
public abstract class Handler {
    protected Handler next;

    public Handler getNext() {
        return next;
    }

    public void setNext(Handler next) {
        this.next = next;
    }

    public abstract void handleRequest(String request);
}
