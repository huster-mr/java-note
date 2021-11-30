package design.pattern.strategy;

/**
 * @Author
 * @Description
 * @Date 2021/12/1
 */
public class CashNormalStrategy implements CashStrategy{
    @Override
    public double acceptCash(double price) {
        return price;
    }
}
