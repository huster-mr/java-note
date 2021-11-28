package design.pattern.factory.abstractfactory;

import design.pattern.factory.AbstractOperation;
import design.pattern.factory.Retangle;
import design.pattern.factory.Shape;
import design.pattern.factory.SubOperation;

/**
 * @Author
 * @Description
 * @Date 2021/11/28
 */
public class Goods2 implements AbstractFactory{
    @Override
    public AbstractOperation createOperation() {
        return new SubOperation();
    }

    @Override
    public Shape getShape() {
        return new Retangle();
    }
}
