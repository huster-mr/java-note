package design.pattern.factory.methodfactory2;

import design.pattern.factory.AbstractOperation;
import design.pattern.factory.SubOperation;

/**
 * @Author
 * @Description
 * @Date 2021/11/28
 */
public class SubOperationMethodFactory implements OperationMethodFactory {
    @Override
    public AbstractOperation createOperation() {
        return new SubOperation();
    }
}
