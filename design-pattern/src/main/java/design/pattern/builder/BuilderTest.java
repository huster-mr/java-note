package design.pattern.builder;

/**
 * @Author
 * @Description
 * @Date 2021/12/5
 */
public class BuilderTest {
    public static void main(String[] args) {
        Builder builder1 = new ConcreteBuilder1();
        Director director1 = new Director(builder1);
        Product product1 = director1.construct();
        product1.show();

        Builder builder2 = new ConcreteBuilder2();
        Director director2 = new Director(builder2);
        Product product2 = director2.construct();
        product2.show();
    }
}
