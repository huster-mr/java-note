package design.pattern.factory.abstractfactory;

import design.pattern.factory.AbstractOperation;
import design.pattern.factory.AddOperation;
import design.pattern.factory.Cricle;
import design.pattern.factory.Shape;

/**
 * @Author
 * @Description
 * @Date 2021/11/28
 */
public class Goods1 implements AbstractFactory{
    @Override
    public AbstractOperation createOperation() {
        return new AddOperation();
    }

    @Override
    public Shape getShape() {
        return new Cricle();
    }
}
