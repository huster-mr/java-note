package design.pattern.strategy;

import java.util.Scanner;

/**
 * @Author
 * @Description
 * @Date 2021/12/1
 */
public class StrategyTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入优惠方式：");
        String type = scanner.next();
        System.out.println("请输入原价：");
        double money = scanner.nextDouble();
        CashContext cashContext = new CashContext(type);
        System.out.println(cashContext.getResult(money));
    }

}
