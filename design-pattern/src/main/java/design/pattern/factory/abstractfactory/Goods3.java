package design.pattern.factory.abstractfactory;

import design.pattern.factory.AbstractOperation;
import design.pattern.factory.MulOperation;
import design.pattern.factory.Shape;
import design.pattern.factory.Square;

/**
 * @Author
 * @Description
 * @Date 2021/11/28
 */
public class Goods3 implements AbstractFactory{
    @Override
    public AbstractOperation createOperation() {
        return new MulOperation();
    }

    @Override
    public Shape getShape() {
        return new Square();
    }
}
