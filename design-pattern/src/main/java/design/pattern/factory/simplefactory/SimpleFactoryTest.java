package design.pattern.factory.simplefactory;

import design.pattern.factory.AbstractOperation;

import java.util.Scanner;

/**
 * @Author
 * @Description
 * @Date 2021/12/1
 */
public class SimpleFactoryTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入第一个数：");
        double numberA = scanner.nextDouble();
        System.out.println("请输入运算符：");
        String operationStr = scanner.next();
        System.out.println("请输入第二个数：");
        double numberB = scanner.nextDouble();

        try{
            AbstractOperation operation = OperationSimpleFactory.createOperation(operationStr);
            operation.setNumberA(numberA);
            operation.setNumberB(numberB);
            System.out.println(operation.getResult());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
