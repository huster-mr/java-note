package design.pattern.factory.methodfactory2;

import design.pattern.factory.AbstractOperation;
import design.pattern.factory.DivOperation;

/**
 * @Author
 * @Description
 * @Date 2021/11/28
 */
public class DivOperationMethodFactory implements OperationMethodFactory{
    @Override
    public AbstractOperation createOperation() {
        return new DivOperation();
    }
}
