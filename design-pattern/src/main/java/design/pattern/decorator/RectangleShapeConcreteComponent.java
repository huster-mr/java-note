package design.pattern.decorator;

/**
 * @Author
 * @Description
 * @Date 2021/12/5
 */
public class RectangleShapeConcreteComponent implements ShapeComponent{
    @Override
    public void draw() {
        System.out.println("画矩形！");
    }
}
