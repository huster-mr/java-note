package design.pattern.strategy;

/**
 * @Author
 * @Description
 * @Date 2021/12/1
 */
public class CashRebateStrategy implements CashStrategy{
    private double moneyRebate = 1;

    public CashRebateStrategy(double moneyRebate) {
        this.moneyRebate = moneyRebate;
    }

    @Override
    public double acceptCash(double price) {
        return price * moneyRebate;
    }
}
