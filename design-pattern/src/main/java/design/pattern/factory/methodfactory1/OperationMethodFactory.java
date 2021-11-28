package design.pattern.factory.methodfactory1;

import design.pattern.factory.AddOperation;
import design.pattern.factory.DivOperation;
import design.pattern.factory.MulOperation;
import design.pattern.factory.SubOperation;

/**
 * @Author
 * @Description  工厂方法1
 * @Date 2021/11/28
 */
public class OperationMethodFactory {
    public static AddOperation createAddOperation() {
        return new AddOperation();
    }

    public static SubOperation createSubOperation() {
        return new SubOperation();
    }

    public static MulOperation createMulOperation() {
        return new MulOperation();
    }

    public static DivOperation createDivOperation() {
        return new DivOperation();
    }
}
