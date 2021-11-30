package design.pattern.strategy;

/**
 * @Author
 * @Description
 * @Date 2021/11/30
 */
public class CashContext {
    private CashStrategy cashStrategy;

    public CashContext(String type) {
        switch (type) {
            case "正常收费" :
                cashStrategy = new CashNormalStrategy();
                break;
            case "打八折":
                cashStrategy = new CashRebateStrategy(0.8);
                break;
            case "每满300减100":
                cashStrategy = new CashReturnStrategy(300.0, 100.0);
            default:
                throw new RuntimeException("没有选择优惠方式！");
        }
    }

    public double getResult(double price) {
        return cashStrategy.acceptCash(price);
    }

}
