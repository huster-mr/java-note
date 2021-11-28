package design.pattern.factory;

/**
 * @Author
 * @Description
 * @Date 2021/11/28
 */
public class MulOperation extends AbstractOperation{
    @Override
    public double getResult() {
        return getNumberA() * getNumberB();
    }
}
