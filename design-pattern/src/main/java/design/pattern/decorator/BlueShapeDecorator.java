package design.pattern.decorator;

/**
 * @Author
 * @Description
 * @Date 2021/12/5
 */
public class BlueShapeDecorator extends ShapeDecorator{
    public BlueShapeDecorator(ShapeComponent shapeComponent) {
        super(shapeComponent);
    }
    @Override
    public void draw() {
        super.draw();
        setBlueBorder(shapeComponent);
    }

    public void setBlueBorder(ShapeComponent decoratorShape) {
        System.out.println("涂蓝色");
    }
}
