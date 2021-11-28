package design.pattern.factory.abstractfactory;

import design.pattern.factory.AbstractOperation;
import design.pattern.factory.Shape;

/**
 * @Author
 * @Description
 * @Date 2021/11/28
 */
public interface AbstractFactory {
    AbstractOperation createOperation();

    Shape getShape();
}
