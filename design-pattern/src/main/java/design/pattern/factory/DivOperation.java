package design.pattern.factory;

/**
 * @Author
 * @Description
 * @Date 2021/11/28
 */
public class DivOperation extends AbstractOperation{
    @Override
    public double getResult() {
        if (0 == getNumberB()) {
            throw new RuntimeException("除数不能为0！");
        }
        return getNumberA() / getNumberB();
    }
}
