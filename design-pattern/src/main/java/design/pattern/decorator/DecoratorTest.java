package design.pattern.decorator;

/**
 * @Author
 * @Description
 * @Date 2021/12/5
 */
public class DecoratorTest {
    public static void main(String[] args) {
        ShapeComponent circle = new CircleShapeConcreteComponent();
        circle.draw();
        ShapeComponent rectang = new RectangleShapeConcreteComponent();
        rectang.draw();

        ShapeComponent redShapeDecorator = new RedShapeDecorator(circle);
        redShapeDecorator.draw();

        ShapeComponent blueShapeDecorator = new BlueShapeDecorator(redShapeDecorator);
        blueShapeDecorator.draw();
    }
}
