package design.pattern.builder;

/**
 * @Author
 * @Description
 * @Date 2021/12/5
 */
public class ConcreteBuilder1 extends Builder{
    public void buildPartA() {
        product.setPartA("ConcreteBuilder1 建造 PartA");
    }
    public void buildPartB() {
        product.setPartB("ConcreteBuilder1 建造 PartB");
    }
    public void buildPartC() {
        product.setPartC("ConcreteBuilder1 建造 PartC");
    }
}
