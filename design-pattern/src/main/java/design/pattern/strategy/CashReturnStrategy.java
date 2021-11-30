package design.pattern.strategy;

/**
 * @Author
 * @Description
 * @Date 2021/12/1
 */
public class CashReturnStrategy implements CashStrategy{
    private double moneyCondition = 0;
    private double moneyReturn = 0;

    public CashReturnStrategy(double moneyCondition, double moneyReturn) {
        this.moneyCondition = moneyCondition;
        this.moneyReturn = moneyReturn;
    }

    public void setMoneyCondition(double moneyCondition) {
        this.moneyCondition = moneyCondition;
    }

    public void setMoneyReturn(double moneyReturn) {
        this.moneyReturn = moneyReturn;
    }

    @Override
    public double acceptCash(double price) {
        if(price > moneyCondition) {
            return price - Math.floor(price/moneyCondition) * moneyReturn;
        }
        return price;
    }
}
