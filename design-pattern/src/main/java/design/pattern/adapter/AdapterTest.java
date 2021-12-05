package design.pattern.adapter;

/**
 * @Author
 * @Description
 * @Date 2021/12/5
 */
public class AdapterTest {
    public static void main(String[] args) {
        Target target = new Adapter();
        target.request();
    }
}
