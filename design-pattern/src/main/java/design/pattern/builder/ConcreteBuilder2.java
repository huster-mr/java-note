package design.pattern.builder;

/**
 * @Author
 * @Description
 * @Date 2021/12/5
 */
public class ConcreteBuilder2 extends Builder{
    public void buildPartA() {
        product.setPartA("ConcreteBuilder2 建造 PartA");
    }
    public void buildPartB() {
        product.setPartB("ConcreteBuilder2 建造 PartB");
    }
    public void buildPartC() {
        product.setPartC("ConcreteBuilder2 建造 PartC");
    }
}
