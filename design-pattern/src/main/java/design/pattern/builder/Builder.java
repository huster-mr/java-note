package design.pattern.builder;

/**
 * @Author
 * @Description
 * @Date 2021/12/5
 */
public abstract class Builder {
    protected Product product = new Product();

    public abstract void buildPartA();
    public abstract void buildPartB();
    public abstract void buildPartC();

    public Product  getResult() {
        return product;
    }
}
