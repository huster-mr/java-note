package design.pattern.factory.abstractfactory;

import design.pattern.factory.AbstractOperation;
import design.pattern.factory.DivOperation;
import design.pattern.factory.Shape;
import design.pattern.factory.Triangle;

/**
 * @Author
 * @Description
 * @Date 2021/11/28
 */
public class Goods4 implements AbstractFactory{
    @Override
    public AbstractOperation createOperation() {
        return new DivOperation();
    }

    @Override
    public Shape getShape() {
        return new Triangle();
    }
}
