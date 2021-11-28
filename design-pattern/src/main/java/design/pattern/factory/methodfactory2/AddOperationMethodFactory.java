package design.pattern.factory.methodfactory2;

import design.pattern.factory.AbstractOperation;
import design.pattern.factory.AddOperation;

/**
 * @Author
 * @Description
 * @Date 2021/11/28
 */
public class AddOperationMethodFactory implements OperationMethodFactory {
    @Override
    public AbstractOperation createOperation() {
        return new AddOperation();
    }
}
