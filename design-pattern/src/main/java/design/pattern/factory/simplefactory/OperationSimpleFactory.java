package design.pattern.factory.simplefactory;

import design.pattern.factory.*;

/**
 * @Author
 * @Description 简单工厂
 * @Date 2021/11/28
 */
public class OperationSimpleFactory {
    public static AbstractOperation createOperation(String operationStr) throws Exception {
        switch (operationStr) {
            case "+":
                return new AddOperation();
            case "-":
                return new SubOperation();
            case "*":
                return new MulOperation();
            case "/":
                return new DivOperation();
            default:
                throw new Exception("请输入正确的运算符！");
        }
    }
}
